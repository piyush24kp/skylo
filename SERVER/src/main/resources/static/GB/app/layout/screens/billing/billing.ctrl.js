(function() {
    'use strict';

    angular
        .module('app.layout')
        .controller('billingCtr', billingCtr);

    billingCtr.$inject = ['$timeout', '$filter', '$q', 'config', '$rootScope', '$cookies', '$scope', '$location', 'authfactory'];

    function billingCtr($timeout, $filter, $q, config, $rootScope, $cookies, $scope, $location, authfactory) {
        var vm = this;
        var params = {};
        vm.itemsByPage = 10;



        function activate() {
            vm.getSellOrders();
        }

        vm.getSellOrders = getSellOrders;

        function getSellOrders() {
            resetParam();
            /*if (!uid) {
                return false;
            }
            params.uid = uid;*/
            return authfactory.getSellOrders(params).then(function successCallback(response) {
                if (response.status === 200) {

                    response = response.data.databean;

                    vm.sellDetail = response;
                    vm.sellDetailList = response;


                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });
        }

        /*vm.returnItem = returnItem;

        function returnItem(row) {
            var t = confirm('Are you sure you want to return?');
            if (t) {
                return authfactory.returnItem(row.orderId).then(function successCallback(response) {
                    if (response.status === 200) {
                        toastr.success("Stock Added");
                    }
                }, function errorCallback(response) {
                    return false;
                });
            }
        }*/

        vm.paymentChange = paymentChange;

        function paymentChange(row) {

            var tmpData = row;
            var model = {};
            var brand = {};
            angular.copy(tmpData.brand, brand);
            angular.copy(tmpData.model, model);
            tmpData.brand = tmpData.brand.brandId;
            tmpData.model = tmpData.model.modelId;

            // console.log(tmpData);
            return authfactory.updateSellOrder(tmpData).then(function successCallback(response) {
                if (response.status === 200) {
                    toastr.success("Updated Successfully");
                }
                if (row.payment === 'Paid' || row.payment === 'Due') {
                    row.returnDate = '';
                }
                row.brand = brand;
                row.model = model;
            }, function errorCallback(response) {
                row.brand = brand;
                row.model = model;
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

        activate();

    }
})();
