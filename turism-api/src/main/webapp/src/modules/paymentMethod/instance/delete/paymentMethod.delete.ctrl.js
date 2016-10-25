/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("paymentMethodModule");

    mod.controller("paymentMethodDeleteCtrl", ["$state", "paymentMethod", function ($state, paymentMethod) {
            this.confirmDelete = function () {
                paymentMethod.remove().then(function () {
                    $state.go('paymentMethodList', null, {reload: true});
                });
            };
        }]);
})(window.angular);

