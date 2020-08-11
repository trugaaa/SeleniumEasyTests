package tests.input_forms;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.input_forms.RadioButtonsPage;
import util.SingltonDriver;
import util.TestData;

import static org.testng.Assert.*;

public class RadioButtonsTests {
    RadioButtonsPage radioButtonsPage;

    @BeforeMethod
    void init() {
        radioButtonsPage = new RadioButtonsPage();
    }

    @Test
    public void radioOptionMaleCheck() {
        radioButtonsPage.checkMaleRadioOption();
        radioButtonsPage.radioOptionButtonClick();
        assertTrue(radioButtonsPage.isMaleOptionChecked());
        assertFalse(radioButtonsPage.isFemaleOptionChecked());
        assertEquals(radioButtonsPage.getRadioCheckResultText(), TestData.maleRadioResultText);
    }

    @Test
    public void radioOptionFemaleCheck() {
        radioButtonsPage.checkFemaleRadioOption();
        radioButtonsPage.radioOptionButtonClick();
        assertFalse(radioButtonsPage.isMaleOptionChecked());
        assertTrue(radioButtonsPage.isFemaleOptionChecked());
        assertEquals(radioButtonsPage.getRadioCheckResultText(), TestData.femaleRadioResultText);
    }

    @Test
    public void radioNoOptionChecked() {
        radioButtonsPage.radioOptionButtonClick();
        assertFalse(radioButtonsPage.isMaleOptionChecked());
        assertFalse(radioButtonsPage.isFemaleOptionChecked());
        assertEquals(radioButtonsPage.getRadioCheckResultText(), TestData.notCheckedRadioResultText);
    }

    @Test
    public void maleFirstAgeRadioGroupOptionCheck() {
        radioButtonsPage.checkRadioGroupMaleOption();
        assertTrue(radioButtonsPage.isMaleRadioGroupOptionChecked());
        assertFalse(radioButtonsPage.isFemaleRadioGroupOptionChecked());
        radioButtonsPage.checkFirstAgeGroupOption();
        assertTrue(radioButtonsPage.isFirstAgeRadioGroupOptionChecked());
        assertFalse(radioButtonsPage.isSecondAgeRadioGroupOptionChecked());
        assertFalse(radioButtonsPage.isThirdAgeRadioGroupOptionChecked());
        radioButtonsPage.radioGroupButtonClick();
        assertTrue(radioButtonsPage.isRadioGroupTextRight(TestData.male, TestData.firstAgeGroup));
    }

    @Test
    public void femaleSecondAgeRadioGroupOptionCheck() {
        radioButtonsPage.checkRadioGroupFemaleOption();
        assertTrue(radioButtonsPage.isFemaleRadioGroupOptionChecked());
        assertFalse(radioButtonsPage.isMaleRadioGroupOptionChecked());
        radioButtonsPage.checkSecondAgeGroupOption();
        assertFalse(radioButtonsPage.isFirstAgeRadioGroupOptionChecked());
        assertTrue(radioButtonsPage.isSecondAgeRadioGroupOptionChecked());
        assertFalse(radioButtonsPage.isThirdAgeRadioGroupOptionChecked());
        radioButtonsPage.radioGroupButtonClick();
        assertTrue(radioButtonsPage.isRadioGroupTextRight(TestData.female, TestData.secondAgeGroup));
    }

    @Test
    public void femaleThirdAgeRadioGroupOptionCheck() {
        radioButtonsPage.checkRadioGroupFemaleOption();
        assertTrue(radioButtonsPage.isFemaleRadioGroupOptionChecked());
        assertFalse(radioButtonsPage.isMaleRadioGroupOptionChecked());
        radioButtonsPage.checkRadioThirdAgeGroupOption();
        assertFalse(radioButtonsPage.isFirstAgeRadioGroupOptionChecked());
        assertFalse(radioButtonsPage.isSecondAgeRadioGroupOptionChecked());
        assertTrue(radioButtonsPage.isThirdAgeRadioGroupOptionChecked());
        radioButtonsPage.radioGroupButtonClick();
        assertTrue(radioButtonsPage.isRadioGroupTextRight(TestData.female, TestData.thirdAgeGroup));
    }

    @AfterMethod
    void dispose() {
        SingltonDriver.close();
    }
}
