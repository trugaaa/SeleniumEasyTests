package pages.input_forms;

import org.openqa.selenium.By;
import util.SingltonDriver;
import util.TestData;
import util.WebElementExtendedMethods;


public class CheckboxPage {
    public final static String CHECKBOX_URL = "https://www.seleniumeasy.com/test/basic-checkbox-demo.html";

    public CheckboxPage() {
        SingltonDriver.init(CHECKBOX_URL);
    }

    /*
     * String locators
     */
    private final String checkBoxId = "isAgeSelected";
    private final String singleCheckboxResultMessageId = "txtAge";
    private final String option1Xpath = "//label[text()='Option 1']/input";
    private final String option2Xpath = "//label[text()='Option 2']/input";
    private final String option3Xpath = "//label[text()='Option 3']/input";
    private final String option4Xpath = "//label[text()='Option 4']/input";
    private final String buttonCheckId = "check1";

    /*
     * Locators
     */
    private final By checkBoxLocator = new By.ById(checkBoxId);
    private final By singleCheckboxResultMessageLocator = new By.ById(singleCheckboxResultMessageId);
    private final By option1Locator = new By.ByXPath(option1Xpath);
    private final By option2Locator = new By.ByXPath(option2Xpath);
    private final By option3Locator = new By.ByXPath(option3Xpath);
    private final By option4Locator = new By.ByXPath(option4Xpath);
    private final By checkUncheckAllButtonLocator = new By.ById(buttonCheckId);

    /*
     * Actions
     */
    public void checkSingleCheckboxClick() {
        SingltonDriver.waitAndFind(checkBoxLocator).click();

    }

    public String getCheckboxResultText() {
        return SingltonDriver.waitAndFind(singleCheckboxResultMessageLocator).getText();
    }

    public void checkUncheckButtonClick() {
        SingltonDriver.waitAndFind(checkUncheckAllButtonLocator).click();
    }

    public void checkFirstOption() {
        SingltonDriver.waitAndFind(option1Locator).click();
    }

    /*
     * Verifications
     */
    public Boolean isButtonSetForCheckedAll() {
        return WebElementExtendedMethods.getElementValue(checkUncheckAllButtonLocator).equals(TestData.checkAllButtonText);
    }

    public Boolean allOptionsChecked() {
        return WebElementExtendedMethods.isElementChecked(option1Locator)
                && WebElementExtendedMethods.isElementChecked(option2Locator)
                && WebElementExtendedMethods.isElementChecked(option3Locator)
                && WebElementExtendedMethods.isElementChecked(option4Locator);
    }
}
