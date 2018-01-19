app.factory('Judge', ['$http', function($http) {
  return $http.get('http://localhost:8080/judges')
            .success(function(data) {
              return data;
            })
            .error(function(err) {
              return err;
            });
}]);