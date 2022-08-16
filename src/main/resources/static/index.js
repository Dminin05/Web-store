angular.module('market', []).controller('indexController', function ($scope, $http) {

    const contextPath = 'http://localhost:8189/market/'
    $scope.pageIndex = 1;

    var loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + 'products',
            method:'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
        });
    };

    $scope.delete = function (idToDelete) {
        $http({
            url: contextPath + 'products/delete',
            method: 'GET',
            params: {
                id: idToDelete
            }
        }).then(function (response) {
            if ($scope.productsPage.content.length == 1 && $scope.productsPage.totalPages != 1) {
                $scope.pageIndex -= 1;
                loadProducts($scope.pageIndex);
            } else {
                loadProducts($scope.pageIndex);
            }
        });
    };

    $scope.refresh = function () {
        loadProducts($scope.pageIndex)
    };

    $scope.forward = function () {
        if ($scope.pageIndex < $scope.productsPage.totalPages) {
            $scope.pageIndex++
            loadProducts($scope.pageIndex);
        }
    };

    $scope.back = function () {
        if ($scope.pageIndex > 1) {
            $scope.pageIndex--;
            loadProducts($scope.pageIndex);
        }
    };

    loadProducts($scope.pageIndex);

});