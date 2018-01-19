app.factory('Table', ['$http', function($http) {
  return $http.get('http://localhost:8080/table')
            .success(function(data) {
              return data;
            })
            .error(function(err) {
              return err;
            });
}]);