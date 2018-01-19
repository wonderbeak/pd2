app.factory('Place', ['$http', function($http) {
  return $http.get('http://localhost:8080/places')
            .success(function(data) {
              return data;
            })
            .error(function(err) {
              return err;
            });
}]);