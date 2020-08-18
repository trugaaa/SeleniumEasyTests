package pages.input_forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import util.SingletonDriver;
import util.WebElementExtendedMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JQuerySelectDropdownPage {
    private final String JQUERY_DROPDOWN_SELECT_URL = "https://www.seleniumeasy.com/test/jquery-dropdown-search-demo.html";
    private final String unCheckSymbol = "×";

    public JQuerySelectDropdownPage() {
        SingletonDriver.init(JQUERY_DROPDOWN_SELECT_URL);
    }

    /*
    Selectors
     */
    private final By selectCountryComboboxSelector = new By.ByXPath("//span[@class='selection']");
    private final By selectCountryInputFieldSelector = new By.ByXPath("//span[@class='select2-search select2-search--dropdown']/input[@class='select2-search__field' and @type='search']");
    private final By selectStateMultipleComboboxSelector = new By.ByXPath("//span[@class='select2-selection select2-selection--single']/parent::span[@class='selection']");
    private final By selectStateMultipleInputFieldSelector = new By.ByXPath("//li[@class='select2-search select2-search--inline']/input[@class='select2-search__field' and @type='search']");
    private final By selectedElementsMultipleStatesSelector = new By.ByXPath("//span[@class='selection']/span/ul[@class='select2-selection__rendered']/li");
    private final By selectFileDropdownSelector = new By.ById("files");
    private final By selectionWithNotSelectableItemsSelector = new By.ByXPath("(//span[@class='select2-selection__arrow'])[2]");
    private final By selectionWithNotSelectableInputSelector = new By.ByXPath("(//input[@class='select2-search__field'])[2]");
    private final By selectedElementWithDropdownSelector = new By.ByXPath("(//span[@class='select2-selection__arrow'])[2]/parent::span/span[@class='select2-selection__rendered']");

    private By unCheckCheckedStateSelector(String state) {
        return new By.ByXPath(String.format("//li[text()='%s']/span[text()='%s']", state, unCheckSymbol));
    }

    private By getCountryListElementSelector(String country) {
        return new By.ByXPath(String.format("//li[@role='treeitem' and text()='%s']", country));
    }

    private By getMultipleElementSelector(String state) {
        return new By.ByXPath(String.format("//span[@class='select2-results']/ul/li[text()='%s']", state));
    }

    private By getElementWithCategory(String category, String element) {
        return new By.ByXPath(String.format("//select[@id='files']/optgroup[@label='%s']/option[text()='%s']", category, element));
    }

    private By getTerritory(String fullTerritory) {
        return new By.ByXPath(String.format("//span[@class='select2-results']/ul[@class='select2-results__options']/li[text()='%s']", fullTerritory));
    }

    /*
    Actions
     */
    public void selectWithNotSelectable(String hint, String fullTerritory) {
        SingletonDriver.waitAndFindElement(selectionWithNotSelectableItemsSelector).click();
        SingletonDriver.waitAndFindElement(selectionWithNotSelectableInputSelector).sendKeys(hint);
        SingletonDriver.waitAndFindElement(getTerritory(fullTerritory)).click();
    }

    public void selectCountry(String hint, String country) {
        SingletonDriver.waitAndFindElement(selectCountryComboboxSelector).click();
        SingletonDriver.waitAndFindElement(selectCountryInputFieldSelector).click();
        SingletonDriver.waitAndFindElement(selectCountryInputFieldSelector).sendKeys(hint);
        SingletonDriver.ajaxElementWait(getCountryListElementSelector(country)).click();
    }

    public void selectMultipleStates(HashMap<String, String> stateMap) {
        SingletonDriver.waitAndFindElement(selectStateMultipleComboboxSelector).click();
        stateMap.forEach((hint, state) -> {
            SingletonDriver.waitAndFindElement(selectStateMultipleInputFieldSelector).sendKeys(hint);
            SingletonDriver.waitAndFindElement(getMultipleElementSelector(state)).click();
        });
    }

    public void uncheckElements(HashMap<String, String> expectedSelected) {
        expectedSelected.forEach((k, v) -> {
            SingletonDriver.waitAndFindElement(unCheckCheckedStateSelector(v)).click();
        });
    }

    public void selectDropDownWithCategory(String category, String element) {
        SingletonDriver.waitAndFindElement(selectFileDropdownSelector).click();
        SingletonDriver.waitAndFindElement(getElementWithCategory(category, element)).click();
    }

    /*
    Verifications
     */
    public String getSelectedCountry() {
        return SingletonDriver.waitAndFindElement(selectCountryComboboxSelector).getText();
    }

    public List<String> getSelectedValues() {
        List<WebElement> selectedElements = SingletonDriver.waitAndFindElements(selectedElementsMultipleStatesSelector);
        return WebElementExtendedMethods.getElementsText(selectedElements);
    }

    public Boolean areAllElementsSelected(HashMap<String, String> expectedSelected) {
        ArrayList<String> expectedSelectedValues = new ArrayList<>();
        //<span> symbol adding
        expectedSelected.forEach((k, v) -> {
            expectedSelectedValues.add(unCheckSymbol + v);
        });
        List<String> actualSelected = getSelectedValues();
        actualSelected.sort(String.CASE_INSENSITIVE_ORDER);
        expectedSelectedValues.sort(String.CASE_INSENSITIVE_ORDER);
        return actualSelected.equals(expectedSelectedValues);
    }

    public String getSelectedElementWithCategory() {
        Select select = new Select(SingletonDriver.waitAndFindElement(selectFileDropdownSelector));
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedTerritory() {
        return WebElementExtendedMethods.getElementTitle(selectedElementWithDropdownSelector);
    }
}
