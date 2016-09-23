(function (ng) {

    var mod = ng.module("buyModule");

    mod.controller("buyDeleteCtrl", ["$state", "buy", function ($state, buy) {
            this.confirmDelete = function () {
                buy.remove().then(function () {
                    $state.go('buyList', null, {reload: true});
                });
            };
        }]);
})(window.angular);


