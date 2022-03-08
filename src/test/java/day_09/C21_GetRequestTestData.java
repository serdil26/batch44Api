package day_09;

import Base_Url.JsonPlaceHolderBaseUrl;
import TestData.JsonPlaceHolderTestData;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class C21_GetRequestTestData extends JsonPlaceHolderBaseUrl {
      /*
https://jsonplaceholder.typicode.com/todos/2
1) Status kodunun 200,
2) respose body'de,
         "completed": değerinin false
         "title": değerinin "quis ut nam facilis et officia qui"
         "userId" sinin 1 ve
    header değerlerinden
         "via" değerinin "1.1 vegur" ve
         "Server" değerinin "cloudflare" olduğunu test edin…
*/
    @Test
    public void test21(){

        //1) URL OLUSTUR
        spec04.pathParams("1", "todos", "2", 2);

        //2)EXPECTED DATA OLUSTUR
        JsonPlaceHolderTestData expectedDataObject= new JsonPlaceHolderTestData();
        HashMap<String, Object> expectedData= (HashMap<String, Object>) expectedDataObject.setupTestData();
        System.out.println("test datanin icindeki datalar= "+ expectedData);

        //3)REQUEST-RESPONSE

        Response response=given().spec(spec04).when().get("/{1}/{2}");
        response.prettyPrint();




    }
}
