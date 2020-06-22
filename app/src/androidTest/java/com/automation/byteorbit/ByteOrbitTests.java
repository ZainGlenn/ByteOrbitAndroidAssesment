package com.automation.byteorbit;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.automation.byteorbit.test.loggedin.scripts.ValidateLoggedIn;
import com.automation.byteorbit.test.login.scripts.LaunchApplication;
import com.automation.byteorbit.test.login.scripts.LoginToApplication;
import com.automation.byteorbit.test.login.scripts.OpenRegistrationScreen;
import com.automation.byteorbit.test.login.scripts.ValidateUserNotLoggedIn;
import com.automation.byteorbit.test.register.scripts.RegisterUserWithKeyboardDismissed;
import com.automation.byteorbit.test.register.scripts.RegisterUserWithKeyboardShown;
import com.automation.byteorbit.test.register.scripts.ValidateRegistrationIsOpen;
import com.automation.byteorbit.utilities.SystemAutomationUtility;
import com.automation.byteorbit.utilities.Utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class ByteOrbitTests {


    @Before
    public void setUp() {
        Utils.initAutomation();
        Utils.setApplicationPackage("com.byteorbit.qaassignment1");
    }

    @Test
    public void testLoginSuccess() {
        LaunchApplication launchApplication = new LaunchApplication();
        String result = launchApplication.runTest();
        if (!Utils.resultChecker(result)) {
            Assert.fail("LaunchApplication -> " + result);
        }

        String email = "user@byteorbit.com";
        String password = "Password123!";

        LoginToApplication loginToApplication = new LoginToApplication();
        result = loginToApplication.runTest(email, password);
        if (!Utils.resultChecker(result)) {
            Assert.fail("LoginToApplication ->" + result);
        }

        ValidateLoggedIn validateLoggedIn = new ValidateLoggedIn();
        result = validateLoggedIn.runTest();
        if (!Utils.resultChecker(result)) {
            Assert.fail("ValidateLoggedIn ->" + result);
        }
    }

    @Test
    public void testLoginInvalidCredentials() {
        LaunchApplication launchApplication = new LaunchApplication();
        String result = launchApplication.runTest();
        if (!Utils.resultChecker(result)) {
            Assert.fail("ValidateLoggedIn -> " + result);
        }

        String email = "invalid@byteorbit.com";
        String password = "Password123!";

        LoginToApplication loginToApplication = new LoginToApplication();
        result = loginToApplication.runTest(email, password);
        if (!Utils.resultChecker(result)) {
            Assert.fail("LoginToApplication ->" + result);
        }

        ValidateUserNotLoggedIn validateUserNotLoggedIn = new ValidateUserNotLoggedIn();
        result = validateUserNotLoggedIn.runTest();
        if (!Utils.resultChecker(result)) {
            Assert.fail("ValidateUserNotLoggedIn ->" + result);
        }

    }

    @Test
    public void testRegisterUserWithKeyboardVisible() {
        LaunchApplication launchApplication = new LaunchApplication();
        String result = launchApplication.runTest();
        if (!Utils.resultChecker(result)) {
            Assert.fail("ValidateLoggedIn -> " + result);
        }

        OpenRegistrationScreen openRegistrationScreen = new OpenRegistrationScreen();
        result = openRegistrationScreen.runTest();
        if (!Utils.resultChecker(result)) {
            Assert.fail("ValidateLoggedIn -> " + result);
        }

        ValidateRegistrationIsOpen validateRegistrationIsOpen = new ValidateRegistrationIsOpen();
        result = validateRegistrationIsOpen.runTest();
        if (!Utils.resultChecker(result)) {
            Assert.fail("ValidateRegistrationIsOpen -> " + result);
        }

        String surname = "Glenn";
        String email = "zainglenn1995@gmail.com";
        String password = "123QWEasd@";
        String confirmPassword = "123QWEasd@";

        RegisterUserWithKeyboardShown registerUserWithKeyboardShown = new RegisterUserWithKeyboardShown();
        result = registerUserWithKeyboardShown.runTest(surname, email, password, confirmPassword);
        if (!Utils.resultChecker(result)) {
            Assert.fail("RegisterUserWithKeyboardShown ->" + result);
        }

    }

    @Test
    public void testRegisterUserWithKeyboardDismissed() {
        LaunchApplication launchApplication = new LaunchApplication();
        String result = launchApplication.runTest();
        if (!Utils.resultChecker(result)) {
            Assert.fail("ValidateLoggedIn -> " + result);
        }

        OpenRegistrationScreen openRegistrationScreen = new OpenRegistrationScreen();
        result = openRegistrationScreen.runTest();
        if (!Utils.resultChecker(result)) {
            Assert.fail("OpenRegistrationScreen -> " + result);
        }

        ValidateRegistrationIsOpen validateRegistrationIsOpen = new ValidateRegistrationIsOpen();
        result = validateRegistrationIsOpen.runTest();
        if (!Utils.resultChecker(result)) {
            Assert.fail("ValidateRegistrationIsOpen -> " + result);
        }

        String surname = "Doe";
        String email = "zainglenn1995@gmail.com";
        String password = "123QWEasd@";
        String confirmPassword = "123QWEasd@";

        RegisterUserWithKeyboardDismissed registerUserWithKeyboardDismissed = new RegisterUserWithKeyboardDismissed();
        result = registerUserWithKeyboardDismissed.runTest(surname, email, password, confirmPassword);
        if (!Utils.resultChecker(result)) {
            Assert.fail("RegisterUserWithKeyboardShown ->" + result);
        }

        //login with validated user if possible
    }

    @After
    public void tearDown() throws Exception {
        SystemAutomationUtility.closeApps();
    }
}
