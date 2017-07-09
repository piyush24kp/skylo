(function() {
    'use strict';

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
