package desktop.pages;

import abstractClasses.page.AbstractPage;
import static constants.Constants.BASE_URL;


public class HomePage extends AbstractPage {


    public HomePage() {
        setPageUrl(BASE_URL);
    }

    public HomePage getHomePage(String homePageUrl){
        setPageUrl(homePageUrl);
        getPage();
        return this;
    }
}
