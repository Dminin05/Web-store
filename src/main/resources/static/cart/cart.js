angular.module('market').controller('cartController', function ($scope, $http, $routeParams, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.loadProducts = function () {
        $http({
            url: contextPath + 'api/v1/cart/showAll',
            method:'GET'
        }).then(function (response) {
            $scope.products = response.data;
        });
    };

    $scope.getCart = function () {
        $http({
            url: contextPath + 'api/v1/cart',
            method:'GET'
        }).then(function (response) {
            $scope.cart = response.data;
            console.log(response.data)
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
            $scope.getCart();
            $scope.loadProducts();
        });
    };

    $scope.createOrder = function () {
        $http({
            url: contextPath + 'api/v1/cart/createOrder',
            method:'GET',
            params: {
                username: $localStorage.webUserStorage.username
            }
        }).then(function (response) {
            $scope.getCart();
            $scope.loadProducts();
        });
    };

    $scope.isCartEmpty = function () {
        if ($scope.products !== undefined) {
            if ($scope.products.length == 0) {
                return false;
            } else {
                return true;
            }
        }
    }

    $scope.getCart();
    $scope.loadProducts();

});