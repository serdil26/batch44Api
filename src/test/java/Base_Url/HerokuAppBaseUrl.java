package Base_Url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerokuAppBaseUrl {
    protected RequestSpecification spec05;

    @Before
    public void setup(){
        spec05= new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
    }
}
