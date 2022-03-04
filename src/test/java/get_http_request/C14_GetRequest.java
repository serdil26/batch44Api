package get_http_request;

import Base_Url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C14_GetRequest extends GMIBankBaseUrl {
 /*
http://www.gmibank.com/api/tp-customers/110472 adresindeki müşteri bilgilerini doğrulayın

"firstName": "Melva",
"lastName": "Bernhard",
"email": "chas.kuhlman@yahoo.com"
"zipCode": "40207"

"country" "name": "San
"login": "delilah.metz"
 */


    @Test
    public void test13() {
        spec03.pathParams("bir", "tp-customers", "iki", "110472");
        //http://www.gmibank.com/api
        Response response = given().
                spec(spec03).header("Authorization", "Bearer " + generateToken())
                .when().get("/{bir}/{iki}"); //"/{bir}/{iki}" >>tp-customers/114351
        response.prettyPrint();

        //MATCHER CLASS ILE
        response.then().assertThat().body(
                "firstName",equalTo("Melva"),
                "lastName",equalTo("Bernhard"),
                "email", equalTo("chas.kuhlman@yahoo.com"),
                "zipCode",equalTo("40207"),
                "country.name", equalTo("San Jose"),
                "user.login", equalTo("delilah.metz"));

        //JSON PATH ILE
        JsonPath json=response.jsonPath();
        Assert.assertEquals("Melva",json.get("firstName"));
        Assert.assertEquals("Bernhard",json.get("lastName"));
        Assert.assertEquals("chas.kuhlman@yahoo.com",json.get("email"));
        Assert.assertEquals("40207",json.get("zipCode"));
        Assert.assertEquals("San Jose",json.get("country.name"));
        Assert.assertEquals("delilah.metz",json.get("user.login"));
    }
}
