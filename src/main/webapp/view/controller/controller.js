app.controller('MainController', ['$scope', '$http', 'Table', 'Top', 'Players', 'Judge', 'Goalkeeper', 'Evil', function($scope, $http, Table, Top, Players, Judge, Goalkeeper, Evil, getPlayer) {
  Table.success(function(data) {
    $scope.table = data;
  });
  Top.success(function(data) {
    $scope.top = data
  });
  Players.success(function(data) {
    $scope.players = data
  });
  Judge.success(function(data) {
    $scope.judge = data
  });
  Goalkeeper.success(function(data) {
    $scope.goalkeeper = data
  });
  Evil.success(function(data) {
    $scope.evil = data
  });


  $scope.getPlayer = function(id) {
            $scope.getPlayer = $http.get('http://localhost:8080/player/'+id)
                            .success(function(data) {
                              return data;
                            })
                            .error(function(err) {
                              return err;
                            });
        }

}]);
