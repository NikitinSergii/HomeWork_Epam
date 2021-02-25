package desktop.pages;

import abstractClasses.page.AbstractPage;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;


import java.util.List;
import java.util.Map;

import static driver.SingletonDriver.getDriver;
import static org.junit.Assert.assertTrue;

public class BasketPage extends AbstractPage {

    private static final By BASKET_TITLE = By.xpath("//h1[contains(text(),'Your basket')]");
    private static final By DELIVERY_COST = By.xpath("//dl/dt[text()='Delivery cost']/following-sibling::*");
    private static final By TOTAL_COST = By.xpath("//dl/dt[text()='Total']/following-sibling::*");

    public void basketPageIsVisible(){
        assertTrue(getDriver().findElement(BASKET_TITLE).isDisplayed());
    }

    public void checkBasketSummary(DataTable dataTable) {
        List<Map<String, String>> signUpForms = dataTable.asMaps(String.class, String.class);
        String deliveryCost = signUpForms.get(0).get("Delivery cost");
        String totalCost = signUpForms.get(0).get("Total");
        isElementVisible(DELIVERY_COST);
        assertTrue(getDriver().findElement(DELIVERY_COST).getText().equals(deliveryCost));
        assertTrue(getDriver().findElement(TOTAL_COST).getText().equals(totalCost));
    }

    public BasketPage clickCheckoutPage(){
        clickElementByName("Checkout");
        return this;
    }

}
