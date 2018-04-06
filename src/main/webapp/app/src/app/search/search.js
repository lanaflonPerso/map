angular.module( 'ngBoilerplate.search', ['ui.router','ngBoilerplate.auth'])

    .config(function config( $stateProvider ) {
        $stateProvider.state( 'searchMovie', {
            url: '/search?key',
            views: {
                "main": {
                    controller: 'SearchCtrl',
                    templateUrl: 'search/search.tpl.html'
                }
            },
            data:{ pageTitle: 'Search' }
        });
    })

    .factory('getSearchFactory', function ($resource) {
        var genre = {};
        genre.getSearch = function(key){
            return $resource('/search-movie/'+key).query();
        };
        return genre;
    })

    .factory('postSearch',function ($resource) {
        var search = {};
        search.save = function (search,success,failure) {
            var Search = $resource('/search/new');
            Search.save({},search);
        };
        return search;
    })

    .controller( 'SearchCtrl', function SearchCtrl( $scope,sessionService,$stateParams,getSearchFactory,postSearch ) {
        $scope.user = sessionService.getLoggedInUser();
        $scope.keyword = $stateParams.key;
        $scope.movies = getSearchFactory.getSearch($stateParams.key);
        postSearch.save({userid: $scope.user.userId,searchWord:$scope.keyword});
    })
;