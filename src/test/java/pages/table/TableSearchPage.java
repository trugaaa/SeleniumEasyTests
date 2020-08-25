package pages.table;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import base.SingletonDriver;
import util.TestData;

import java.util.ArrayList;
import java.util.List;

import static org.testng.FileAssert.fail;
import static base.WebElementExtendedMethods.*;

public class TableSearchPage {
    private final static String TABLE_SEARCH_URL = "https://www.seleniumeasy.com/test/table-search-filter-demo.html";

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
    public List<SingleSearchTableRow> searchByAllFields(String searchString) {
        ArrayList<SingleSearchTableRow> allSingleSearchTableRows = new ArrayList<>();
        waitAndFindElement(filterTaskAssigneeStatusSearchSelector).click();
        waitAndFindElement(filterTaskAssigneeStatusSearchSelector).sendKeys(searchString);
        List<WebElement> tableRowsCells = ajaxElementsWait(taskTableCellSelector);
        if (!isElementNotPresent()) {
            for (int i = 0; i < tableRowsCells.size(); i += 4) {
                allSingleSearchTableRows.add(new SingleSearchTableRow(tableRowsCells.get(i).getText(),
                        tableRowsCells.get(i + 1).getText(),
                        tableRowsCells.get(i + 2).getText(),
                        tableRowsCells.get(i + 3).getText()));
            }
        }
        return allSingleSearchTableRows;
    }

    private void filterButtonClick() {
        waitAndFindElement(filterButtonSelector).click();
    }

    private List<FilterTableRow> getFilteredValues() {
        ArrayList<FilterTableRow> filteredTableRows = new ArrayList<>();
        if (isFilteredElementsFound()) {
            List<WebElement> filteredCells = ajaxElementsWait(filterTableCellSelector);
            for (int i = 0; i < filteredCells.size(); i += 4) {
                filteredTableRows.add(new FilterTableRow(filteredCells.get(i).getText(),
                        filteredCells.get(i + 1).getText(),
                        filteredCells.get(i + 2).getText(),
                        filteredCells.get(i + 3).getText()));
            }
        }
        return filteredTableRows;
    }

    public List<FilterTableRow> filterById(int id) {
        filterButtonClick();
        waitAndFindElement(filterByIdInputSelector).sendKeys(String.valueOf(id));
        if (getFilteredValues().size() > 1) fail();
        return getFilteredValues();
    }

    public List<FilterTableRow> filterByUsername(String username) {
        filterButtonClick();
        waitAndFindElement(filterByUsernameInputSelector).sendKeys(String.valueOf(username));
        return getFilteredValues();
    }

    public List<FilterTableRow> filterByFirstName(String firstName) {
        filterButtonClick();
        waitAndFindElement(filterByFirstNameInputSelector).sendKeys(String.valueOf(firstName));
        return getFilteredValues();
    }

    public List<FilterTableRow> filterBySecondName(String secondName) {
        filterButtonClick();
        waitAndFindElement(filterBySecondNameInputSelector).sendKeys(String.valueOf(secondName));
        return getFilteredValues();
    }

    /*
    Verifications
     */
    public Boolean isAllRowsContainsString(List<SingleSearchTableRow> rows, String stringToContain) {
        boolean isElementPresentInAllRows = false;
        for (SingleSearchTableRow el : rows) {
            isElementPresentInAllRows = el.getStatus().contains(stringToContain) ||
                    el.getTask().contains(stringToContain) ||
                    el.getAssignee().contains(stringToContain);
            if (!isElementPresentInAllRows) return false;
        }
        return isElementPresentInAllRows;
    }

    public Boolean isElementNotPresent() {
        return ajaxElementWait(taskTableCellSelector).getText().equals(TestData.noTableElementsFound);
    }

    public Boolean isFilteredElementsFound() {
        return !ajaxElementWait(filterTableCellSelector).getText().equals(TestData.noFilteredTableElementFound);
    }

    public Boolean isElementsFilteredById(List<FilterTableRow> rows, int id) {
        boolean isElementPresentInAllRows = false;
        for (FilterTableRow el : rows) {
            isElementPresentInAllRows = el.getId() == id;
            if (!isElementPresentInAllRows) return false;
        }
        return isElementPresentInAllRows;
    }

    public Boolean isElementsFilteredByUsername(List<FilterTableRow> rows, String username) {
        boolean isElementPresentInAllRows = false;
        for (FilterTableRow el : rows) {
            isElementPresentInAllRows = el.getUsername().contains(username);
            if (!isElementPresentInAllRows) return false;
        }
        return isElementPresentInAllRows;
    }

    public Boolean isElementsFilteredByFirstname(List<FilterTableRow> rows, String firstName) {
        boolean isElementPresentInAllRows = false;
        for (FilterTableRow el : rows) {
            isElementPresentInAllRows = el.getFirstName().contains(firstName);
            if (!isElementPresentInAllRows) return false;
        }
        return isElementPresentInAllRows;
    }

    public Boolean isElementsFilteredBySecondName(List<FilterTableRow> rows, String secondName) {
        boolean isElementPresentInAllRows = false;
        for (FilterTableRow el : rows) {
            isElementPresentInAllRows = el.getSecondName().contains(secondName);
            if (!isElementPresentInAllRows) return false;
        }
        return isElementPresentInAllRows;
    }

    /*
    Row table classes presentation
     */
    public class SingleSearchTableRow {
        private final int id;
        private final String task;
        private final String assignee;
        private final String status;

        SingleSearchTableRow(String id, String task, String assignee, String status) {
            this.id = Integer.parseInt(id);
            this.assignee = assignee;
            this.task = task;
            this.status = status;
        }

        public String getTask() {
            return task;
        }

        public String getAssignee() {
            return assignee;
        }

        public String getStatus() {
            return status;
        }
    }

    public class FilterTableRow {
        private final int id;
        private final String username;
        private final String firstName;
        private final String secondName;

        FilterTableRow(String id, String username, String firstName, String secondName) {
            this.id = Integer.parseInt(id);
            this.firstName = firstName;
            this.username = username;
            this.secondName = secondName;
        }

        public String getUsername() {
            return username;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getSecondName() {
            return secondName;
        }

        public int getId() {
            return id;
        }
    }
}
