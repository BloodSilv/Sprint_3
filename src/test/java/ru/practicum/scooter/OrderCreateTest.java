package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class OrderCreateTest {

    @Before
    public void setUp() {
    }

    @Test
    @Feature("Creating an order")
    @DisplayName("Control return track")
    @Description("Test for /api/v1/orders endpoint")
    public void testOrderGetTrack() {
        CreateOrder order = new CreateOrder(List.of("BLACK"));
        assertTrue(order.getResponse().getBody().jsonPath().getInt("track") > 0);
    }

    @After
    public void rollBck() {
    }
}
