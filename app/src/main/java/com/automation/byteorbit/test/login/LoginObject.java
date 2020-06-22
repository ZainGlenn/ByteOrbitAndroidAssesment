package com.automation.byteorbit.test.login;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;

import com.automation.byteorbit.utilities.Utils;

public class LoginObject {

    public BySelector loginToolbar() {
        return By.res(addPackageToRes("toolbar"));
    }

    public BySelector loginLabel() {
        return By.text("Login");
    }

    public BySelector emailTextField() {
        return By.res(addPackageToRes("email"));
    }

    public BySelector passwordTextField() {
        return By.res(addPackageToRes("password"));
    }

    public BySelector loginButton() {
        return By.text("Login");
    }

    public BySelector registerLink() {
        return By.text("Register");
    }

    private String addPackageToRes(String id) {
        return String.format("%s:id/%s", Utils.getApplicationPackage(), id);
    }


}
