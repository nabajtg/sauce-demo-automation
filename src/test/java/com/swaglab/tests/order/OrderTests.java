package com.swaglab.tests.order;

import java.util.List;

import org.testng.annotations.Test;

import com.swaglab.constants.Constants.FilePaths;
import com.swaglab.constants.Constants.OrderConfirmation;
import com.swaglab.data.order.OrderData;
import com.swaglab.domains.Item;
import com.swaglab.pages.CartPage;
import com.swaglab.pages.CheckoutPage;
import com.swaglab.pages.HomePage;
import com.swaglab.pages.LoginPage;
import com.swaglab.pages.OrderConfirmationPage;
import com.swaglab.pages.OrderOverviewPage;
import com.swaglab.tests.base.BaseTest;
import com.swaglab.utils.JsonUtil;

public class OrderTests extends BaseTest {

    @Test
    public void ORDER001_verifyOderFlowForSingleItem() {
        placeOrderAndVerify(testCaseId);
    }

    @Test
    public void ORDER002_verifyOderFlowForSingleItem() {
        placeOrderAndVerify(testCaseId);
    }

    private void placeOrderAndVerify(String testCaseId){
        OrderData orderData = JsonUtil.convertJsonToPojo(
                JsonUtil.readFile(FilePaths.ORDER_TEST_DATA + testCaseId), OrderData.class);
        HomePage homePage = loginAndAddToCart(orderData);
        List<Item> itemsInHomePage = homePage.getItemDetails(orderData.getItems());
        CartPage cartPage = gotoCartAndVerify(homePage, itemsInHomePage);
        OrderOverviewPage orderOverviewPage = checkout(cartPage, orderData);
        verifyOverViewPage(orderOverviewPage, itemsInHomePage);
        OrderConfirmationPage orderConfirmationPage = orderOverviewPage.confirmOrder();
        verifyOrderConfirmation(orderConfirmationPage);
    }

    private void verifyOrderConfirmation(OrderConfirmationPage orderConfirmationPage){
        String confirmationHeaderText = orderConfirmationPage.getConfirmationHeaderText();
        assertUtil.assertEquals(confirmationHeaderText, OrderConfirmation.EXPECTED_CONFIRMATION_HEADER,
            "Order Confirmation Header"
        );

        String confirmationText = orderConfirmationPage.getConfirmationText();
        assertUtil.assertEquals(confirmationText, OrderConfirmation.EXPECTED_CONFIRMATION_TEXT,
            "Order Confirmation Text"
        );
    }

    private void verifyOverViewPage(OrderOverviewPage orderOverviewPage, 
        List<Item> itemsInHomePage) {
        
        int noOfItemsInOverview = orderOverviewPage.getNoOfItems();
        assertUtil.assertEquals(noOfItemsInOverview, itemsInHomePage.size(),
            "No of Items in Overview Page");
        
        if(noOfItemsInOverview > 0){
            List<Item> itemsAdded = orderOverviewPage.getItems();
            itemsInHomePage.forEach(item->{
                
                Item itemAdded = itemsAdded.stream()
                        .filter(cartItem -> cartItem.getName().equals(item.getName()))
                        .findFirst().get();
                assertUtil.assertNotNull(itemAdded, "Item found in Cart");
                assertUtil.assertEquals(itemAdded.getName(), item.getName(),
                        "Item Name");
                assertUtil.assertEquals(itemAdded.getPrice(), item.getPrice(),
                        "Item Price");
                assertUtil.assertEquals(itemAdded.getDescription(),
                        item.getDescription(), "Item Description");
                
            });
        }

        Double expectedItemTotal = itemsInHomePage.stream().map(item-> item.getPrice())
                                            .reduce(0.0, Double::sum);
        Double actualItemTotal = orderOverviewPage.getItemTotalAmount();
        assertUtil.assertEquals(actualItemTotal, expectedItemTotal, "Item Total Amount");

        Double taxAmount = orderOverviewPage.getTaxAmount();
        assertUtil.assertTrue(taxAmount > 0, "Tax amount is not zero");

        Double totalAmount = orderOverviewPage.getTotalAmount();
        assertUtil.assertEquals(totalAmount, actualItemTotal+taxAmount, "Total Amount");
        

    }

    private OrderOverviewPage checkout(CartPage cartPage, OrderData orderData) {
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        return checkoutPage.enterCustomerDetailsAndContinue(
                orderData.getCustomerDetails().getFirstName(),
                orderData.getCustomerDetails().getLastName(),
                orderData.getCustomerDetails().getPostalCode());

    }

    private HomePage loginAndAddToCart(OrderData orderData) {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(orderData.getUser());

        orderData.getItems().forEach(itemName -> {
            homePage.clickOnAddRemoveButton(itemName);
        });

        return homePage;
    }

    private CartPage gotoCartAndVerify(HomePage homePage, List<Item> items) {
        CartPage cartPage = homePage.goToCart();
        List<Item> itemsInCart = cartPage.getItemsInCart();

        assertUtil.assertEquals(itemsInCart.size(), items.size(),
                "No Of items in Cart");

        items.forEach(item -> {
            if (itemsInCart.size() > 0) {
                Item itemInCart = itemsInCart.stream()
                        .filter(cartItem -> cartItem.getName().equals(item.getName()))
                        .findFirst().get();
                assertUtil.assertNotNull(itemInCart, "Item found in Cart");
                assertUtil.assertEquals(itemInCart.getName(), item.getName(),
                        "Item Name");
                assertUtil.assertEquals(itemInCart.getPrice(), item.getPrice(),
                        "Item Price");
                assertUtil.assertEquals(itemInCart.getDescription(),
                        item.getDescription(), "Item Description");
            }
        });

        return cartPage;
    }
}
