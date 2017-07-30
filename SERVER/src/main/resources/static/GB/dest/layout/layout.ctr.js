(function() {
    'use strict';

    angular
        .module('app.layout')
        .controller('LayoutCtr', LayoutCtr);

    LayoutCtr.$inject = ['$timeout', '$filter', '$q', 'config'];

    function LayoutCtr($timeout, $filter, $q, config) {
        /*jshint validthis: true */
        var vm = this;

        activate();

        function activate() {
            //logger.success(config.appTitle + ' Shell loaded!', null);
        }

    }
})();
