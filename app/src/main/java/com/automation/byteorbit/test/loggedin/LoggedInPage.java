package com.automation.byteorbit.test.loggedin;

import com.automation.byteorbit.utilities.NativeAutomationUtility;

public class LoggedInPage {

    private LoggedInObject loggedInObject;

    public LoggedInPage() {
        this.loggedInObject = new LoggedInObject();
    }

    public String validateUserIsLoggedIn() {
        if (!NativeAutomationUtility.waitForUiObject(loggedInObject.loggedToolbar())) {
            return "Failed to validate logged in toolbar is present";
        }

        if (!NativeAutomationUtility.waitForUiObject(loggedInObject.toolbarLoggedInMessage())) {
            return "Failed to validate logged in message on toolbar is present";
        }

        if (!NativeAutomationUtility.waitForUiObject(loggedInObject.welcomeMessageLabel())) {
            return "Failed to validate user is logged in with welcome message visible";
        }

        return null;
    }

}
