package util;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SingletonDriver {
    private static WebDriver driver;
    private static WebDriverWait driverWait;
    private static final long SECONDS_ELEMENT_WAIT = 10;

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
                WebDriverWait driverWait = new WebDriverWait(driver, 10);

                ExpectedCondition<Boolean> expectation;
                expectation = new ExpectedCondition<Boolean>() {

                    public Boolean apply(WebDriver driverjs) {

                        JavascriptExecutor js = (JavascriptExecutor) driverjs;
                        return js.executeScript("return((window.jQuery != null) && (jQuery.active === 0))").equals("true");
                    }
                };
                driverWait.until(expectation);
            }
            catch (TimeoutException exTimeout) {

                // fail code
            }
    }


    public static WebElement ajaxElementWait(By selector) {
        waitForAjax();
        return waitAndFind(selector);
    }

    public static WebElement waitAndFind(By selector) {
        //Implicit wait for SECONDS_ELEMENT_WAIT seconds
        driver.manage().timeouts().implicitlyWait(SECONDS_ELEMENT_WAIT, TimeUnit.SECONDS);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return driver.findElement(selector);
    }


}
