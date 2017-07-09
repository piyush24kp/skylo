(function() { //code for open modal window
    'use strict';

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
