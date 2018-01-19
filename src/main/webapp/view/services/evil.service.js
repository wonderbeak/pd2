app.factory('Evil', ['$http', function($http) {

      return $http.get('http://localhost:8080/evil')
                            .success(function(data) {
                              return data;
                            })
                            .error(function(err) {
                              return err;
                            });
            }




]);