package pages.input_forms;

import org.openqa.selenium.By;
import util.SingletonDriver;
import util.WebElementExtendedMethods;

public class RadioButtonsPage {
    public final static String RADIO_BUTTONS_URL = "https://www.seleniumeasy.com/test/basic-radiobutton-demo.html";

    public RadioButtonsPage() {
        SingletonDriver.init(RADIO_BUTTONS_URL);
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
        SingletonDriver.waitAndFindElement(radioOptionButtonSelector).click();
    }

    public void checkMaleRadioOption() {
        SingletonDriver.waitAndFindElement(radioOptionMaleSelector).click();
    }

    public void checkFemaleRadioOption() {
        SingletonDriver.waitAndFindElement(radioOptionFemaleSelector).click();
    }

    public void checkRadioGroupMaleOption() {
        SingletonDriver.waitAndFindElement(radioGroupMaleOptionSelector).click();
    }

    public void checkRadioGroupFemaleOption() {
        SingletonDriver.waitAndFindElement(radioGroupFemaleOptionSelector).click();
    }

    public void checkFirstAgeGroupOption() {
        SingletonDriver.waitAndFindElement(radioGroupFirstAgeGroupSelector).click();
    }

    public void checkSecondAgeGroupOption() {
        SingletonDriver.waitAndFindElement(radioGroupSecondAgeGroupSelector).click();
    }

    public void checkRadioThirdAgeGroupOption() {
        SingletonDriver.waitAndFindElement(radioGroupThirdAgeGroupSelector).click();
    }

    public void radioGroupButtonClick() {
        SingletonDriver.waitAndFindElement(radioGroupResultButtonSelector).click();
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
        return SingletonDriver.waitAndFindElement(radioOptionResultTextSelector).getText();
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
        return SingletonDriver.waitAndFindElement(radioGroupResultTextSelector).getText().contains(gender) &&
                SingletonDriver.waitAndFindElement(radioGroupResultTextSelector).getText().contains(ageGroup);
    }
}
