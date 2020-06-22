package com.automation.byteorbit.test.register;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;

import com.automation.byteorbit.utilities.Utils;

public class RegisterObject {

    public BySelector registerToolbar() {
        return By.res(addPackageToRes("toolbar"));
    }

    public BySelector registerLabel() {
        return By.text("Register");
    }

    public BySelector registerBackButton() {
        return By.desc("Navigate up");
    }

    public BySelector surnameTextField() {
        return By.res(addPackageToRes("surname"));
    }

    public BySelector emailTextField() {
        return By.res(addPackageToRes("email"));
    }

    public BySelector passwordField() {
        return By.res(addPackageToRes("password"));
    }

    public BySelector confirmPasswordField() {
        return By.res(addPackageToRes("passConf"));
    }

    public BySelector registerButton() {
        return By.res(addPackageToRes("register"));
    }


    private String addPackageToRes(String id) {
        return String.format("%s:id/%s", Utils.getApplicationPackage(), id);
    }
}
