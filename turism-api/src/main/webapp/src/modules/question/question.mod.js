/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module('questionModule', ['ngCrud', 'ui.router']);

    mod.constant('questionModel', {
        name: 'question',
        displayName: 'FAQ',
		url: 'questions',
        fields: {            name: {
                displayName: 'Pregunta',
                type: 'String',
                required: true
            },
            respuesta: {
                displayName: 'Respuesta',
                type: 'String',
                required: true
            }
        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/question/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('question', {
                url: '/questions?page&limit',
                abstract: true,

                views: {
                     tripChieldView: {
                        templateUrl: basePath + 'question.tpl.html',
                        controller: 'questionCtrl'
                    }
                },
                resolve: {
                    model: 'questionModel',
                    questions: ['trip', '$stateParams', 'model', function (trip, $params, model) {
                            return trip.getList(model.url, $params);
                        }]                }
            });
            $sp.state('questionList', {
                url: '/list',
                parent: 'question',
                views: {
                    questionView: {
                        templateUrl: basePath + 'list/question.list.tpl.html',
                        controller: 'questionListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('questionNew', {
                url: '/new',
                parent: 'question',
                views: {
                    questionView: {
                        templateUrl: basePath + 'new/question.new.tpl.html',
                        controller: 'questionNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('questionInstance', {
                url: '/{questionId:int}',
                abstract: true,
                parent: 'question',
                views: {
                    questionView: {
                        template: '<div ui-view="questionInstanceView"></div>'
                    }
                },
                resolve: {
                    question: ['questions', '$stateParams', function (questions, $params) {
                            return questions.get($params.questionId);
                        }]
                }
            });
            $sp.state('questionDetail', {
                url: '/details',
                parent: 'questionInstance',
                views: {
                    questionInstanceView: {
                        templateUrl: baseInstancePath + 'detail/question.detail.tpl.html',
                        controller: 'questionDetailCtrl'
                    }
                }
            });
            $sp.state('questionEdit', {
                url: '/edit',
                sticky: true,
                parent: 'questionInstance',
                views: {
                    questionInstanceView: {
                        templateUrl: baseInstancePath + 'edit/question.edit.tpl.html',
                        controller: 'questionEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('questionDelete', {
                url: '/delete',
                parent: 'questionInstance',
                views: {
                    questionInstanceView: {
                        templateUrl: baseInstancePath + 'delete/question.delete.tpl.html',
                        controller: 'questionDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);
