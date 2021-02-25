package abstractClasses.page;

import abstractClasses.fragment.AbstractFragment;
import io.cucumber.java.ro.Si;
import org.openqa.selenium.By;

import java.util.List;

import static driver.SingletonDriver.getDriver;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.Assert.*;

public abstract class AbstractPage extends AbstractFragment {

    private String pageUrl;
    private String pageUrlPattern;
    private String BASE_LOCATOR = "//*[contains(text(), \"%s\")]";
    private String RESULT_ELEMENT = "//div[@class=\"tab search\"]/div";


    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public String setPageUrlPattern(String pageUrlPattern) {
        return this.pageUrlPattern = pageUrlPattern;
    }

    public String getPageUrlPattern() {
        return pageUrlPattern;
    }

    public boolean checkUrl() {
        boolean result = pageUrl.equals(getDriver().getCurrentUrl());
        if (!result && isNotEmpty(pageUrlPattern)) {
            return getDriver().getCurrentUrl().matches(pageUrlPattern);
        }
        return result;
    }
    public void deleteCookies(){
        getDriver().manage().deleteAllCookies();
    }

    public void getPage(){
        getDriver().get(getPageUrl());
    }

    public void clickElementByLocatorAndName(String menuName, String menuValue){
        getDriver().findElement(By.xpath(String.format(menuName, menuValue))).click();
    }
    public void clickElementByXPathLocator(By menuName){
        getDriver().findElement(menuName).click();
    }
    public void clickElementByName(String menuName){
        getDriver().findElement(By.xpath(String.format(BASE_LOCATOR, menuName))).click();
    }

    public void checkSearchResultAmount(List<String> resultList){
        assertTrue(getDriver().findElements(By.xpath(RESULT_ELEMENT)).size() == resultList.size());
    }

    public void putValueToTheField(By fieldLocator, String searchValue) {
        getDriver().findElement(fieldLocator).sendKeys(searchValue);
    }

}
