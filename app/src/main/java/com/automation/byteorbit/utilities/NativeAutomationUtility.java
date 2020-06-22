package com.automation.byteorbit.utilities;

import android.graphics.Rect;

import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import java.util.List;

public class NativeAutomationUtility {
    private static UiDevice uiDevice;
    private static UiObject2 uiObject2;
    private static Long TIMEOUT = 60000L;

    public static void setUiDevice(UiDevice uiDevice) {
        NativeAutomationUtility.uiDevice = uiDevice;
    }

    public static boolean clickUiObject(BySelector selector) {
        return clickUiObject(selector, TIMEOUT);
    }

    public static UiObject2 getUniqueObject(BySelector unique, BySelector search) {
        UiObject2 uiObject2 = checkIfUiObjectIsAvailable(unique);
        if (uiObject2 != null) {
            return uiObject2.getParent().findObject(search);
        }
        return null;

    }

    public static boolean isUiObjectSelected(BySelector selector) {
        long to = 3000L;
        uiObject2 = checkIfUiObjectIsAvailable(selector, to);
        if (uiObject2 != null) {
            return uiObject2.isSelected();
        }
        return false;
    }

    public static boolean clickUiObject(BySelector selector, long timeout) {
        uiObject2 = checkIfUiObjectIsAvailable(selector, timeout);
        if (uiObject2 != null) {
            uiObject2.click();
            return true;
        }
        return false;
    }


    public static boolean doubleClickWhenNotClickable(BySelector selector) {
        uiObject2 = checkIfUiObjectIsAvailable(selector);

        if (uiObject2 != null) {
            uiObject2.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            uiObject2.click();
            return true;
        }
        return false;
    }

    public static boolean clickUiObjectLong(BySelector selector, int secondsToClick) {
        uiObject2 = checkIfUiObjectIsAvailable(selector);
        if (uiObject2 != null) {
            uiObject2.click((secondsToClick * 1000));
            return true;
        }
        return false;
    }

    public static boolean waitForUiObject(BySelector selector) {
        uiObject2 = checkIfUiObjectIsAvailable(selector);
        return uiObject2 != null;
    }

    public static boolean waitForUiObject(BySelector selector, long timeout) {
        timeout = timeout * 1000;
        uiObject2 = checkIfUiObjectIsAvailable(selector, timeout);
        return uiObject2 != null;
    }

    public static boolean enterTextInUiObject(BySelector selector, String text, boolean shouldClear) {
        uiObject2 = checkIfUiObjectIsAvailable(selector);
        if (uiObject2 != null) {
            if (shouldClear) {
                uiObject2.clear();
                uiObject2.setText(text);
            } else {
                uiObject2.setText(text);

            }
            return true;
        } else {
            return false;
        }
    }

    public static String getTextOnScreen(BySelector selector, boolean isContentDesc) {
        uiObject2 = checkIfUiObjectIsAvailable(selector);
        if (uiObject2 != null) {
            if (!isContentDesc) {
                return uiObject2.getText();
            } else {
                return uiObject2.getContentDescription();
            }
        }
        return null;
    }

    public static boolean validateTextOnScreen(BySelector selector, String validationText, boolean isContentDesc) {
        String onScreenText = getTextOnScreen(selector, isContentDesc);

        if (onScreenText == null) {
            return false;
        }

        return validationText.equals(onScreenText);
    }

    public static boolean scrollToStart(int maxSwipes) {
        try {
            new UiScrollable(new UiSelector().scrollable(true)).flingToBeginning(maxSwipes);
            return true;
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean scrollToUiObjectWithText(UiSelector selector) {
        try {
            UiScrollable scrollable = new UiScrollable(new UiSelector().scrollable(true));
            UiObject object = uiDevice.findObject(selector);
            scrollable.scrollIntoView(object);
            scrollable.ensureFullyVisible(object);
            return true;
        } catch (UiObjectNotFoundException exception) {
            return false;
        }
    }

    public static boolean scrollToUiObjectWithText(String text) {
        try {
            UiScrollable scrollable = new UiScrollable(new UiSelector().scrollable(true));
            scrollable.scrollTextIntoView(text);
            return true;
        } catch (UiObjectNotFoundException exception) {
            return false;
        }
    }

    public static boolean waitForObjectToNotBePresent(BySelector selector) {
        return waitForObjectToNotBePresent(selector, TIMEOUT);
    }

    public static boolean waitForObjectToNotBePresent(BySelector selector, long timeout) {
        boolean isVisible = uiDevice.hasObject(selector);
        long timeOfExecution = System.currentTimeMillis();
        long endTime = timeOfExecution + timeout;

        if (isVisible) {
            while (timeOfExecution < endTime) {
                timeOfExecution = System.currentTimeMillis();
                isVisible = uiDevice.hasObject(selector);
                if (!isVisible) {
                    return true;
                }
            }
        } else return true;

        return false;
    }


    private static UiObject2 checkIfUiObjectIsAvailable(BySelector selector) {
        return checkIfUiObjectIsAvailable(selector, TIMEOUT);
    }

    private static UiObject2 checkIfUiObjectIsAvailable(BySelector selector, long timeout) {
        if (uiDevice.hasObject(selector)) {
            return uiDevice.findObject(selector);
        } else {
            return pollUntilObjectIsReady(selector, timeout);
        }
    }

    private static UiObject2 pollUntilObjectIsReady(BySelector selector, long polltime) {
        UiObject2 object2;
        long startTime = System.currentTimeMillis();
        long endTime = startTime + polltime;
        while (startTime < endTime) {
            startTime = System.currentTimeMillis();
            object2 = uiDevice.wait(Until.findObject(selector), 100);
            if (object2 != null) {
                return object2;
            } else {
                SystemAutomationUtility.sleep(200);
            }
        }
        return null;
    }

    public static int checkSize(BySelector selector, long timeout) {
        UiObject2 object2 = checkIfUiObjectIsAvailable(selector, timeout);
        if (object2 == null) {
            return 0;
        }

        List<UiObject2> uiObject2s = object2.getChildren();
        return uiObject2s.size();
    }

    public static boolean hasChidren(BySelector selector) {
        UiObject2 object = uiDevice.findObject(selector);
        List<UiObject2> objects = object.getChildren();
        if (objects.size() > 0) {
            return true;
        }
        return false;
    }

    public static boolean clickFirstItem(BySelector selector) {
        UiObject2 object = checkIfUiObjectIsAvailable(selector);
        List<UiObject2> objects = object.getChildren();
        UiObject2 o = objects.get(0);
        o.click();
        return true;
    }

    public static boolean clickLatestListItem(BySelector selector) {
        UiObject2 object = checkIfUiObjectIsAvailable(selector);
        if (object != null) {
            List<UiObject2> objects = uiDevice.findObjects(selector);
            UiObject2 o = objects.get(0);
            o.click();
            return true;
        }
        return false;
    }

    private static UiObject2 getLatestInList(BySelector selector) {
        UiObject2 object = checkIfUiObjectIsAvailable(selector);
        if (object != null) {
            List<UiObject2> objects = object.getChildren();
            return objects.get(0);
        }
        return null;
    }

    public static boolean validateChildInLatestListItem(BySelector listSelector, BySelector childSelector) {
        UiObject2 object2 = getLatestInList(listSelector);
        if (object2 != null) {
            return object2.hasObject(childSelector);
        }
        return false;
    }

    public static boolean validateTextInLatestListItem(BySelector listSelector, BySelector childSelector, String text) {
        UiObject2 object2 = getLatestInList(listSelector);
        if (object2 != null) {
            String actualText = object2.findObject(childSelector).getText();
            return text.equals(actualText);
        }
        return false;
    }

    public static boolean validateContainsTextInLatestListItem(BySelector listSelector, BySelector childSelector, String text) {
        UiObject2 object2 = getLatestInList(listSelector);
        if (object2 != null) {
            UiObject2 childObject = object2.findObject(childSelector);
            if (childObject == null) {
                return false;
            }
            String actualText = childObject.getText();
            return actualText.contains(text);
        }
        return false;
    }

    public static boolean clickObjectinLatestListItem(BySelector listSelector, BySelector childSelector) {
        UiObject2 object2 = getLatestInList(listSelector);
        if (object2 != null) {
            object2.findObject(childSelector).click();
            return true;
        }
        return false;
    }


    public static boolean clickBottomOfUiObjectBounds(BySelector selector) {
        UiObject2 object2 = checkIfUiObjectIsAvailable(selector);
        if (object2 != null) {
            Rect rect = object2.getVisibleBounds();
            int x = (rect.left + rect.right) / 2;
            int y = rect.bottom - 30;
            SystemAutomationUtility.clickByCoOrdinates(x, y);
            return true;
        }
        return false;
    }


    public static boolean clickIncidentStatusButton(BySelector selector) {
        UiObject2 object = checkIfUiObjectIsAvailable(selector);
        if (object != null) {
            List<UiObject2> objects = object.getChildren();
            UiObject2 firstItem = objects.get(0);
            List<UiObject2> objectsFirst = firstItem.getChildren();
            UiObject2 objectClick = objectsFirst.get(2);
            objectClick.click();
            return true;
        }
        return false;
    }

    public static boolean clickObjectByFindingCoOrdinates(BySelector selector) {
        UiObject2 object2 = checkIfUiObjectIsAvailable(selector);
        if (object2 != null) {
            Rect rect = object2.getVisibleBounds();
            int x = (rect.left + rect.right) / 2;
            int y = (rect.bottom + rect.top) / 2;
            SystemAutomationUtility.clickByCoOrdinates(x, y);
            return NativeAutomationUtility.waitForObjectToNotBePresent(selector, 2500L);
        }
        return false;
    }

    public static boolean clickAndHoldFirstItemInRecyclerView(BySelector selector, long timeout) {
        UiObject2 object2 = checkIfUiObjectIsAvailable(selector, timeout);
        if (object2 != null) {
            List<UiObject2> uiObject2s = object2.getChildren();
            if (uiObject2s.get(0).isLongClickable()) {
                uiObject2s.get(0).click(2000L);
                return true;
            }
        }

        return false;
    }

    public static boolean clickAndHoldFirstItemInRecyclerView(BySelector selector) {
        return clickAndHoldFirstItemInRecyclerView(selector, TIMEOUT);
    }

    public static int getChildrenCount(BySelector selector) {
        UiObject2 object = uiDevice.findObject(selector);
        List<UiObject2> objects = object.getChildren();
        return objects.size();
    }


}
