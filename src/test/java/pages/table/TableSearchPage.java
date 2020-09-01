package pages.table;

import base.WebElementExtendedMethods;
import org.apache.commons.collections4.ListUtils;
import org.openqa.selenium.By;
import base.SingletonDriver;
import org.openqa.selenium.WebElement;
import util.TestData;

import java.util.List;
import java.util.stream.Collectors;

import static base.WebElementExtendedMethods.*;

public class TableSearchPage {
    private final static String TABLE_SEARCH_URL = "https://www.seleniumeasy.com/test/table-search-filter-demo.html";
    private final static int TABLE_SEARCH_ALL_FIELDS_COLUMN_NUMBER = 4;
    private final static int TABLE_FILTERED_ALL_FIELDS_COLUMN_NUMBER = 4;

    public TableSearchPage() {
        SingletonDriver.init(TABLE_SEARCH_URL);
    }

    /*
    Selectors
     */
    private final By filterTaskAssigneeStatusSearchSelector = new By.ById("task-table-filter");
    private final By taskTableCellSelector = new By.ByXPath("//table[@id='task-table']/tbody/tr[not(@style='display: none;')]/td");
    private final By filterButtonSelector = new By.ByCssSelector(".panel-heading>.pull-right>button");
    private final By filterByIdInputSelector = new By.ByCssSelector(".filters>th>input[placeholder='#']");
    private final By filterByUsernameInputSelector = new By.ByCssSelector(".filters>th>input[placeholder='Username']");
    private final By filterByFirstNameInputSelector = new By.ByCssSelector(".filters>th>input[placeholder='First Name']");
    private final By filterBySecondNameInputSelector = new By.ByCssSelector(".filters>th>input[placeholder='Last Name']");
    private final By filterTableCellSelector = new By.ByXPath("//table[@class='table']/tbody/tr[not(@style='display: none;')]/td");

    /*
    Actions
     */
    public void searchByAllFields(String searchString) {
        waitAndFindElement(filterTaskAssigneeStatusSearchSelector).click();
        waitAndFindElement(filterTaskAssigneeStatusSearchSelector).sendKeys(searchString);
    }

    private void filterButtonClick() {
        waitAndFindElement(filterButtonSelector).click();
    }

    private List<List<String>> getTableCElls(By selector, int tableColumnsCount) {
        List<String> listOfCellsString =
                WebElementExtendedMethods.waitAndFindElements(selector)
                        .stream().map(WebElement::getText)
                        .collect(Collectors.toList());
        return ListUtils.partition(listOfCellsString, tableColumnsCount);
    }

    private List<List<String>> getSearchedByAllColumnsCells() {
        return getTableCElls(taskTableCellSelector, TABLE_SEARCH_ALL_FIELDS_COLUMN_NUMBER);
    }

    private List<List<String>> getFilteredTableColumnsCells() {
        return getTableCElls(filterTableCellSelector, TABLE_FILTERED_ALL_FIELDS_COLUMN_NUMBER);
    }


    public void filterById(int id) {
        filterButtonClick();
        waitAndFindElement(filterByIdInputSelector).sendKeys(String.valueOf(id));
    }

    public void filterByUsername(String username) {
        filterButtonClick();
        waitAndFindElement(filterByUsernameInputSelector).sendKeys(username);

    }

    public void filterByFirstName(String firstName) {
        filterButtonClick();
        waitAndFindElement(filterByFirstNameInputSelector).sendKeys(String.valueOf(firstName));
    }

    public void filterBySecondName(String secondName) {
        filterButtonClick();
        waitAndFindElement(filterBySecondNameInputSelector).sendKeys(String.valueOf(secondName));
    }

    /*
    Verifications
     */
    private boolean isElementFilteredBy(int columnIndex, String searchedString) {
        for (List<String> subList : getFilteredTableColumnsCells()) {
            if (!subList.get(columnIndex).contains(searchedString)) return false;
        }
        return true;
    }

    public Boolean isRowContainFilterString(String searchedString) {
        for (List<String> subList : getSearchedByAllColumnsCells()) {
            if (subList.stream().noneMatch(el -> el.contains(searchedString))) return false;
        }
        return true;
    }

    public boolean isTaskTableEmpty() {
        return waitAndFindElement(taskTableCellSelector).getText().equals(TestData.noTableElementsFound);
    }

    public boolean isFilteredElementsNotFound() {
        return waitAndFindElement(filterTableCellSelector).getText().equals(TestData.noFilteredTableElementFound);
    }

    public boolean isFilteredById(int searchedId) {
        return isElementFilteredBy(0, String.valueOf(searchedId));
    }

    public boolean isElementsFilteredByUsername(String username) {
        return isElementFilteredBy(1, username);
    }

    public boolean isElementsFilteredByFirstName(String firstName) {
        return isElementFilteredBy(2, firstName);
    }

    public boolean isElementsFilteredBySecondName(String secondName) {
        return isElementFilteredBy(3, secondName);
    }
}
