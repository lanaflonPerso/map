angular.module( 'ngBoilerplate', [
  'templates-app',
  'templates-common',
  'ngBoilerplate.home',
  'ngBoilerplate.about',
  'ngBoilerplate.auth',
  'ngBoilerplate.account',
  'ngBoilerplate.movie',
  'ngBoilerplate.collection',
  'ngBoilerplate.search',
  'ngBoilerplate.admin',
  'ui.router'
])

.config( function myAppConfig ( $stateProvider, $urlRouterProvider ) {
  $urlRouterProvider.otherwise( '/home' );
})

.run( function run () {
})

.controller( 'AppCtrl', function AppCtrl ( $scope,$location,sessionService,$state ) {
  $scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){
    if ( angular.isDefined( toState.data.pageTitle ) ) {
      $scope.pageTitle = toState.data.pageTitle + ' | Movie Recommendation Portal';
      $scope.isLoggedIn = sessionService.isLoggedIn;
      $scope.isAdmin = sessionService.isAdmin;
    }
  });
  $scope.search = function(event){
      if(event.keyCode == 13){
        $state.go('searchMovie',{key:$scope.search.data});
      }
  };
  $scope.logout = function(){
      sessionService.logout();
      var notification = document.querySelector('.mdl-js-snackbar');
      notification.MaterialSnackbar.showSnackbar({message: 'Logout successful!'});
  };
})
;
