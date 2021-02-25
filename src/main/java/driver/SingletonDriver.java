package driver;

import desktop.pages.HomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.SessionId;

import java.util.concurrent.TimeUnit;

import static constants.Constants.IMPLICITLY_WAIT_TIMEOUT;
import static driver.CapabilitiesHelper.getChromeOptions;


public class SingletonDriver {

    public static WebDriver instance;
    public static String filePath;
    public static JavascriptExecutor jse;


    public static WebDriver getDriverForApiTest() {
        if (instance == null) {
            instance = setDriver();
        }
        return instance;
    }

    public static WebDriver setDriver(){
        System.setProperty("webdriver.chrome.driver", "src//main//resources//drivers//chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model

        instance = new ChromeDriver(options);
        instance.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return instance;
    }

    public static WebDriver getDriver() {
        if (instance == null) {
            instance = setUpWebDriver();
        }
        return instance;
    }

    public static WebDriver setUpWebDriver() {

        String os = System.getProperty("OS_NAME").toLowerCase();
        if (os.contains("win")){
            //Operating system is based on Windows
            String driverName = System.getProperty("BROWSER_NAME");
            switch (driverName) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("start-maximized"); // open Browser in maximized mode
                    options.addArguments("disable-infobars"); // disabling infobars
                    options.addArguments("--disable-extensions"); // disabling extensions
                    options.addArguments("--disable-gpu"); // applicable to windows os only
                    options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                    options.addArguments("--no-sandbox"); // Bypass OS security model

                    instance = new ChromeDriver(options);
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "src//main//resources//drivers//geckodriver.exe");
                    FirefoxProfile firefoxProfile = new FirefoxProfile();
                    firefoxProfile.setPreference("browser.download.manager.showWhenStarting",false);
                    firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete",false);
                    firefoxProfile.setPreference("browser.download.dir","C:\\Users\\Administrator\\Downloads\\");
                    firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf, application/vnd.ms-excel");
                    firefoxProfile.setPreference("pdfjs.disabled", true);
                    firefoxProfile.setPreference("plugin.scan.Acrobat", "99.0");
                    firefoxProfile.setPreference("plugin.scan.plid.all", false);
                    System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
                    System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
                    DesiredCapabilities dc = DesiredCapabilities.firefox();
                    dc.setCapability(FirefoxDriver.PROFILE, firefoxProfile);

                    instance = new FirefoxDriver(dc);
                    instance.manage().window().maximize();
                    break;
                default:
                    throw new IllegalStateException("This driver is not supported");
            }
        }
        else if (os.contains("nix") || os.contains("aix") || os.contains("nux") || os.contains("NUX")){
            //Operating system is based on Linux/Unix/*AIX
            String driverName = System.getProperty("ENV_VAR");
            switch (driverName) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "src//test//resources//drivers//chromedriver");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("start-maximized"); // open Browser in maximized mode
                    options.addArguments("disable-infobars"); // disabling infobars
                    options.addArguments("--disable-extensions"); // disabling extensions
                    options.addArguments("--disable-gpu"); // applicable to windows os only
                    options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                    options.addArguments("--no-sandbox"); // Bypass OS security model
                    instance = new ChromeDriver(options);
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "src//test//resources//drivers//geckodriver.exe");
                    FirefoxProfile firefoxProfile = new FirefoxProfile();
                    firefoxProfile.setPreference("browser.download.manager.showWhenStarting",false);
                    firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete",false);
                    firefoxProfile.setPreference("browser.download.dir","C:\\Users\\Administrator\\Downloads\\");
                    firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf, application/vnd.ms-excel");
                    firefoxProfile.setPreference("pdfjs.disabled", true);
                    firefoxProfile.setPreference("plugin.scan.Acrobat", "99.0");
                    firefoxProfile.setPreference("plugin.scan.plid.all", false);
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setProfile(firefoxProfile);
                    instance = new FirefoxDriver(firefoxOptions);
                    instance.manage().window().maximize();
                    break;
                default:
                    throw new IllegalStateException("This driver is not supported");
            }
        }
        jse = (JavascriptExecutor)instance;
        instance.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return instance;
    }
}
