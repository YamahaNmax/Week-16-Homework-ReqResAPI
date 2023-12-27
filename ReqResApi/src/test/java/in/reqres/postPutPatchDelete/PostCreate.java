package in.reqres.postPutPatchDelete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class PostCreate {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void postCreate() {

        String jsonData = "{\n" +
                "    \"name\": \"Miten\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        RestAssured.baseURI = "https://reqres.in/api/users";
        requestSpecification = RestAssured.given().log().all();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonData);
        response = requestSpecification.post();
        System.out.println(response.prettyPrint());
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);
        validatableResponse.body("name", equalTo("Miten"));

    }
}
