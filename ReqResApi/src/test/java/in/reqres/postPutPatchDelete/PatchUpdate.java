package in.reqres.postPutPatchDelete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class PatchUpdate {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void patchUpdate(){
        String jsonData = "{\n" +
                "    \"job\": \"AutomationTester\"\n" +
                "}";

        RestAssured.baseURI = "https://reqres.in/api/users/2";
        requestSpecification = RestAssured.given().log().all();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonData);
        response = requestSpecification.patch();
        System.out.println(response.prettyPrint());
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        validatableResponse.body("job", equalTo("AutomationTester"));


    }
}
