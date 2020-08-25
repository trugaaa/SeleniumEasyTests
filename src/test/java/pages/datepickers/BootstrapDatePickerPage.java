package pages.datepickers;

import org.openqa.selenium.By;
import util.Months;
import base.SingletonDriver;
import util.UtilMethods;
import base.WebElementExtendedMethods;

import static base.WebElementExtendedMethods.waitAndFindElement;

public class BootstrapDatePickerPage {
    private final String BOOTSTRAP_DATE_PICKER_URL = "https://www.seleniumeasy.com/test/bootstrap-date-picker-demo.html";

    public BootstrapDatePickerPage() {
        SingletonDriver.init(BOOTSTRAP_DATE_PICKER_URL);
    }

    /*
    Selectors
     */
    private final By soloDatePickerSelector = new By.ByXPath("//div[@class='input-group date']/input[@placeholder='dd/mm/yyyy']");
    private final By currentDateSelector = new By.ByXPath("//tr/th[@class='today']");
    private final By clearDateSelector = new By.ByXPath("//tr/th[@class='clear']");
    private final By mainMonthsHeaderSoloDatePickerSelector = new By.ByXPath("//div[@class='datepicker-months']/table[@class='table-condensed']/thead/tr/th[@class='datepicker-switch']");
    private final By mainDaysHeaderSoloDatePickerSelector = new By.ByXPath("//div[@class='datepicker-days']/table[@class='table-condensed']/thead/tr/th[@class='datepicker-switch']");
    private final By startDateSelector = new By.ByXPath("//div[@id='datepicker']/input[@placeholder='Start date']");
    private final By finishDateSelector = new By.ByXPath("//div[@id='datepicker']/input[@placeholder='End date']");

    private By getYearSelector(int year) {
        return new By.ByXPath(String.format("//div[@class='datepicker-years']/table[@class='table-condensed']/tbody/tr/td/span[text()='%s']", year));
    }

    private By getMonthSelector(Months month) {
        return new By.ByXPath(String.format("//div[@class='datepicker-months']/table[@class='table-condensed']/tbody/tr/td/span[text()='%s']", month.toString()));
    }

    private By getDaySelector(int day) {
        return new By.ByXPath(String.format("//div[@class='datepicker-days']/table[@class='table-condensed']/tbody/tr/td[text()='%s']", day));
    }

    /*
    Actions
     */
    public void soloDatePickerClick() {
        waitAndFindElement(soloDatePickerSelector).click();
    }

    public void currentDateSelect() {
        soloDatePickerClick();
        waitAndFindElement(currentDateSelector).click();
    }

    public void clearDateSelect() {
        soloDatePickerClick();
        waitAndFindElement(clearDateSelector).click();
    }

    private void mainHeaderSoloDatePickerClick(By selector) {
        waitAndFindElement(selector).click();
    }

    public void yearSelectionDataPickerOpen() {
        mainHeaderSoloDatePickerClick(mainDaysHeaderSoloDatePickerSelector);
        mainHeaderSoloDatePickerClick(mainMonthsHeaderSoloDatePickerSelector);
    }

    public void selectDate(int year, Months month, int day) {
        waitAndFindElement(getYearSelector(year)).click();
        if (UtilMethods.getCurrentYear() >= year) {
            if (!WebElementExtendedMethods.getClass(getMonthSelector(month)).equals("month disabled")) {
                waitAndFindElement(getMonthSelector(month)).click();
                waitAndFindElement(getDaySelector(day)).click();
            }
        }
    }

    public void startDateClick() {
        waitAndFindElement(startDateSelector).click();
    }

    public void endDateClick() {
        waitAndFindElement(finishDateSelector).click();
    }

    /*
    Verifications
     */
    public String getSoloSelectedDate() {
        return WebElementExtendedMethods.getElementValue(soloDatePickerSelector);
    }

    public String getStartSelectedDate() {
        return WebElementExtendedMethods.getElementValue(startDateSelector);
    }

    public String getEndSelectedDate() {
        return WebElementExtendedMethods.getElementValue(finishDateSelector);
    }
}
