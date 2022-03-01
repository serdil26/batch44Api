package get_http_request;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_GetRequest {

    @Test
    public void test01(){

        String url="https://restful-booker.herokuapp.com/booking";
        Response response= given().when().get(url);
        //response.prettyPrint();

        System.out.println("status code =" + response.statusCode());
        System.out.println("content type= " + response.contentType());
        System.out.println("test suresi= " + response.time());

        Assert.assertEquals(200, response.statusCode());

    }







}
