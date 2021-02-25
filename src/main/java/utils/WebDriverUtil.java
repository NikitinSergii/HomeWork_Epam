package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static driver.SingletonDriver.instance;


public class WebDriverUtil {

    public static JavascriptExecutor jse = (JavascriptExecutor)instance;
    public WebDriverWait wait;


    public WebDriverUtil() {
        wait = new WebDriverWait(instance, 5);
    }

//    public void waitTheElement(String xPath){
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
//    }



}
