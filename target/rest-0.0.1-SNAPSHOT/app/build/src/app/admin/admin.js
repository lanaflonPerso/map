angular.module( 'ngBoilerplate.admin', ['ui.router','ngBoilerplate.auth'])

    .config(function config( $stateProvider ) {
        $stateProvider.state( 'admin', {
            url: '/admin',
            views: {
                "main": {
                    controller: 'AdminCtrl',
                    templateUrl: 'admin/admin.tpl.html'
                }
            },
            data:{ pageTitle: 'Admin' }
        });
    })

    .config(function config( $stateProvider ) {
        $stateProvider.state( 'admin_movies', {
            url: '/admin_movies',
            views: {
                "main": {
                    controller: 'AdminMoviesCtrl',
                    templateUrl: 'admin/movies.tpl.html'
                }
            },
            data:{ pageTitle: 'Movies' }
        });
    })

    .config(function config( $stateProvider ) {
        $stateProvider.state( 'admin_users', {
            url: '/admin_users',
            views: {
                "main": {
                    controller: 'AdminUsersCtrl',
                    templateUrl: 'admin/users.tpl.html'
                }
            },
            data:{ pageTitle: 'Users' }
        });
    })

    .factory('updateUser', function($resource) {
        var user = {};
        user.update = function (account,success,failure) {
            var Account = $resource('/user/'+account.userId,account,{'update': { method:'PUT' }});
            Account.update({},account,success,failure);
        };
        return user;
    })

    .factory('movieService',function ($resource) {
        var service = {};
        service.add = function (movie,success,failure) {
            var Movie = $resource('/movie/new');
            Movie.save({},movie,success,failure);
        };
        return service;
    })

    .factory('getMovies', function ($resource) {
        return $resource('/movies');
    })

    .factory('getUsers', function ($resource) {
        return $resource('/users');
    })

    .factory('deleteMovie', function ($resource) {
        return $resource('/movie/:id',{id:'@id'});
    })

    .factory('deleteUser', function ($resource) {
        return $resource('/user/:id',{id:'@id'});
    })

    .controller('AdminMoviesCtrl', function AdminMoviesCtrl( $scope,getMovies,$timeout,$state,deleteMovie ) {
        $scope.isLoaded = false;
        var movies = getMovies.query();
        movies.$promise.then(function(data) {
            $scope.movies = data;
            $scope.isLoaded = true;
        });
        $scope.loadMore = function(){
            $state.reload();
        };
        $scope.deleteMovie= function(id){
            var notification = document.querySelector('.mdl-js-snackbar');
            deleteMovie.remove({id:id},function() {
                $state.reload();
                notification.MaterialSnackbar.showSnackbar({message: 'Movie deleted!'});
            });
        };
    })

    .controller('AdminUsersCtrl', function AdminUsersCtrl( $scope,getUsers,$state,deleteUser ) {
        $scope.isLoaded = false;
        var users = getUsers.query();
        users.$promise.then(function(data) {
            $scope.users = data;
            $scope.isLoaded = true;
        });
        $scope.loadMore = function(){
            $state.reload();
        };
        $scope.deleteUser  = function(id){
            var notification = document.querySelector('.mdl-js-snackbar');
            deleteUser.remove({id:id},function() {
                $state.reload();
                notification.MaterialSnackbar.showSnackbar({message: 'User deleted!'});
            });
        };
    })

    .controller('AdminCtrl', function AdminCtrl( $scope,sessionService,updateUser,movieService ) {
        $scope.user = sessionService.getLoggedInUser();
        $scope.openPasswordModal = function () {
            document.querySelector('#password-dialog').showModal();
        };
        $scope.closePasswordModal = function () {
            document.querySelector('#password-dialog').close();
        };
        $scope.openMovieModal = function () {
            document.querySelector('#movie-dialog').showModal();
        };
        $scope.closeMovieModal = function () {
            document.querySelector('#movie-dialog').close();
        };
        $scope.updatePassword = function () {
            var notification = document.querySelector('.mdl-js-snackbar');
            if(angular.equals($scope.update.password,$scope.update.confirm_password)){
                $scope.user.password = $scope.update.password;
                updateUser.update($scope.user,
                    function (returnedData) {
                        $scope.user = returnedData;
                        document.querySelector('#password-dialog').close();
                        notification.MaterialSnackbar.showSnackbar({message: 'Password changed!'});
                    },
                    function(){
                        notification.MaterialSnackbar.showSnackbar({message: 'Update failed!'});
                    });

            }else{
                notification.MaterialSnackbar.showSnackbar({message: 'Passwords do no match!'});
            }
        };

        $scope.movie = {};

        $scope.addMovie = function(){
            var btc = angular.isUndefined($scope.movie.belongs_to_collection)?0:$scope.movie.belongs_to_collection;
            var pop = angular.isUndefined($scope.movie.popularity)?0:$scope.movie.popularity;
            var pp = angular.isUndefined($scope.movie.poster_path)?0:$scope.movie.poster_path;
            var pid = angular.isUndefined($scope.movie.productionID)?0:$scope.movie.productionID;
            var votea = angular.isUndefined($scope.movie.vote_average)?0:$scope.movie.vote_average;
            var votec = angular.isUndefined($scope.movie.vote_count)?0:$scope.movie.vote_count;
            var run = angular.isUndefined($scope.movie.runtime)?0:$scope.movie.runtime;
            var movieData = {
                belongs_to_collection: btc,
                popularity: pop,
                poster_path: pp,
                productionID: pid,
                runtime: run,
                vote_average: votea,
                vote_count: votec,
                movieName: $scope.movie.movieName,
                genre: $scope.movie.genre,
                original_language: $scope.movie.original_language,
                overview: $scope.movie.overview,
                release_date: $scope.movie.release_date
            };
            var notification = document.querySelector('.mdl-js-snackbar');
            movieService.add(movieData,
                function (returnedData) {
                    console.log(returnedData);
                    notification.MaterialSnackbar.showSnackbar({message: 'Movie Added!'});
                },
                function(){
                    var notification = document.querySelector('.mdl-js-snackbar');
                    notification.MaterialSnackbar.showSnackbar({message: 'Operation failed!'});
                });
        };
    })

;