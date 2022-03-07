package day_08;

import Base_Url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C20_GetRequest extends JsonPlaceHolderBaseUrl {
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
    public void test20(){
        //1-URL OLUSTUR
        spec04.pathParams("parametre1", "todos", "parametre2", 2);

        //2-ExpectedData olustur
        HashMap<String, Object> expectedData= new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("completed", false);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("userId", 1);
        expectedData.put("via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");

        //3-REQUEST ve RESPONSE
        Response response= given().spec(spec04).when().get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        //4-DOGRULAMA
        //I.YOL>matchers class
        response.then().assertThat()
                .statusCode((Integer)expectedData.get("statusCode"))
                .headers("via",equalTo(expectedData.get("via"))
                        ,"server",equalTo(expectedData.get("server")))
                .body("completed",equalTo(expectedData.get("completed"))
                        ,"title",equalTo(expectedData.get("title")),
                        "userId", equalTo(expectedData.get("userId")));


        //II.YOL>>JsonPath
        JsonPath json=response.jsonPath();
       //Assert.assertEquals(200, json.getInt("statusCode"));
       //Assert.assertEquals(false, json.getString("completed"));
       //Assert.assertEquals("quis ut nam facilis et officia qui", json.getString("title"));
       //Assert.assertEquals(1, json.getInt("userId"));
       //Assert.assertEquals("1.1 vegur", json.getString("via"));
       //Assert.assertEquals("cloudflare", json.getString("Server"));
       Assert.assertEquals(expectedData.get("statusCode"),response.statusCode());
       Assert.assertEquals(expectedData.get("via"), response.getHeader("via"));
       Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));
       Assert.assertEquals(expectedData.get("completed"), json.getBoolean("completed"));
       Assert.assertEquals(expectedData.get("title"), json.getString("title"));
       Assert.assertEquals(expectedData.get("userId"), json.getInt("userId"));

        //III.YOL>>>De-serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
    }
}
