package pages.table;

import org.openqa.selenium.By;
import base.SingletonDriver;

import static base.WebElementExtendedMethods.waitAndFindElement;

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
        return waitAndFindElement(new By.ByXPath(String.format("//ul[@id='myPager']/li/a[text()='%s']/parent::li", page)))
                .getAttribute("class").equals("active");
    }

    /*
    Actions
     */
    public void clickNextButton() {
        waitAndFindElement(nextPageButtonSelector).click();
    }

    public void clickBackButton() {
        waitAndFindElement(prevPageButtonSelector).click();
    }

    public boolean isFirstPageSelected() {
        return isActivePage(1) && !isActivePage(2) && !isActivePage(3)
                && waitAndFindElement(nextPageButtonSelector).isDisplayed();
    }

    public boolean isSecondPageSelected() {
        return !isActivePage(1) && isActivePage(2) && !isActivePage(3)
                && waitAndFindElement(prevPageButtonSelector).isDisplayed()
                && waitAndFindElement(nextPageButtonSelector).isDisplayed();
    }

    public boolean isThirdPageSelected() {
        return !isActivePage(1) && !isActivePage(2) && isActivePage(3)
                && waitAndFindElement(prevPageButtonSelector).isDisplayed();
    }
}
