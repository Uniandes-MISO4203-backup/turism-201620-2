/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("destinationModule");

    mod.controller("destinationNewCtrl", ["$scope", "$state", "destinations",
        function ($scope, $state, destinations) {
            $scope.currentRecord = {};
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.destinationForm.$valid) {
                            destinations.post($scope.currentRecord).then(function (rc) {
                                $state.go('destinationDetail', {destinationId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('destinationList');
                    }
                }
            };
        }]);
})(window.angular);


