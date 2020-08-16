package tests.input_forms;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.input_forms.CheckboxPage;
import util.SingletonDriver;
import util.TestData;

import static org.testng.Assert.*;

public class CheckboxTests {
    CheckboxPage checkboxPage;

    @BeforeMethod
    void init() {
        checkboxPage = new CheckboxPage();
    }

    @Test
    void successfulSingleCheckboxCheck() {
        checkboxPage.checkSingleCheckboxClick();
        assertEquals(checkboxPage.getCheckboxResultText(), TestData.singleCheckboxSuccessfulResultText);
    }

    @Test
    void checkAllCheckboxes() {
        checkboxPage.checkUncheckButtonClick();
        assertTrue(checkboxPage.allOptionsChecked());
        assertFalse(checkboxPage.isButtonSetForCheckedAll());
    }

    @Test
    void oneOptionUnchecked() {
        checkboxPage.checkUncheckButtonClick();
        assertTrue(checkboxPage.allOptionsChecked());
        checkboxPage.checkFirstOption();
        assertTrue(checkboxPage.isButtonSetForCheckedAll());
    }

    @AfterMethod
    void dispose() {
        SingletonDriver.close();
    }
}
