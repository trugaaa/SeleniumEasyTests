package tests.input_forms;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.input_forms.SimpleFormPage;
import util.SingletonDriver;

import static org.testng.Assert.*;

public class SimpleFormTests {
    SimpleFormPage simpleFormPage;

    @BeforeMethod
    public void init() {
        simpleFormPage = new SimpleFormPage();
    }

    @Test
    public void successfulSingleFieldWrite() {
        simpleFormPage.writeUserMessage("123");
        assertEquals(simpleFormPage.getUserWroteResultMessage(), "123");
    }

    @Test
    public void unsuccessfulSingleFieldWrite() {
        simpleFormPage.writeUserMessage("123");
        assertEquals(simpleFormPage.getUserWroteResultMessage(), "123");
    }

    @Test
    public void writeAAndBWithResult() {
        simpleFormPage.writeAAndB(123, 321);
        assertEquals(simpleFormPage.getValueOfSumResult(), "444");
    }

    @Test
    public void onlyAValuePresent() {
        simpleFormPage.writeA(1);
        assertEquals(simpleFormPage.getValueOfSumResult(), "NaN");
    }

    @AfterMethod
    public void dispose() {
        SingletonDriver.close();
    }
}
