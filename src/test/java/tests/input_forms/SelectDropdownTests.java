package tests.input_forms;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.input_forms.SelectDropdownPage;
import util.SingltonDriver;
import util.TestData;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

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
    void selectMultiValuesGetAllSelected() {
        selectDropdownPage.selectMultiValues(prepareStatesList());
        selectDropdownPage.getAllSelectedButtonClick();
        assertEquals(selectDropdownPage.getResultMultiText(),TestData.expectedMultiDropdownGetAllSelectedText);
    }

    @Test
    void selectMultiValuesGetFirstSelected() {
        selectDropdownPage.selectMultiValues(prepareStatesList());
        selectDropdownPage.getFirstSelectedButtonClick();
        assertEquals(selectDropdownPage.getResultMultiText(),TestData.expectedMultiDropdownGetFirstSelectedText);
    }

    @AfterMethod
    void dispose() {
        SingltonDriver.close();
    }

    private List<String> prepareStatesList(){
        ArrayList<String> valuesToBeChecked = new ArrayList<>();
        valuesToBeChecked.add(TestData.florida);
        valuesToBeChecked.add(TestData.texas);
        valuesToBeChecked.add(TestData.california);
        return valuesToBeChecked;
    }
}
