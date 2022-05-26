package ru.practicum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*  Задание Удалить курьера
    1. неуспешный запрос возвращает соответствующую ошибку;
    2. успешный запрос возвращает ok: true;
    3. если отправить запрос без id, вернётся ошибка;
    4. если отправить запрос с несуществующим id, вернётся ошибка.
 */

public class CourierDeleteTest {
    private String courierLogin;
    private String courierPassword;
    private LoginCourier loginId;

    @Before
    public void setUp() {
        courierLogin = "TestCourier1";
        courierPassword = "asasas";
        String courierFirstName = "Proveryalshik1";
        RegisterCourier courier = new RegisterCourier(courierLogin, courierPassword, courierFirstName);
        courier.registerNewCourierAndReturnLoginPassword();
        loginId = new LoginCourier(courierLogin, courierPassword);
    }

    @Test
    @Feature("Courier deleting")
    @DisplayName("Checking if send a request without an id, an error will be returned;")
    @Description("Test for /api/v1/courier/:id endpoint")
    public void testDeleteCourierWithoutId() {
        DeleteCourier courier = new DeleteCourier(loginId, false);
        Response response = courier.deleteCourier(courierLogin, courierPassword);
        assertEquals(404, response.getStatusCode());
    }

    @Test
    @Feature("Courier deleting")
    @DisplayName("Checking if send a request with a non-existent id, an error will be returned")
    @Description("Test for /api/v1/courier/:id endpoint")
    public void testDeleteCourierWithNonExistentId() {
        DeleteCourier courier = new DeleteCourier(loginId, "1234321");
        Response response = courier.deleteCourier(courierLogin, courierPassword);
        assertEquals(404, response.getStatusCode());
    }

    @After
    public void rollBck(){
        DeleteCourier courier = new DeleteCourier(loginId);
        courier.deleteCourier(courierLogin, courierPassword);
    }
}
