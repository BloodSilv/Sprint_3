package ru.practicum.scooter;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import java.util.ArrayList;
import static io.restassured.RestAssured.given;

class RegisterCourier extends BaseUrl {
    String courierLogin;
    String courierPassword;
    private final String courierFirstName;
    int responseCode;
    JsonPath responseBody;
    JSONObject registerRequestBody;

    RegisterCourier() {
        this.courierLogin = RandomStringUtils.randomAlphabetic(10);
        this.courierPassword = RandomStringUtils.randomAlphabetic(10);
        this.courierFirstName = RandomStringUtils.randomAlphabetic(10);
    }

    RegisterCourier(String courierLogin, String courierPassword, String courierFirstName) {
        this.courierLogin = courierLogin;
        this.courierPassword = courierPassword;
        this.courierFirstName = courierFirstName;
    }

    @Step("Регистрация новго пользователя")
    ArrayList<String> registerNewCourierAndReturnLoginPassword() {
        ArrayList<String> loginPass = new ArrayList<>();
        Allure.attachment("login ", String.valueOf(this.courierLogin));
        Allure.attachment("password ", String.valueOf(this.courierPassword));
        Allure.attachment("firstName ", String.valueOf(this.courierFirstName));
        this.registerRequestBody = new JSONObject()
                .put("login", this.courierLogin)
                .put("password", this.courierPassword)
                .put("firstName", this.courierFirstName);
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(this.registerRequestBody.toString())
                .when()
                .post(getBaseUrl() + "/api/v1/courier");
        this.responseCode = response.statusCode();
        this.responseBody = response.getBody().jsonPath();
        if (this.responseCode == 201) {
            loginPass.add(courierLogin);
            loginPass.add(courierPassword);
        }
        Allure.attachment("ResponseCode ", String.valueOf(this.responseCode));
        Allure.attachment("возвращаем список", String.valueOf(loginPass));
        return loginPass;
    }
}
