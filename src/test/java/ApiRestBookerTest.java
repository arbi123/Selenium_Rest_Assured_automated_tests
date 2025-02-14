import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.internal.matcher.xml.XmlXsdMatcher.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class ApiRestBookerTest {

         int bookingID;


         String token;
        @Test(priority = 0)
        public void createToken(){
        String jsonSchema = "{"
                + "\"type\": \"object\","
                + "\"properties\": {"
                + "\"token\": { \"type\": \"string\" }"
                + "},"
                + "\"required\": [\"token\"],"
                + "\"additionalProperties\": false"
                + "}";
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

            String requestBody = "{ \"username\": \"admin\", \"password\": \"password123\" }";
        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(jsonSchema))
                .extract().response();
            Assert.assertNotNull(response.jsonPath().getString("token"), "Token is null!");
         token= response.jsonPath().getString("token");

    }

         @Test(priority = 7)
         public void getBookingID(){
               RestAssured.baseURI = "https://restful-booker.herokuapp.com";
               String jsonSchema = "{"
                       + "\"type\": \"array\","
                       + "\"items\": {"
                       + "\"type\": \"object\","
                       + "\"properties\": {"
                       + "\"bookingid\": { \"type\": \"number\" }"
                       + "},"
                       + "\"required\": [\"bookingid\"],"
                       + "\"additionalProperties\": false"
                       + "}"
                       + "}";

               Response response = given().get("/booking").
                       then().
                       statusCode(200) // Validate response code
                       .body(matchesJsonSchema(jsonSchema)) // Validate schema
                       .extract().response();
               List<Integer> bookingIds = response.jsonPath().getList("bookingid", Integer.class);

               // Validate that at least one booking ID is returned
               Assert.assertFalse(bookingIds.isEmpty(), "No booking IDs found!");

               for(Integer in : bookingIds)
                   if(in == bookingID){
                       Assert.fail("Nuk eshte fshire si duhet");
                   }else
                       Assert.assertTrue(true,"Eshte fshire");
}
         @Test(priority = 8)
         public void getByName(){
             RestAssured.baseURI = "https://restful-booker.herokuapp.com";

             String firstName = "devin";
             String lastName = "boker";

// Send GET request with filters
             Response response = given()
                     .queryParam("firstname", firstName)
                     .queryParam("lastname", lastName)
                     .when()
                     .get("/booking")
                     .then()
                     .statusCode(200)
                     .extract().response();
             List<Integer> filteredBookingIds = response.jsonPath().getList("bookingid", Integer.class);

             System.out.println("Filtered Booking IDs: " + filteredBookingIds);

         }

         @Test(priority = 9)
         public void getByDate(){
             RestAssured.baseURI = "https://restful-booker.herokuapp.com";
             //Year-Month-Day
             String checkinDate = "2012-10-10";
             String checkoutDate = "2013-10-10";

             Response response = given()
                     .queryParam("checkin", checkinDate)
                     .queryParam("checkout", checkoutDate)
                     .when()
                     .get("/booking")
                     .then()
                     .statusCode(200)
                     .extract().response();

             List<Integer> filteredBookingIds = response.jsonPath().getList("bookingid", Integer.class);

             System.out.println("Filtered Booking IDs by Date: " + filteredBookingIds);

             if (filteredBookingIds.isEmpty()) {
                 System.out.println("No bookings found for the given date range!");
             } else {
                 System.out.println("Total Bookings Found: " + filteredBookingIds.size());
             }
         }

         @Test(priority = 2)
         public void getBookingJson(){
             RestAssured.baseURI = "https://restful-booker.herokuapp.com";
             int bookingId=bookingID;
             String jsonSchema = "{"
                     + "\"type\": \"object\","
                     + "\"properties\": {"
                     + "\"firstname\": { \"type\": \"string\" },"
                     + "\"lastname\": { \"type\": \"string\" },"
                     + "\"totalprice\": { \"type\": \"number\" },"
                     + "\"depositpaid\": { \"type\": \"boolean\" },"
                     + "\"bookingdates\": {"
                     + "\"type\": \"object\","
                     + "\"properties\": {"
                     + "\"checkin\": { \"type\": \"string\", \"format\": \"date\" },"
                     + "\"checkout\": { \"type\": \"string\", \"format\": \"date\" }"
                     + "},"
                     + "\"required\": [\"checkin\", \"checkout\"]"
                     + "},"
                     + "\"additionalneeds\": { \"type\": \"string\" }"
                     + "},"
                     + "\"required\": [\"firstname\", \"lastname\", \"totalprice\", \"depositpaid\", \"bookingdates\", \"additionalneeds\"]"
                     + "}";

             Response response = given().header("Accept", "application/json")
                     .when()
                     .get("/booking/" + bookingId)
                     .then()
                     .statusCode(200)
                     .body(matchesJsonSchema(jsonSchema))
                     .extract().response();
             System.out.println("Response Body: " + response.asString());
         }
         @Test(priority = 4)
         public void getBookingXML(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        int bookingId=bookingID;
        Response response = given().header("Accept", "application/xml")
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body(matchesXsdInClasspath("booking-schema.xsd"))
                .extract().response();
        System.out.println("Response Body: " + response.asString());
    }

          @Test(priority = 1)
         public void createBooking(){
         RestAssured.baseURI = "https://restful-booker.herokuapp.com";

             String firstname = "Jim";
             String lastname = "Brown";
             int totalprice = 111;
             boolean depositpaid = true;
             String checkin = "2018-01-01";
             String checkout = "2019-01-01";
             String additionalneeds = "Breakfast";

             JSONObject bookingDates = new JSONObject();
             bookingDates.put("checkin", checkin);
             bookingDates.put("checkout", checkout);

             JSONObject bookingDetails = new JSONObject();
             bookingDetails.put("firstname", firstname);
             bookingDetails.put("lastname", lastname);
             bookingDetails.put("totalprice", totalprice);
             bookingDetails.put("depositpaid", depositpaid);
             bookingDetails.put("bookingdates", bookingDates);
             bookingDetails.put("additionalneeds", additionalneeds);

         Response response =given().
                 header("Content-Type", "application/json")
                 .body(bookingDetails.toString())
                 .when().post("/booking")
                .then().statusCode(200).extract().response();
              bookingID =  response.jsonPath().getInt("bookingid");}

    @Test(priority = 3)
    public void  updateBooking(){


        // Define the updated booking details
        String firstname = "James";
        String lastname = "Brown";
        int totalprice = 111;
        boolean depositpaid = true;
        String checkin = "2018-01-01";
        String checkout = "2019-01-01";
        String additionalneeds = "Breakfast";

        // Construct the JSON body for the PUT request
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);

        JSONObject bookingDetails = new JSONObject();
        bookingDetails.put("firstname", firstname);
        bookingDetails.put("lastname", lastname);
        bookingDetails.put("totalprice", totalprice);
        bookingDetails.put("depositpaid", depositpaid);
        bookingDetails.put("bookingdates", bookingDates);
        bookingDetails.put("additionalneeds", additionalneeds);

            RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .cookie("token", token) // Include the token in the Cookie header
                .body(bookingDetails.toString()) // Use the dynamic JSON body
                .when()
                .put("/booking/" + bookingID) // PUT request to update the specific booking by ID
                .then()
                .statusCode(200) // Check for success status code
                .extract().response();
        System.out.println(response.asString());

        }

        @Test(priority = 5)
        public void partialUpdate(){
            RestAssured.baseURI = "https://restful-booker.herokuapp.com";
            String firstname = "John";
            int totalprice = 150;


            JSONObject updateData = new JSONObject();
            updateData.put("firstname", firstname);
            updateData.put("totalprice", totalprice);


            Response response = given()
                    .header("Content-Type", "application/json")
                    .cookie("token",token)
                    .body(updateData.toString())
                    .when().patch("/booking/"+bookingID)
                    .then().statusCode(200).extract().response();

            System.out.println(response.asString());
        }

        @Test(priority = 6)
    public void  deleteBooking(){
            RestAssured.baseURI = "https://restful-booker.herokuapp.com";
            Response response = given().header("Content-Type","application/json")
                    .cookie("token",token) // Pass the basic authorization
                    .when().delete("/booking/" + bookingID)
                    .then().statusCode(201).extract().response();
            System.out.println(response.asString());
            Assert.assertTrue(response.asString().contains("Created"));

        }


         @Test(priority = 10)
         public void testApiHealth(){
    Response response =given().when().get("https://restful-booker.herokuapp.com/ping").then().statusCode(201).extract().response();
             Assert.assertTrue(response.asString().contains("Created"),"Api is not working");
         }

}


