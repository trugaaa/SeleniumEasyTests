package tests.table;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.table.TablePaginationPage;
import util.SingletonDriver;

import static org.testng.Assert.assertTrue;

public class TablePaginationTests {
    TablePaginationPage tablePaginationPage;

    @BeforeMethod
    void init() {
        tablePaginationPage = new TablePaginationPage();
    }


    @Test
    void firstPagePreselected() {
        assertTrue(tablePaginationPage.isFirstPageSelected());
    }

    @Test
    void returnFirstPage() {
        tablePaginationPage.clickNextButton();
        tablePaginationPage.clickBackButton();
        assertTrue(tablePaginationPage.isFirstPageSelected());

    }

    @Test
    void gotoSecondPage() {
        tablePaginationPage.clickNextButton();
        assertTrue(tablePaginationPage.isSecondPageSelected());
    }

    @Test
    void gotoThirdPage() {
        tablePaginationPage.clickNextButton();
        tablePaginationPage.clickNextButton();
        assertTrue(tablePaginationPage.isThirdPageSelected());
    }

    @AfterMethod
    void dispose() {
        SingletonDriver.close();
    }

}
