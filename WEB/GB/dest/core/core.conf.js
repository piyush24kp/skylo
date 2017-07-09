(function() {
    'use strict';

    var core = angular.module('app.core');

    core.config(toastrConfig);

    /* @ngInject */
    toastrConfig.$inject = ['toastr'];

    function toastrConfig(toastr) {
        toastr.options.timeOut = 4000;
        toastr.options.positionClass = 'toast-bottom-right';
        toastr.options.tapToDismiss = true;
    }

    var config = {
        appErrorPrefix: '[APP Error] ', //Configure the exceptionHandler decorator
        appTitle: 'GB',
        version: '1.0.0',
        //development
        APIurl: 'http://localhost:9090'

    };

    core.value('config', config);

    core.run(run);

    /* Inject Run
       App bootstrap settings excute in run time
    */
    run.$inject = ['$rootScope', '$location', 'config', '$cookies', '$window'];

    function run($rootScope, $location, config, $cookies, $window) {

        /*$rootScope.$on("$routeChangeStart", function(event, next, current) {

            $rootScope.isLogin = true;
            var getcookie = $cookies.get('isLogin');

            if (getcookie === 'false' || getcookie === undefined) {
                $rootScope.isLogin = false;
            } else {
                $rootScope.isLogin = true;

                $rootScope.userDetail = $cookies.getObject('userDetail');
            }

        });*/
    }



})();
