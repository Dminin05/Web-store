angular.module('market').controller('createNewProductController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:5555/core/';

    $scope.createNewProduct = function () {
        $http.post(contextPath + 'api/v1/products/', $scope.new_product)
            .then(function successCallBack(response){
                console.log(response)
                $location.path('/store');
            }, function failureCallback (response) {
                alert(response.data.message);
                console.log(response)
            });
    }
});