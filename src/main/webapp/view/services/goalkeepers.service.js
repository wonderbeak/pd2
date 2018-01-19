app.factory('Goalkeeper', ['$http', function($http) {
  return $http.get('http://localhost:8080/goalkeepers')
            .success(function(data) {
              return data;
            })
            .error(function(err) {
              return err;
            });
}]);