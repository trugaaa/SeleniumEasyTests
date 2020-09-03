package pages.input_forms;

import org.openqa.selenium.By;
import base.WebElementExtendedMethods;

import static base.WebElementExtendedMethods.waitAndFindElement;

public class RadioButtonsPage {
    public RadioButtonsPage() {
        WebElementExtendedMethods.navigateTo("https://www.seleniumeasy.com/test/basic-radiobutton-demo.html");
    }
    
    /*
     * Selectors
     */
    private final By radioOptionMaleSelector =  By.xpath("//label[@class=\"radio-inline\"]/input[@value=\"Male\"]");
    private final By radioOptionFemaleSelector =  By.xpath("//label[@class=\"radio-inline\"]/input[@value=\"Female\"]");
    private final By radioOptionButtonSelector =  By.id("buttoncheck");
    private final By radioOptionResultTextSelector =  By.xpath("//div[@class='panel-body']/p[@class='radiobutton']");
    private final By radioGroupMaleOptionSelector =  By.xpath("//input[@type='radio'][@value='Male'][@name='gender']");
    private final By radioGroupFemaleOptionSelector =  By.xpath("//input[@type='radio'][@value='Female'][@name='gender']");
    private final By radioGroupFirstAgeGroupSelector =  By.xpath("//input[@name='ageGroup' and @value='0 - 5']");
    private final By radioGroupSecondAgeGroupSelector =  By.xpath("//input[@name='ageGroup' and @value='5 - 15']");
    private final By radioGroupThirdAgeGroupSelector =  By.xpath("//input[@name='ageGroup' and @value='15 - 50']");
    private final By radioGroupResultButtonSelector =  By.xpath("//div[@class='panel-body']/button[@class='btn btn-default' and @type='button']");
    private final By radioGroupResultTextSelector =  By.xpath("//div[@class='panel-body']/p[@class='groupradiobutton']");

    /*
     * Actions
     */
    public void radioOptionButtonClick() {
        waitAndFindElement(radioOptionButtonSelector).click();
    }

    public void checkMaleRadioOption() {
        waitAndFindElement(radioOptionMaleSelector).click();
    }

    public void checkFemaleRadioOption() {
        waitAndFindElement(radioOptionFemaleSelector).click();
    }

    public void checkRadioGroupMaleOption() {
        waitAndFindElement(radioGroupMaleOptionSelector).click();
    }

    public void checkRadioGroupFemaleOption() {
        waitAndFindElement(radioGroupFemaleOptionSelector).click();
    }

    public void checkFirstAgeGroupOption() {
        waitAndFindElement(radioGroupFirstAgeGroupSelector).click();
    }

    public void checkSecondAgeGroupOption() {
        waitAndFindElement(radioGroupSecondAgeGroupSelector).click();
    }

    public void checkRadioThirdAgeGroupOption() {
        waitAndFindElement(radioGroupThirdAgeGroupSelector).click();
    }

    public void radioGroupButtonClick() {
        waitAndFindElement(radioGroupResultButtonSelector).click();
    }

    /*
     * Verifications
     */
    public Boolean isMaleOptionChecked() {
        return WebElementExtendedMethods.isElementChecked(radioOptionMaleSelector);
    }

    public Boolean isFemaleOptionChecked() {
        return WebElementExtendedMethods.isElementChecked(radioOptionFemaleSelector);
    }

    public String getRadioCheckResultText() {
        return waitAndFindElement(radioOptionResultTextSelector).getText();
    }

    public Boolean isMaleRadioGroupOptionChecked() {
        return WebElementExtendedMethods.isElementChecked(radioGroupMaleOptionSelector);
    }

    public Boolean isFemaleRadioGroupOptionChecked() {
        return WebElementExtendedMethods.isElementChecked(radioGroupFemaleOptionSelector);
    }

    public Boolean isFirstAgeRadioGroupOptionChecked() {
        return WebElementExtendedMethods.isElementChecked(radioGroupFirstAgeGroupSelector);
    }

    public Boolean isSecondAgeRadioGroupOptionChecked() {
        return WebElementExtendedMethods.isElementChecked(radioGroupSecondAgeGroupSelector);
    }

    public Boolean isThirdAgeRadioGroupOptionChecked() {
        return WebElementExtendedMethods.isElementChecked(radioGroupThirdAgeGroupSelector);
    }

    public Boolean isRadioGroupTextRight(String gender, String ageGroup) {
        return waitAndFindElement(radioGroupResultTextSelector).getText().contains(gender) &&
                waitAndFindElement(radioGroupResultTextSelector).getText().contains(ageGroup);
    }
}
