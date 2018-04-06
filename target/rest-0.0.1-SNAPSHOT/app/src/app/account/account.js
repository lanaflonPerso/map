angular.module( 'ngBoilerplate.account', ['ui.router','ngBoilerplate.auth'])

    .config(function config( $stateProvider ) {
        $stateProvider.state( 'account', {
            url: '/account',
            views: {
                "main": {
                    controller: 'AccountCtrl',
                    templateUrl: 'account/account.tpl.html'
                }
            },
            data:{ pageTitle: 'Account' }
        });
    })

    .config(function config( $stateProvider ) {
        $stateProvider.state( 'list', {
            url: '/list?type',
            views: {
                "main": {
                    controller: 'ListCtrl',
                    templateUrl: 'account/list.tpl.html'
                }
            },
            data:{ pageTitle: 'List' }
        });
    })

    .factory('getMoviesbyType', function ($resource) {
        var movies = {};
        movies.getFav = function(userId){
            return $resource('/userfav/'+userId).query();
        };
        movies.getWat = function(userId){
            return $resource('/userwat/'+userId).query();
        };
        movies.getRat = function(userId){
            return $resource('/rating/user/'+userId).query();
        };
        return movies;
    })

    .factory('updateUser', function($resource) {
        var user = {};
        user.update = function (account,success,failure) {
            var Account = $resource('/user/'+account.userId,account,{'update': { method:'PUT' }});
            Account.update({},account,success,failure);
        };
        return user;
    })

    .controller( 'AccountCtrl', function AccountCtrl( $scope,sessionService,updateUser ) {
        $scope.user = sessionService.getLoggedInUser();
        $scope.openPasswordModal = function () {
            document.querySelector('#password-dialog').showModal();
        };
        $scope.closePasswordModal = function () {
            document.querySelector('#password-dialog').close();
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
    })

    .controller( 'ListCtrl', function ListCtrl( $scope,sessionService,$stateParams,getMoviesbyType ) {
        $scope.user = sessionService.getLoggedInUser();
        $scope.type = $stateParams.type;
        if($scope.type=='Favorites'){
            $scope.movies = getMoviesbyType.getFav($scope.user.userId);
        }else if($scope.type=='Watched'){
            $scope.movies = getMoviesbyType.getWat($scope.user.userId);
        }else{
            $scope.movies = getMoviesbyType.getRat($scope.user.userId);
        }
    })

;
