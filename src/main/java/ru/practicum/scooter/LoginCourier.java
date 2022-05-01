package ru.practicum.scooter;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class LoginCourier extends BaseUrl {

    JSONObject loginRequestBody;

    LoginCourier(String courierLogin, String courierPassword) {
        this.loginRequestBody = new JSONObject()
                .put("login", courierLogin)
                .put("password",courierPassword);
    }

    LoginCourier(String courierLogin) {
        this.loginRequestBody = new JSONObject()
                .put("login", courierLogin)
                .put("password:","");
    }

    public Response getResponse(){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(this.loginRequestBody.toString())
                .when()
                .post(getBaseUrl() +  "/api/v1/courier/login");
    }

    @Step("Логин курьера")
    int loginCourier(){
        int statusCode = getResponse().statusCode();
        Allure.attachment("Получено значение: ", String.valueOf(statusCode));
        return getResponse().statusCode();
    }

    @Step("Получение id курьера")
    int getIdCourier(){
        int id = getResponse().getBody().jsonPath().getInt("id");
        Allure.attachment("Получено значение: ", String.valueOf(id));
        return id;
    }
}
