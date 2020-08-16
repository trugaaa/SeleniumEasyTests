package util;

import org.openqa.selenium.By;

public class WebElementExtendedMethods {
    public static Boolean isElementChecked(By locator) {
        return SingletonDriver.waitAndFind(locator).isSelected();
    }

    public static String getElementValue(By locator) {
        return SingletonDriver.waitAndFind(locator).getAttribute("value");
    }

    public static String[] getCSSBorderColor(By locator) {
        return SingletonDriver.ajaxElementWait(locator).getCssValue("border-bottom-color").
                replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))", "").split(",");
    }

    public static Boolean isBorderColorRed(By selector) {
        String[] rgb = getCSSBorderColor(selector);
        return Integer.parseInt(rgb[0]) > 225 && Integer.parseInt(rgb[1]) < 20 && Integer.parseInt(rgb[2]) < 20;
    }
}
