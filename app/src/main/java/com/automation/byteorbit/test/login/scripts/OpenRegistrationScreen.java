package com.automation.byteorbit.test.login.scripts;

import com.automation.byteorbit.test.login.LoginPage;

public class OpenRegistrationScreen {
    private LoginPage loginPage;
    public OpenRegistrationScreen() {
        this.loginPage = new LoginPage();
    }
    public String runTest(){
        return loginPage.openRegisterScreen();
    }
}
