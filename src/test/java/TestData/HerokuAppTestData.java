package TestData;

import java.util.HashMap;

public class HerokuAppTestData {

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
*/

    public HashMap<String, Object> setupTestData(){
        HashMap<String, Object> bookingDates=new HashMap<>();
        bookingDates.put("checkin","2022-02-01");
        bookingDates.put("checkout","2022-02-11");

        HashMap<String, Object> expectedData=new HashMap<>();
        expectedData.put("firstname", "Ali");
        expectedData.put("lastname" ,"Can");
        expectedData.put("totalprice", 700);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingDates);

        return expectedData;
    }

}
