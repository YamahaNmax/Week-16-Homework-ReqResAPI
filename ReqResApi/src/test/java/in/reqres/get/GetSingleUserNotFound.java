package in.reqres.get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetSingleUserNotFound {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getSingleUserNotFoundBdd(){
        RestAssured.baseURI="https://reqres.in/api/users/23";
        requestSpecification = RestAssured.given().log().all();
        response= requestSpecification.get();
        response.then().log().all();
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 404 Not Found");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,404);


    }
}
