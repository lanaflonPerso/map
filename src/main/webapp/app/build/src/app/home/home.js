angular.module( 'ngBoilerplate.home', ['ui.router', 'ngResource'])

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
        return $resource('/movie/:id',{id:'id'}).query();
    })

    .factory('moviesLatest', function ($resource) {
        return $resource('/movie/recent').query();
    })

    .factory('moviesTopRated', function ($resource) {
        return $resource('/recommeded/topRatedMovies').query();
    })

    .factory('moviesAction', function ($resource) {
        return $resource('/movie/top6/Action').query();
    })

    .factory('moviesComedy', function ($resource) {
        return $resource('/movie/top6/Comedy').query();
    })

    .factory('moviesHorror', function ($resource) {
        return $resource('/movie/top6/Horror').query();
    })

    .factory('moviesBollywood', function ($resource) {
        return $resource('/movie/language/hi').query();
    })

    .factory('moviesJapanese', function ($resource) {
        return $resource('/movie/language/ja').query();
    })

    .controller( 'HomeCtrl', function HomeCtrl($scope,getMovie,moviesLatest,moviesTopRated,moviesAction,moviesComedy,moviesHorror,moviesBollywood,moviesJapanese) {
        $scope.movieTitle = getMovie.get({id:129});
        $scope.moviesLatest = moviesLatest;
        $scope.moviesTopRated = moviesTopRated;
        $scope.moviesAction = moviesAction;
        $scope.moviesComedy = moviesComedy;
        $scope.moviesHorror = moviesHorror;
        $scope.moviesBollywood = moviesBollywood;
        $scope.moviesJapanese = moviesJapanese;
    })

;
