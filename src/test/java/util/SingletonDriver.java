package util;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SingletonDriver {
    private static WebDriver driver;
    private static WebDriverWait driverWait;
    private static final long SECONDS_LONG_WAIT = 10;
    private static final long SECONDS_SHORT_WAIT = 1;


    public static WebDriver getDriver() {
        return driver;
    }

    private SingletonDriver() {
    }


    public static WebDriver init(String url) {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
            driverWait = new WebDriverWait(driver, 30);
            driver.get(url);
        }
        return driver;
    }

    public static void close() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver performCtrlDown() {
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).build().perform();
        return driver;
    }

    public static WebDriver performCtrlUp() {
        Actions action = new Actions(driver);
        action.keyUp(Keys.CONTROL).build().perform();
        return driver;
    }

    private static void waitForAjax() {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, SECONDS_SHORT_WAIT);

            ExpectedCondition<Boolean> expectation;
            expectation = driverJs -> {

                JavascriptExecutor js = (JavascriptExecutor) driverJs;
                return js.executeScript("return((window.jQuery != null) && (jQuery.active === 0))").equals("true");
            };
            driverWait.until(expectation);
        } catch (TimeoutException ignored) {
        }
    }


    public static WebElement ajaxElementWait(By selector) {
        waitForAjax();
        return waitAndFindElement(selector);
    }

    public static List<WebElement> ajaxElementsWait(By selector) {
        waitForAjax();
        return waitAndFindElements(selector);
    }

    public static WebElement waitAndFindElement(By selector) {
        //Implicit wait for SECONDS_ELEMENT_WAIT seconds
        driver.manage().timeouts().implicitlyWait(SECONDS_LONG_WAIT, TimeUnit.SECONDS);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return driver.findElement(selector);
    }

    public static List<WebElement> waitAndFindElements(By selector) {
        //Implicit wait for SECONDS_ELEMENT_WAIT seconds
        driver.manage().timeouts().implicitlyWait(SECONDS_LONG_WAIT, TimeUnit.SECONDS);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return driver.findElements(selector);
    }


}
