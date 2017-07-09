/*
this route provider for gust and member users
*/

(function() {
    'use strict';

    angular.module('app')

    .config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
        $routeProvider
            .when('/home', {
                templateUrl: 'GB/app/layout/screens/home/home.tmpl.html',
                controller: 'homeCtr',
                controllerAs: 'vm',
            })
            .when('/billing', {
                templateUrl: 'GB/app/layout/screens/billing/billing.tmpl.html',
                controller: 'billingCtr',
                controllerAs: 'vm',
            })
            .when('/login', {
                templateUrl: 'GB/app/layout/screens/login/login.tmpl.html',
                controller: 'loginCtr',
                controllerAs: 'vm',
            })
            .when('/setting', {
                templateUrl: 'GB/app/layout/screens/login/setting.tmpl.html',
                controller: 'loginCtr',
                controllerAs: 'vm',
            })
            .when('/review', {
                templateUrl: 'GB/app/layout/screens/review/review.tmpl.html',
                controller: 'reviewCtr',
                controllerAs: 'vm',
            })
            .when('/error', {
                templateUrl: 'GB/app/layout/screens/contactAdmin.tmpl.html',

            })
            .otherwise({
                redirectTo: '/login'
            });

    }]).config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.headers.common = {
            "Accept": "application/json;charset=utf-8",
            'Content-Type': 'application/json; charset=utf-8',
            /*  'X-Frame-Options': 'ALLOW-FROM SAMEORIGIN'*/
        };
    }]);


})();
