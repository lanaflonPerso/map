describe('AppCtrl >',function(){
  describe('isCurrentUrl',function(){
    var AppCtrl,$location,$scope,userExists;

    beforeEach(module('ngBoilerplate','ngBoilerplate.auth'));

    beforeEach(inject(function($controller,_$location_,$rootScope,_userExists_) {
      $location=_$location_;
      $scope=$rootScope.$new();
      userExists=_userExists_;
      AppCtrl=$controller('AppCtrl',{$location:$location,$scope:$scope});
    }));

    //Test 0.1
    it('should init the Controller',inject(function(){
      expect(AppCtrl).toBeTruthy();
    }));

    //Test 0.2
    it('should init the userExists factory',inject(function(){
        expect(userExists).toBeTruthy();
    }));

      it('should check 2+2', function () {
          expect(4).toBe(2+2);
      });
  });
});
