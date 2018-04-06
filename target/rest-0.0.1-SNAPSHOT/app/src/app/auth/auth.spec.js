describe('Auth Section >',function(){
    beforeEach(module('ngBoilerplate'));

    var rScope = {},lScope = {},LoginCtrl,RegisterCtrl,sessionService;

    beforeEach(inject(function($controller,$rootScope,_sessionService_) {
        rScope=$rootScope.$new();
        lScope=$rootScope.$new();
        sessionService=_sessionService_;
        LoginCtrl=$controller('LoginCtrl',{$scope:lScope});
        RegisterCtrl=$controller('RegisterCtrl',{$scope:rScope});
    }));

    //Test 0
    it('should init the Controllers', function () {
        expect(LoginCtrl).toBeTruthy();
        expect(RegisterCtrl).toBeTruthy();
    });

    describe('Register Controller >',function () {
        //Test 1.1
        it('should init sessionService', function () {
            expect(sessionService).toBeTruthy();
        });
        //Test 1.2
        it('should Login/Logout a dummy user', function () {
            var user = {emailId: 'sachin@gmail.com',password: 'sachin'};
            //Login
            spyOn(sessionService, 'login');
            sessionService.login(user);
            expect(sessionService.login).toHaveBeenCalled();
            //IsLoggedIn
            spyOn(sessionService, 'isLoggedIn');
            sessionService.isLoggedIn();
            expect(sessionService.isLoggedIn).toBeTruthy();
            //Logout user
            spyOn(sessionService, 'logout');
            sessionService.logout();
            expect(sessionService.logout).toBeTruthy();
        });
    });
});