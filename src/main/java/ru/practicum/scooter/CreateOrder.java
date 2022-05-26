package ru.practicum.scooter;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;
import java.util.List;
import static io.restassured.RestAssured.given;

public class CreateOrder extends BaseUrl {

    private final List<String> color;

    CreateOrder(List<String> color) {
        this.color = color;
    }

    public JSONObject getJson() {
        return new JSONObject()
                .put("firstName", "Petr")
                .put("lastName", "Petrov")
                .put("address", "Lenina, 12")
                .put("metroStation", 2)
                .put("phone", "+7 800 333 33 22")
                .put("rentTime", 4)
                .put("deliveryDate", "2022-05-05")
                .put("comment", "HI")
                .put("color", List.of(""));
    }

    @Step("Create order")
    public Response getResponse() {
        JSONObject json = getJson();
        json.put("color", this.color);
        Allure.attachment("Передются данные заказа: ", String.valueOf(json));
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(json.toString())
                .when()
                .post(getBaseUrl() + "/api/v1/orders");
    }
}

