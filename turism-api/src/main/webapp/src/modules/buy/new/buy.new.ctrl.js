(function (ng) {

    var mod = ng.module("buyModule");

    mod.controller("buyNewCtrl", ["$scope", "$state", "buys",
        function ($scope, $state, buys) {
            $scope.currentRecord = {};
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.buyForm.$valid) {
                            buys.post($scope.currentRecord).then(function (rc) {
                                $state.go('buyDetail', {buyId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('buyList');
                    }
                }
            };
        }]);
})(window.angular);


