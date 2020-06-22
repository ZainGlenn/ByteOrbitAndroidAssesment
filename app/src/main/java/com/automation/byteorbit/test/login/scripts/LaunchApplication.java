package com.automation.byteorbit.test.login.scripts;

import com.automation.byteorbit.test.login.LoginPage;

public class LaunchApplication {
    private LoginPage loginPage;

    public LaunchApplication() {
        loginPage = new LoginPage();
    }

    public String runTest() {
        return loginPage.startApplication();
    }
}
