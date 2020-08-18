package tests.input_forms;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.input_forms.JQuerySelectDropdownPage;
import util.SingletonDriver;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JQuerySelectDropdownTests {
    JQuerySelectDropdownPage jQuerySelectDropdownPage;

    @BeforeMethod
    void init() {
        jQuerySelectDropdownPage = new JQuerySelectDropdownPage();
    }

    @Test
    void countrySelect() {
        jQuerySelectDropdownPage.selectCountry("In", "India");
        assertEquals(jQuerySelectDropdownPage.getSelectedCountry(), "India");

    }

    @Test
    void selectMultipleStates() {
        HashMap<String, String> stateMap = new HashMap<>();
        stateMap.put("Alas", "Alaska");
        stateMap.put("Ala", "Alabama");
        stateMap.put("Kan", "Kansas");

        jQuerySelectDropdownPage.selectMultipleStates(stateMap);
        assertTrue(jQuerySelectDropdownPage.areAllElementsSelected(stateMap));
    }

    @Test
    void uncheckState() {
        HashMap<String, String> stateMap = new HashMap<>();
        HashMap<String, String> elementNotDeleted = new HashMap<>();
        stateMap.put("Alas", "Alaska");
        stateMap.put("Ala", "Alabama");
        stateMap.put("Kan", "Kansas");
        elementNotDeleted.put("Ala", "Alabama");
        jQuerySelectDropdownPage.selectMultipleStates(stateMap);
        stateMap.remove("Ala");
        jQuerySelectDropdownPage.uncheckElements(stateMap);
        assertTrue(jQuerySelectDropdownPage.areAllElementsSelected(elementNotDeleted));
    }

    @Test
    void selectScriptedPHP() {
        jQuerySelectDropdownPage.selectDropDownWithCategory("Scripting languages", "PHP");
        assertEquals(jQuerySelectDropdownPage.getSelectedElementWithCategory(), "PHP");
    }

    @Test
    void selectProgrammingJava() {
        jQuerySelectDropdownPage.selectDropDownWithCategory("Programming languages", "Java");
        assertEquals(jQuerySelectDropdownPage.getSelectedElementWithCategory(), "Java");
    }

    @Test
    void selectTerritory() {
        jQuerySelectDropdownPage.selectWithNotSelectable("Nor", "Northern Mariana Islands");
        assertEquals(jQuerySelectDropdownPage.getSelectedTerritory(), "Northern Mariana Islands");
    }

    @Test
    void cantSelectTerritory() {
        jQuerySelectDropdownPage.selectWithNotSelectable("Gu", "Guam");
        assertEquals(jQuerySelectDropdownPage.getSelectedTerritory(), "American Samoa");
    }

    @AfterMethod
    void dispose() {
        SingletonDriver.close();
    }
}
