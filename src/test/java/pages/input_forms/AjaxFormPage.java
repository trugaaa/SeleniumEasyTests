package pages.input_forms;

import org.openqa.selenium.By;
import base.SingletonDriver;
import base.WebElementExtendedMethods;

import static base.WebElementExtendedMethods.ajaxElementWait;
import static base.WebElementExtendedMethods.waitAndFindElement;

public class AjaxFormPage {
    private final String AJAX_FORM_URL = "https://www.seleniumeasy.com/test/ajax-form-submit-demo.html";

    public AjaxFormPage() {
        SingletonDriver.init(AJAX_FORM_URL);
    }

    /*
     * Selectors
     */
    private final By nameFieldSelector = new By.ById("title");
    private final By commentFieldSelector = new By.ById("description");
    private final By submitButtonSelector = new By.ById("btn-submit");
    private final By necessaryNameSymbolSelector = new By.ByXPath("//span[@class='title-validation validation-error']");
    private final By resultSubmitMessageSelector = new By.ById("submit-control");

    /*
    Actions
     */
    public void writeTitle(String title) {
        waitAndFindElement(nameFieldSelector).sendKeys(title);
    }

    public void writeComment(String comment) {
        waitAndFindElement(commentFieldSelector).sendKeys(comment);
    }

    public void submitButtonClick() {
        waitAndFindElement(submitButtonSelector).click();
    }

    public void writeTitleAndComment(String title, String comment) {
        writeTitle(title);
        writeComment(comment);
        submitButtonClick();
    }

    /*
    Verifications
     */
    public Boolean isTitleFieldErrorHighlighted() {
        return WebElementExtendedMethods.isBorderColorRed(nameFieldSelector);
    }

    public String isAjaxProcessingGetText() {
        return waitAndFindElement(resultSubmitMessageSelector).getText();
    }


    public String isAjaxFormSuccessful() {
        return ajaxElementWait(resultSubmitMessageSelector).getText();
    }

    public Boolean isNecessaryNameShown() {
        return ajaxElementWait(necessaryNameSymbolSelector).isDisplayed();
    }


}
