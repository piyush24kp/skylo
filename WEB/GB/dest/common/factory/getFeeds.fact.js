(function() {
    'use strict';

    angular
        .module('app.widgets')
        .factory('Feeds', Feeds);

    Feeds.$inject = ['$http', '$q', 'config'];

    function Feeds($http, $q, config) {
        var isPrimed = false;
        var primePromise;

        var service = {
            getNews: getNews,
            getBlogs: getBlogs,
            getParamUrl: getParamUrl

        };
        return service;

        function getNews(params) {

            var url = 'http://ajax.googleapis.com/ajax/services/feed/load';

            params.url = config.newsUrl;
            url = getParamUrl(url, params);
            return $http.jsonp(url);
        }

        function getBlogs(params) {

            var url = 'http://ajax.googleapis.com/ajax/services/feed/load';
            params.url = config.blogsUrl;
            url = getParamUrl(url, params);
            return $http.jsonp(url);
        }

        function getParamUrl(url, param) {
            if (param) {

                url = url + "?v=1.0";

                if (param.num) {
                    url = url + "&num=" + param.num;
                }
                url = url + "&callback=JSON_CALLBACK";

                url = url + "&q=" + encodeURIComponent(param.url);
            }
            return url;
        }

    }

})();
