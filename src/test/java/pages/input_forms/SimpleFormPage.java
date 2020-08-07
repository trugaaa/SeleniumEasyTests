package pages.input_forms;

import org.openqa.selenium.By;
import util.SingltonDriver;
import util.TestURLs;

public class SimpleFormPage {
    public SimpleFormPage(){
        SingltonDriver.init(TestURLs.INPUT_FORMS_SIMPLE_FORM_URL);
    }

    /*
     * String locators
     */
    private final String userMessageId = "user-message";
    private final String userMessageConfirmButtonXpath = "//button[text()='Show Message']";
    private final String userDisplayTextId = "display";

    /*
     * Locators
     */
    By userMessageLocator = new By.ById(userMessageId);
    By userMessageButtonLocator = new By.ByXPath(userMessageConfirmButtonXpath);
    By userDisplayTextLocator = new By.ById(userDisplayTextId);


    /*
     * Actions
     */
    public void writeUserMessage(String message) {
        SingltonDriver.waitAndFind(userMessageLocator).sendKeys(message);
        SingltonDriver.waitAndFind(userMessageButtonLocator).click();
    }

    /*
     * Verifications
     */
    public Boolean verifyWriteMessage(String message) {
        return SingltonDriver.waitAndFind(userDisplayTextLocator).getText().equals(message);
    }
}
