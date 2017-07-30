(function() {
    'use strict';

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
            changePaymentStatus: changePaymentStatus,
            updateSellOrder: updateSellOrder,
            getChartDetail: getChartDetail,
            generateExcel: generateExcel,
            changePwd: changePwd,
            setExpense: setExpense,
            getExpense: getExpense,
            getHistory: getHistory,
            searchExp: searchExp
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

        function changePaymentStatus(params) {
            var url = '/billing/changePaymentStatus?id=' + params.orderId + '&t=' + params.payment + '&p=';
            if (params.payment === 'Paid') {
                url = url + params.amount;
            } else {
                url = url + params.amountPaid;
            }
            return $http.get(config.APIurl + url)
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

        function getExpense() {
            var url = '/getExpense';
            return $http.get(config.APIurl + url)
                .then(getDataComplete);
        }

        function setExpense(data) {
            var url = '/setExpense';
            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function getHistory(data) {
            var url = '/getHistory';
            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function searchExp(param) {
            var url = '/searchExpence' + "?q=" + param;
            return $http.get(config.APIurl + url)
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
