package pages.datepickers;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import util.Months;
import util.SingletonDriver;
import util.WebElementExtendedMethods;

public class JQueryDatePickerPage {
    private final String JQUERY_DATE_PICKER_URL = "https://www.seleniumeasy.com/test/jquery-date-picker-demo.html";

    public JQueryDatePickerPage() {
        SingletonDriver.init(JQUERY_DATE_PICKER_URL);
    }

    /*
    Selectors
     */
    private final By fromDateSelector = new By.ByCssSelector("#from");
    private final By toDateSelector = new By.ByCssSelector("#to");
    private final By prevMonthCalendarLinkSelector = new By.ByCssSelector("#ui-datepicker-div>div>a[title='Prev']");
    private final By nextMonthCalendarLinkSelector = new By.ByCssSelector("#ui-datepicker-div>div>a[title='Next']");
    private final By selectMonthSelector = new By.ByCssSelector(".ui-datepicker-month");
    private final By selectYearSelector = new By.ByCssSelector(".ui-datepicker-year");

    private By dateSelector(int dayNumber) {
        return new By.ByXPath(String.format("//tbody/tr/td/*[text()=%s]", dayNumber));
    }

    /*
    Actions
     */
    public void fromDateFieldClick() {
        SingletonDriver.ajaxElementWait(fromDateSelector).click();
    }

    public void toDateFieldClick() {
        SingletonDriver.ajaxElementWait(toDateSelector).click();
    }

    public void nextMonthSelect() {
        SingletonDriver.waitAndFindElement(nextMonthCalendarLinkSelector).click();
    }

    public void prevMonthSelect() {
        SingletonDriver.waitAndFindElement(prevMonthCalendarLinkSelector).click();
    }

    private void selectMonth(Months month) {
        Select monthSelect = new Select(SingletonDriver.waitAndFindElement(selectMonthSelector));
        if (!monthSelect.getFirstSelectedOption().getText().equals(month.toString())) {
            SingletonDriver.waitAndFindElement(selectMonthSelector).click();
            monthSelect.selectByVisibleText(month.toString());
        }
    }

    private void selectYear(int year) {
        if (Integer.parseInt(SingletonDriver.waitAndFindElement(selectYearSelector).getText()) != year) {
            //Can be looped forever if start year > finish year
            if (year < Integer.parseInt(SingletonDriver.waitAndFindElement(selectYearSelector).getText())) {
                while (Integer.parseInt(SingletonDriver.waitAndFindElement(selectYearSelector).getText()) != year) {
                    prevMonthSelect();
                }
            } else {
                while (Integer.parseInt(SingletonDriver.waitAndFindElement(selectYearSelector).getText()) != year) {
                    nextMonthSelect();
                }
            }
        }
    }

    private void selectDate(int date) {
        SingletonDriver.waitAndFindElement(dateSelector(date)).click();
    }

    public void selectDate(int date, Months month, int year) {
        selectYear(year);
        selectMonth(month);
        selectDate(date);
    }

    /*
    Verifications
     */
    public String getFromSelectedDate() {
        return WebElementExtendedMethods.getElementValue(fromDateSelector);
    }

    public String getToSelectedDate() {
        return WebElementExtendedMethods.getElementValue(toDateSelector);
    }
}
