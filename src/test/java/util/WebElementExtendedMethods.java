package util;

import org.openqa.selenium.By;

public class WebElementExtendedMethods {
    public static Boolean isElementChecked(By locator) {
        return SingltonDriver.waitAndFind(locator).isSelected();
    }

    public static String getElementValue(By locator){
        return SingltonDriver.waitAndFind(locator).getAttribute("value");
    }
}
