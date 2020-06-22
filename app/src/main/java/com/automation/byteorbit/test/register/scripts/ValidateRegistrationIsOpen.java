package com.automation.byteorbit.test.register.scripts;

import com.automation.byteorbit.test.register.RegisterPage;

public class ValidateRegistrationIsOpen {
    private RegisterPage registerPage;

    public ValidateRegistrationIsOpen() {
        registerPage = new RegisterPage();
    }

    public String runTest() {
        return registerPage.validateRegistrationScreen();
    }
}
