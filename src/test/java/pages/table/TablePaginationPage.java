package pages.table;

import org.openqa.selenium.By;
import util.SingletonDriver;

public class TablePaginationPage {
    private final String TABLE_PAGINATION_URL = "https://www.seleniumeasy.com/test/table-pagination-demo.html";

    public TablePaginationPage() {
        SingletonDriver.init(TABLE_PAGINATION_URL);
    }

    /*
    Selectors
     */
    private final By prevPageButtonSelector = new By.ByCssSelector("#myPager>li>.prev_link");
    private final By nextPageButtonSelector = new By.ByCssSelector("#myPager>li>.next_link");

    private Boolean isActivePage(int page) {
        return SingletonDriver.waitAndFindElement(new By.ByXPath(String.format("//ul[@id='myPager']/li/a[text()='%s']/parent::li", page)))
                .getAttribute("class").equals("active");
    }

    /*
    Actions
     */
    public void clickNextButton() {
        SingletonDriver.waitAndFindElement(nextPageButtonSelector).click();
    }

    public void clickBackButton() {
        SingletonDriver.waitAndFindElement(prevPageButtonSelector).click();
    }

    public boolean isFirstPageSelected() {
        return isActivePage(1) && !isActivePage(2) && !isActivePage(3)
                && SingletonDriver.waitAndFindElement(nextPageButtonSelector).isDisplayed();
    }

    public boolean isSecondPageSelected() {
        return !isActivePage(1) && isActivePage(2) && !isActivePage(3)
                && SingletonDriver.waitAndFindElement(prevPageButtonSelector).isDisplayed()
                && SingletonDriver.waitAndFindElement(nextPageButtonSelector).isDisplayed();
    }

    public boolean isThirdPageSelected() {
        return !isActivePage(1) && !isActivePage(2) && isActivePage(3)
                && SingletonDriver.waitAndFindElement(prevPageButtonSelector).isDisplayed();
    }
}
