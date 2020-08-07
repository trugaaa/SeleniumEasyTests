package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.input_forms.SimpleFormPage;
import util.SingltonDriver;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SimpleFormTests {
    SimpleFormPage simpleFormPage;

    @BeforeMethod
    public void init() {
        simpleFormPage = new SimpleFormPage();
    }

    @Test
    public void successfulSingleFieldWrite() {
        simpleFormPage.writeUserMessage("123");
        assertTrue(simpleFormPage.verifyWriteMessage("123"));
    }

    @Test
    public void unsuccessfulSingleFieldWrite() {
        simpleFormPage.writeUserMessage("123");
        assertFalse(simpleFormPage.verifyWriteMessage("231"));
    }

    @AfterMethod
    public void dispose() {
        SingltonDriver.close();
    }
}
