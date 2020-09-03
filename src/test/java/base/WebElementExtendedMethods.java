package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static base.JSAwaits.ajaxElementWait;

public class WebElementExtendedMethods {
    public static void navigateTo(String url) {
        SingletonDriver.getDriver().get(url);
    }

    public static WebElement waitAndFindElement(By selector) {
        WebDriverWait driverWait = new WebDriverWait(SingletonDriver.getDriver(), 30);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return SingletonDriver.getDriver().findElement(selector);
    }

    public static List<WebElement> waitAndFindElements(By selector) {
        WebDriverWait driverWait = new WebDriverWait(SingletonDriver.getDriver(), 30);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return SingletonDriver.getDriver().findElements(selector);
    }

    public static Boolean isElementChecked(By locator) {
        return waitAndFindElement(locator).isSelected();
    }

    public static String getElementValue(By locator) {
        return waitAndFindElement(locator).getAttribute("value");
    }

    public static String[] getCSSBorderColor(By locator) {
        return ajaxElementWait(locator).getCssValue("border-bottom-color").
                replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))", "").split(",");
    }

    public static Boolean isBorderColorRed(By selector) {
        String[] rgb = getCSSBorderColor(selector);
        return Integer.parseInt(rgb[0]) > 225 && Integer.parseInt(rgb[1]) < 20 && Integer.parseInt(rgb[2]) < 20;
    }

    public static String getClass(By selector) {
        return waitAndFindElement(selector).getAttribute("class");
    }

    public static String getElementTitle(By selector) {
        return waitAndFindElement(selector).getAttribute("title");
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
