(function() {
    'use strict';

    angular
        .module('app.layout')
        .controller('expenseModalCtr', expenseModalCtr);

    expenseModalCtr.$inject = ['$timeout', '$filter', '$q', 'config', '$rootScope', '$cookies', '$scope', '$location', 'authfactory'];

    function expenseModalCtr($timeout, $filter, $q, config, $rootScope, $cookies, $scope, $location, authfactory) {
        var vm = this;
        vm.activeForm = 0;
        vm.expense = {};
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

        vm.expense.type = $scope.modalopt.expense;

        vm.setExpense = setExpense;

        function setExpense() {
            return authfactory.setExpense(vm.expense).then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;
                    toastr.success("Added Successfully.");
                }
            }, function errorCallback(response) {
                toastr.error("error");
                return false;
            });
        }

        vm.cancel = function() {
            $scope.$emit("cancelModal");
        };

    }
})();
