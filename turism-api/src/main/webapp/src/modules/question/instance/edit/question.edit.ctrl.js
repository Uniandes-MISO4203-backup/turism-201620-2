/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("questionModule");

    mod.controller("questionEditCtrl", ["$scope", "$state", "question",
        function ($scope, $state, question) {
            $scope.currentRecord = question;
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.questionForm.$valid) {
                            $scope.currentRecord.put().then(function (rc) {
                                $state.go('questionDetail', {questionId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('questionDetail');
                    }
                }
            };
        }]);
})(window.angular);


