package in.reqres.get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetListResource {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;


    @Test
    public void getListResource() {
        given().log().all()
                .when()
                .get("https://reqres.in/api/unknown")
                .then().log().all()
                .statusCode(200)
                .body("data[2].id", equalTo(3))
                .body("data[2].name", equalTo("true red"))
                .body("data[2].year", equalTo(2002))
                .body("data[2].color", equalTo("#BF1932"))
                .body("data[2].pantone_value", equalTo("19-1664"));
    }

    @Test
    public void getListResourceBdd() {
        RestAssured.baseURI = "https://reqres.in/api/unknown";
        requestSpecification = RestAssured.given().log().all();
        response = requestSpecification.get();
        validatableResponse = response.then().log().all(); //for log purpose you need to use then
        //System.out.println(response.prettyPrint());
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        validatableResponse.body("data[2].id", equalTo(3));
        validatableResponse.body("data[2].name", equalTo("true red"));
        validatableResponse.body("data[2].year", equalTo(2002));
        validatableResponse.body("data[2].color", equalTo("#BF1932"));
        validatableResponse.body("data[2].pantone_value", equalTo("19-1664"));
    }

}
