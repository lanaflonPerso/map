angular.module( 'ngBoilerplate.collection', ['ui.router'])

    .config(function config( $stateProvider ) {
        $stateProvider.state( 'collection', {
            url: '/collection',
            views: {
                "main": {
                    controller: 'CollectionCtrl',
                    templateUrl: 'collection/collection.tpl.html'
                }
            },
            data:{ pageTitle: 'Collection' }
        });
    })

    .controller( 'CollectionCtrl', function AboutCtrl( $scope ) {

    })

;
