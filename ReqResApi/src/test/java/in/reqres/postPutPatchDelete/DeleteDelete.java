package in.reqres.postPutPatchDelete;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class DeleteDelete {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void deleteDelete() {

        RestAssured.baseURI = "https://reqres.in/api/users";
        requestSpecification = RestAssured.given().log().all();
        response = requestSpecification.delete();
        System.out.println(response.prettyPrint());
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(204);

    }
}
