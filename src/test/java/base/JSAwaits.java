package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static base.WebElementExtendedMethods.*;

public class JSAwaits {
    private static final long SECONDS_SHORT_WAIT = 1;

    private static void waitForAjax() {
        try {
            WebDriverWait driverWait = new WebDriverWait(SingletonDriver.getDriver(), SECONDS_SHORT_WAIT);

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
}
