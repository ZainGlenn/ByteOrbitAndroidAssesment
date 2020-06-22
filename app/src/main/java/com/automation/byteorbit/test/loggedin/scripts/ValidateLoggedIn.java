package com.automation.byteorbit.test.loggedin.scripts;

import com.automation.byteorbit.test.loggedin.LoggedInPage;

public class ValidateLoggedIn {
    private LoggedInPage loggedInPage;

    public ValidateLoggedIn() {
        loggedInPage = new LoggedInPage();
    }

    public String runTest() {
        return loggedInPage.validateUserIsLoggedIn();
    }
}
