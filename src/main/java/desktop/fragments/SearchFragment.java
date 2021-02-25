package desktop.fragments;

import abstractClasses.fragment.AbstractFragment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static driver.SingletonDriver.getDriver;

public class SearchFragment extends AbstractFragment {

    private static final By SEARCH_FIELD = By.xpath("//input[contains(@placeholder, 'Search')]");
    private static final By SEARCH_BUTTON = By.xpath("//span[contains(text(),'Search')]");

    public SearchFragment search (String searchValue) {
        getDriver().findElement(SEARCH_FIELD).sendKeys(searchValue);
        getDriver().findElement(SEARCH_BUTTON).click();
        return this;
    }
}
