package abstractClasses.fragment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.WebDriverUtil;

import static driver.SingletonDriver.getDriver;
import static org.junit.Assert.assertTrue;

public abstract class AbstractFragment extends WebDriverUtil {

    private WebElement rootElement;

    public AbstractFragment() {
        PageFactory.initElements(getDriver(), this);
    }

    public void setRootElement(WebElement element) {
        this.rootElement = element;
    }

    public WebElement getRootElement() {
        return rootElement;
    }

    public AbstractFragment clickElement(By byLocator) {
        rootElement.findElement(byLocator);
        return this;
    }

    public void isElementVisible(By selector){
        assertTrue(getDriver().findElement(selector).isDisplayed());
    }
}
