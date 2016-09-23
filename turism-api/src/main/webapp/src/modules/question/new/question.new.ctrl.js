/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module("questionModule");

    mod.controller("questionNewCtrl", ["$scope", "$state", "questions",
        function ($scope, $state, questions) {
            $scope.currentRecord = {};
            $scope.actions = {
                save: {
                    displayName: 'Save',
                    icon: 'save',
                    fn: function () {
                        if ($scope.questionForm.$valid) {
                            questions.post($scope.currentRecord).then(function (rc) {
                                $state.go('questionDetail', {questionId: rc.id}, {reload: true});
                            });
                        }
                    }
                },
                cancel: {
                    displayName: 'Cancel',
                    icon: 'remove',
                    fn: function () {
                        $state.go('questionList');
                    }
                }
            };
        }]);
})(window.angular);


