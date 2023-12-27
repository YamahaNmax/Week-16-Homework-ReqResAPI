package in.reqres.get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetListUsers {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getListUsersWay1() {
        given().log().all()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void getListUsersWay2() {
        RestAssured.baseURI = "https://reqres.in/api/users?page=2";
        requestSpecification = RestAssured.given();
        response = requestSpecification.get();
        System.out.println(response.prettyPrint());
        validatableResponse = response.then();
        validatableResponse.statusCode(200);

    }

    @Test
    public void getListUsersWayBdd() {
        RestAssured.baseURI = "https://reqres.in/api/users";
        requestSpecification = RestAssured.given().queryParam("page",2).log().all();
        response = requestSpecification.get();
        response.then().log().all(); //for log purpose you need to use then
        //System.out.println(response.prettyPrint());
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void verifyUserDetails() {
        given().log().all()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then().log().all()
                .statusCode(200)
                .body("data[2].id", equalTo(9))
                .body("data[2].first_name", equalTo("Tobias"))
                .body("data[2].last_name", equalTo("Funke"));
    }

    @Test
    public void verifyUserDetailsBdd() {
        RestAssured.baseURI="https://reqres.in/api/users?page=2";
        requestSpecification = RestAssured.given().queryParam("page",2).log().all();
        response = requestSpecification.get();
        validatableResponse = response.then().log().all(); //for log purpose you need to use then
        //System.out.println(response.prettyPrint());
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        validatableResponse.body("data[2].id", equalTo(9));
        validatableResponse.body("data[2].first_name", equalTo("Tobias"));
        validatableResponse.body("data[2].last_name", equalTo("Funke"));
    }


}
