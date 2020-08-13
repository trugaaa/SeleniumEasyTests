package util;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SingltonDriver {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    private SingltonDriver() {
    }


    public static WebDriver init(String url) {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
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

    public static WebElement waitAndFind(By selector) {
//        try {
//            Thread.sleep(2500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return driver.findElement(selector);
    }
}
