(function (ng) {
    var mod = ng.module('productModule');

    mod.controller('productBuysEditCtrl', ['$scope', 'Buys', 'pool', 'model', '$state',
        function ($scope, Buys, available, model, $state) {
            $scope.records = Buys;
            $scope.model = model;
            $scope.available = available.plain();
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        $scope.records.customPUT().then(function () {
                            $state.go('productBuysList', null, {reload: true});
                        });
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('productBuysList');
                    }
                }
            };
        }]);
})(window.angular);

