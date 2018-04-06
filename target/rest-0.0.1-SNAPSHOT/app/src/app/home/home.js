angular.module( 'ngBoilerplate.home', [
  'ui.router',
  'plusOne'
])

    .config(function config( $stateProvider ) {
      $stateProvider.state( 'home', {
        url: '/home',
        views: {
          "main": {
            controller: 'HomeCtrl',
            templateUrl: 'home/home.tpl.html'
          }
        },
        data:{ pageTitle: 'Home' }
      });
    })

    .factory('getMovie', function ($resource) {
        return $resource('http://localhost:8080/movie/:id',{id:'@id'});
    })

    .factory('moviesLatest', function ($resource) {
      return $resource('/movie/recent');
    })

    .factory('moviesTopRated', function ($resource) {
        return $resource('/recommeded/topRatedMovies');
    })

    .factory('moviesAction', function ($resource) {
        return $resource('/movie/top6/Action');
    })

    .factory('moviesComedy', function ($resource) {
        return $resource('/movie/top6/Comedy');
    })

    .factory('moviesHorror', function ($resource) {
        return $resource('/movie/top6/Horror');
    })

    .factory('moviesInternational', function ($resource) {
        return $resource('/movie/top6/Foreign');
    })

    .factory('moviesRomantic', function ($resource) {
        return $resource('/movie/top6/Romance');
    })

    .controller( 'HomeCtrl', function HomeCtrl( $scope,$http,moviesLatest,getMovie,moviesRomantic,moviesInternational,moviesHorror,moviesTopRated,moviesAction,moviesComedy ) {
        $scope.movieTitle = getMovie.get({id:129});
        $scope.moviesLatest = moviesLatest.query();
        $scope.moviesTopRated = moviesTopRated.query();
        $scope.moviesAction = moviesAction.query();
        $scope.moviesComedy = moviesComedy.query();
        $scope.moviesHorror = moviesHorror.query();
        $scope.moviesInternational = moviesInternational.query();
        $scope.moviesRomantic = moviesRomantic.query();
        $scope.testMethod = function () {
            return true;
        };
    })
;

