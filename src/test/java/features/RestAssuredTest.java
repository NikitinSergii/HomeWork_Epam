package features;

import com.fasterxml.jackson.core.JsonProcessingException;
import desktop.models.BasketRequestModel;
import desktop.models.BasketResponseModel;
import desktop.pages.KruidvatCartPage;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
//import resources/models/BasketRequestModel
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static driver.SingletonDriver.*;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertTrue;

public class RestAssuredTest {
    public static final String BASE_URI = "https://www.kruidvat.nl";
    public Map<String, Object> storage = new HashMap<>();
    private static final String PRODUCT_CODE = "2876350";
    private static final String QUANTITY = "1";
    private static final String CART_TYPE = "DIGITAL";

    @BeforeClass
    public static void setUp(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @AfterClass
    public static void quite() {
        getDriverForApiTest().quit();
    }

    @Test
    public void testAddProductToBasket() throws JsonProcessingException {
        postBasket();
        postBasketLineItem();
        connectApiAndWeb();
    }

    public void postBasket() throws JsonProcessingException {
        Response response = given().
                post(BASE_URI + "/api/v2/kvn/users/anonymous/carts");
        response.then().statusCode(201);
        response.then().assertThat().body(matchesJsonSchemaInClasspath("cart-schema.json"));
        storage.put("guid", response.jsonPath().get("guid"));

        //parse response as Model and verify response schema by Model
        ObjectMapper om = new ObjectMapper();
        BasketResponseModel responseAsModel = om.readValue(response.print(), BasketResponseModel.class);

        //assert cartType == "DIGITAL"
        assertEquals(responseAsModel.cartType, CART_TYPE);
    }

    public void postBasketLineItem(){
        Map<String, Object> value = new HashMap<>();
        value.put("code", PRODUCT_CODE);
        value.put("quantity", QUANTITY);

        Response response = given().
                contentType("application/json").
//                body(BasketRequestModel.class.getResourceAsStream("geg"), value)).
                body(getResolvedTemplate("postBasketRequest.txt", value)).
                post(BASE_URI + "/api/v2/kvn/users/anonymous/carts/" + storage.get("guid") + "/entries");
        response.then().statusCode(200);

        //verify response by Json schema
        response.then().assertThat().body(matchesJsonSchemaInClasspath("cart-with-line-item-schema.json"));

        //asserts
        assertEquals(response.jsonPath().get("entry.product.baseOptions[0].selected.code"), PRODUCT_CODE);
        assertEquals(response.jsonPath().get("quantity").toString(), QUANTITY);
    }

    public void connectApiAndWeb(){
        setDriver();
        KruidvatCartPage kruidvatCartPage = new KruidvatCartPage();
        kruidvatCartPage.deleteCookies();
        kruidvatCartPage.getPage();
        kruidvatCartPage.clickApproveCookies();
        kruidvatCartPage.chooseLocation();
        kruidvatCartPage.addCookiesToChrome((String) storage.get("guid"));
        kruidvatCartPage.refreshPage();

        //asserts that product is present on the Cart page
        assertTrue(kruidvatCartPage.isProductPresentOnThePage());
    }

    public static String getResolvedTemplate(String path, Map<String, Object> value)  {
        String template = findTemplate(path);
        StrSubstitutor strSubstitutor = new StrSubstitutor(value, "{{", "}}");
        return strSubstitutor.replace(template).replaceAll("\\s", "");
    }

    public static String findTemplate(String path) {
        try {
            ClassLoader classLoader = RestAssuredTest.class.getClassLoader();
            String fileContents = IOUtils.toString((InputStream) Objects.requireNonNull(classLoader.getResourceAsStream(path)),
                    Charset.defaultCharset());
            return fileContents;
        } catch (IOException var3) {
            throw new RuntimeException("Cannot read from file '" + path + "'", var3);
        }
    }
}
