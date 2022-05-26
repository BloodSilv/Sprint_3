package ru.practicum.scooter;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

class DeleteCourier extends BaseUrl {

    private final LoginCourier loginId;
    private final Boolean getCourierId;
    private String courierId;

    DeleteCourier(LoginCourier loginId) {
        this.loginId = loginId;
        this.getCourierId = true;
        this.courierId = "";
    }

    DeleteCourier(LoginCourier loginId, Boolean getCourierId) {
        this.loginId = loginId;
        this.getCourierId = getCourierId;
        this.courierId = "";
    }

    DeleteCourier(LoginCourier loginId, String courierId) {
        this.loginId = loginId;
        this.getCourierId = false;
        this.courierId = courierId;
    }

    @Step("Удаление курьера")
    Response deleteCourier(String courierLogin, String courierPassword) {
        Allure.attachment("Login: ", String.valueOf(courierLogin));
        Allure.attachment("Password: ", String.valueOf(courierPassword));
        if (this.getCourierId) {
            this.courierId = String.valueOf(loginId.getIdCourier());
        }

        return given()
                .pathParam("id", this.courierId)
                .when()
                .delete(getBaseUrl() + "/api/v1/courier/{id}");
    }
}
