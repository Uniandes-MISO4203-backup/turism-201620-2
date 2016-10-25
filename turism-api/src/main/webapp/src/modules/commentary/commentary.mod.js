/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
(function (ng) {
    var mod = ng.module('commentaryModule', ['ngCrud', 'ui.router']);

    mod.constant('commentaryModel', {
        name: 'commentary',
        displayName: 'Commentary',
		url: 'commentarys',
        fields: {      description: {
                displayName: 'Description',
                type: 'String',
                required: true
            },
            score : {
              displayName: 'Score',
              type: 'Long',
              required: true
            }
        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/commentary/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('commentary', {
                url: '/commentarys?page&limit',
                abstract: true,
                parent: 'tripDetail',
                views: {
                     tripChieldView: {
                        templateUrl: basePath + 'commentary.tpl.html',
                        controller: 'commentaryCtrl'
                    }
                },
                 resolve: {
                    model: 'commentaryModel',
                    commentarys: ['trip', '$stateParams', 'model', function (trip, $params, model) {
                            return trip.getList(model.url, $params);
                        }]                
                }
            });
            $sp.state('commentaryList', {
                url: '/list',
                parent: 'commentary',
                views: {
                    commentaryView: {
                        templateUrl: basePath + 'list/commentary.list.tpl.html',
                        controller: 'commentaryListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('commentaryNew', {
                url: '/new',
                parent: 'commentary',
                views: {
                    commentaryView: {
                        templateUrl: basePath + 'new/commentary.new.tpl.html',
                        controller: 'commentaryNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('commentaryInstance', {
                url: '/{commentaryId:int}',
                abstract: true,
                parent: 'commentary',
                views: {   
                commentaryView: {
                        template: '<div ui-view="commentaryInstanceView"></div>'
                    }
                },
                resolve: {
                    commentary: ['commentarys', '$stateParams', function (commentarys, $params) {
                            return commentarys.get($params.commentaryId);
                        }]
                }
            });
            $sp.state('commentaryDetail', {
                url: '/details',
                parent: 'commentaryInstance',
                views: {
                    commentaryInstanceView: {
                        templateUrl: baseInstancePath + 'detail/commentary.detail.tpl.html',
                        controller: 'commentaryDetailCtrl'
                    }
                }
            });
            $sp.state('commentaryEdit', {
                url: '/edit',
                sticky: true,
                parent: 'commentaryInstance',
                views: {
                    commentaryInstanceView: {
                        templateUrl: baseInstancePath + 'edit/commentary.edit.tpl.html',
                        controller: 'commentaryEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('commentaryDelete', {
                url: '/delete',
                parent: 'commentaryInstance',
                views: {
                    commentaryInstanceView: {
                        templateUrl: baseInstancePath + 'delete/commentary.delete.tpl.html',
                        controller: 'commentaryDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);
