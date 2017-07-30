(function() {
    'use strict';

    angular
        .module('app.layout')
        .controller('addModalCtr', addModalCtr);

    addModalCtr.$inject = ['$timeout', '$filter', '$q', 'config', '$rootScope', '$cookies', '$scope', '$location', 'authfactory'];

    function addModalCtr($timeout, $filter, $q, config, $rootScope, $cookies, $scope, $location, authfactory) {
        var vm = this;
        vm.order = {};
        vm.order.size = {};
        vm.order.size.s = 0;
        vm.order.size.m = 0;
        vm.order.size.l = 0;
        vm.order.size.xl = 0;
        vm.order.size.xxl = 0;
        vm.order.size.xxxl = 0;
        vm.perUnit = 0;
        vm.order.amount = vm.perUnit * (vm.order.size.s + vm.order.size.m + vm.order.size.l + vm.order.size.xl + vm.order.size.xxl + vm.order.size.xxxl);

        var params = {};
        var option = {};
        var modalData = {};
        vm.dirtyCheck = false;
        $scope.popup1 = {
            opened: false
        };
        $scope.format = "dd-MMMM-yyyy";
        $scope.altInputFormats = ['M!/d!/yyyy'];
        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };

        $scope.open1 = function() {
            $scope.popup1.opened = true;
        };

        angular.copy($scope.modalData, modalData);
        angular.copy($scope.modalopt, option);

        if (modalData && option) {

            if (option.type === 'stock') {
                vm.order = modalData;
                vm.order.brand = modalData.brand.brandId;
                vm.order.model = modalData.model.modelId;
                vm.order.suppliedBy = modalData.suppliedBy.supplierId;
            } else if (option.type === 'supplier') {

            } else if (option.type === 'brand') {

            } else if (option.type === 'model') {

            } else if (option.type === 'return') {
                vm.order = modalData;
                vm.order.brand = modalData.brand.brandId;
                vm.order.model = modalData.model.modelId;
            }
            vm.editMode = true;
        }

        vm.ok = function() {

            return authfactory.createOrder(vm.order).then(function successCallback(response) {

                if (response.status === 200) {
                    response = response.data.databean;
                    $scope.$emit("addOrder", {
                        order: response
                    });
                    toastr.success("Stock Added");
                    $scope.$emit("cancelModal");
                    return response;
                }
            }, function errorCallback(response) {
                toastr.error("Something Went Wrong");
                return false;
            });
        };

        vm.setSellOrder = function() {
            return authfactory.setSellOrder(vm.order).then(function successCallback(response) {

                if (response.status === 200) {
                    response = response.data.databean;
                    $scope.$emit("sellOrder", {
                        order: response
                    });
                    toastr.success("Order Selled");
                    $scope.$emit("cancelModal");
                    return response;
                }
            }, function errorCallback(response) {
                toastr.error("Something Went Wrong");
                return false;
            });
        };

        vm.saveSupplier = function() {

            return authfactory.createSupplier(vm.order).then(function successCallback(response) {

                if (response.status === 200) {
                    response = response.data.databean;
                    $scope.$emit("supplierOrder", {
                        supplier: response
                    });
                    toastr.success("Suppliyer Added");
                    $scope.$emit("cancelModal");
                    return response;
                }
            }, function errorCallback(response) {
                toastr.error("Something Went Wrong");
                return false;
            });
        };

        vm.saveBrand = saveBrand;

        function saveBrand() {
            return authfactory.saveBrand(vm.order).then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;
                    $scope.$emit("brandOrder", {
                        brand: response
                    });
                    toastr.success("Brand Added");
                    $scope.$emit("cancelModal");
                    return response;
                }
            }, function errorCallback(response) {
                toastr.error("Something Went Wrong");
                return false;
            });
        }

        vm.saveModel = saveModel;

        function saveModel() {
            return authfactory.saveModel(vm.order).then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;
                    $scope.$emit("modelOrder", {
                        model: response
                    });
                    toastr.success("Model Added");
                    $scope.$emit("cancelModal");
                    return response;
                }
            }, function errorCallback(response) {
                toastr.error("Something Went Wrong");
                return false;
            });
        }

        vm.updateStock = updateStock;

        function updateStock() {
            return authfactory.updateStock(vm.order).then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;
                    $scope.$emit("addOrder", {
                        order: response
                    });
                    toastr.success("Stock Updated");
                    $scope.$emit("cancelModal");
                    return response;
                }
            }, function errorCallback(response) {
                toastr.error("Something Went Wrong");
                return false;
            });
        }

        vm.changePaymentStatus = changePaymentStatus;

        function changePaymentStatus() {
            return authfactory.changePaymentStatus(vm.order).then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;
                    $scope.$emit("sellOrder", {
                        order: response
                    });
                    toastr.success("Stock Updated");
                    $scope.$emit("cancelModal");
                    return response;
                }
            }, function errorCallback(response) {
                toastr.error("Something Went Wrong");
                return false;
            });
        }

        vm.getSuppliers = function() {

            return authfactory.getSupplierId().then(function successCallback(response) {

                if (response.status === 200) {
                    response = response.data.databean;

                    vm.suppliyers = response;
                    return response;
                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });
        };

        vm.getSuppliers();

        vm.getBrand = getBrand;

        function getBrand() {
            return authfactory.getBrand(params).then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;
                    vm.brandDetail = response;
                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });
        }

        vm.getBrand();

        vm.changeBrand = changeBrand;

        function changeBrand(brandId) {
            if (!brandId) {
                return false;
            }
            return authfactory.changeBrand(brandId).then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;
                    vm.modelDetail = response;
                    if (modalData) {
                        vm.dirtyCheck = true;
                    }
                } else {
                    vm.modelDetail = [];
                    toastr.info("No Model Found");
                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });
        }
        vm.changeBrand(vm.order.brand);

        vm.getPerUnit = getPerUnit;

        function getPerUnit() {

            if (vm.order.model) {
                angular.forEach(vm.modelDetail, function(value, key) {
                    if (value.modelId === vm.order.model) {
                        vm.perUnit = value.price;
                    }
                });

            }
        }

        vm.getPerUnit();

        vm.getAmount = getAmount;

        function getAmount() {
            if (vm.perUnit) {
                vm.order.amount = vm.perUnit * (vm.order.size.s + vm.order.size.m + vm.order.size.l + vm.order.size.xl + vm.order.size.xxl + vm.order.size.xxxl);
            }
        }

        vm.cancel = function() {
            $scope.$emit("cancelModal");
        };

    }
})();
