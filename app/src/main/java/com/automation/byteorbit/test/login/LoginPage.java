package com.automation.byteorbit.test.login;

import com.automation.byteorbit.utilities.Utils;
import com.automation.byteorbit.utilities.NativeAutomationUtility;
import com.automation.byteorbit.utilities.SystemAutomationUtility;

public class LoginPage {
    private LoginObject loginObject;

    public LoginPage() {
        this.loginObject = new LoginObject();
    }

    public String startApplication() {
        //launches application
        if (!SystemAutomationUtility.runApplication(Utils.getApplicationPackage())) {
            return "Failed to start application with package -> " + Utils.getApplicationPackage();
        }

        return null;
    }

    public String validateLoginScreen() {
        if (!NativeAutomationUtility.waitForUiObject(loginObject.loginToolbar())) {
            return "Failed validate toolbar of byte orbit application is present";
        }

        if (!NativeAutomationUtility.waitForUiObject(loginObject.loginLabel())) {
            return "Failed to validate correct application is launched";
        }

        return null;
    }

    public String logUserIn(String email, String password) {
        if (!NativeAutomationUtility.enterTextInUiObject(loginObject.emailTextField(), email, true)) {
            return "Failed to enter email into email field";
        }

        if (!NativeAutomationUtility.enterTextInUiObject(loginObject.passwordTextField(), password, true)) {
            return "Failed to enter password into password field";
        }

        //click login
        if (!NativeAutomationUtility.clickUiObject(loginObject.loginButton())) {
            return "Failed to click login button";
        }

        return null;
    }

    public String validateUserNotLoggedIn(){
        //click login
        if (!NativeAutomationUtility.waitForUiObject(loginObject.loginButton())) {
            return "Failed to click login button";
        }
        return null;
    }

    public String openRegisterScreen() {
        if (!NativeAutomationUtility.clickUiObject(loginObject.registerLink())) {
            return "Failed to click register button";
        }

        return null;
    }
}
