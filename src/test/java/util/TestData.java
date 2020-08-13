package util;

public class TestData {

    /*
    Checkbox
     */
    public final static String singleCheckboxSuccessfulResultText = "Success - Check box is checked";
    public final static String checkAllButtonText = "Check All";

    /*
    Radio Buttons
     */
    public final static String maleRadioResultText = "Radio button 'Male' is checked";
    public final static String femaleRadioResultText = "Radio button 'Female' is checked";
    public final static String notCheckedRadioResultText = "Radio button is Not checked";
    public final static String firstAgeGroup = "0 - 5";
    public final static String secondAgeGroup = "5 - 15";
    public final static String thirdAgeGroup = "15 - 50";
    public final static String male = "Male";
    public final static String female = "Female";

    /*
    Dropdown
     */
    public final static String sunday = "Sunday";
    public final static String expectedSundayResultString = String.format("Day selected :- %s", sunday);
    public final static String california = "California";
    public final static String florida = "Florida";
    public final static String texas = "Texas";
    public final static String expectedMultiDropdownGetAllSelectedText = String.format("Options selected are : %s,%s,%s", california, florida, texas);
    public final static String expectedMultiDropdownGetFirstSelectedText = String.format("First selected option is : %s", california);
}
