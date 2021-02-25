package desktop.pages;

import abstractClasses.page.AbstractPage;
import io.cucumber.java.eo.Se;
import org.openqa.selenium.By;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import static driver.SingletonDriver.getDriver;


public class SearchPage extends AbstractPage {

    private static final By SEARCH_TITLE = By.xpath("//h1[contains(text(),'Search results')]");
    private static final By REFINE_RESULTS_BUTTON = By.xpath("//button[contains(text(),'Refine results')]");
    private static final String DEFAULT_LOCATOR = "//a[contains(text(), \"%s\")]";
    private static final String BASE_DROPDOWN_LOCATOR = "//div/label[text()=\"%s\"]/following-sibling::select";
    private static final String BASE_DROPDOWN_MENU_LOCATOR = "//option[contains(text(), \"%s\")]";
    private static final String ADD_TO_BASKET_BY_PRODUCT_NAME_LOCATOR = "//h3/a[contains(text(),\"%s\")]/../../following-sibling::div/div/a";


    public void searchPageIsVisible(){
        assertTrue(getDriver().findElement(SEARCH_TITLE).isDisplayed());
    }

    public void searchResultContains(List<String> searchValue){
        for (String currentSearchValue : searchValue){
//            waitTheElement(String.format(DEFAULT_LOCATOR, currentSearchValue));
            assertTrue(getDriver().findElement(By.xpath(String.format(DEFAULT_LOCATOR, currentSearchValue))).isDisplayed());
        }
    }

    public void setFilter(Map<String, String> filterValue){
        for(Map.Entry<String,String> value : filterValue.entrySet()){
            setDropDownMenu(value.getKey(), value.getValue());
        }
    }

    public void setDropDownMenu(String menuName, String menuValue){
        clickElementByLocatorAndName(BASE_DROPDOWN_LOCATOR, menuName);
//        waitTheElement(String.format(BASE_DROPDOWN_MENU_LOCATOR, menuValue));
        clickElementByLocatorAndName(BASE_DROPDOWN_MENU_LOCATOR, menuValue);
    }

    public SearchPage clickRefineResultsButton(){
        clickElementByXPathLocator(REFINE_RESULTS_BUTTON);
        return this;
    }

    public SearchPage addProductToBasket(String productName){
        clickElementByLocatorAndName(ADD_TO_BASKET_BY_PRODUCT_NAME_LOCATOR, productName);
        return this;
    }
}
