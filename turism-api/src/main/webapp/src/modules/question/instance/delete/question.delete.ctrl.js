/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("questionModule");

    mod.controller("questionDeleteCtrl", ["$state", "question", function ($state, question) {
            this.confirmDelete = function () {
                question.remove().then(function () {
                    $state.go('questionList', null, {reload: true});
                });
            };
        }]);
})(window.angular);

