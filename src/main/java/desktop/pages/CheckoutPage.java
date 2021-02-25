package desktop.pages;

import abstractClasses.page.AbstractPage;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static driver.SingletonDriver.getDriver;
import static org.junit.Assert.assertTrue;

public class CheckoutPage extends AbstractPage {

    private static final By EMAIL_FIELD = By.xpath("//input[@name='emailAddress']");
    private static final By FAMILY_NAME = By.xpath("//input[@name='delivery-fullName']");
    private static final By DELIVERY_COUNTRY = By.xpath("(//dt/strong[contains(text(), 'Sub-total')]/../following-sibling::*)[2]");
    private static final By ADDRESS_LINE_1 = By.xpath("//input[@name='delivery-addressLine1']");
    private static final By ADDRESS_LINE_2 = By.xpath("//input[@name='delivery-addressLine2']");
    private static final By TOWN_CITY = By.xpath("//input[@name='delivery-city']");
    private static final By COUNTRY_STATE = By.xpath("//input[@name='delivery-county']");
    private static final By POSTCODE = By.xpath("//input[@name='delivery-postCode']");
    private static final By MANUAL_BUTTON = By.xpath("//button[@id='manualEntryDeliveryAddress']");
    private static final By CARD_TYPE = By.xpath("//select[@id='cardType']");
    private static final By BILLING_ADDRESS = By.xpath("//*[contains(text(), 'Different')]");
    private static final By EXPIRE_MONTH = By.xpath("//select[@id='visacardValidToMonth']");
    private static final By EXPIRE_YEAR = By.xpath("//select[@id='visacardValidToYear']");
    private static final By CARD_NUMBER_FIELD = By.xpath("(//label[contains(text(), 'Card Number')]/../../p/input)[1]");
    private static final By CARD_NAME_FIELD = By.xpath("(//label[contains(text(), 'Name on card')]/../../p/input)[1]");
    private static final By CVV_FIELD = By.xpath("(//span[contains(text(), 'CVV2')]/../../following-sibling::*//input)[1]");
    private static final By BUY_NOW_BOTTON = By.xpath("//input[@id='submitButton']");
    private static final String EXPIRE_MONTH_SELECTOR = "(//option[text()=\"%s\"])[1]";
    private static final String EXPIRE_YEAR_SELECTOR = "(//option[text()=\"%s\"])[1]";
    private static final String COUNTRY_SELECTOR = "(//option[text()=\"%s\"])[2]";
    private static final String DEFAULT_LOCATOR = "//a[contains(text(), \"%s\")]";
    private static final String VISA_PAYMENT_CARD = "//option[@name=\"%s\"]";
    private static final String PAYMENT_CARD_TYPE = "//select[@id='brandSelected']";
    private Actions builder = new Actions(getDriver());


    public void putValueToEmailField (String searchValue) {
        putValueToTheField(EMAIL_FIELD, searchValue);
    }

    public void fillAddressInformation(DataTable dataTable) {
        List<Map<String, String>> signUpForms = dataTable.asMaps(String.class, String.class);
        String fullName = signUpForms.get(0).get("Full name");
        String deliveryCountry = signUpForms.get(0).get("Delivery country");
        String addressLine1 = signUpForms.get(0).get("Address line 1");
        String addressLine2 = signUpForms.get(0).get("Address line 2");
        String townCity = signUpForms.get(0).get("Town/City");
        String countyState = signUpForms.get(0).get("County/State");
        String Postcode = signUpForms.get(0).get("Postcode");

        putValueToTheField(FAMILY_NAME, fullName);

        //I use scrolling here by JavaScript Executor because some elements con be not clickable if the are out of screen
        jse.executeScript("scroll(0, 250)");

        builder.click(getDriver().findElement(DELIVERY_COUNTRY))
                .click(getDriver().findElement(By.xpath(String.format(COUNTRY_SELECTOR, deliveryCountry))))
                .click(getDriver().findElement(MANUAL_BUTTON))
                .build().perform();

        putValueToTheField(ADDRESS_LINE_1, addressLine1);
        putValueToTheField(ADDRESS_LINE_2, addressLine2);
        putValueToTheField(TOWN_CITY, townCity);
        putValueToTheField(COUNTRY_STATE, countyState);
        putValueToTheField(POSTCODE, Postcode.toString());

    }

    public void checkPaymentSectionForEditing () {
        jse.executeScript("scroll(0, 400)");
        assertTrue(!getDriver().findElement(CARD_TYPE).isSelected());
    }

    public CheckoutPage continueToPayment(String buttonName) {
        clickElementByName(buttonName);
        return this;
    }

    public void checkAddressIsDisabled () {
        jse.executeScript("scroll(1000, 0)");
        assertTrue(!getDriver().findElement(DELIVERY_COUNTRY).isSelected());
        jse.executeScript("scroll(0, 1000)");
        assertTrue(!getDriver().findElement(BILLING_ADDRESS).isSelected());
        jse.executeScript("scroll(1000, 1500)");
    }

    public void setPaymentDetails(Map<String, String> filterValue){
        //select Card type
        getDriver().switchTo().frame("chase");
//        waitTheElement(PAYMENT_CARD_TYPE);
        getDriver().findElement(By.xpath(PAYMENT_CARD_TYPE)).click();
//        waitTheElement(String.format(VISA_PAYMENT_CARD, filterValue.get("Card Type").toLowerCase()));
        getDriver().findElement(By.xpath(String.format(VISA_PAYMENT_CARD, filterValue.get("Card Type").toLowerCase()))).click();
        //put cardNumber
        putValueToTheField(CARD_NUMBER_FIELD, filterValue.get("cardNumber"));
        //choose expire month
        clickElementByXPathLocator(EXPIRE_MONTH);
//        waitTheElement(String.format(EXPIRE_MONTH_SELECTOR, filterValue.get("Expiry Month")));
        getDriver().findElement(By.xpath(String.format(EXPIRE_MONTH_SELECTOR, filterValue.get("Expiry Month")))).click();
        //choose expire year
        clickElementByXPathLocator(EXPIRE_YEAR);
//        waitTheElement(String.format(EXPIRE_YEAR_SELECTOR, filterValue.get("Expiry Year")));
        getDriver().findElement(By.xpath(String.format(EXPIRE_YEAR_SELECTOR, filterValue.get("Expiry Year")))).click();
        //type Card name
        putValueToTheField(CARD_NAME_FIELD, filterValue.get("Name On Card"));
        //type CVV
        putValueToTheField(CVV_FIELD, filterValue.get("Cvv"));
        //press BuyNow button
        //this step can be implemented after step will bw added to the feature file
    }
}
