package pages.input_forms;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import util.SingletonDriver;
import util.TestData;

import java.util.List;

public class SelectDropdownPage {
    private final String SELECT_DROPDOWN_URL = "https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html";

    public SelectDropdownPage() {
        SingletonDriver.init(SELECT_DROPDOWN_URL);
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
    Select selectStateDropdown;

    /*
     * Actions
     */
    public void selectADayDropdownClick() {
        SingletonDriver.waitAndFindElement(selectADaySelector).click();
    }

    public void selectSunday() {
        selectADayDropdownClick();
        SingletonDriver.waitAndFindElement(selectSundaySelector).click();
    }

    public void selectMultiValues(List<String> multiDropDownElementsToBeChecked) throws Exception {
        SingletonDriver.waitAndFindElement(multiValuesSelectDropdownSelector).click();
        selectStateDropdown = new Select(SingletonDriver.waitAndFindElement(multiValuesSelectDropdownSelector));
        multiDropDownElementsToBeChecked.forEach(state -> selectStateDropdown.selectByVisibleText(state));

        if (selectStateDropdown.getAllSelectedOptions().size() != multiDropDownElementsToBeChecked.size())
            throw new Exception("Sizes of expected checked list and actual checked are not equal");
    }

    public void getFirstSelectedButtonClick() {
        SingletonDriver.waitAndFindElement(firstSelectedButtonSelector).click();
    }

    public void getAllSelectedButtonClick() {
        SingletonDriver.waitAndFindElement(getAllSelectedButtonSelector).click();
    }

    /*
     * Verifications
     */
    public String getDropdownResultText() {
        return SingletonDriver.waitAndFindElement(sundayExpectedResultTextSelector).getText();
    }

    public String getResultMultiText() {
        return SingletonDriver.waitAndFindElement(getMultiSelectedResultTextSelector).getText();
    }

    public Boolean isMultiSelectStateSupported() {
        return new Select(SingletonDriver.waitAndFindElement(multiValuesSelectDropdownSelector)).isMultiple();
    }
}
