package base;

import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserOptions {
    public static ChromeOptions getDefaultChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--allow-insecure-localhost");
        chromeOptions.addArguments("--ignore-certificate-errors");
        return chromeOptions;
    }
}
