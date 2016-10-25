/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("paymentMethodModule");

    mod.controller("paymentMethodNewCtrl", ["$scope", "$state", "paymentMethods",
        function ($scope, $state, paymentMethods) {
            $scope.currentRecord = {};
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.paymentMethodForm.$valid) {
                            paymentMethods.post($scope.currentRecord).then(function (rc) {
                                $state.go('paymentMethodDetail', {paymentMethodId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('paymentMethodList');
                    }
                }
            };
        }]);
})(window.angular);

