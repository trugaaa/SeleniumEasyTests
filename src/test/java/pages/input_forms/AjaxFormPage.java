package pages.input_forms;

import org.openqa.selenium.By;
import base.WebElementExtendedMethods;

import static base.JSAwaits.ajaxElementWait;
import static base.WebElementExtendedMethods.waitAndFindElement;

public class AjaxFormPage {

    public AjaxFormPage() {
        String AJAX_FORM_URL = "https://www.seleniumeasy.com/test/ajax-form-submit-demo.html";
        WebElementExtendedMethods.navigateTo(AJAX_FORM_URL);
    }

    /*
     * Selectors
     */
    private final By nameFieldSelector =  By.id("title");
    private final By commentFieldSelector =  By.id("description");
    private final By submitButtonSelector =  By.id("btn-submit");
    private final By necessaryNameSymbolSelector =  By.xpath("//span[@class='title-validation validation-error']");
    private final By resultSubmitMessageSelector =  By.id("submit-control");

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
