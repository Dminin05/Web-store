angular.module('market').controller('editProductController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:5555/core/';

    $scope.prepareProductForUpdate = function () {
        $http.get(contextPath + 'api/v1/products/' + $routeParams.productId)
            .then(function successCallback (response) {
                $scope.updated_product = response.data;
                console.log(response)
            }, function failureCallback (response) {
                console.log(response);
                alert(response.data.messages);
                $location.path('/store');
            });
    }

    $scope.updateProduct = function () {
        $http.put(contextPath + 'api/v1/products', $scope.updated_product)
            .then(function successCallback (response) {
                $scope.updated_product = null;
                alert('Product updated successfully');
                $location.path('/store');
            }, function failureCallback (response) {
                alert(response.data.messages);
            });
    }

    $scope.prepareProductForUpdate();
});