angular.module('market').controller('orderController', function ($scope, $http, $routeParams, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.showOrders = function () {
        $http({
            url: contextPath + 'api/v1/order',
            method:'GET',
            params: {
                username: $localStorage.webUserStorage.username
            }
        }).then(function (response) {
            $scope.orders = response.data
            console.log(response.data)
            console.log($localStorage.webUserStorage.username)
        });
    };

    $scope.toInfo = function (id) {
        $localStorage.orderId = id
        $location.path("/infoOrder")
    };

    $scope.showOrders();
});