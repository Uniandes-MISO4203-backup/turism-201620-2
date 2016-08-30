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
    var mod = ng.module('commentsModule', ['ngCrud', 'ui.router']);

    mod.constant('commentsModel', {
        name: 'comments',
        displayName: 'Comments',
		url: 'comments',
        fields: {      comentary: {
                displayName: 'Comentary',
                type: 'String',
                required: true
            }        
        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/comments/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('comments', {
                url: '/comments?page&limit',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'comments.tpl.html',
                        controller: 'commentsCtrl'
                    }
                },
                resolve: {
                    model: 'commentsModel',
                    comments: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('commentsList', {
                url: '/list',
                parent: 'comments',
                views: {
                    commentsView: {
                        templateUrl: basePath + 'list/comments.list.tpl.html',
                        controller: 'commentsListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('commentsNew', {
                url: '/new',
                parent: 'comments',
                views: {
                    commentsView: {
                        templateUrl: basePath + 'new/comments.new.tpl.html',
                        controller: 'commentsNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('commentsInstance', {
                url: '/{commentsId:int}',
                abstract: true,
                parent: 'comments',
                views: {
                    commentsView: {
                        template: '<div ui-view="commentsInstanceView"></div>'
                    }
                },
                resolve: {
                    comments: ['comments', '$stateParams', function (comments, $params) {
                            return comments.get($params.commentsId);
                        }]
                }
            });
            $sp.state('commentsDetail', {
                url: '/details',
                parent: 'commentsInstance',
                views: {
                    commentsInstanceView: {
                        templateUrl: baseInstancePath + 'detail/comments.detail.tpl.html',
                        controller: 'commentsDetailCtrl'
                    }
                }
            });
            $sp.state('commentsEdit', {
                url: '/edit',
                sticky: true,
                parent: 'commentsInstance',
                views: {
                    commentsInstanceView: {
                        templateUrl: baseInstancePath + 'edit/comments.edit.tpl.html',
                        controller: 'commentsEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('commentsDelete', {
                url: '/delete',
                parent: 'commentsInstance',
                views: {
                    commentsInstanceView: {
                        templateUrl: baseInstancePath + 'delete/comments.delete.tpl.html',
                        controller: 'commentsDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);
