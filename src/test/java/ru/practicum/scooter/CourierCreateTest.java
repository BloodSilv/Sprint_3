package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class CourierCreateTest {
    private RegisterCourier courier;
    private String courierLogin;
    private String courierPassword;
    private String courierFirstName;

    @Before
    public void setUp() {
        this.courierLogin = "TestCourier1";
        this.courierPassword = "asasas";
        this.courierFirstName = "Proveryalshik1";
    }

    @Test
    @Feature("Courier Creation")
    @DisplayName("Checking the possibility of creating a courier")
    @Description("Test for /api/v1/courier endpoint")
    public void testCanCreateCourier() {
        courier = new RegisterCourier();
        ArrayList<String> loginPass = courier.registerNewCourierAndReturnLoginPassword();
        assertFalse(loginPass.isEmpty());
    }

    @Test
    @Feature("Courier Creation")
    @DisplayName("Checking cannot create two identical couriers")
    @Description("Test for /api/v1/courier endpoint")
    public void testCantCreateTwoIdenticalCouriers() {
        courier = new RegisterCourier(courierLogin, courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        courier.registerNewCourierAndReturnLoginPassword();
        assertTrue(courier.registerNewCourierAndReturnLoginPassword().isEmpty());
    }

    @Test
    @Feature("Courier Creation")
    @DisplayName("Checking if one of the fields is missing, the request returns an error")
    @Description("Test for /api/v1/courier endpoint")
    public void testResponseCodeIfOneFieldsIsMissing() {
        courier = new RegisterCourier("", courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        assertEquals(courier.responseCode, 400);
    }

    @Test
    @Feature("Courier Creation")
    @DisplayName("Checking if create a user with a username that already exists, an error is returned")
    @Description("Test for /api/v1/courier endpoint")
    public void testResponseCodeIfUserAlreadyExists() {
        courier = new RegisterCourier(courierLogin, courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        courier.registerNewCourierAndReturnLoginPassword();
        assertEquals("Creation success", courier.responseCode, 409);
    }
}
