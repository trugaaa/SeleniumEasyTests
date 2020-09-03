package pages.input_forms;

import org.openqa.selenium.By;

import static base.WebElementExtendedMethods.navigateTo;
import static base.WebElementExtendedMethods.waitAndFindElement;

public class SimpleFormPage {
    public SimpleFormPage() {
        navigateTo("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
    }

    /*
     * Locators
     */
    By userMessageLocator = By.id("user-message");
    By userMessageButtonLocator = By.xpath("//button[text()='Show Message']");
    By userDisplayTextLocator = By.id("display");
    By aEnterFieldLocator = By.id("sum1");
    By bEnterFieldLocator = By.id("sum2");
    By sumResultButtonLocator = By.xpath("//button[text()='Get Total']");
    By sumResultLocator = By.id("displayvalue");


    /*
     * Actions
     */
    public void writeUserMessage(String message) {
        waitAndFindElement(userMessageLocator).sendKeys(message);
        waitAndFindElement(userMessageButtonLocator).click();
    }

    public void writeAAndB(int a, int b) {
        writeA(a);
        writeB(b);
    }

    public void writeA(int a) {
        waitAndFindElement(aEnterFieldLocator).sendKeys(String.valueOf(a));
    }

    public void writeB(int b) {
        waitAndFindElement(bEnterFieldLocator).sendKeys(String.valueOf(b));
    }


    /*
     * Verifications
     */
    public String getUserWroteResultMessage() {
        return waitAndFindElement(userDisplayTextLocator).getText();
    }

    public String getValueOfSumResult() {
        waitAndFindElement(sumResultButtonLocator).click();
        return waitAndFindElement(sumResultLocator).getText();
    }
}
