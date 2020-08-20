package tests.input_forms;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.input_forms.AjaxFormPage;
import util.SingletonDriver;
import util.TestData;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AjaxFormTests {
    AjaxFormPage ajaxFormPage;

    @BeforeMethod
    public void init() {
        ajaxFormPage = new AjaxFormPage();
    }

    @Test
    public void successFormSubmit() {
        ajaxFormPage.writeTitleAndComment(TestData.titleAjaxForm, TestData.commentAjaxForm);
        assertEquals(ajaxFormPage.isAjaxProcessingGetText(), TestData.ajaxProcessing);
        assertEquals(ajaxFormPage.isAjaxFormSuccessful(), TestData.successfulAjaxResultText);
    }

    @Test
    public void emptyTitle() {
        ajaxFormPage.submitButtonClick();
        assertTrue(ajaxFormPage.isNecessaryNameShown());
        assertTrue(ajaxFormPage.isTitleFieldErrorHighlighted());
    }

    @AfterMethod
    public void dispose() {
        SingletonDriver.close();
    }
}
