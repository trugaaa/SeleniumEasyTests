package tests.date_pickers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.datepickers.JQueryDatePickerPage;
import util.Months;
import util.SingletonDriver;

import static org.testng.Assert.assertEquals;

public class JQueryDatePickerTests {
    JQueryDatePickerPage jQueryDatePickerPage;

    @BeforeMethod
    public void init() {
        jQueryDatePickerPage = new JQueryDatePickerPage();
    }

    @Test
    public void dateSelect() {
        jQueryDatePickerPage.fromDateFieldClick();
        jQueryDatePickerPage.selectDate(11, Months.Aug, 2020);
        jQueryDatePickerPage.toDateFieldClick();
        jQueryDatePickerPage.selectDate(12, Months.Aug, 2020);
        assertEquals(jQueryDatePickerPage.getFromSelectedDate(), "08/11/2020");
        assertEquals(jQueryDatePickerPage.getToSelectedDate(), "08/12/2020");
    }

    @Test
    public void anotherMonthSelect() {
        jQueryDatePickerPage.fromDateFieldClick();
        jQueryDatePickerPage.selectDate(11, Months.Oct, 2020);
        jQueryDatePickerPage.toDateFieldClick();
        jQueryDatePickerPage.selectDate(12, Months.Nov, 2020);
        assertEquals(jQueryDatePickerPage.getFromSelectedDate(), "10/11/2020");
        assertEquals(jQueryDatePickerPage.getToSelectedDate(), "11/12/2020");
    }

    @Test
    public void anotherYearSelect() {
        jQueryDatePickerPage.fromDateFieldClick();
        jQueryDatePickerPage.selectDate(11, Months.Oct, 2021);
        jQueryDatePickerPage.toDateFieldClick();
        jQueryDatePickerPage.selectDate(12, Months.Nov, 2022);
        assertEquals(jQueryDatePickerPage.getFromSelectedDate(), "10/11/2021");
        assertEquals(jQueryDatePickerPage.getToSelectedDate(), "11/12/2022");
    }

    @Test
    public void prevDateAfterNextSelect() {
        jQueryDatePickerPage.fromDateFieldClick();
        jQueryDatePickerPage.selectDate(11, Months.May, 2021);
        jQueryDatePickerPage.toDateFieldClick();
        jQueryDatePickerPage.selectDate(10, Months.May, 2021);
        assertEquals(jQueryDatePickerPage.getFromSelectedDate(), "05/11/2021");
        assertEquals(jQueryDatePickerPage.getToSelectedDate(), "");
    }

    @AfterMethod
    public void dispose() {
        SingletonDriver.close();
    }
}
