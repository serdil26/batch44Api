package get_http_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C06_GetRequest {
    //https://restful-booker.herokuapp.com/booking/5 url’ine
    //accept type’i “application/json” olan GET request’i yolladigimda
    //gelen response’un
    //status kodunun 200
    //ve content type’inin “application/json”
    //ve firstname’in “Susan”
    //ve totalprice’in 958
    //ve checkin date’in 2018-12-06"oldugunu test edin

    @Test
    public void test06(){
        String url= "https://restful-booker.herokuapp.com/booking/5";
        Response response= given().when().get(url);

        response.prettyPrint();
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
        response.then().body("firstname", Matchers.equalTo("Susan"),
                "totalprice", Matchers.equalTo(958),
                "bookingdates.checkin", Matchers.equalTo("2018-12-06"));


    }
}
