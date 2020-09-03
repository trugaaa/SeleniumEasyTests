package pages.input_forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import base.WebElementExtendedMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static base.JSAwaits.ajaxElementWait;
import static base.WebElementExtendedMethods.*;

public class JQuerySelectDropdownPage {
    private final String unCheckSymbol = "×";

    public JQuerySelectDropdownPage() {
        WebElementExtendedMethods.navigateTo("https://www.seleniumeasy.com/test/jquery-dropdown-search-demo.html");
    }

    /*
    Selectors
     */
    private final By selectCountryComboboxSelector = By.xpath("//span[@class='selection']");
    private final By selectCountryInputFieldSelector = By.xpath("//span[@class='select2-search select2-search--dropdown']/input[@class='select2-search__field' and @type='search']");
    private final By selectStateMultipleComboboxSelector = By.xpath("//span[@class='select2-selection select2-selection--single']/parent::span[@class='selection']");
    private final By selectStateMultipleInputFieldSelector = By.xpath("//li[@class='select2-search select2-search--inline']/input[@class='select2-search__field' and @type='search']");
    private final By selectedElementsMultipleStatesSelector = By.xpath("//span[@class='selection']/span/ul[@class='select2-selection__rendered']/li");
    private final By selectFileDropdownSelector = By.id("files");
    private final By selectionWithNotSelectableItemsSelector = By.xpath("(//span[@class='select2-selection__arrow'])[2]");
    private final By selectionWithNotSelectableInputSelector = By.xpath("(//input[@class='select2-search__field'])[2]");
    private final By selectedElementWithDropdownSelector = By.xpath("(//span[@class='select2-selection__arrow'])[2]/parent::span/span[@class='select2-selection__rendered']");

    private By unCheckCheckedStateSelector(String state) {
        return new By.ByXPath(String.format("//li[text()='%s']/span", state));
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
        waitAndFindElement(selectionWithNotSelectableItemsSelector).click();
        waitAndFindElement(selectionWithNotSelectableInputSelector).sendKeys(hint);
        waitAndFindElement(getTerritory(fullTerritory)).click();
    }

    public void selectCountry(String hint, String country) {
        waitAndFindElement(selectCountryComboboxSelector).click();
        waitAndFindElement(selectCountryInputFieldSelector).click();
        waitAndFindElement(selectCountryInputFieldSelector).sendKeys(hint);
        ajaxElementWait(getCountryListElementSelector(country)).click();
    }

    public void selectMultipleStates(HashMap<String, String> stateMap) {
        ajaxElementWait(selectStateMultipleComboboxSelector).click();
        stateMap.forEach((hint, state) -> {
            ajaxElementWait(selectStateMultipleInputFieldSelector).sendKeys(hint);
            ajaxElementWait(getMultipleElementSelector(state)).click();
        });
    }

    public void uncheckElements(HashMap<String, String> expectedSelected) {
        expectedSelected.forEach((k, v) -> ajaxElementWait(unCheckCheckedStateSelector(v)).click());
    }

    public void selectDropDownWithCategory(String category, String element) {
        waitAndFindElement(selectFileDropdownSelector).click();
        waitAndFindElement(getElementWithCategory(category, element)).click();
    }

    /*
    Verifications
     */
    public String getSelectedCountry() {
        return waitAndFindElement(selectCountryComboboxSelector).getText();
    }

    public List<String> getSelectedValues() {
        List<WebElement> selectedElements = waitAndFindElements(selectedElementsMultipleStatesSelector);
        return WebElementExtendedMethods.getElementsText(selectedElements);
    }

    public Boolean areAllElementsSelected(HashMap<String, String> expectedSelected) {
        ArrayList<String> expectedSelectedValues = new ArrayList<>();
        //<span> symbol adding
        expectedSelected.forEach((k, v) -> expectedSelectedValues.add(unCheckSymbol + v));
        List<String> actualSelected = getSelectedValues();
        actualSelected.sort(String.CASE_INSENSITIVE_ORDER);
        expectedSelectedValues.sort(String.CASE_INSENSITIVE_ORDER);
        return actualSelected.equals(expectedSelectedValues);
    }

    public String getSelectedElementWithCategory() {
        Select select = new Select(waitAndFindElement(selectFileDropdownSelector));
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedTerritory() {
        return getElementTitle(selectedElementWithDropdownSelector);
    }
}
