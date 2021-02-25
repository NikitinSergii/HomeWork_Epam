package desktop.pages;

import abstractClasses.page.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import static driver.SingletonDriver.getDriver;
import static driver.SingletonDriver.getDriverForApiTest;

public class KruidvatCartPage extends AbstractPage {

    public void deleteCookies(){
        getDriverForApiTest().manage().deleteAllCookies();
    }

    public void getPage(){
        getDriverForApiTest().get("https://www.kruidvat.nl/cart");
    }

    public KruidvatCartPage clickApproveCookies(){

        getDriverForApiTest().findElement(By.xpath("//button[@id=\"onetrust-accept-btn-handler\"]")).click();
        return this;
    }

    public KruidvatCartPage chooseLocation(){
        clickElementByName("Naar Kruidvat.nl");
        return this;
    }

    public KruidvatCartPage addCookiesToChrome(String cookiesValue){
        getDriverForApiTest().manage().addCookie(new Cookie("kvn-cart", cookiesValue));
        return this;
    }

    public void refreshPage(){
        getDriverForApiTest().navigate().refresh();
    }

    public boolean isProductPresentOnThePage(){
        return getDriverForApiTest().findElement(By.xpath("//div[@class=\"product-summary__description-name\"]")).isDisplayed();
    }
}

