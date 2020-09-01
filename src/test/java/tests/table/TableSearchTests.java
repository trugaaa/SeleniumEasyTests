package tests.table;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.table.TableSearchPage;
import base.SingletonDriver;

import static org.testng.Assert.assertTrue;

public class TableSearchTests {
    TableSearchPage tableSearchPage;
    String stringToSearch;

    @BeforeMethod
    void init() {
        tableSearchPage = new TableSearchPage();
    }

    @Test
    void searchThreeRowsByAllFieldsFound() {
        stringToSearch = "Joh";
        tableSearchPage.searchByAllFields(stringToSearch);
        assertTrue(tableSearchPage.isRowContainFilterString(stringToSearch));
    }

    @Test
    void searchTwoRowsByAllFieldsFound() {
        stringToSearch = "an";
        tableSearchPage.searchByAllFields(stringToSearch);
        assertTrue(tableSearchPage.isRowContainFilterString(stringToSearch));
    }

    @Test
    void noElementsByAllFieldsFound() {
        tableSearchPage.searchByAllFields("Andratanchik");
        assertTrue(tableSearchPage.isTaskTableEmpty());
    }

    @Test
    void NoFilteredRowFound() {
        tableSearchPage.filterById(-1);
        assertTrue(tableSearchPage.isFilteredElementsNotFound());
    }

    @Test
    void filterTableById() {
        int id = 1;
        tableSearchPage.filterById(id);
        assertTrue(tableSearchPage.isFilteredById(id));
    }

    @Test
    void filterTableByUsername() {
        stringToSearch = "m";
        tableSearchPage.filterByUsername(stringToSearch);
        assertTrue(tableSearchPage.isElementsFilteredByUsername(stringToSearch));
    }

    @Test
    void filterTableByFirstName() {
        stringToSearch = "an";
        tableSearchPage.filterByFirstName(stringToSearch);
        assertTrue(tableSearchPage.isElementsFilteredByFirstName(stringToSearch));
    }

    @Test
    void filterTableBySecondName() {
        stringToSearch = "an";
        tableSearchPage.filterBySecondName(stringToSearch);
        assertTrue(tableSearchPage.isElementsFilteredBySecondName(stringToSearch));
    }

    @AfterMethod
    void dispose() {
        SingletonDriver.destroy();
    }
}
