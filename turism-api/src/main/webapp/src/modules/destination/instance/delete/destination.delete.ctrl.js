/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("destinationModule");

    mod.controller("destinationDeleteCtrl", ["$state", "destination", function ($state, destination) {
            this.confirmDelete = function () {
                destination.remove().then(function () {
                    $state.go('destinationList', null, {reload: true});
                });
            };
        }]);
})(window.angular);

