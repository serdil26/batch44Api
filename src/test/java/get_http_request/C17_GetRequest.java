package get_http_request;

import Base_Url.GMIBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.Authentication;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C17_GetRequest extends GMIBankBaseUrl {
     /*
   http://www.gmibank.com/api/tp-customers/114351 adresindeki müşteri bilgilerini doğrulayın

{
   "firstName": "Della",
   "lastName": "Heaney",
   "email": "ricardo.larkin@yahoo.com",
   "mobilePhoneNumber": "123-456-7893",
}
    */
    @Test
    public void test17(){
        //1)URL olustur
spec03.pathParams("bir","tp-customers", "iki", "114351");
        //2)ExpectedData olustur
Map<String, Object> expectedData= new HashMap<>();
expectedData.put("firstName", "Della");
expectedData.put("lastName", "Heaney");
expectedData.put("email", "ricardo.larkin@yahoo.com");
expectedData.put("mobilePhoneNumber", "123-456-7893");
        System.out.println("expected= "+ expectedData);
        //3) Request-Response
        Response response= given().spec(spec03).header("Authorization", "Bearer "+ generateToken()).when().get("/{bir}/{iki}");

        response.prettyPrint();

        Map<String, Object> actualData= response.as(HashMap.class);
        System.out.println("actula = " +actualData);


        //4)Assert
        Assert.assertEquals(expectedData.get("firstName"), actualData.get("firstName"));
        Assert.assertEquals(expectedData.get("lastName"), actualData.get("lastName"));
        Assert.assertEquals(expectedData.get("email"), actualData.get("email"));
        Assert.assertEquals(expectedData.get("mobilePhoneNumber"), actualData.get("mobilePhoneNumber"));

    }

}
