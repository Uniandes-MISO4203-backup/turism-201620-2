(function (ng) {

    var mod = ng.module("buyModule");

    mod.controller("buyListCtrl", ["$scope", '$state', 'buys', 'products', '$stateParams','Restangular',
        function ($scope, $state, buys, products, $params,Restangular) {
            $scope.records = buys;
            $scope.products = products;
            //Paginación
            this.itemsPerPage = $params.limit;
            this.currentPage = $params.page;
            this.totalItems = buys.totalRecords;
            $scope.categorys = [];

            $scope.getCategorys = function (parentCategory) {
                Restangular.all("categorys").customGET('parents/'+parentCategory).then(function (response) {
                    if (response.length>0) {
                        $scope.categorys=response;
                    }
                });
            };
            $scope.filtrar = function (parentCategory) {
                $scope.getCategorys(parentCategory);
                Restangular.all("buys").customGET(parentCategory).then(function (response) {
                        $scope.records=response;
                });
            };
            $scope.getCategorys("");

            this.pageChanged = function () {
                $state.go('buyList', {page: this.currentPage});
            };

            $scope.actions = {
                create: {
                    displayName: 'Create',
                    icon: 'plus',
                    fn: function () {
                        $state.go('buyNew');
                    }
                },
                refresh: {
                    displayName: 'Refresh',
                    icon: 'refresh',
                    fn: function () {
                        $state.reload();
                    }
                }            };            
            $scope.productActions = {
                detail: {
                    displayName: 'Detail',
                    icon: 'eye-open',
                    fn: function (rc) {
                        $state.go('productDetail', {productId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                }
            };           
            $scope.recordActions = {
                detail: {
                    displayName: 'Detail',
                    icon: 'eye-open',
                    fn: function (rc) {
                        $state.go('buyDetail', {buyId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                },
                edit: {
                    displayName: 'Edit',
                    icon: 'edit',
                    fn: function (rc) {
                        $state.go('buyEdit', {buyId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                },
                delete: {
                    displayName: 'Delete',
                    icon: 'minus',
                    fn: function (rc) {
                        $state.go('buyDelete', {buyId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                }
            };
        }]);
})(window.angular);