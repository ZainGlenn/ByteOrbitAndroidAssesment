package com.automation.byteorbit.test.login.scripts;

import com.automation.byteorbit.utilities.Utils;
import com.automation.byteorbit.test.login.LoginPage;

public class LoginToApplication {

    private LoginPage loginPage;

    public LoginToApplication() {
        loginPage = new LoginPage();
    }

    public String runTest(String username, String password) {
        String result = loginPage.validateLoginScreen();
        if (!Utils.resultChecker(result)) {
            return result;
        }

        result = loginPage.logUserIn(username, password);
        if (!Utils.resultChecker(result)) {
            return result;
        }

        return null;
    }
}
