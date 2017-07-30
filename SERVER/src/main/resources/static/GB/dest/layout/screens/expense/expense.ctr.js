(function() {
    'use strict';

    angular
        .module('app.layout')
        .controller('expenseCtr', expenseCtr);

    expenseCtr.$inject = ['$timeout', '$filter', '$q', 'config', '$rootScope', '$cookies', '$scope', '$location', 'authfactory'];

    function expenseCtr($timeout, $filter, $q, config, $rootScope, $cookies, $scope, $location, authfactory) {
        var vm = this;
        vm.activeForm = 0;
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
        $scope.popup2 = {
            opened: false
        };
        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };

        $scope.open2 = function() {
            $scope.popup2.opened = true;
        };

        vm.getExpense = getExpense;

        function getExpense() {
            return authfactory.getExpense().then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;
                    vm.expenseList = response;
                    vm.defaultList = response;
                }
            }, function errorCallback(response) {
                return false;
            });
        }

        vm.historyListBtn = historyListBtn;

        function historyListBtn() {
            return authfactory.getHistory(vm.order).then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;
                    vm.historyList = response;
                }
            }, function errorCallback(response) {
                return false;
            });
        }

        vm.searchExp = searchExp;

        function searchExp() {
            if (vm.searchExpense) {
                return authfactory.searchExp(vm.searchExpense).then(function successCallback(response) {
                    if (response.status === 200) {
                        response = response.data.databean;
                        vm.expenseList = response;
                    }
                }, function errorCallback(response) {
                    return false;
                });
            } else {
                vm.expenseList = vm.defaultList;
            }

        }
        vm.selectedHistory = selectedHistory;

        function selectedHistory(row) {
            console.log(row);

            var r = confirm("Add this to Expense / Income");
            if (r === true) {
                var data = {};
                if (row.historyType === "STOCK") {
                    data.type = "expense";
                    data.expenseDate = row.historyDate;
                    data.expenseName = row.orderDetailVo.orderName + row.orderDetailVo.orderId;
                    data.amount = row.orderDetailVo.amount;
                } else {
                    data.type = "income";
                    data.expenseDate = row.historyDate;
                    data.expenseName = row.sellDetailVo.customerName + row.sellDetailVo.orderId;
                    data.amount = row.sellDetailVo.amount;
                }

                return authfactory.setExpense(data).then(function successCallback(response) {
                    if (response.status === 200) {
                        response = response.data.databean;
                        toastr.success("Added Successfully.");
                        vm.expenseList.push(data);

                    }
                }, function errorCallback(response) {
                    toastr.error("error");
                    return false;
                });

            }
        }

        vm.getExpense();

        vm.cancel = function() {
            $scope.$emit("cancelModal");
        };

    }
})();
