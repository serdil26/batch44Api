package get_http_request;

import Base_Url.ReqresInBaseUrl;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C07_GetRequest extends ReqresInBaseUrl {
    /*
https://reqres.in/api/users URL request olustur.
body icerisindeki idsi 5 olan datayi
1) Matcher CLASS ile
2) JsonPath ile dogrulayin.
*/
    @Test
    public void test07(){

        spec01.pathParams("parametre1", "api" , "parametre2", "users");

        //  https://reqres.in
        Response response = given().spec(spec01).when().get("/{parametre1}/{parametre2}");
        // "/{parametre1}/{parametre2}" -> /api/users

      //  response.prettyPrint();

      response.then().assertThat().body("data[4].email", equalTo("charles.morris@reqres.in"),
                "data[4].first_name", equalTo("Charles"),
                "data[4].last_name", equalTo("Morris"));

        //JsonPath ile dogrulama
        JsonPath json= response.jsonPath();
        System.out.println(json.getList("data.email"));
        System.out.println(json.getList("data.first_name"));
        System.out.println(json.getList("data.last_name"));
        Assert.assertEquals("charles.morris@reqres.in", json.get("data[4].email"));
    }

}
