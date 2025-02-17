package com.swaglab.constants;

public class Constants {

    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String HOME_PAGE_URL = "https://www.saucedemo.com/inventory.html";
    public static final String INDIVIDUAL_ITEM_PAGE_URL = "https://www.saucedemo.com/inventory-item.html";
    public static final Integer IMPLICITLY_WAIT_TIMEOUT = 5;
    
    public class FilePaths{
        public static final String LOGIN_TEST_DATA = "/src/test/java/com/swaglab/data/login/LoginTestData.xlsx";  
        public static final String ITEM_SORTING_TEST_DATA = "/src/test/java/com/swaglab/data/item/ItemSortingTestData.xlsx";  
        public static final String ITEM_DETAILS_TEST_DATA = "/src/test/java/com/swaglab/data/item/ItemDetailsTestData.xlsx";  
        public static final String CART_TEST_DATA = "/src/test/java/com/swaglab/data/cart/";
        public static final String ORDER_TEST_DATA = "/src/test/java/com/swaglab/data/order/";
    }

    public class OrderConfirmation{
        public static final String EXPECTED_CONFIRMATION_HEADER = "Thank you for your order!";
        public static final String EXPECTED_CONFIRMATION_TEXT = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
    } 

}
