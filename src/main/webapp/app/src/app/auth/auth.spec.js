describe('Auth Section >',function(){
    beforeEach(module('ngBoilerplate'));

    var rScope = {},lScope = {},LoginCtrl,RegisterCtrl,sessionService,userExists,$httpBackend;

    beforeEach(inject(function($controller,$rootScope,_sessionService_,_userExists_,_$httpBackend_) {
        rScope=$rootScope.$new();
        lScope=$rootScope.$new();
        sessionService=_sessionService_;
        userExists=_userExists_;
        $httpBackend=_$httpBackend_;
        LoginCtrl=$controller('LoginCtrl',{$scope:lScope});
        RegisterCtrl=$controller('RegisterCtrl',{$scope:rScope});
    }));

    afterEach(function () {
        // $httpBackend.verifyNoPendingTasks();
    });

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
        // //Test 1.2
        // it('should Login a dummy user', function () {
        //     var user = {emailId: 'sachin@gmail.com',password: 'sachin'};
        //     //Login
        //     spyOn(sessionService, 'login');
        //     sessionService.login(user);
        //     expect(sessionService.login).toHaveBeenCalled();
        //
        //     //IsLoggedIn
        //     spyOn(sessionService, 'isLoggedIn');
        //     var isLoggedIn = sessionService.isLoggedIn();
        //     expect(isLoggedIn).toBeTruthy();
        // });
        // //Test 1.3
        // it('should Logout the dummy user', function () {
        //     var user = {emailId: 'sachin@gmail.com',password: 'sachin'};
        //     //IsLoggedIn
        //     spyOn(sessionService, 'isLoggedIn');
        //     var isLoggedIn = sessionService.isLoggedIn();
        //     expect(isLoggedIn).toBeTruthy();
        //
        //     //Logout
        //     spyOn(sessionService, 'logout');
        //     sessionService.logout();
        //     expect(isLoggedIn).toBeFalsy();
        // });
        // //Test 1.4
        // it('check if user exists', function () {
        //     var user = {emailId: 'sachin@gmail.com',password: 'sachin'};
        //     var userName = user.emailId.split('@')[0];
        //
        //     $httpBackend.when('GET','/user/email/'+userName).respond(user);
        //     var new_user = userExists.get({email: userName});
        //
        //     expect(new_user).toBe(user);
        //     $httpBackend.flush();
        // });
    });
});