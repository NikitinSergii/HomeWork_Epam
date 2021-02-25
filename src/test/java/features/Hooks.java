package features;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static driver.SingletonDriver.getDriver;
import static driver.SingletonDriver.setUpWebDriver;

public class Hooks {

    @Before
    public static void start() {
        setUpWebDriver();
    }

    @After
    public static void quite() {
        getDriver().quit();
    }
}
