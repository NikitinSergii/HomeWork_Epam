package desktop.fragments;

import abstractClasses.page.AbstractPage;
import org.openqa.selenium.By;

import static driver.SingletonDriver.getDriver;
import static utils.WebDriverUtil.jse;

public class BasketPopUpFragment extends AbstractPage {

    private static final String BASKET_CHECKOUT_BUTTON = "//a[contains(text(),\"%s\")]";


    public void clickBasketCheckoutButton(String buttonName){
        jse.executeScript("arguments[0].click();", getDriver().findElement(By.xpath(String.format(BASKET_CHECKOUT_BUTTON, buttonName))));
    }

}
