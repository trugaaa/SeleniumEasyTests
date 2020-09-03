package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class SingletonDriver {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            init();
        }
        return driver;
    }

    private SingletonDriver() {
    }

    private static synchronized SingletonDriver init() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/");
        driver = new ChromeDriver(BrowserOptions.getDefaultChromeOptions());
        return new SingletonDriver();
    }

    public static void destroy() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}
