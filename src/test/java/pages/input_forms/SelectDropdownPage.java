package pages.input_forms;

import base.WebElementExtendedMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static base.WebElementExtendedMethods.waitAndFindElement;

public class SelectDropdownPage {
    public SelectDropdownPage() {
        WebElementExtendedMethods.navigateTo("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
    }

    /*
     * Selectors
     */
    private final By selectADaySelector = By.id("select-demo");
    private final By selectSundaySelector = By.xpath("//select[@id='select-demo']/option[@value='Sunday']");
    private final By sundayExpectedResultTextSelector = By.xpath("//div[@class='panel-body']/p[@class='selected-value']");
    private final By multiValuesSelectDropdownSelector = By.id("multi-select");
    private final By firstSelectedButtonSelector = By.id("printMe");
    private final By getAllSelectedButtonSelector = By.id("printAll");
    private final By getMultiSelectedResultTextSelector = By.xpath("//div[@class='panel-body']/p[@class='getall-selected']");

    private By getSelectorOfState(String state) {
        return new By.ByXPath(String.format("//select[@id='multi-select']/option[@value='%s']", state));
    }

    /*
    Elements
     */
    Select selectStateDropdown;

    /*
     * Actions
     */
    public void selectADayDropdownClick() {
        waitAndFindElement(selectADaySelector).click();
    }

    public void selectSunday() {
        selectADayDropdownClick();
        waitAndFindElement(selectSundaySelector).click();
    }

    public void selectMultiValues(List<String> multiDropDownElementsToBeChecked) throws Exception {
        waitAndFindElement(multiValuesSelectDropdownSelector).click();
        selectStateDropdown = new Select(waitAndFindElement(multiValuesSelectDropdownSelector));
        multiDropDownElementsToBeChecked.forEach(state -> selectStateDropdown.selectByVisibleText(state));

        if (selectStateDropdown.getAllSelectedOptions().size() != multiDropDownElementsToBeChecked.size())
            throw new Exception("Sizes of expected checked list and actual checked are not equal");
    }

    public void getFirstSelectedButtonClick() {
        waitAndFindElement(firstSelectedButtonSelector).click();
    }

    public void getAllSelectedButtonClick() {
        waitAndFindElement(getAllSelectedButtonSelector).click();
    }

    /*
     * Verifications
     */
    public String getDropdownResultText() {
        return waitAndFindElement(sundayExpectedResultTextSelector).getText();
    }

    public String getResultMultiText() {
        return waitAndFindElement(getMultiSelectedResultTextSelector).getText();
    }

    public Boolean isMultiSelectStateSupported() {
        return new Select(waitAndFindElement(multiValuesSelectDropdownSelector)).isMultiple();
    }
}
