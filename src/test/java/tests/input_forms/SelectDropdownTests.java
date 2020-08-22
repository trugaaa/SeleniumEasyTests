package tests.input_forms;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.input_forms.SelectDropdownPage;
import util.SingletonDriver;
import util.TestData;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SelectDropdownTests {
    SelectDropdownPage selectDropdownPage;

    @BeforeMethod
    void init() {
        selectDropdownPage = new SelectDropdownPage();
    }

    @Test
    void selectSunday() {
        selectDropdownPage.selectSunday();
        assertEquals(selectDropdownPage.getDropdownResultText(), TestData.expectedSundayResultString);
    }

    @Test
    void selectMultiValuesGetAllSelected() throws Exception {
        assertTrue(selectDropdownPage.isMultiSelectStateSupported());
        selectDropdownPage.selectMultiValues(prepareStatesList());
        selectDropdownPage.getAllSelectedButtonClick();
        assertEquals(selectDropdownPage.getResultMultiText(), TestData.expectedMultiDropdownGetAllSelectedText);
    }

    @Test
    void selectMultiValuesGetFirstSelected() throws Exception {
        selectDropdownPage.selectMultiValues(prepareStatesList());
        selectDropdownPage.getFirstSelectedButtonClick();
        assertEquals(selectDropdownPage.getResultMultiText(), TestData.expectedMultiDropdownGetFirstSelectedText);
    }

    @AfterMethod
    void dispose() {
        SingletonDriver.close();
    }

    private List<String> prepareStatesList() {
        ArrayList<String> valuesToBeChecked = new ArrayList<>();
        valuesToBeChecked.add(TestData.florida);
        valuesToBeChecked.add(TestData.texas);
        valuesToBeChecked.add(TestData.california);
        return valuesToBeChecked;
    }
}
