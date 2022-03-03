package get_http_request;

import Base_Url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C08_GetRequest extends DummyBaseUrl {
    /*
   http://dummy.restapiexample.com/api/v1/employees url'inde bulunan




  4) En son calisanin adini konsola yazdiralim
*/

    @Test
    public void test08() {
        spec02.pathParams("first", "api", "second", "v1", "third", "employees");

        Response response = given().spec(spec02).when().get("/{first}/{second}/{third}");
        //response.prettyPrint();
        //1) Butun calisanlarin isimlerini consola yazdıralim
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getList("data.employee_name"));
        // 2) 3. calisan kisinin ismini konsola yazdıralim
        System.out.println(jsonPath.getString("data[2].employee_name"));
        // 3) Ilk 5 calisanin adini konsola yazdiralim
        System.out.println(jsonPath.getList("data[0,1,2,3,4].employee_name")); //BIRINCI YONTEM
        for (int i = 0; i < 5; i++) {
            System.out.println(i + 1 + ". calisan : " + jsonPath.getString("data[" + i + "].employee_name"));
        }  //IKINCI YONTEM

        //4) En son calisanin adini konsola yazdiralim
        System.out.println(jsonPath.getString("data[-1].employee_name"));
    }
}
