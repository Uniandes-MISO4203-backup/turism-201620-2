/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("destinationModule");

    mod.controller("destinationEditCtrl", ["$scope", "$state", "destination",
        function ($scope, $state, destination) {
            $scope.currentRecord = destination;
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.destinationForm.$valid) {
                            $scope.currentRecord.put().then(function (rc) {
                                $state.go('destinationDetail', {destinationId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('destinationDetail');
                    }
                }
            };
        }]);
})(window.angular);


