package pages.datepickers;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import util.Months;
import base.WebElementExtendedMethods;

import static base.JSAwaits.ajaxElementWait;
import static base.WebElementExtendedMethods.waitAndFindElement;

public class JQueryDatePickerPage {

    public JQueryDatePickerPage() {
        WebElementExtendedMethods.navigateTo("https://www.seleniumeasy.com/test/jquery-date-picker-demo.html");
    }

    /*
    Selectors
     */
    private final By fromDateSelector = By.cssSelector("#from");
    private final By toDateSelector = By.cssSelector("#to");
    private final By prevMonthCalendarLinkSelector = By.cssSelector("#ui-datepicker-div>div>a[title='Prev']");
    private final By nextMonthCalendarLinkSelector = By.cssSelector("#ui-datepicker-div>div>a[title='Next']");
    private final By selectMonthSelector = By.cssSelector(".ui-datepicker-month");
    private final By selectYearSelector = By.cssSelector(".ui-datepicker-year");

    private By dateSelector(int dayNumber) {
        return By.xpath(String.format("//tbody/tr/td/*[text()=%s]", dayNumber));
    }

    /*
    Actions
     */
    public void fromDateFieldClick() {
        ajaxElementWait(fromDateSelector).click();
    }

    public void toDateFieldClick() {
        ajaxElementWait(toDateSelector).click();
    }

    public void nextMonthSelect() {
        waitAndFindElement(nextMonthCalendarLinkSelector).click();
    }

    public void prevMonthSelect() {
        waitAndFindElement(prevMonthCalendarLinkSelector).click();
    }

    private void selectMonth(Months month) {
        Select monthSelect = new Select(waitAndFindElement(selectMonthSelector));
        if (!monthSelect.getFirstSelectedOption().getText().equals(month.toString())) {
            waitAndFindElement(selectMonthSelector).click();
            monthSelect.selectByVisibleText(month.toString());
        }
    }

    private void selectYear(int year) {
        if (Integer.parseInt(waitAndFindElement(selectYearSelector).getText()) != year) {
            //Can be looped forever if start year > finish year
            if (year < Integer.parseInt(waitAndFindElement(selectYearSelector).getText())) {
                while (Integer.parseInt(waitAndFindElement(selectYearSelector).getText()) != year) {
                    prevMonthSelect();
                }
            } else {
                while (Integer.parseInt(waitAndFindElement(selectYearSelector).getText()) != year) {
                    nextMonthSelect();
                }
            }
        }
    }

    private void selectDate(int date) {
        waitAndFindElement(dateSelector(date)).click();
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
