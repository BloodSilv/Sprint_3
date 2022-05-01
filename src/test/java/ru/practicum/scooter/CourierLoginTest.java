package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourierLoginTest {
    private String courierLogin;
    private String courierPassword;

    @Before
    @Step("Регистрация новго пользователя")
    public void setUp() {
        courierLogin = "TestCourier";
        courierPassword = "asasas";
        String courierFirstName = "TestCourier";
        RegisterCourier courier = new RegisterCourier(courierLogin, courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
    }

    @Test
    @Feature("Courier login")
    @DisplayName("Checking the possibility courier can login")
    @Description("Test for /api/v1/courier/login endpoint")
    public void testLoginCourier() {
        LoginCourier loginId = new LoginCourier(courierLogin, courierPassword);
        assertEquals(loginId.loginCourier(),200 );
        DeleteCourier courier = new DeleteCourier(loginId);
        courier.deleteCourier(courierLogin, courierPassword);
    }

    @Test
    @Feature("Courier login")
    @DisplayName("Checking for authorization, need to pass all the required fields")
    @Description("Test for /api/v1/courier/login endpoint")
    public void testEmptyPasswordForLogin() {
        LoginCourier courier = new LoginCourier(courierLogin, "");
        assertEquals(courier.loginCourier(),400 );
    }

    @Test
    @Feature("Courier login")
    @DisplayName("Checking for authorization, need to pass all the required fields")
    @Description("Test for /api/v1/courier/login endpoint")
    public void testEmptyLoginForLogin() {
        LoginCourier courier = new LoginCourier("", courierPassword);
        assertEquals(courier.loginCourier(),400 );
    }

    @Test
    @Feature("Courier login")
    @DisplayName("Checking for the system will return an error if the username is incorrect")
    @Description("Test for /api/v1/courier/login endpoint")
    public void testIncorrectLoginForLogin() {
        LoginCourier courier = new LoginCourier("qwertytrwer", courierPassword);
        assertEquals(courier.loginCourier(),404 );
    }

    @Test
    @Feature("Courier login")
    @DisplayName("Checking for the system will return an error if password is incorrect")
    @Description("Test for /api/v1/courier/login endpoint")
    public void testIncorrectPasswordForLogin() {
        LoginCourier courier = new LoginCourier(courierLogin, "qwertytrwer");
        assertEquals(courier.loginCourier(),404 );
    }

    @Test
    @Feature("Courier login")
    @DisplayName("Checking for a successful request returns an id.")
    @Description("Test for /api/v1/courier/login endpoint")
    public void testLoginIdCourier() {
        LoginCourier loginId = new LoginCourier(courierLogin, courierPassword);
        assertTrue(loginId.getIdCourier() > 0);
        DeleteCourier courier = new DeleteCourier(loginId);
        courier.deleteCourier(courierLogin, courierPassword);
    }

    @Test
    @Feature("Courier login")
    @DisplayName("Checking the server freezes in case of an error in the password field name")
    @Description("Test for /api/v1/courier/login endpoint")
    public void testLoginIdCourierIncorrectFieldPassword() {
        LoginCourier courier = new LoginCourier(courierLogin);
        assertEquals(courier.loginCourier(),504 );
    }

    @After
    public void rollBck(){
    }
}
