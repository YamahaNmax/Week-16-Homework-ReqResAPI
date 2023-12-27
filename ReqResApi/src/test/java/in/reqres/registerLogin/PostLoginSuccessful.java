package in.reqres.registerLogin;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class PostLoginSuccessful {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void postLoginSuccessful() {

        String jsonData = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";

        RestAssured.baseURI = "https://reqres.in/api/login";
        requestSpecification = RestAssured.given().log().all();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonData);
        response = requestSpecification.post();
        System.out.println(response.prettyPrint());
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        validatableResponse.body("token", equalTo("QpwL5tke4Pnpja7X4"));

    }
}
