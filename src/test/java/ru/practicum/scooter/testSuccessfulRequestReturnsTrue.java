package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class testSuccessfulRequestReturnsTrue {
    private RegisterCourier courier;
    private String courierLogin;
    private String courierPassword;
    private String courierFirstName;
    private LoginCourier loginId;

    @Before
    public void setUp() {
        this.courierLogin = "TestCourier1";
        this.courierPassword = "asasas";
        this.courierFirstName = "Proveryalshik1";
    }

    @Test
    @Feature("Courier Creation")
    @DisplayName("Checking successful request returns ok: true")
    @Description("Test for /api/v1/courier endpoint")
    public void testSuccessfulRequestReturnsTrue() {
        courier = new RegisterCourier(
                this.courierLogin,
                this.courierPassword,
                this.courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        assertTrue(courier.responseBody.getBoolean("ok"));
    }

    @After
    public void rollBck(){
        LoginCourier loginId = new LoginCourier(courierLogin, courierPassword);
        DeleteCourier courier = new DeleteCourier(loginId);
        courier.deleteCourier(courierLogin, courierPassword);
    }
}
