package pages.input_forms;

import org.openqa.selenium.By;
import util.TestData;
import base.WebElementExtendedMethods;

import static base.WebElementExtendedMethods.waitAndFindElement;


public class CheckboxPage {
    public CheckboxPage() {
        WebElementExtendedMethods.navigateTo("https://www.seleniumeasy.com/test/basic-checkbox-demo.html");
    }

    /*
     * Locators
     */
    private final By checkBoxLocator = By.id("isAgeSelected");
    private final By singleCheckboxResultMessageLocator = By.id("txtAge");
    private final By option1Locator = By.xpath("//label[text()='Option 1']/input");
    private final By option2Locator = By.xpath("//label[text()='Option 2']/input");
    private final By option3Locator = By.xpath("//label[text()='Option 3']/input");
    private final By option4Locator = By.xpath("//label[text()='Option 4']/input");
    private final By checkUncheckAllButtonLocator = By.id("check1");

    /*
     * Actions
     */
    public void checkSingleCheckboxClick() {
        waitAndFindElement(checkBoxLocator).click();

    }

    public String getCheckboxResultText() {
        return waitAndFindElement(singleCheckboxResultMessageLocator).getText();
    }

    public void checkUncheckButtonClick() {
        waitAndFindElement(checkUncheckAllButtonLocator).click();
    }

    public void checkFirstOption() {
        waitAndFindElement(option1Locator).click();
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
