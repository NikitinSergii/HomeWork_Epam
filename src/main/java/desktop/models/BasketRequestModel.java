package desktop.models;

import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
 import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */


public class BasketRequestModel{
    public Product product;
    public String quantity;

    public class Product{
        public String code;
    }
}