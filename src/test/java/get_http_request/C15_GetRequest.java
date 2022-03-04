package get_http_request;

import Base_Url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C15_GetRequest extends GMIBankBaseUrl {

    /*
https://www.gmibank.com/api/tp-customers/85694
        "login": "dino.kohler",
    "firstName": "Winona",
    "lastName": "Abernathy",
    "email": "winonaabernathy@gmail.com"

 */

    @Test
    public void test13() {
        spec03.pathParams("bir", "tp-customers", "iki", "85694");
        //http://www.gmibank.com/api
        Response response = given().
                spec(spec03).header("Authorization", "Bearer " + generateToken())
                .when().get("/{bir}/{iki}"); //"/{bir}/{iki}" >>tp-customers/114351
        response.prettyPrint();

        //MATCHER CLASS ILE
        response.then().assertThat().body(
                "user.login", equalTo("dino.kohler"),
                "firstName", equalTo("Winona"),
                "lastName", equalTo("Abernathy"),
                "email", equalTo("winonaabernathy@gmail.com"));

        //JSON PATH ILE
        JsonPath json = response.jsonPath();
        Assert.assertEquals("dino.kohler", json.get("user.login"));
        Assert.assertEquals("Winona", json.get("firstName"));
        Assert.assertEquals("Abernathy", json.get("lastName"));
        Assert.assertEquals("winonaabernathy@gmail.com", json.get("email"));


    }
}
