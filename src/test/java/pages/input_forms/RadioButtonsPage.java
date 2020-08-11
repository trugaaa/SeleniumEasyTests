package pages.input_forms;

import org.openqa.selenium.By;
import util.SingltonDriver;
import util.WebElementExtendedMethods;

public class RadioButtonsPage {
    public final static String RADIO_BUTTONS_URL = "https://www.seleniumeasy.com/test/basic-radiobutton-demo.html";

    public RadioButtonsPage() {
        SingltonDriver.init(RADIO_BUTTONS_URL);
    }

    /*
     * String selectors
     */
    private final String radioOptionMaleXpath = "//label[@class=\"radio-inline\"]/input[@value=\"Male\"]";
    private final String radioOptionFemaleXpath = "//label[@class=\"radio-inline\"]/input[@value=\"Female\"]";
    private final String radioOptionCheckButtonId = "buttoncheck";
    private final String radioOptionResultTextXpath = "//div[@class='panel-body']/p[@class='radiobutton']";
    private final String radioGroupMaleOptionXpath = "//input[@type='radio'][@value='Male'][@name='gender']";
    private final String radioGroupFemaleOptionXpath = "//input[@type='radio'][@value='Female'][@name='gender']";
    private final String radioGroupAge0_5OptionXpath = "//input[@name='ageGroup' and @value='0 - 5']";
    private final String radioGroupAge5_15OptionXpath = "//input[@name='ageGroup' and @value='5 - 15']";
    private final String radioGroupAge15_50OptionXpath = "//input[@name='ageGroup' and @value='15 - 50']";
    private final String radioGroupResultButtonXpath = "//div[@class='panel-body']/button[@class='btn btn-default' and @type='button']";
    private final String radioGroupResultTextXpath = "//div[@class='panel-body']/p[@class='groupradiobutton']";

    /*
     * Selectors
     */
    private final By radioOptionMaleSelector = new By.ByXPath(radioOptionMaleXpath);
    private final By radioOptionFemaleSelector = new By.ByXPath(radioOptionFemaleXpath);
    private final By radioOptionButtonSelector = new By.ById(radioOptionCheckButtonId);
    private final By radioOptionResultTextSelector = new By.ByXPath(radioOptionResultTextXpath);
    private final By radioGroupMaleOptionSelector = new By.ByXPath(radioGroupMaleOptionXpath);
    private final By radioGroupFemaleOptionSelector = new By.ByXPath(radioGroupFemaleOptionXpath);
    private final By radioGroupFirstAgeGroupSelector = new By.ByXPath(radioGroupAge0_5OptionXpath);
    private final By radioGroupSecondAgeGroupSelector = new By.ByXPath(radioGroupAge5_15OptionXpath);
    private final By radioGroupThirdAgeGroupSelector = new By.ByXPath(radioGroupAge15_50OptionXpath);
    private final By radioGroupResultButtonSelector = new By.ByXPath(radioGroupResultButtonXpath);
    private final By radioGroupResultTextSelector = new By.ByXPath(radioGroupResultTextXpath);

    /*
     * Actions
     */
    public void radioOptionButtonClick() {
        SingltonDriver.waitAndFind(radioOptionButtonSelector).click();
    }

    public void checkMaleRadioOption() {
        SingltonDriver.waitAndFind(radioOptionMaleSelector).click();
    }

    public void checkFemaleRadioOption() {
        SingltonDriver.waitAndFind(radioOptionFemaleSelector).click();
    }

    public void checkRadioGroupMaleOption() {
        SingltonDriver.waitAndFind(radioGroupMaleOptionSelector).click();
    }

    public void checkRadioGroupFemaleOption() {
        SingltonDriver.waitAndFind(radioGroupFemaleOptionSelector).click();
    }

    public void checkFirstAgeGroupOption() {
        SingltonDriver.waitAndFind(radioGroupFirstAgeGroupSelector).click();
    }

    public void checkSecondAgeGroupOption() {
        SingltonDriver.waitAndFind(radioGroupSecondAgeGroupSelector).click();
    }

    public void checkRadioThirdAgeGroupOption() {
        SingltonDriver.waitAndFind(radioGroupThirdAgeGroupSelector).click();
    }

    public void radioGroupButtonClick() {
        SingltonDriver.waitAndFind(radioGroupResultButtonSelector).click();
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
        return SingltonDriver.waitAndFind(radioOptionResultTextSelector).getText();
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
        return SingltonDriver.waitAndFind(radioGroupResultTextSelector).getText().contains(gender) &&
                SingltonDriver.waitAndFind(radioGroupResultTextSelector).getText().contains(ageGroup);
    }
}
