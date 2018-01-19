app.factory('PlayerService', ['$http', '$q', function($http, $q){
    var factory = {
        getAllPlayers: getAllPlayers,
        getTopPlayers: getTopPlayers,
        getWorstPlayers: getWorstPlayers,
        getPlayer: getPlayer
    };

    return factory;

    function getAllPlayers() {
        var deferred = $q.defer();
        $http.get('http://localhost:8080/players')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching All Players');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function getTopPlayers() {
        var deferred = $q.defer();
        $http.get('http://localhost:8080/top')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Top Players');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function getWorstPlayers() {
        $http.get('http://localhost:8080/worst')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Worst Players');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }



    function getPlayer(id) {
        $http.get('http://localhost:8080/player/'+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Player');
                deferred.reject(errResponse);
            }
        );
    }

}]);