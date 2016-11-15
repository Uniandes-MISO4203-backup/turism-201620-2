(function (ng) {
    var mod = ng.module('buyModule', ['ngCrud', 'ui.router','productModule']);

    mod.constant('buyModel', {
        name: 'buy',
        displayName: 'Buy',
        url: 'buys',
        fields: {
            name: {
                displayName: 'Name',
                type: 'String',
                required: true
            },
            description: {
                displayName: 'Description',
                type: 'String',
                required: true
            },
            product: {
                displayName: 'Product',
                type: 'Reference',
                model: 'productModel',
                options: [],
                required: true
            },
            quantity: {
                displayName: 'Quantity',
                type: 'String',
                required: true
            }
        }
    });

    mod.config(['$stateProvider',
        function ($sp) {
            var basePath = 'src/modules/buy/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('buy', {
                url: '/buys?page&limit',
                abstract: true,
                views: {
                    mainView: {
                        templateUrl: basePath + 'buy.tpl.html',
                        controller: 'buyCtrl'
                    }
                },
                resolve: {
                    references: ['$q', 'Restangular', function ($q, r) {
                            return $q.all({
                                product: r.all('products').getList()
                            });
                        }],
                    model: 'buyModel',
                    buys: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }],                    
                    products: ['Restangular', 'productModel', '$stateParams', function (r, productModel, $params) {
                        return r.all(productModel.url).getList($params);
                    }]                    
                }
            });
            $sp.state('buyList', {
                url: '/list',
                parent: 'buy',
                views: {
                    buyView: {
                        templateUrl: basePath + 'list/buy.list.tpl.html',
                        controller: 'buyListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('buyNew', {
                url: '/new',
                parent: 'buy',
                views: {
                    buyView: {
                        templateUrl: basePath + 'new/buy.new.tpl.html',
                        controller: 'buyNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('buyInstance', {
                url: '/{buyId:int}',
                abstract: true,
                parent: 'buy',
                views: {
                    buyView: {
                        template: '<div ui-view="buyInstanceView"></div>'
                    }
                },
                resolve: {
                    buy: ['buys', '$stateParams', function (buys, $params) {
                            return buys.get($params.buyId);
                        }]
                }
            });
            $sp.state('buyDetail', {
                url: '/details',
                parent: 'buyInstance',
                views: {
                    buyInstanceView: {
                        templateUrl: baseInstancePath + 'detail/buy.detail.tpl.html',
                        controller: 'buyDetailCtrl'
                    }
                }
            });
            $sp.state('buyEdit', {
                url: '/edit',
                sticky: true,
                parent: 'buyInstance',
                views: {
                    buyInstanceView: {
                        templateUrl: baseInstancePath + 'edit/buy.edit.tpl.html',
                        controller: 'buyEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('buyDelete', {
                url: '/delete',
                parent: 'buyInstance',
                views: {
                    buyInstanceView: {
                        templateUrl: baseInstancePath + 'delete/buy.delete.tpl.html',
                        controller: 'buyDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
    var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope) {
    $scope.names = ["Emil", "Tobias", "Linus"];
});
})(window.angular);