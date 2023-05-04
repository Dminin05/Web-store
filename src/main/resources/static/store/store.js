angular.module('market').controller('storeController', function ($scope, $http, $location) {

    const contextPath = 'http://localhost:8189/market/';
    $scope.currentPageIndex = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        $scope.currentPageIndex = pageIndex;
        $http({
            url: contextPath + 'api/v1/products',
            method:'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            $scope.paginationArray = $scope.generatePageIndexes(1, $scope.productsPage.totalPages);
        });
    };

    $scope.generatePageIndexes = function (startPage, endPage) {
        let arrayIndexes = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arrayIndexes.push(i)
        }
        return arrayIndexes;
    }

    $scope.forward = function () {
        if ($scope.currentPageIndex < $scope.productsPage.totalPages) {
            $scope.currentPageIndex++
            $scope.loadProducts($scope.currentPageIndex);
        }
    };

    $scope.back = function () {
        if ($scope.currentPageIndex > 1) {
            $scope.currentPageIndex--;
            $scope.loadProducts($scope.currentPageIndex);
        }
    };

    $scope.navToEditProductPage = function (productId) {
        $location.path('/edit_product/' + productId)
    }

    $scope.delete = function (idToDelete) {
        $http({
            url: contextPath + 'api/v1/products/delete',
            method: 'GET',
            params: {
                id: idToDelete
            }
        }).then(function (response) {
            if ($scope.productsPage.content.length == 1 && $scope.productsPage.totalPages != 1) {
                $scope.currentPageIndex -= 1;
                $scope.loadProducts($scope.currentPageIndex);
            } else {
                $scope.loadProducts($scope.currentPageIndex);
            }
        });
    };

    $scope.addProduct = function (idToAdd) {
        $http({
            url: contextPath + 'api/v1/cart/add',
            method:'GET',
            params: {
                id: idToAdd
            }
        }).then(function (response) {
            $scope.getCart();
            console.log(response)
        });
    };

    $scope.getCart = function () {
        $http({
            url: contextPath + 'api/v1/cart',
            method:'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    };

    $scope.loadProducts();



});
