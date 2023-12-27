package in.reqres.postPutPatchDelete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class PutUpdate {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void putUpdate() {

        String jsonData = "{\n" +
                "    \"name\": \"Mit\",\n" +
                "    \"job\": \"Tester\"\n" +
                "}";

        RestAssured.baseURI = "https://reqres.in/api/users/2";
        requestSpecification = RestAssured.given().log().all();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonData);
        response = requestSpecification.put();
        System.out.println(response.prettyPrint());
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        validatableResponse.body("name", equalTo("Mit"));
        validatableResponse.body("job", equalTo("Tester"));


    }
}
