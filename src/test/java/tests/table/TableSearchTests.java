package tests.table;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.table.TableSearchPage;
import base.SingletonDriver;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TableSearchTests {
    TableSearchPage tableSearchPage;
    String stringToSearch;
    List<TableSearchPage.SingleSearchTableRow> foundRows;
    List<TableSearchPage.FilterTableRow> filteredRows;

    @BeforeMethod
    void init() {
        tableSearchPage = new TableSearchPage();
    }

    @Test
    void searchThreeRowsByAllFieldsFound() {
        stringToSearch = "Joh";
        foundRows = tableSearchPage.searchByAllFields(stringToSearch);
        assertTrue(tableSearchPage.isAllRowsContainsString(foundRows, stringToSearch));
    }

    @Test
    void searchTwoRowsByAllFieldsFound() {
        stringToSearch = "an";
        foundRows = tableSearchPage.searchByAllFields(stringToSearch);
        assertTrue(tableSearchPage.isAllRowsContainsString(foundRows, stringToSearch));
    }

    @Test
    void noElementsByAllFieldsFound() {
        tableSearchPage.searchByAllFields("Andratanchik");
        assertTrue(tableSearchPage.isElementNotPresent());
    }

    @Test
    void NoFilteredRowFound() {
        tableSearchPage.filterById(-1);
        assertFalse(tableSearchPage.isFilteredElementsFound());
    }

    @Test
    void filterTableById() {
        int id = 1;
        filteredRows = tableSearchPage.filterById(id);
        assertTrue(tableSearchPage.isElementsFilteredById(filteredRows, id));
    }

    @Test
    void filterTableByUsername() {
        stringToSearch = "m";
        filteredRows = tableSearchPage.filterByUsername(stringToSearch);
        assertTrue(tableSearchPage.isElementsFilteredByUsername(filteredRows, stringToSearch));
    }

    @Test
    void filterTableByFirstName() {
        stringToSearch = "an";
        filteredRows = tableSearchPage.filterByFirstName(stringToSearch);
        assertTrue(tableSearchPage.isElementsFilteredByFirstname(filteredRows, stringToSearch));
    }

    @Test
    void filterTableBySecondName() {
        stringToSearch = "an";
        filteredRows = tableSearchPage.filterBySecondName(stringToSearch);
        assertTrue(tableSearchPage.isElementsFilteredBySecondName(filteredRows, stringToSearch));
    }

    @AfterMethod
    void dispose() {
        SingletonDriver.destroy();
    }
}
