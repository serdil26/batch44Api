package get_http_request;

import Base_Url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C16_GetRequest extends JsonPlaceHolderBaseUrl {
    /*
   https://jsonplaceholder.typicode.com/todos/7

   {
   "userId": 1,
   "id": 7,
   "title": "illo expedita consequatur quia in",
   "completed": false
}
    */
    @Test
    public void test16(){
        //1) URL OLUSTURMA
        spec04.pathParams("bir", "todos","iki", 7);

        //2) EXPECTED DATA OLUSTUR
        Map<String, Object> expectedData= new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",7);
        expectedData.put("title", "illo expedita consequatur quia in");
        expectedData.put("completed", false);
        System.out.println("expected data= " + expectedData);

        //3)REQUEST VE RESPONSE

       Response response= given().spec(spec04).when().get("/{bir}/{iki}");
        // https://jsonplaceholder.typicode.com adresin ana sayfasi
        //  "/{bir}/{iki}">>adrese "todos" ve "7"yi ekler
        response.prettyPrint();

        //datayi Jsondan>>Java'ya donusturme isi= de-serialization
        //datayi javadan>>Json'a donusturme isi= serialization
        //expected ve actual datalarin ayni formatta karsilastirilmasi icin yapilan donusturme islemleri

        Map<String, Object> actualData=response.as(HashMap.class);  //deserialization
        System.out.println("actual data= " + actualData); //actual data= {id=7, completed=false, title=illo expedita consequatur quia in, userId=1}

        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"),actualData.get("id"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
    }

}
