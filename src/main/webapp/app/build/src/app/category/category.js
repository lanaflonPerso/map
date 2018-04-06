angular.module( 'ngBoilerplate.category', ['ui.router','ngResource'])

    .config(function config( $stateProvider ) {
        $stateProvider.state( 'category', {
            url: '/category',
            views: {
                "main": {
                    controller: 'CategoryCtrl',
                    templateUrl: 'category/category.tpl.html'
                }
            },
            data:{ pageTitle: 'Category' }
        });
    })

    .controller( 'CategoryCtrl', function CategoryCtrl($scope,sessionService) {
        $scope.isLoggedIn = sessionService.isLoggedIn();
        $scope.isAdmin = sessionService.isAdmin();
        console.log($scope.isLoggedIn);
        console.log($scope.isAdmin);
    })
;