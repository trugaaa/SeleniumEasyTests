package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebElementExtendedMethods {
    public static Boolean isElementChecked(By locator) {
        return SingletonDriver.waitAndFindElement(locator).isSelected();
    }

    public static String getElementValue(By locator) {
        return SingletonDriver.waitAndFindElement(locator).getAttribute("value");
    }

    public static String[] getCSSBorderColor(By locator) {
        return SingletonDriver.ajaxElementWait(locator).getCssValue("border-bottom-color").
                replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))", "").split(",");
    }

    public static Boolean isBorderColorRed(By selector) {
        String[] rgb = getCSSBorderColor(selector);
        return Integer.parseInt(rgb[0]) > 225 && Integer.parseInt(rgb[1]) < 20 && Integer.parseInt(rgb[2]) < 20;
    }

    public static String getClass(By selector) {
        return SingletonDriver.waitAndFindElement(selector).getAttribute("class");
    }

    public static String getElementTitle(By selector) {
        return SingletonDriver.waitAndFindElement(selector).getAttribute("title");
    }

    public static List<String> getElementsText(List<WebElement> listOfElements) {
        ArrayList<String> stringList = new ArrayList<>();
        for (WebElement webElement : listOfElements) {
            stringList.add(webElement.getText());
        }
        stringList.remove(stringList.size() - 1);
        return stringList;
    }
}
