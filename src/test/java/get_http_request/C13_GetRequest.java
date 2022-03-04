package get_http_request;

import Base_Url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C13_GetRequest extends GMIBankBaseUrl {
    //http://www.gmibank.com/api/tp-customers/114351 adresindeki müşteri bilgilerini doğrulayın
    //    “firstName”: “Della”,
    //    “lastName”: “Heaney”,
    //    “mobilePhoneNumber”: “123-456-7893”,
    //    “address”: “75164 McClure Stream”,
    //    “country” : “USA”
    //    “state”: “New York43"
    //    “CREDIT_CARD”,hesabında 69700$ ,
    //    “CHECKING” hesabında 11190$

    @Test
    public void test13(){
        spec03.pathParams("bir","tp-customers", "iki", "114351");
        //http://www.gmibank.com/api
        Response response=given().
                spec(spec03).header("Authorization","Bearer "+generateToken())
                .when().get("/{bir}/{iki}"); //"/{bir}/{iki}" >>tp-customers/114351
        response.prettyPrint();

        //MATCHER CLASS ILE
        response.then().assertThat().body(
                "firstName",equalTo("Della"),
                "lastName",equalTo("Heaney"),
                "mobilePhoneNumber", equalTo("123-456-7893"),
                "phoneNumber", equalTo("213-456-7893"),
                "address", equalTo("75164 McClure Stream"),
                "country.name", equalTo("USA"),
                "state", equalTo("New York43"),
                "accounts.balance[0]", equalTo(69700),
                "accounts.balance[1]", equalTo(11190));

        //JSON PATH ILE
        JsonPath json=response.jsonPath();
        Assert.assertEquals("Della",json.get("firstName"));
        Assert.assertEquals("Heaney",json.get("lastName"));
        Assert.assertEquals("123-456-7893",json.get("mobilePhoneNumber"));
        Assert.assertEquals("75164 McClure Stream",json.get("address"));
        Assert.assertEquals("USA",json.get("country.name"));
        Assert.assertEquals("New York43",json.get("state"));
        Assert.assertEquals(69700, json.getInt("accounts[0].balance"));
        Assert.assertEquals(11190, json.getInt("accounts[1].balance"));


    }
}
