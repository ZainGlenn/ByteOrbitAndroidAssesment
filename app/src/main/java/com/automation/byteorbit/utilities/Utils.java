package com.automation.byteorbit.utilities;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class Utils {
    private static String applicationPackage;

    public static boolean resultChecker(String result) {
        return result == null;
    }

    public static void initAutomation() {
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        NativeAutomationUtility.setUiDevice(uiDevice);
        SystemAutomationUtility.setUiDevice(uiDevice);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static void setApplicationPackage(String applicationPackage) {
        Utils.applicationPackage = applicationPackage;
    }

    public static String getApplicationPackage() {
        return applicationPackage;
    }
}
