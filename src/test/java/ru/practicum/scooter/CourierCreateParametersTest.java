package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CourierCreateParametersTest {
    private final String courierLogin;
    private final String courierPassword;
    private final String courierFirstName;
    private final int statusRequest;

    public CourierCreateParametersTest(String courierLogin,
                                       String courierPassword,
                                       String courierFirstName,
                                       int statusRequest) {
        this.courierLogin = courierLogin;
        this.courierPassword = courierPassword;
        this.courierFirstName = courierFirstName;
        this.statusRequest = statusRequest;
    }

    @BeforeClass
    public static void setup() {
        LoginCourier loginId = new LoginCourier("TestCourier", "asasas");
        DeleteCourier courier = new DeleteCourier(loginId);
        courier.deleteCourier("TestCourier", "asasas");
    }

    @Parameterized.Parameters
    public static Object[] getStatus() {
        return new Object[][] {
                { "TestCourier", "asasas", "Proveryalshik", 201},
                { "TestCourier", "asasas", "Proveryalshik", 409},
                { "", "asasas", "BruceLee", 400},
        };
    }

    @Test
    @Feature("Courier Creation")
    @DisplayName("Checking the request returns the correct response code")
    @Description("Test for /api/v1/courier endpoint")
    public void CreateCourier() {
        RegisterCourier courier = new RegisterCourier(courierLogin, courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        assertEquals(statusRequest, courier.responseCode);
    }

}
