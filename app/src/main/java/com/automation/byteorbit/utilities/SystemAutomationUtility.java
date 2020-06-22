package com.automation.byteorbit.utilities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.RemoteException;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;

import com.orhanobut.logger.Logger;

public class SystemAutomationUtility {

    private static UiDevice uiDevice;

    public static void setUiDevice(UiDevice uiDevice) {
        SystemAutomationUtility.uiDevice = uiDevice;
    }

    public static boolean runApplication(String autPackage) {
        try {
            uiDevice.pressHome();
            uiDevice.wait(Until.hasObject(By.pkg(uiDevice.getCurrentPackageName()).depth(0)), 1000);
            Context context = ApplicationProvider.getApplicationContext();
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(autPackage);
            context.startActivity(intent);
            uiDevice.wait(Until.hasObject(By.pkg(autPackage).depth(0)), 1000);
            return true;
        } catch (Exception exception) {
            Logger.e("Failed to launch application with package name -> %s", autPackage);
            return false;
        }
    }

    public static boolean runapp(String autPackage) {
        try {
            Context context = ApplicationProvider.getApplicationContext();
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(autPackage);
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            } else return false;
            context.startActivity(intent);
            uiDevice.wait(Until.hasObject(By.pkg(autPackage)), 1000);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static void closeApps() {
        try {
            uiDevice.pressRecentApps();
            if (!NativeAutomationUtility.waitForUiObject(By.res("com.sec.android.app.launcher:id/empty_text"), 3)) {
                NativeAutomationUtility.clickUiObject(By.textContains("Close all"));
            }
        } catch (RemoteException ignored) {

        }
    }

    public static void clickByCoOrdinates(int x, int y) {
        uiDevice.click(x, y);
    }

    public static void swipeOnScreenWithCoOrdinates(int startX, int endX, int startY, int endY, int steps) {
        uiDevice.swipe(startX, startY, endX, endY, steps);
    }

    public static boolean wakeScreenUp() {
        try {
            uiDevice.wakeUp();
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }


    public static boolean isScreenOn() {
        try {
            return uiDevice.isScreenOn();
        } catch (RemoteException e) {
            return false;
        }
    }

    public static void navigateToHomeScreen() {
        uiDevice.pressHome();
    }

    public static boolean disableRotation() {
        try {
            uiDevice.freezeRotation();
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean sendNumber(int number) {
        if (number > 9) {
            return false;
        } else {
            uiDevice.pressKeyCode(number + 7);
            return true;
        }
    }

    public static boolean enableRotation() {
        try {
            uiDevice.unfreezeRotation();
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void simulateBack() {
        uiDevice.pressBack();
    }

    public static void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Point ScreenSize() {
        return uiDevice.getDisplaySizeDp();
    }

}
