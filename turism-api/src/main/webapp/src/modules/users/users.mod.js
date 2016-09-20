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
    var mod = ng.module('usersModule', ['ngCrud', 'ui.router', 'clientModule', 'agencyModule']);

    mod.constant('usersModule', {
        name: 'users',
        displayName: 'Users',
        url: 'users',
        fields: {
            name: {
                displayName: 'Name',
                type: 'String',
                required: true
            }
        }
    });

    mod.config(['$stateProvider',
        function ($sp) {
            var basePath = 'src/modules/users/';
            // var baseInstancePath = basePath + 'instance/';

            $sp.state('user', {
                url: '/users?page&limit',
                abstract: true,

                views: {
                    mainView: {
                        templateUrl: basePath + 'users.tpl.html',
                        controller: 'usersCtrl'
                    }
                },
                resolve: {
                    model: 'usersModule',
                    clients: ['Restangular', 'clientModel', '$stateParams', function (r, clientModel, $params) {
                        return r.all(clientModel.url).getList($params);
                    }],
                    agencies: ['Restangular', 'agencyModel', '$stateParams', function (r, agencyModel, $params) {
                        return r.all(agencyModel.url).getList($params);
                    }]
                }
            });
            $sp.state('usersList', {
                url: '/list',
                parent: 'user',
                views: {
                    usersView: {
                        templateUrl: basePath + 'list/user.list.tpl.html',
                        controller: 'userListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            // $sp.state('agencyNew', {
            //     url: '/new',
            //     parent: 'agency',
            //     views: {
            //         agencyView: {
            //             templateUrl: basePath + 'new/agency.new.tpl.html',
            //             controller: 'agencyNewCtrl',
            //             controllerAs: 'ctrl'
            //         }
            //     }
            // });
            // $sp.state('agencyInstance', {
            //     url: '/{agencyId:int}',
            //     abstract: true,
            //     parent: 'agency',
            //     views: {
            //         agencyView: {
            //             template: '<div ui-view="agencyInstanceView"></div>'
            //         }
            //     },
            //     resolve: {
            //         agency: ['agencys', '$stateParams', function (agencys, $params) {
            //             return agencys.get($params.agencyId);
            //         }]
            //     }
            // });
            // $sp.state('agencyDetail', {
            //     url: '/details',
            //     parent: 'agencyInstance',
            //     views: {
            //         agencyInstanceView: {
            //             templateUrl: baseInstancePath + 'detail/agency.detail.tpl.html',
            //             controller: 'agencyDetailCtrl'
            //         }
            //     }
            // });
            // $sp.state('agencyEdit', {
            //     url: '/edit',
            //     sticky: true,
            //     parent: 'agencyInstance',
            //     views: {
            //         agencyInstanceView: {
            //             templateUrl: baseInstancePath + 'edit/agency.edit.tpl.html',
            //             controller: 'agencyEditCtrl',
            //             controllerAs: 'ctrl'
            //         }
            //     }
            // });
            // $sp.state('agencyDelete', {
            //     url: '/delete',
            //     parent: 'agencyInstance',
            //     views: {
            //         agencyInstanceView: {
            //             templateUrl: baseInstancePath + 'delete/agency.delete.tpl.html',
            //             controller: 'agencyDeleteCtrl',
            //             controllerAs: 'ctrl'
            //         }
            //     }
            // });
        }]);
})(window.angular);
