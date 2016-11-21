/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module('destinationModule', ['ngCrud', 'ui.router']);

    mod.constant('destinationModel', {
        name: 'destination',
        displayName: 'Destination',
		url: 'destinations',
        fields: {            name: {
                displayName: 'Destino',
                type: 'String',
                required: true,
            },
            inicialDate: {
                displayName: 'Fecha Inicial',
                type: 'Date',
                required: true
            },
            finalDate: {
                displayName: 'Fecha Final',
                type: 'Date',
                required: true
            },
            duration: {
                displayName: 'Duración',
                type: 'Integer',
                required: true
            },
            activities: {
                displayName: 'Actividades',
                type: 'TextArea',
                required: true
            }
        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/destination/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('destination', {
                url: '/destination?page&limit',
                abstract: true,
                parent: 'tripDetail',
                views: {
                     tripChieldView: {
                        templateUrl: basePath + 'destination.tpl.html',
                        controller: 'destinationCtrl'
                    }
                },
                resolve: {
                    model: 'destinationModel',
                    destinations: ['trip', '$stateParams', 'model', function (trip, $params, model) {
                            return trip.getList(model.url, $params);
                        }]                }
            });
            $sp.state('destinationList', {
                url: '/list',
                parent: 'destination',
                views: {
                    destinationView: {
                        templateUrl: basePath + 'list/destination.list.tpl.html',
                        controller: 'destinationListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('destinationNew', {
                url: '/new',
                parent: 'destination',
                views: {
                    destinationView: {
                        templateUrl: basePath + 'new/destination.new.tpl.html',
                        controller: 'destinationNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('destinationInstance', {
                url: '/{destinationId:int}',
                abstract: true,
                parent: 'destination',
                views: {
                    destinationView: {
                        template: '<div ui-view="destinationInstanceView"></div>'
                    }
                },
                resolve: {
                    destination: ['destinations', '$stateParams', function (destinations, $params) {
                            return destinations.get($params.destinationId);
                        }]
                }
            });
            $sp.state('destinationDetail', {
                url: '/details',
                parent: 'destinationInstance',
                views: {
                    destinationInstanceView: {
                        templateUrl: baseInstancePath + 'detail/destination.detail.tpl.html',
                        controller: 'destinationDetailCtrl'
                    }
                }
            });
            $sp.state('destinationEdit', {
                url: '/edit',
                sticky: true,
                parent: 'destinationInstance',
                views: {
                    destinationInstanceView: {
                        templateUrl: baseInstancePath + 'edit/destination.edit.tpl.html',
                        controller: 'destinationEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('destinationDelete', {
                url: '/delete',
                parent: 'destinationInstance',
                views: {
                    destinationInstanceView: {
                        templateUrl: baseInstancePath + 'delete/destination.delete.tpl.html',
                        controller: 'destinationDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);
