package in.reqres;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class DelayResponse {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getDelayResponseBdd() {
        RestAssured.baseURI = "https://reqres.in/api/users";
        requestSpecification = RestAssured.given().queryParam("delay", 3).log().all();
        response = requestSpecification.get();
        response.then().log().all(); //for log purpose you need to use then
        //System.out.println(response.prettyPrint());
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }


    @Test
    public void verifyUserDetailsBdd() {
        RestAssured.baseURI = "https://reqres.in/api/users";
        requestSpecification = RestAssured.given().queryParam("delay", 3).log().all();
        response = requestSpecification.get();
        validatableResponse = response.then().log().all(); //for log purpose you need to use then
        //System.out.println(response.prettyPrint());
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        validatableResponse.body("data[2].id", equalTo(3));
        validatableResponse.body("data[2].first_name", equalTo("Emma"));
        validatableResponse.body("data[2].last_name", equalTo("Wong"));
    }

}
