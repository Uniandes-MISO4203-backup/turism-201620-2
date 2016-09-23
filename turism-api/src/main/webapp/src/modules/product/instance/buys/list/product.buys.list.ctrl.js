(function (ng) {
    var mod = ng.module('productModule');

    mod.controller('productBuysListCtrl', ['$scope', 'Buys', 'model', '$state',
        function ($scope, Buys, model, $state) {
            $scope.records = Buys;
            $scope.model = model;
            $scope.actions = {
                edit: {
                    displayName: 'Edit',
                    icon: 'ok',
                    fn: function () {
                        $state.go('productBuysEdit');
                    }
                },
                cancel: {
                    displayName: 'Go back',
                    icon: 'arrow-left',
                    fn: function () {
                        $state.go('productDetail');
                    }
                }
            };
        }]);
})(window.angular);


