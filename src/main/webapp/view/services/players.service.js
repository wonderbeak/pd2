app.factory('Players', ['$http', function($http) {

      return $http.get('http://localhost:8080/players')
                            .success(function(data) {
                              return data;
                            })
                            .error(function(err) {
                              return err;
                            });
            }




]);