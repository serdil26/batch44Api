package day_09;

import Base_Url.HerokuAppBaseUrl;
import TestData.HerokuAppTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C22_GetRequest extends HerokuAppBaseUrl {
/*
https://restful-booker.herokuapp.com/booking/47
       {
           "firstname": "Ali",
           "lastname": "Can",
           "totalprice": 500,
           "depositpaid": true,
           "bookingdates": {
               "checkin": "2022-02-01",
               "checkout": "2022-02-11"
          }
       }
1) JsonPath
2) De-Serialization
*/

    @Test
    public void test22(){
        //1)URL olustur
        spec05.pathParams("first", "booking", "second", 17);

        //2)EXPECTED DATA OLUSTUR
        HerokuAppTestData expectedObje= new HerokuAppTestData();
        HashMap<String,Object> expectedTestDataMap= expectedObje.setupTestData();
        System.out.println("expectedTestdatamap= "+expectedTestDataMap);
        //firstname=Ali,
        // bookingdates=
                       // {checkin=2022-02-01, checkout=2022-02-11},
        // totalprice=500,
        // depositpaid=true,
        // lastname=Can

        //3-REQUEST-RESPONSE
        Response response= given().spec(spec05).when().get("/{first}/{second}");
        response.prettyPrint();

        //4-DOGRULAMA
        //birinci yol  de-serialization
        HashMap<String, Object> actualData=response.as(HashMap.class); //json formatindaki datayi hashmap'e donusturur
        System.out.println("actula data=" +actualData);

        Assert.assertEquals(expectedTestDataMap.get("firstname"),actualData.get("firstname"));
        Assert.assertEquals(expectedTestDataMap.get("lastname"),actualData.get("lastname"));
        Assert.assertEquals(expectedTestDataMap.get("totalprice"),actualData.get("totalprice"));
        Assert.assertEquals(expectedTestDataMap.get("depositpaid"),actualData.get("depositpaid"));
        Assert.assertEquals(((Map)expectedTestDataMap.get("bookingdates")).get("checkin"),
               ((Map)actualData.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map)expectedTestDataMap.get("bookingdates")).get("checkout"),
                ((Map)actualData.get("bookingdates")).get("checkout"));

        //ikinci yol json path ile
        JsonPath json= response.jsonPath();
        Assert.assertEquals(expectedTestDataMap.get("firstname"), json.getString("firstname"));
        Assert.assertEquals(expectedTestDataMap.get("lastname"), json.getString("lastname"));
        Assert.assertEquals(expectedTestDataMap.get("totalprice"), json.getInt("totalprice"));
        Assert.assertEquals(expectedTestDataMap.get("depositpaid"), json.getBoolean("depositpaid"));
        Assert.assertEquals(  ((Map<?, ?>) expectedTestDataMap.get("bookingdates")).get("checkin")
                ,json.getString("bookingdates.checkin") );
        Assert.assertEquals(  ((Map<?, ?>) expectedTestDataMap.get("bookingdates")).get("checkout")
                ,json.getString("bookingdates.checkout") );








    }

}
