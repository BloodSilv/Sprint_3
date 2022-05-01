package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderCreateParametersTest {
    private final List<String> typeColor;
    private final int statusRequest;

    public OrderCreateParametersTest(List<String> typeColor, int statusRequest) {
        this.typeColor = typeColor;
        this.statusRequest = statusRequest;
    }

    @Parameterized.Parameters
    public static Object[] getStatus() {
        return new Object[][] {
                { List.of("BLACK"), 201},
                { List.of("BLACK, GRAY"), 201},
                { List.of(""), 201},
        };
    }

    @Test
    @Feature("Creating an order")
    @DisplayName("Checking can specify one of the colors — BLACK AND GREY")
    @Description("Test for /api/v1/orders endpoint")
    public void getScooter() {
        CreateOrder order = new CreateOrder(typeColor);
        assertEquals(statusRequest, order.getResponse().statusCode());
    }
}
