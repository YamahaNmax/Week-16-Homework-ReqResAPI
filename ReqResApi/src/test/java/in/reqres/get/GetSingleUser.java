package in.reqres.get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetSingleUser {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getSingleUserBdd() {
        RestAssured.baseURI = "https://reqres.in/api/users/12";
        requestSpecification = given().log().all();
        response = requestSpecification.get();
        System.out.println(response.prettyPrint());
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void verifyUserDetails() {
        given().log().all()
                .when()
                .get("https://reqres.in/api/users/12")
                .then().log().all()
                .statusCode(200)
                .body("data.first_name", equalTo("Rachel"))
                .body("support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));


    }

}
