/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module('paymentMethodModule', ['ngCrud', 'ui.router']);

    mod.constant('paymentMethodModel', {
        name: 'paymentMethod',
        displayName: 'Payment Method',
        url: 'paymentMethods',
        fields: {
            name: {
                displayName: 'Name',
                type: 'String',
                required: true
            },
            cardType: {
                displayName: 'Type',
                type: 'String',
                //options: ['Visa', 'Master Card', 'American Express'],
                required: true
            },
            cardNumber: {
                displayName: 'Card Number',
                type: 'Long',
                required: true
            },
            securityCode: {
                displayName: 'Security Code',
                type: 'Integer',
                required: true
            },
            expirationMonth: {
                displayName: 'Expiration Month',
                type: 'Integer',
                required: true
            },
            expirationYear: {
                displayName: 'Expiration Year',
                type: 'Integer',
                required: true
            }
        }
    });

    mod.config(['$stateProvider',
        function ($sp) {
            var basePath = 'src/modules/paymentMethod/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('paymentMethod', {
                url: '/paymentMethods?page&limit',
                abstract: true,
                parent: 'clientDetail',
                views: {
                    clientChieldView: {
                        templateUrl: basePath + 'paymentMethod.tpl.html',
                        controller: 'paymentMethodCtrl'
                    }
                },
                resolve: {
                    model: 'paymentMethodModel',
                    paymentMethods: ['client', '$stateParams', 'model', function (client, $params, model) {
                            return client.getList(model.url, $params);
                        }]}
            });
            $sp.state('paymentMethodList', {
                url: '/list',
                parent: 'paymentMethod',
                views: {
                    paymentMethodView: {
                        templateUrl: basePath + 'list/paymentMethod.list.tpl.html',
                        controller: 'paymentMethodListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('paymentMethodNew', {
                url: '/new',
                parent: 'paymentMethod',
                views: {
                    paymentMethodView: {
                        templateUrl: basePath + 'new/paymentMethod.new.tpl.html',
                        controller: 'paymentMethodNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('paymentMethodInstance', {
                url: '/{paymentMethodId:int}',
                abstract: true,
                parent: 'paymentMethod',
                views: {
                    paymentMethodView: {
                        template: '<div ui-view="paymentMethodInstanceView"></div>'
                    }
                },
                resolve: {
                    paymentMethod: ['paymentMethods', '$stateParams', function (paymentMethods, $params) {
                            return paymentMethods.get($params.paymentMethodId);
                        }]
                }
            });
            $sp.state('paymentMethodDetail', {
                url: '/details',
                parent: 'paymentMethodInstance',
                views: {
                    paymentMethodInstanceView: {
                        templateUrl: baseInstancePath + 'detail/paymentMethod.detail.tpl.html',
                        controller: 'paymentMethodDetailCtrl'
                    }
                }
            });
            $sp.state('paymentMethodEdit', {
                url: '/edit',
                sticky: true,
                parent: 'paymentMethodInstance',
                views: {
                    paymentMethodInstanceView: {
                        templateUrl: baseInstancePath + 'edit/paymentMethod.edit.tpl.html',
                        controller: 'paymentMethodEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('paymentMethodDelete', {
                url: '/delete',
                parent: 'paymentMethodInstance',
                views: {
                    paymentMethodInstanceView: {
                        templateUrl: baseInstancePath + 'delete/paymentMethod.delete.tpl.html',
                        controller: 'paymentMethodDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);

