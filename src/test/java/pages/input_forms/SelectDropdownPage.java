package pages.input_forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import util.SingltonDriver;
import util.TestData;
import util.WebElementExtendedMethods;

import java.util.ArrayList;
import java.util.List;

public class SelectDropdownPage {
    private final String SELECT_DROPDOWN_URL = "https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html";

    public SelectDropdownPage() {
        SingltonDriver.init(SELECT_DROPDOWN_URL);
    }

    /*
     * String selectors
     */
    private final String selectADayId = "select-demo";
    private final String sundayDropdownValueXpath = String.format("//select[@id='%s']/option[@value='%s']", selectADayId, TestData.sunday);
    private final String sundayExpectedResultTextXpath = "//div[@class='panel-body']/p[@class='selected-value']";
    private final String multiValuesSelectDropdownId = "multi-select";
    private final String firstSelectedButtonId = "printMe";
    private final String getAllSelectedButtonId = "printAll";
    private final String getMultiSelectedResultTextXpath = "//div[@class='panel-body']/p[@class='getall-selected']";

    /*
     * Selectors
     */
    private final By selectADaySelector = new By.ById(selectADayId);
    private final By selectSundaySelector = new By.ByXPath(sundayDropdownValueXpath);
    private final By sundayExpectedResultTextSelector = new By.ByXPath(sundayExpectedResultTextXpath);
    private final By multiValuesSelectDropdownSelector = new By.ById(multiValuesSelectDropdownId);
    private final By firstSelectedButtonSelector = new By.ById(firstSelectedButtonId);
    private final By getAllSelectedButtonSelector = new By.ById(getAllSelectedButtonId);
    private final By getMultiSelectedResultTextSelector = new By.ByXPath(getMultiSelectedResultTextXpath);

    private By getSelectorOfState(String state) {
        return new By.ByXPath(String.format("//select[@id='%s']/option[@value='%s']", multiValuesSelectDropdownId, state));
    }

    /*
    Elements
     */
    Select multiDropdown;

    /*
     * Actions
     */
    public void selectADayDropdownClick() {
        SingltonDriver.waitAndFind(selectADaySelector).click();
    }

    public void selectSunday() {
        selectADayDropdownClick();
        SingltonDriver.waitAndFind(selectSundaySelector).click();
    }

    public void selectMultiValues(List<String> multiDropDownElementsToBeChecked) {
        SingltonDriver.waitAndFind(multiValuesSelectDropdownSelector).click();
        multiDropdown = new Select(SingltonDriver.waitAndFind(multiValuesSelectDropdownSelector));
        for (String element : multiDropDownElementsToBeChecked) {
            multiDropdown.selectByVisibleText(element);
        }
    }

    public void getFirstSelectedButtonClick() {
        SingltonDriver.waitAndFind(firstSelectedButtonSelector).click();
    }

    public void getAllSelectedButtonClick() {
        SingltonDriver.waitAndFind(getAllSelectedButtonSelector).click();
    }

    /*
     * Verifications
     */
    public String getDropdownResultText() {
        return SingltonDriver.waitAndFind(sundayExpectedResultTextSelector).getText();
    }

    public String getResultMultiText() {
        return SingltonDriver.waitAndFind(getMultiSelectedResultTextSelector).getText();
    }
}
