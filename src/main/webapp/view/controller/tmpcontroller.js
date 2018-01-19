app.controller('MainController', ['$scope', 'PlayerService', function($scope, PlayerService, top) {

    $scope.top = function getAllPlayers(){
        PlayerService.getAllPlayers()
            .then(
            function(d) {
                return d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
}]);