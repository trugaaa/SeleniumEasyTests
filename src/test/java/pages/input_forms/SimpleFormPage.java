package pages.input_forms;

import org.openqa.selenium.By;
import util.SingletonDriver;

public class SimpleFormPage {
    public final static String INPUT_FORMS_SIMPLE_FORM_URL = "https://www.seleniumeasy.com/test/basic-first-form-demo.html";

    public SimpleFormPage() {
        SingletonDriver.init(INPUT_FORMS_SIMPLE_FORM_URL);
    }

    /*
     * String locators
     */
    private final String userMessageId = "user-message";
    private final String userMessageConfirmButtonXpath = "//button[text()='Show Message']";
    private final String userDisplayTextId = "display";
    private final String aId = "sum1";
    private final String bId = "sum2";
    private final String sumButtonXpath = "//button[text()='Get Total']";
    private final String sumResultId = "displayvalue";
    /*
     * Locators
     */
    By userMessageLocator = new By.ById(userMessageId);
    By userMessageButtonLocator = new By.ByXPath(userMessageConfirmButtonXpath);
    By userDisplayTextLocator = new By.ById(userDisplayTextId);
    By aEnterFieldLocator = new By.ById(aId);
    By bEnterFieldLocator = new By.ById(bId);
    By sumResultButtonLocator = new By.ByXPath(sumButtonXpath);
    By sumResultLocator = new By.ById(sumResultId);


    /*
     * Actions
     */
    public void writeUserMessage(String message) {
        SingletonDriver.waitAndFind(userMessageLocator).sendKeys(message);
        SingletonDriver.waitAndFind(userMessageButtonLocator).click();
    }

    public void writeAAndB(int a, int b) {
        writeA(a);
        writeB(b);
    }

    public void writeA(int a) {
        SingletonDriver.waitAndFind(aEnterFieldLocator).sendKeys(String.valueOf(a));
    }

    public void writeB(int b) {
        SingletonDriver.waitAndFind(bEnterFieldLocator).sendKeys(String.valueOf(b));
    }


    /*
     * Verifications
     */
    public String getUserWroteResultMessage() {
        return SingletonDriver.waitAndFind(userDisplayTextLocator).getText();
    }

    public String getValueOfSumResult(){
        SingletonDriver.waitAndFind(sumResultButtonLocator).click();
        return SingletonDriver.waitAndFind(sumResultLocator).getText();
    }
}
