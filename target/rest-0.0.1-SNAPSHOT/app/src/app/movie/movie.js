angular.module( 'ngBoilerplate.movie', ['ui.router','ngBoilerplate.auth'])

    .config(function config( $stateProvider ) {
        $stateProvider.state( 'movie', {
            url: '/movie?id',
            views: {
                "main": {
                    controller: 'MovieCtrl',
                    templateUrl: 'movie/movie.tpl.html'
                }
            },
            data:{ pageTitle: 'Movie' }
        });
    })

    .config(function config( $stateProvider ) {
        $stateProvider.state( 'movies', {
            url: '/genre?genre',
            views: {
                "main": {
                    controller: 'MoviesCtrl',
                    templateUrl: 'movie/movies.tpl.html'
                }
            },
            data:{ pageTitle: 'Movies' }
        });
    })

    .config(function config( $stateProvider ) {
        $stateProvider.state( 'recommended', {
            url: '/recommended',
            views: {
                "main": {
                    controller: 'RecCtrl',
                    templateUrl: 'movie/recommended.tpl.html'
                }
            },
            data:{ pageTitle: 'Recommended' }
        });
    })

    .factory('getMovie', function ($resource) {
        return $resource('/movie/:id',{id:'@id'});
    })

    .factory('getGenreFactory', function ($resource) {
        var genre = {};
        genre.getGenre = function(genre){
            return $resource('/movie/genre/'+genre).query();
        };
        return genre;
    })

    .factory('listService',function ($resource) {
        var service = {};
        service.addtoList = function (account,success,failure) {
            var Account = $resource('/userfav/new');
            Account.save({},account,success,failure);
        };
        return service;
    })

    .factory('ratingService',function ($resource) {
        var service = {};
        service.rateMovie = function (account,success,failure) {
            var Rating = $resource('/rating/new');
            Rating.save({},account,success,failure);
        };
        return service;
    })

    .factory('ratedFactory', function ($resource) {
        return $resource('/recommeded/topRatedMovies');
    })

    .factory('watchedFactory', function ($resource) {
        return $resource('/recommend/watched/:id',{id:'@id'});
    })

    .factory('favoritesFactory', function ($resource) {
        return $resource('/recommend/favorite/:id',{id:'@id'});
    })

    .factory('searchFactory', function ($resource) {
        return $resource('/recommend/presearch/:id',{id:'@id'});
    })

    .controller( 'RecCtrl', function RecCtrl( $scope,watchedFactory,searchFactory,favoritesFactory,ratedFactory,sessionService ) {
        $scope.user = sessionService.getLoggedInUser();
        $scope.moviesRated = ratedFactory.query();
        $scope.moviesWatched = watchedFactory.query({id:$scope.user.userId});
        $scope.moviesFavorites = favoritesFactory.query({id:$scope.user.userId});
        $scope.moviesSearch = searchFactory.query({id:$scope.user.userId});
    })

    .controller( 'MovieCtrl', function MovieCtrl( $scope,getMovie,$stateParams,sessionService,userExists,listService,ratingService ) {
        $scope.movie = getMovie.get({id:$stateParams.id});
        $scope.user = sessionService.getLoggedInUser();
        $scope.movie_rating = $scope.movie.vote_average;
        var notification = document.querySelector('.mdl-js-snackbar');
        $scope.toFavorite = function(){
            var postData = {
                userid: $scope.user.userId,
                movieid: $scope.movie.movieId,
                type: 'F'
            };
            listService.addtoList(postData,
                function() {
                    notification.MaterialSnackbar.showSnackbar({message: 'Movie added to favorites!'});
                },
                function(){
                    notification.MaterialSnackbar.showSnackbar({message: 'Operation failed!'});
                });
        };
        $scope.toWatched = function(){
            var postData = {
                userid: $scope.user.userId,
                movieid: $scope.movie.movieId,
                type: 'W'
            };
            listService.addtoList(postData,
                function() {
                    notification.MaterialSnackbar.showSnackbar({message: 'Movie added to watched!'});
                },
                function(){
                    notification.MaterialSnackbar.showSnackbar({message: 'Operation failed!'});
                });
        };
        $scope.openUpdateModal = function(){
            document.querySelector('#rating-dialog').showModal();
        };
        $scope.closeUpdateModal = function(){
            document.querySelector('#rating-dialog').close();
        };
        $scope.addRating = function(){
            $scope.movie_rating = $scope.rating.value;
            var ratingData = {
                userid: $scope.user.userId,
                movieid: $scope.movie.movieId,
                imdbId: 0,
                rating: parseInt($scope.movie_rating,10)
            };
            ratingService.rateMovie(ratingData,
                function() {
                    document.querySelector('#rating-dialog').close();
                    notification.MaterialSnackbar.showSnackbar({message: 'Movie rated successfully!'});
                },
                function(){
                    notification.MaterialSnackbar.showSnackbar({message: 'Operation failed!'});
                });
        };
    })

    .controller( 'MoviesCtrl', function MoviesCtrl( $scope,$stateParams,getGenreFactory,$state,$templateCache ) {
        $scope.genre = $stateParams.genre;
        $scope.movies = getGenreFactory.getGenre($stateParams.genre);
        $scope.loadMore = function(){
            $state.reload();
        };
    })

;