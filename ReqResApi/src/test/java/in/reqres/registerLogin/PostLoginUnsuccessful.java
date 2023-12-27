package in.reqres.registerLogin;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class PostLoginUnsuccessful {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void postLoginUnsuccessful() {

        String jsonData = "{\n" +
                "    \"email\": \"peter@klaven\"\n" +
                "}";

        RestAssured.baseURI = "https://reqres.in/api/login";
        requestSpecification = RestAssured.given().log().all();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonData);
        response = requestSpecification.post();
        System.out.println(response.prettyPrint());
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(400);
        validatableResponse.body("error", equalTo("Missing password"));

    }
}
