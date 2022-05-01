package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrdersAllGet {
    @Before
    public void setUp() {
    }

    @Test
    @Feature("List of orders")
    @DisplayName("Checking the response body returns a list of orders.")
    @Description("Test for /api/v1/orders endpoint")
    public void testGetOrders() {
        ListOrder order = new ListOrder();
        System.out.println(order.getResponse().getBody().jsonPath().getString("orders"));
    }

    @After
    public void rollBck(){
    }
}
