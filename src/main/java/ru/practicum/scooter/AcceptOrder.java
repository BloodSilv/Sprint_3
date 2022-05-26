package ru.practicum.scooter;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

class AcceptOrder extends BaseUrl {

    private final String orderId;
    private final String courierId;

    AcceptOrder(String orderId, String courierId) {
        this.orderId = orderId;
        this.courierId = courierId;
    }

    @Step("Принять заказ от курьера")
    Response acceptOrderFromCourier() {
        Allure.attachment("ID заказа: ", orderId);
        Allure.attachment("ID курьера: ", courierId);
        return given()
                .pathParam("id", this.orderId)
                .queryParam("courierId", this.courierId)
                .when()
                .put(getBaseUrl() + "/api/v1/orders/accept/{id}");
    }
}


