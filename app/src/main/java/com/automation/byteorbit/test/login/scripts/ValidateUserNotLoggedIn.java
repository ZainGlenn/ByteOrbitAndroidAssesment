package com.automation.byteorbit.test.login.scripts;

import com.automation.byteorbit.test.login.LoginPage;

public class ValidateUserNotLoggedIn {
    private LoginPage loginPage;

    public ValidateUserNotLoggedIn() {
        this.loginPage = new LoginPage();
    }

    public String runTest() {
        return loginPage.validateUserNotLoggedIn();
    }
}
