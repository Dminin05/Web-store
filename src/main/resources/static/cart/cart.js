angular.module('market').controller('cartController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.loadProducts = function () {
        $http({
            url: contextPath + 'api/v1/cart',
            method:'GET'
        }).then(function (response) {
            $scope.products = response.data;
        });
    };

    $scope.deleteProduct = function (idToDelete) {
        $http({
            url: contextPath + 'api/v1/cart/delete',
            method:'GET',
            params: {
                id: idToDelete
            }
        }).then(function (response) {
            $scope.products = response.data;
            $scope.loadProducts();
        });
    };

    $scope.loadProducts();

});