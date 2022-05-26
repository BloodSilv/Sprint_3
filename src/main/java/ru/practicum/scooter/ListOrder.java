package ru.practicum.scooter;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ListOrder extends BaseUrl {

    ListOrder() {
    }

    @Step("Запрос списка заказов")
    public Response getResponse() {
        return given()
                .get(getBaseUrl() + "/api/v1/orders");
    }
}
