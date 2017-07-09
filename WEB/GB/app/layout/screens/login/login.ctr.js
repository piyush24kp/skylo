(function() {
    'use strict';

    angular
        .module('app.layout')
        .controller('loginCtr', loginCtr);

    loginCtr.$inject = ['$timeout', '$filter', '$q', 'config', '$rootScope', '$cookies', '$scope', '$location', 'authfactory'];

    function loginCtr($timeout, $filter, $q, config, $rootScope, $cookies, $scope, $location, authfactory) {
        var vm = this;
        vm.loginForm = function() {
            return authfactory.login(vm.login).then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;
                    $rootScope.isLogin = true;
                    $location.path('/home');
                    toastr.success("Login Successfully");

                } else {
                    toastr.warning("Wrong Username / Password");
                }
            }, function errorCallback(response) {
                return false;
            });
        };

        vm.changePwd = function() {
            return authfactory.changePwd(vm.login).then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;

                    toastr.success("Password Changed successfully");
                }
            }, function errorCallback(response) {
                return false;
            });
        };

        vm.logout = function() {
            $rootScope.isLogin = false;
            $location.path('/login');
        };


    }
})();
