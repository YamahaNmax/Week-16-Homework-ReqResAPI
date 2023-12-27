package in.reqres.registerLogin;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class PostRegisterUnsuccessful {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void postRegisterUnsuccessful() {

        String jsonData = "{\n" +
                "    \"email\": \"sydney@fife\"\n" +
                "}";

        RestAssured.baseURI = "https://reqres.in/api/register";
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
