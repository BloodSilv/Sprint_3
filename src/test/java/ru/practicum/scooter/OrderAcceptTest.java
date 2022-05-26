package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderAcceptTest {
    private LoginCourier loginId;
    private String courierLogin;
    private String courierPassword;
    private String courierId;
    private String orderId;

    @Before
    public void setUp() {
        courierLogin = "TestCourier1";
        courierPassword = "asasas";
        String courierFirstName = "TestCourier1";
        RegisterCourier courier = new RegisterCourier(courierLogin, courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        loginId = new LoginCourier(courierLogin, courierPassword);
        courierId = String.valueOf(loginId.getIdCourier());
        String orderTrack = String.valueOf(new CreateOrder(List.of("BLACK")).getResponse().getBody().jsonPath().getInt("track"));
        GetOrderByNumber response = new GetOrderByNumber(orderTrack);
        orderId = String.valueOf(response.getOrder().getBody().jsonPath().getMap("order").get("id"));
    }

    @Test
    @Feature("Accept the order")
    @DisplayName("a successful request returns ok: true")
    @Description("Test for /api/v1/orders/accept/:id endpoint")
    public void testAcceptOrderOk() {
        AcceptOrder response = new AcceptOrder(orderId, courierId);
        assertEquals("true", response.acceptOrderFromCourier().getBody().jsonPath().getString("ok"));
        DeleteCourier courier = new DeleteCourier(loginId);
        courier.deleteCourier(courierLogin, courierPassword);
    }

    @Test
    @Feature("Accept the order")
    @DisplayName("if the courier id is not passed, the request returns an error")
    @Description("Test for /api/v1/orders/accept/:id endpoint")
    public void testAcceptOrderIfCourierWithoutId() {
        AcceptOrder response = new AcceptOrder(orderId, "");
        assertEquals(400, response.acceptOrderFromCourier().getStatusCode());
    }

    @Test
    @Feature("Accept the order")
    @DisplayName("if you pass an incorrect courier id, the request will return an error")
    @Description("Test for /api/v1/orders/accept/:id endpoint")
    public void testAcceptOrderIfCourierWrongId() {
        AcceptOrder response = new AcceptOrder(orderId, "123321");
        assertEquals(404, response.acceptOrderFromCourier().getStatusCode());
    }

    @Test
    @Feature("Accept the order")
    @DisplayName("if you do not pass the order number, the request will return an error")
    @Description("Test for /api/v1/orders/accept/:id endpoint")
    public void testAcceptOrderIfOrderIdEmpty() {
        AcceptOrder response = new AcceptOrder("", courierId);
        assertEquals(404, response.acceptOrderFromCourier().getStatusCode());
    }

    @Test
    @Feature("Accept the order")
    @DisplayName("if the order number is incorrect, the request returns an error.")
    @Description("Test for /api/v1/orders/accept/:id endpoint")
    public void testAcceptOrderIfOrderIdWrong() {
        AcceptOrder response = new AcceptOrder("123321", courierId);
        assertEquals(404, response.acceptOrderFromCourier().getStatusCode());
    }

    @After
    public void rollBck() {
    }
}

