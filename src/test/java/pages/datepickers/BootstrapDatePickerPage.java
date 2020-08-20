package pages.datepickers;

import org.openqa.selenium.By;
import tests.date_pickers.BootstrapDatePickerTests;
import util.SingletonDriver;
import util.UtilMethods;
import util.WebElementExtendedMethods;

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

    private By getMonthSelector(BootstrapDatePickerTests.Months month) {
        return new By.ByXPath(String.format("//div[@class='datepicker-months']/table[@class='table-condensed']/tbody/tr/td/span[text()='%s']", month.toString()));
    }

    private By getDaySelector(int day) {
        return new By.ByXPath(String.format("//div[@class='datepicker-days']/table[@class='table-condensed']/tbody/tr/td[text()='%s']", day));
    }

    /*
    Actions
     */
    public void soloDatePickerClick() {
        SingletonDriver.waitAndFindElement(soloDatePickerSelector).click();
    }

    public void currentDateSelect() {
        soloDatePickerClick();
        SingletonDriver.waitAndFindElement(currentDateSelector).click();
    }

    public void clearDateSelect() {
        soloDatePickerClick();
        SingletonDriver.waitAndFindElement(clearDateSelector).click();
    }

    private void mainHeaderSoloDatePickerClick(By selector) {
        SingletonDriver.waitAndFindElement(selector).click();
    }

    public void yearSelectionDataPickerOpen() {
        mainHeaderSoloDatePickerClick(mainDaysHeaderSoloDatePickerSelector);
        mainHeaderSoloDatePickerClick(mainMonthsHeaderSoloDatePickerSelector);
    }

    public void selectDate(int year, BootstrapDatePickerTests.Months month, int day) {
        SingletonDriver.waitAndFindElement(getYearSelector(year)).click();
        if (UtilMethods.getCurrentYear() >= year) {
            if (!WebElementExtendedMethods.getClass(getMonthSelector(month)).equals("month disabled")) {
                SingletonDriver.waitAndFindElement(getMonthSelector(month)).click();
                SingletonDriver.waitAndFindElement(getDaySelector(day)).click();
            }
        }
    }

    public void startDateClick() {
        SingletonDriver.waitAndFindElement(startDateSelector).click();
    }

    public void endDateClick() {
        SingletonDriver.waitAndFindElement(finishDateSelector).click();
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
