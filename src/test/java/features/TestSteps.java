package features;

import desktop.fragments.OrderSummaryFragment;
import desktop.fragments.SearchFragment;
import desktop.fragments.BasketPopUpFragment;
import desktop.pages.BasketPage;
import desktop.pages.CheckoutPage;
import desktop.pages.HomePage;
import desktop.pages.SearchPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class TestSteps {
    public static HomePage homePage = new HomePage();
    public static SearchFragment searchFragment = new SearchFragment();
    public static SearchPage searchPage = new SearchPage();
    public static BasketPopUpFragment basketPopUpFragment = new BasketPopUpFragment();
    public static BasketPage basketPage = new BasketPage();
    public static CheckoutPage checkoutPage = new CheckoutPage();
    public static OrderSummaryFragment orderSummaryFragment = new OrderSummaryFragment();


    @Given("I am an anonymous customer with clear cookies")
    public void iCanDeleteCookies() {
        homePage.deleteCookies();
    }

    @And("I open the {string}")
    public void getHomePage(String homePageUrl){
        homePage.getHomePage(homePageUrl);
    }

    @And("I search for {string}")
    public void searchSomething(String searchValue){
        searchFragment.search(searchValue);
    }

    @And("I am redirected to a \"Search page\"")
    public void isItSearchPage(){
        searchPage.searchPageIsVisible();
    }

    @And("Search results contain the following products")
    public void isResultsContainTheFollowingProducts(List<String> resultValue){
        searchPage.searchResultContains(resultValue);
    }

    @And("I apply the following search filters")
    public void setFilters(Map<String, String> dataTable){
        searchPage.setFilter(dataTable);
        searchPage.clickRefineResultsButton();
    }

    @And("Search results contain only the following products")
    public void searchResultContains(List<String> resultValue){
        searchPage.searchResultContains(resultValue);
        searchPage.checkSearchResultAmount(resultValue);
    }

    @And("I click \"Add to basket\" button for product with name {string}")
    public void addProductToBasket(String productName){
        searchPage.addProductToBasket(productName);
    }

    @And("I select {string} in basket pop-up")
    public void selectBasketCheckout(String basketCheckoutButton){
        basketPopUpFragment.clickBasketCheckoutButton(basketCheckoutButton);
    }

    @And("I am redirected to a \"Basket page\"")
    public void isItBasketPage(){
        basketPage.basketPageIsVisible();
    }

    @And("Basket order summary is as following:")
    public void checkOrderSummary(DataTable dataTable){
        basketPage.checkBasketSummary(dataTable);
    }

    @And("I click \"Checkout\" button on \"Basket\" page")
    public void checkCheckoutButton(){
        basketPage.clickCheckoutPage();
    }

    @And("I checkout as a new customer with email {string}")
    public void putEmail(String email){
        checkoutPage.putValueToEmailField(email);
    }

    @And("Checkout order summary is as following:")
    public void checkoutOrderSummary(DataTable dataTable){
        orderSummaryFragment.checkBasketSummary(dataTable);
    }

    @And("I fill delivery address information manually:")
    public void fillAddress(DataTable dataTable){
        checkoutPage.fillAddressInformation(dataTable);
    }

    @And("'Payment' section is disabled for editing")
    public void checkPaymentSection(){
        checkoutPage.checkPaymentSectionForEditing();
    }

    @When("I press {string} button on checkout")
    public void continueToPayment(String buttonName){
        checkoutPage.continueToPayment(buttonName);
    }

    @And("'Delivery Address' and 'Billing Address' sections are disabled for editing")
    public void checkAddressIsDisabled(){
        checkoutPage.checkAddressIsDisabled();
    }

    @And("I enter my card details")
    public void setPaymentDetails(Map<String, String> dataTable){
        checkoutPage.setPaymentDetails(dataTable);
    }
}
