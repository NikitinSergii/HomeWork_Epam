package driver;

import org.openqa.selenium.chrome.ChromeOptions;

public class CapabilitiesHelper {

    public static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--test-type");
        chromeOptions.addArguments("--ignore-certificate-errors");
        return chromeOptions;
    }


}
