(function() {
    'use strict';

    angular
        .module('app.layout')
        .controller('homeCtr', homeCtr);

    homeCtr.$inject = ['$timeout', '$filter', '$q', 'config', '$rootScope', '$cookies', '$scope', '$location', 'authfactory'];

    function homeCtr($timeout, $filter, $q, config, $rootScope, $cookies, $scope, $location, authfactory) {
        var vm = this;
        var params = {};
        vm.allUsers = [];

        vm.myInterval = 5000;
        vm.noWrapSlides = false;
        vm.active = 0;
        vm.itemsByPage = 10;
        var currIndex = 0;
        vm.slides = [{
            image: 'GB/assets/images/carousel-1.jpg',
            text: ['Nice image', 'Awesome photograph', 'That is so cool', 'I love that'],
            id: currIndex++
        }, {
            image: 'GB/assets/images/carousel-2.jpg',
            text: ['Nice image', 'Awesome photograph', 'That is so cool', 'I love that'],
            id: currIndex++
        }, {
            image: 'GB/assets/images/carousel-3.jpg',
            text: ['Nice image', 'Awesome photograph', 'That is so cool', 'I love that'],
            id: currIndex++
        }];

        activate();

        function activate() {

        }

        vm.getOrders = getOrders;

        function getOrders() {
            resetParam();
            /*if (!uid) {
                return false;
            }
            params.uid = uid;*/
            return authfactory.getOrders(params).then(function successCallback(response) {
                if (response.status === 200) {

                    response = response.data.databean;

                    vm.orderDetails = response;
                    vm.rowCollection = response;

                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });
        }

        vm.getOrders();

        $rootScope.$on('addOrder', function(event, args) {
            vm.orderDetails.push(args.order);
            vm.rowCollection.push(args.order);
        });

        $rootScope.$on('supplierOrder', function(event, args) {
            vm.supplierDetail.push(args.brand);
            vm.suppDetailCollection.push(args.brand);
        });

        $rootScope.$on('brandOrder', function(event, args) {
            vm.brandDetail.push(args.brand);
            vm.brandDetailList.push(args.brand);
        });

        $rootScope.$on('modelOrder', function(event, args) {
            vm.modelDetail.push(args.brand);
            vm.modelDetailList.push(args.brand);
        });


        vm.getSupplier = getSupplier;

        function getSupplier() {
            resetParam();
            /*if (!uid) {
                return false;
            }
            params.uid = uid;*/
            return authfactory.getSupplier(params).then(function successCallback(response) {
                if (response.status === 200) {

                    response = response.data.databean;

                    vm.supplierDetail = response;
                    vm.suppDetailCollection = response;

                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });
        }

        vm.getBrand = getBrand;

        function getBrand() {
            resetParam();
            /*if (!uid) {
                return false;
            }
            params.uid = uid;*/
            return authfactory.getBrand(params).then(function successCallback(response) {
                if (response.status === 200) {

                    response = response.data.databean;

                    vm.brandDetail = response;
                    vm.brandDetailList = response;

                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });
        }

        vm.getModel = getModel;

        function getModel() {
            resetParam();
            /*if (!uid) {
                return false;
            }
            params.uid = uid;*/
            return authfactory.getModel(params).then(function successCallback(response) {
                if (response.status === 200) {

                    response = response.data.databean;

                    vm.modelDetail = response;
                    vm.modelDetailList = response;

                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });
        }

        vm.deleteStock = deleteStock;

        function deleteStock(orderId) {
            var r = confirm("Are you sure you want to delete ?");
            if (!r) {
                return false;
            }
            return authfactory.deleteStock(orderId).then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;
                    toastr.success("Deleted Successfully.");
                }
            }, function errorCallback(response) {
                toastr.error(response.message);
                return false;
            });
        }

        vm.selectTab = selectTab;

        function selectTab(tab) {
            resetParam();

            if (tab === 'supplier') {
                vm.getSupplier();
            } else if (tab === 'brand') {
                vm.getBrand();
            } else if (tab === 'model') {
                vm.getModel();
            } else if (tab === 'category') {

            }
        }

        function resetParam() {
            params = {};
        }

    }
})();
