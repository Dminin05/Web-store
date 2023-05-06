angular.module('market').controller('infoOrderController', function ($scope, $http, $routeParams, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.infoOrder = function () {
        $http({
            url: contextPath + 'api/v1/order/info',
            method:'GET',
            params: {
                id: $localStorage.orderId
            }
        }).then(function (response) {
            $scope.aboutOrder = response.data
            console.log(response.data)
        });
    };

    $scope.back = function () {
       delete $localStorage.orderId;
        $location.path("/order")
    };

    $scope.infoOrder();

});