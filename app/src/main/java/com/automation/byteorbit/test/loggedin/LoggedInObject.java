package com.automation.byteorbit.test.loggedin;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;

import com.automation.byteorbit.utilities.Utils;

public class LoggedInObject {

    public BySelector loggedToolbar() {
        return By.res(addPackageToRes("toolbar"));
    }


    public BySelector welcomeMessageLabel() {
        return By.textContains("LOGGED IN! \n WELCOME");
    }

    public BySelector toolbarLoggedInMessage() {
        return By.text("Logged In");
    }

    private String addPackageToRes(String id) {
        return String.format("%s:id/%s", Utils.getApplicationPackage(), id);
    }


}
