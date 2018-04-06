describe('Home Section >',function(){
    beforeEach(module('ngBoilerplate'));

    var scope = {},HomeCtrl,$httpBackend,$http,getMovie,$q,deferred,result;

    beforeEach(inject(function($controller,$rootScope,_$httpBackend_,_$http_,_getMovie_,_$q_) {
        scope = $rootScope.$new();
        $httpBackend = _$httpBackend_;
        $http=_$http_;
        getMovie=_getMovie_;
        $q=_$q_;
        deferred = $q.defer();
        result = {};
        HomeCtrl=$controller('HomeCtrl',{$scope:scope});
    }));

    //Test 0
    it('should init the Controller', function () {
        expect(HomeCtrl).toBeTruthy();
    });

});