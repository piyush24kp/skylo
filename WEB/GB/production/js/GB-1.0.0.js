'use strict';
// Source: dest/app.mdl.js
(function() {
angular.module('app', [
        /*
         * Order is not important. Angular makes a
         * pass to register all of the modules listed
         * and then when app.dashboard tries to use app.data,
         * its components are available.
         */

        /*
         * Everybody has access to these.
         * We could place these under every feature area,
         * but this is easier to maintain.
         */
        'app.core',
        /*
         * Feature areas
         */
        'app.layout',
        'app.widgets'
    ]);

})();

// Source: dest/app.route.js
/*
this route provider for gust and member users
*/

(function() {
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

// Source: dest/common/widgets.mdl.js
(function() {
angular.module('app.widgets', []);
})();

// Source: dest/core/core.mdl.js
(function() {
angular.module('app.core', [
        /*
         * Angular modules
         */
        'ngAnimate', 'ngRoute', 'ngSanitize', 'ui.bootstrap', 'ngCookies', 'ngTouch', 'ngMaterial', 'smart-table'
        /*
         * Our reusable cross app code modules
         */
        //'blocks.router',
        /*
         * 3rd Party modules
         */
    ]);
})();

// Source: dest/layout/layout.mdl.js
(function() {
angular.module('app.layout', []);
})();

// Source: dest/common/directive/modalBox.drv.js
(function() { //code for open modal window
angular
        .module('app.widgets')
        .directive('modelBox', modelBox);

    /* @ngInject */
    modelBox.$inject = ['$q', '$uibModal', '$location'];

    function modelBox($q, $uibModal, $location) {

        var directive = {
            link: link,
            restrict: 'A',
            scope: {
                "modaldata": "=",
                "options": "="
            }
        };
        return directive;

        function link(scope, element, attrs) {

            var delay = 500,
                clicks = 0,
                timer = null;

            element.on('click', function(event) {
                clicks++; //count clicks
                if (clicks === 1) {
                    timer = setTimeout(function() {
                        scope.$apply(function() {
                            openmodel(event);
                        });
                        clicks = 0; //after action performed, reset counter
                    }, delay);
                } else {
                    clearTimeout(timer); //prevent single-click action
                    clicks = 0; //after action performed, reset counter
                }
            });

            function openmodel(e) {


                var options = scope.options;

                var modalInstance = $uibModal.open({
                    animation: options.animation,
                    templateUrl: options.templateUrl,
                    controller: ['$scope', '$document', '$rootScope', '$window', '$uibModalInstance', 'config', '$timeout', function($scope, $document, $rootScope, $window, $uibModalInstance, config, $timeout) {
                        $scope.isShow = true;
                        $scope.modalData = scope.modaldata;
                        $scope.modalopt = options;
                        $scope.image = $rootScope.image;
                        $scope.typeImage = false;
                        $scope.typeVideo = false;
                        if ($rootScope.type === 'image') {
                            $scope.typeImage = true;
                        }
                        var cleanUp = function() {
                            window.angular.element($window).off('keydown click');
                        };
                        $scope.cancelModal = function() {
                            //dismiss modal
                            $uibModalInstance.dismiss('cancel');

                        };

                        $scope.$on("cancelModal", function(evt, data) {
                            $uibModalInstance.dismiss('cancel');
                        });

                        $scope.$on('$locationChangeStart', function(event, next, current) {
                            $uibModalInstance.dismiss('cancel');
                        });


                    }],
                    size: options.size,
                    resolve: {

                    }
                });

            }

        }
    }

})();

// Source: dest/common/factory/authentication.fact.js
(function() {
angular
        .module('app.widgets')
        .factory('authfactory', authfactory);

    authfactory.$inject = ['$http', '$q', 'config'];

    function authfactory($http, $q, config) {
        var isPrimed = false;
        var primePromise;

        var service = {
            login: login,
            getParamUrl: getParamUrl,
            getOrders: getOrders,
            createOrder: createOrder,
            createSupplier: createSupplier,
            getSupplier: getSupplier,
            getSupplierId: getSupplierId,
            getBrand: getBrand,
            getModel: getModel,
            changeBrand: changeBrand,
            saveBrand: saveBrand,
            getSellOrders: getSellOrders,
            setSellOrder: setSellOrder,
            saveModel: saveModel,
            updateStock: updateStock,
            deleteStock: deleteStock,
            returnSellOrder: returnSellOrder,
            updateSellOrder: updateSellOrder,
            getChartDetail: getChartDetail,
            generateExcel: generateExcel,
            changePwd: changePwd
        };
        return service;

        function getParamUrl(url, param) {
            if (param) {
                if (param.uid) {
                    url = url + "?uid=" + param.uid;
                } else if (param.name) {
                    url = url + "?name=" + param.name;
                }


            }
            return url;
        }

        function getOrders(param) {
            var url = '/stock/getOrders';
            url = getParamUrl(url, param);
            return $http.get(config.APIurl + url, param)
                .then(getDataComplete);
        }

        function login(data) {
            var url = '/login';
            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function changePwd(data) {
            var url = '/changePassword';
            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function createOrder(data) {
            var url = '/stock/setOrders';

            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function createSupplier(data) {
            var url = '/stock/createSupplier';
            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function setSellOrder(data) {
            var url = '/billing/setSellOrder';
            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function saveBrand(data) {
            var url = '/stock/createBrands';
            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function saveModel(data) {
            var url = '/stock/setModel';
            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function updateStock(data) {
            var url = '/stock/updateOrder';
            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function returnSellOrder(data) {
            var url = '/billing/returnSellOrder';
            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function updateSellOrder(data) {
            var url = '/billing/updateSellOrder';
            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function getSupplier(param) {
            var url = '/stock/getSupplier';
            // url = getParamUrl(url, param);
            return $http.get(config.APIurl + url, param)
                .then(getDataComplete);
        }

        function getSupplierId(param) {
            var url = '/stock/getSupplierId';
            // url = getParamUrl(url, param);
            return $http.get(config.APIurl + url, param)
                .then(getDataComplete);
        }

        function getBrand(param) {
            var url = '/stock/getBrands';
            // url = getParamUrl(url, param);
            return $http.get(config.APIurl + url, param)
                .then(getDataComplete);
        }

        function getModel(param) {
            var url = '/stock/getModels';
            // url = getParamUrl(url, param);
            return $http.get(config.APIurl + url, param)
                .then(getDataComplete);
        }

        function changeBrand(brandId) {
            var url = '/stock/getModelById';
            //url = getParamUrl(url, param);
            url = url + '?id=' + brandId;
            return $http.get(config.APIurl + url, brandId)
                .then(getDataComplete);
        }

        function getSellOrders(param) {
            var url = '/billing/getSellOrders';
            // url = getParamUrl(url, param);
            return $http.get(config.APIurl + url, param)
                .then(getDataComplete);
        }

        function deleteStock(stockId) {
            var url = '/stock/deleteOrder';
            //url = getParamUrl(url, param);
            url = url + '?id=' + stockId;
            return $http.get(config.APIurl + url, stockId)
                .then(getDataComplete);
        }

        function getChartDetail(param) {
            var url = '/getChartDetail';
            // url = getParamUrl(url, param);
            return $http.get(config.APIurl + url, param)
                .then(getDataComplete);
        }

        function generateExcel(param) {
            var url = '/myexcel';
            return $http.post(config.APIurl + url, param)
                .then(getDataComplete);
        }

        function getDataComplete(response, status) {
            return response;
        }

        /*        function getSearchData(param) {

                    var url = '/keywordSearch';
                    if (param) {
                        url = url + "?search=" + param.searchKey;
                        if (param.disease) {
                            url = url + "&disease=" + param.disease;
                        }
                        if (param.filter) {
                            url = url + '&filter=' + param.filter;
                        }
                    }
                    return $http.get(config.APIurl + url, param)
                        .then(getDataComplete);
                }
        */
    }

})();

// Source: dest/common/factory/getFeeds.fact.js
(function() {
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

// Source: dest/common/factory/remember.fact.js
(function() {
angular
        .module('app.widgets')
        .factory('rememberFact', rememberFact);

    rememberFact.$inject = ['$filter'];

    function rememberFact($filter) {


        function fetchValue(name) {
            var gCookieVal = document.cookie.split("; ");
            for (var i = 0; i < gCookieVal.length; i++) {
                // a name/value pair (a crumb) is separated by an equal sign
                var gCrumb = gCookieVal[i].split("~=");
                if (name === gCrumb[0]) {
                    var value = '';
                    try {
                        value = angular.fromJson(gCrumb[1]);
                    } catch (e) {
                        value = unescape(gCrumb[1]);
                    }
                    return value;
                }
            }
            // a cookie with the requested name does not exist
            return null;
        }
        return function(name, values) {
            if (arguments.length === 1) {
                return fetchValue(name);
            }
            var cookie = name + '~=';
            if (typeof values === 'object') {
                var expires = '';
                cookie += (typeof values.value === 'object') ? angular.toJson(values.value) + ';' : values.value + ';';
                if (values.expires) {
                    var date = new Date();
                    date.setTime(date.getTime() + (values.expires * 24 * 60 * 60 * 1000));
                    expires = date.toGMTString();
                }
                cookie += (!values.session) ? 'expires=' + expires + ';' : '';
                cookie += (values.path) ? 'path=' + values.path + ';' : '';
                cookie += (values.secure) ? 'secure;' : '';
            } else {
                cookie += values + ';';
            }
            document.cookie = cookie;
        };


    }

})();

// Source: dest/core/core.cnst.js
/* global toastr:false, moment:false */
(function() {
angular
        .module('app.core')
        .constant('toastr', toastr)
        .constant('moment', moment);
})();

// Source: dest/core/core.conf.js
(function() {
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

// Source: dest/layout/layout.ctr.js
(function() {
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

// Source: dest/layout/screens/billing/billing.ctrl.js
(function() {
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
            tmpData.brand = tmpData.brand.brandId;
            tmpData.model = tmpData.model.modelId;

            return authfactory.updateSellOrder(row).then(function successCallback(response) {
                if (response.status === 200) {
                    toastr.success("Stock Added");
                }
            }, function errorCallback(response) {
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

// Source: dest/layout/screens/home/home.ctr.js
(function() {
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

// Source: dest/layout/screens/login/login.ctr.js
(function() {
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

// Source: dest/layout/screens/modal/addModal.ctrl.js
(function() {
angular
        .module('app.layout')
        .controller('addModalCtr', addModalCtr);

    addModalCtr.$inject = ['$timeout', '$filter', '$q', 'config', '$rootScope', '$cookies', '$scope', '$location', 'authfactory'];

    function addModalCtr($timeout, $filter, $q, config, $rootScope, $cookies, $scope, $location, authfactory) {
        var vm = this;
        vm.order = {};
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
                    toastr.success("Stock Updated");
                    $scope.$emit("cancelModal");
                    return response;
                }
            }, function errorCallback(response) {
                toastr.error("Something Went Wrong");
                return false;
            });
        }

        vm.returnItem = returnItem;

        function returnItem() {
            return authfactory.returnSellOrder(vm.order).then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;
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

        vm.cancel = function() {
            $scope.$emit("cancelModal");
        };

    }
})();

// Source: dest/layout/screens/review/review.ctr.js
(function() {
angular
        .module('app.layout')
        .controller('reviewCtr', reviewCtr);

    reviewCtr.$inject = ['$timeout', '$filter', '$q', 'config', '$rootScope', '$cookies', '$scope', '$location', 'authfactory'];

    function reviewCtr($timeout, $filter, $q, config, $rootScope, $cookies, $scope, $location, authfactory) {
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

        vm.chart = chart;

        function chart() {
            return authfactory.getChartDetail().then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;
                    vm.getChartDetail = response;

                    vm.createChart(vm.getChartDetail);
                }
            }, function errorCallback(response) {
                return false;
            });
        }

        vm.createChart = function createChart(data) {


            Highcharts.chart('chartDiv', {
                chart: {
                    type: 'column'
                },
                title: {
                    text: 'Last 30 Days Stock / Sell'
                },
                subtitle: {
                    text: 'Stock / Sell Count'
                },
                xAxis: {
                    categories: data.brands,
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ''
                    }
                },
                colors: ['#419ff1', '#b63838'],
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: [{
                    name: 'Stock',
                    data: data.series.Purchase

                }, {
                    name: 'Sell',
                    data: data.series.Sell

                }]
            });

        };


        vm.chart();

        vm.generateExcel = function() {
            return authfactory.generateExcel(vm.order).then(function successCallback(response) {
                if (response.status === 200) {
                    toastr.success("Excel Generated");
                }
            }, function errorCallback(response) {
                return false;
            });
        };
    }
})();

// Source: dest/layout/shell.ctrl.js
(function() {
angular
        .module('app.layout')
        .controller('ShellCtr', ShellCtr);

    ShellCtr.$inject = ['$timeout', '$filter', '$q', 'config', '$rootScope', '$cookies', '$scope', '$location'];

    function ShellCtr($timeout, $filter, $q, config, $rootScope, $cookies, $scope, $location) {
        /*jshint validthis: true */
        var vm = this;
        $rootScope.isLogin = false;

        activate();

        function activate() {

        }

    }
})();
