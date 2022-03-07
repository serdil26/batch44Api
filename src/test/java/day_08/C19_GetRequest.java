package day_08;

import Base_Url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class C19_GetRequest extends DummyBaseUrl {
    /*
http://dummy.restapiexample.com/api/v1/employees
1) Status kodunun 200,
2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
     ve bunların içerisinde "Charde Marshall" olduğunu test edin
*/
    @Test
    public void test19(){
        //1)URL olustur
        spec02.pathParams("birinci","api","ikinci","v1", "ucuncu", "employees");
        //http://dummy.restapiexample.com >>requestten onceki adres
        Response response= given().spec(spec02).when().get("/{birinci}/{ikinci}/{ucuncu}");
                                                        //adresteki com'dan sonraki parametreler >>> api/v1/employees
        response.prettyPrint();
        //Status kodunun 200,
        Assert.assertEquals(200, response.statusCode());
        response.then().assertThat().statusCode(200);
       //2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
        JsonPath json=response.jsonPath();
        List<Integer> idList= json.getList("data.findAll{it.id>10}.id"); //"data.id.findAll{it>10}" seklinde de olabilir
        System.out.println("10dan buyuk idlerin listesi = "+ idList);
        //groovy, java platformu uzerinde calisan bir bilgisayar dilidir
        //groovy ile loop kullanmadan response'dan gelen degerleri bir sarta gore alabiliriz

       //3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
        List<Integer> ageList= json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println("yasi 30dan kucuklerin listesi= "+ ageList);
        Collections.sort(ageList);
        Assert.assertEquals(23, (int) ageList.get(ageList.size()-1));

       //4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
       //ve bunların içerisinde "Charde Marshall" olduğunu test edin
        List<Integer>salaryList=json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println(salaryList);

        Assert.assertTrue(salaryList.contains("Charde Marshall"));

    }

}
