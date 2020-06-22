package com.automation.byteorbit.test.register.scripts;

import com.automation.byteorbit.test.register.RegisterPage;

public class RegisterUserWithKeyboardDismissed {
    private RegisterPage registerPage;

    public RegisterUserWithKeyboardDismissed() {
        registerPage = new RegisterPage();
    }

    public String runTest(String surname,String email,String password, String confirmPassword) {
        return registerPage.registerUser(surname,email,password,confirmPassword,true);
    }
}
