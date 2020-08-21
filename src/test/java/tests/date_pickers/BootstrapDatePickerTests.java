package tests.date_pickers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.datepickers.BootstrapDatePickerPage;
import util.Months;
import util.SingletonDriver;
import util.UtilMethods;

import static org.testng.Assert.*;

public class BootstrapDatePickerTests {
    BootstrapDatePickerPage bootstrapDatePickerPage;

    @BeforeMethod
    void init() {
        bootstrapDatePickerPage = new BootstrapDatePickerPage();
    }

    @Test
    void currentDateSelect() {
        bootstrapDatePickerPage.currentDateSelect();
        assertEquals(bootstrapDatePickerPage.getSoloSelectedDate(), UtilMethods.getCurrentDate());
    }

    @Test
    void selectValidDate() {
        bootstrapDatePickerPage.soloDatePickerClick();
        bootstrapDatePickerPage.yearSelectionDataPickerOpen();
        bootstrapDatePickerPage.selectDate(2020, Months.Apr, 11);
        assertEquals(bootstrapDatePickerPage.getSoloSelectedDate(), "11/04/2020");
    }

    @Test
    void cantSelectSunday() {
        bootstrapDatePickerPage.soloDatePickerClick();
        bootstrapDatePickerPage.yearSelectionDataPickerOpen();
        bootstrapDatePickerPage.selectDate(2020, Months.May, 10);
        assertEquals(bootstrapDatePickerPage.getSoloSelectedDate(), "");
    }

    @Test
    void cantSelect2024() {
        bootstrapDatePickerPage.soloDatePickerClick();
        bootstrapDatePickerPage.yearSelectionDataPickerOpen();
        bootstrapDatePickerPage.selectDate(2024, Months.Apr, 12);
        assertEquals(bootstrapDatePickerPage.getSoloSelectedDate(), "");
    }

    /**
     * @info test valid until 2020 december
     */
    @Test
    void cantSelectDecember2020() {
        bootstrapDatePickerPage.soloDatePickerClick();
        bootstrapDatePickerPage.yearSelectionDataPickerOpen();
        bootstrapDatePickerPage.selectDate(2024, Months.Apr, 12);
        assertEquals(bootstrapDatePickerPage.getSoloSelectedDate(), "");
    }

    @Test
    void clearDateSelect() {
        assertEquals(bootstrapDatePickerPage.getSoloSelectedDate(), "");
        bootstrapDatePickerPage.currentDateSelect();
        assertEquals(bootstrapDatePickerPage.getSoloSelectedDate(), UtilMethods.getCurrentDate());
        bootstrapDatePickerPage.clearDateSelect();
        assertEquals(bootstrapDatePickerPage.getSoloSelectedDate(), "");
    }

    @Test
    void validStartEndDateSelect() {
        bootstrapDatePickerPage.startDateClick();
        bootstrapDatePickerPage.yearSelectionDataPickerOpen();
        bootstrapDatePickerPage.selectDate(2020, Months.Jan, 17);
        bootstrapDatePickerPage.endDateClick();
        bootstrapDatePickerPage.yearSelectionDataPickerOpen();
        bootstrapDatePickerPage.selectDate(2020, Months.Feb, 17);

        assertEquals(bootstrapDatePickerPage.getStartSelectedDate(), "17/01/2020");
        assertEquals(bootstrapDatePickerPage.getEndSelectedDate(), "17/02/2020");

    }

    @Test
    void cantSelectSundayStartEndDateSelect() {
        bootstrapDatePickerPage.startDateClick();
        bootstrapDatePickerPage.yearSelectionDataPickerOpen();
        bootstrapDatePickerPage.selectDate(2020, Months.Jan, 19);
        bootstrapDatePickerPage.endDateClick();
        bootstrapDatePickerPage.yearSelectionDataPickerOpen();
        bootstrapDatePickerPage.selectDate(2020, Months.Feb, 16);

        assertEquals(bootstrapDatePickerPage.getStartSelectedDate(), "");
        assertEquals(bootstrapDatePickerPage.getEndSelectedDate(), "");
    }


    @Test
    void startDateAfterEndDateSelect() {
        bootstrapDatePickerPage.startDateClick();
        bootstrapDatePickerPage.yearSelectionDataPickerOpen();
        bootstrapDatePickerPage.selectDate(2020, Months.Jul, 17);
        bootstrapDatePickerPage.endDateClick();
        bootstrapDatePickerPage.yearSelectionDataPickerOpen();
        bootstrapDatePickerPage.selectDate(2020, Months.Mar, 6);

        assertEquals(bootstrapDatePickerPage.getStartSelectedDate(), "06/03/2020");
        assertEquals(bootstrapDatePickerPage.getEndSelectedDate(), "06/03/2020");
    }

    @Test
    void selectedStartDayPreEnteredInEndDate() {
        bootstrapDatePickerPage.startDateClick();
        bootstrapDatePickerPage.yearSelectionDataPickerOpen();
        bootstrapDatePickerPage.selectDate(2020, Months.May, 14);
        assertEquals(bootstrapDatePickerPage.getStartSelectedDate(), "14/05/2020");
        assertEquals(bootstrapDatePickerPage.getEndSelectedDate(), "14/05/2020");
    }

    @AfterMethod
    void dispose() {
        SingletonDriver.close();
    }


}
