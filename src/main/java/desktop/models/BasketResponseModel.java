package desktop.models;

 import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
 import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1


 import java.util.List;


    public class BasketResponseModel{
        public String type;
        public String cartType;
        public String code;
        public List<Object> entries;
        public String guid;
        public int payWithPoints;
        public int pointTotalAfterOrder;
        public int redeemLoyaltyPoints;
        public int rewardLoyaltyPoints;
        public int totalItems;
        public TotalPrice totalPrice;
        public TotalPriceWithTax totalPriceWithTax;
        public boolean adultCustomer;
        public boolean checkoutBlocked;
        public boolean consistentAddress;
        public boolean containsOtcProducts;
        public boolean guestCheckoutAllowed;
        public boolean homeDeliveryOnly;
        public boolean placeOrderBlocked;

        public class TotalPrice{
            public String currencyIso;
            public double value;
        }

        public class TotalPriceWithTax{
            public String currencyIso;
            public double value;
        }
    }
