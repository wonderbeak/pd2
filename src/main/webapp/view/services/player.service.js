app.factory('Top', ['$http', function($http) {
  return $http.get('http://localhost:8080/top')
            .success(function(data) {
              return data;
            })
            .error(function(err) {
              return err;
            });
}]);