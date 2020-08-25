package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SingletonDriver {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    private SingletonDriver() {
    }

    public static synchronized WebDriver init(String url) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--allow-insecure-localhost");
        chromeOptions.addArguments("--ignore-certificate-errors");

        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver(chromeOptions);
            driver.get(url);
        }
        return driver;
    }

    public static void destroy() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}
