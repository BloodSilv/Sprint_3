package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateCourierWithoutLogin {

    private RegisterCourier courier;
    private String courierLogin;
    private String courierPassword;
    private String courierFirstName;

        @Test
        @Feature("Courier Creation")
        @DisplayName("Checking to create a courier, need to pass all the required fields to the handle")
        @Description("Test for /api/v1/courier endpoint")
        public void testCreateCouriersWithoutLogin() {
            courier = new RegisterCourier("", courierPassword, courierFirstName);
            courier.registerNewCourierAndReturnLoginPassword();
            assertTrue(courier.registerNewCourierAndReturnLoginPassword().isEmpty());
        }
}
