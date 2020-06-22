package com.automation.byteorbit.test.register;

import com.automation.byteorbit.utilities.NativeAutomationUtility;
import com.automation.byteorbit.utilities.SystemAutomationUtility;

public class RegisterPage {
    private RegisterObject registerObject;

    public RegisterPage() {
        this.registerObject = new RegisterObject();
    }

    public String registerUser(String surname, String email, String password, String confPassword, boolean dismiss) {
        if (!NativeAutomationUtility.clickUiObject(registerObject.surnameTextField())) {
            return "Failed to click surname field";
        }

        if (!NativeAutomationUtility.enterTextInUiObject(registerObject.surnameTextField(), surname, true)) {
            return "Failed to enter text into surname field with value '" + surname + "'";
        }

        if (!NativeAutomationUtility.enterTextInUiObject(registerObject.emailTextField(), email, true)) {
            return "Failed to enter text into email field with value '" + email + "'";
        }

        if (!NativeAutomationUtility.enterTextInUiObject(registerObject.passwordField(), password, true)) {
            return "Failed to enter text into password field with value '" + password + "'";
        }

        if (!NativeAutomationUtility.enterTextInUiObject(registerObject.confirmPasswordField(), confPassword, true)) {
            return "Failed to enter text into confirm password field with value '" + confPassword + "'";
        }

        if (dismiss) {
            SystemAutomationUtility.simulateBack();
        }

        if (!NativeAutomationUtility.clickUiObject(registerObject.registerButton(),5)) {
            return "Failed to click register button";
        }

        return null;
    }

    public String validateRegistrationScreen(){
        if(!NativeAutomationUtility.waitForUiObject(registerObject.registerLabel())){
            return "Failed to validate registration screen is open";
        }

        return null;
    }
}
