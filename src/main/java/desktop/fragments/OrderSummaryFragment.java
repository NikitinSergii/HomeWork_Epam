package desktop.fragments;

import abstractClasses.fragment.AbstractFragment;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;

import static driver.SingletonDriver.getDriver;
import static org.junit.Assert.assertTrue;

public class OrderSummaryFragment extends AbstractFragment {

    private static final By ORDER_SUMMARY_TITLE = By.xpath("(//h2[contains(text(), 'Order Summary')])[2]");
    private static final By SUBTOTAL = By.xpath("(//dt/strong[contains(text(), 'Sub-total')]/../following-sibling::*)[2]");
    private static final By DELIVERY = By.xpath("(//dt/strong[contains(text(), 'Delivery')]/../following-sibling::*)[2]");
    private static final By VAT = By.xpath("(//dt/strong[text()='VAT']/../following-sibling::*)[2]");
    private static final By TOTAL = By.xpath("(//dt/strong[contains(text(), 'Total')]/../following-sibling::*)[2]");


    public void checkBasketSummary(DataTable dataTable) {
        List<Map<String, String>> signUpForms = dataTable.asMaps(String.class, String.class);
        String subTotal = signUpForms.get(0).get("Sub-total");
        String delivery = signUpForms.get(0).get("Delivery");
        String vat = signUpForms.get(0).get("VAT");
        String total = signUpForms.get(0).get("Total");
        isElementVisible(ORDER_SUMMARY_TITLE);

        assertTrue(getDriver().findElement(SUBTOTAL).getText().equals(subTotal));
        assertTrue(getDriver().findElement(DELIVERY).getText().equals(delivery));
        assertTrue(getDriver().findElement(VAT).getText().equals(vat));
        assertTrue(getDriver().findElement(TOTAL).getText().equals(total));
    }
}
