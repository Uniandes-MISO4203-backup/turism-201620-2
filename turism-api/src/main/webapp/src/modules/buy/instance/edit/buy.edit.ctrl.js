(function (ng) {

    var mod = ng.module("buyModule");

    mod.controller("buyEditCtrl", ["$scope", "$state", "buy",
        function ($scope, $state, buy) {
            $scope.currentRecord = buy;
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.buyForm.$valid) {
                            $scope.currentRecord.put().then(function (rc) {
                                $state.go('buyDetail', {buyId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('buyDetail');
                    }
                }
            };
        }]);
})(window.angular);

