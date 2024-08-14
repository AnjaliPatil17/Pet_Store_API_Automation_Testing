package Swagger;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetStore {
    int orderid;
    int petid;


//User
    @Test(priority = 1)
    public void createUser(){
      Response res = given()
              .header("Accept","application/json")
              .header("Content-Type","application/json")
              .body("{\n" +
                      "  \"id\": 1,\n" +
                      "  \"username\": \"anjali17\",\n" +
                      "  \"firstName\": \"anjali\",\n" +
                      "  \"lastName\": \"patil\",\n" +
                      "  \"email\": \"string\",\n" +
                      "  \"password\": \"string\",\n" +
                      "  \"phone\": \"string\",\n" +
                      "  \"userStatus\": 0\n" +
                      "}")
              .when()
              .post("https://petstore.swagger.io/v2/user");
      res.prettyPrint();
    }

    @Test(priority = 2)
    public void getUserByName(){
        Response res = given()
                .header("Accept","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/user/anjali17");
        res.prettyPrint();
       res.then().statusCode(200);
        Assert.assertEquals(res.statusCode(),200);
    }

    @Test(priority = 3)
    public void userlogin(){
        Response res = given()
                .header("Accept","application/json")
                .queryParams("username","anjali17")
                .queryParams("password","string")
                .when()
                .get(" https://petstore.swagger.io/v2/user/login");
        res.prettyPrint();
        res.then().statusCode(200);
        Assert.assertEquals(res.statusCode(),200);
    }

    @Test(priority = 5)
    public void createUsersWithListInput(){
        Response res = given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .body("[\n" +
                        "  {\n" +
                        "    \"id\": 2,\n" +
                        "    \"username\": \"ashwini\",\n" +
                        "    \"firstName\": \"string\",\n" +
                        "    \"lastName\": \"string\",\n" +
                        "    \"email\": \"string\",\n" +
                        "    \"password\": \"string\",\n" +
                        "    \"phone\": \"string\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  },\n" +
                        "{\n" +
                        "    \"id\": 3,\n" +
                        "    \"username\": \"akash\",\n" +
                        "    \"firstName\": \"string\",\n" +
                        "    \"lastName\": \"string\",\n" +
                        "    \"email\": \"string\",\n" +
                        "    \"password\": \"string\",\n" +
                        "    \"phone\": \"string\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  }\n" +
                        "]")
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithList");
        res.prettyPrint();
    }

    @Test(priority = 4)
    public void updateuser(){
        Response res = given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .when()
                .body("{\n" +
                        "  \"id\": 2,\n" +
                        "  \"username\": \"ashwini\",\n" +
                        "  \"firstName\": \"string\",\n" +
                        "  \"lastName\": \"patil\",\n" +
                        "  \"email\": \"int\",\n" +
                        "  \"password\": \"string\",\n" +
                        "  \"phone\": \"string\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .put(" https://petstore.swagger.io/v2/user/ashwini");
        res.prettyPrint();
        res.then().statusCode(200);
        Assert.assertEquals(res.statusCode(),200);
    }

    @Test(priority = 6)
    public void userlogout(){
        Response res = given()
                .header("Accept","application/json")
                .when()
                .get(" https://petstore.swagger.io/v2/user/logout");
        res.prettyPrint();
        res.then().statusCode(200);
        Assert.assertEquals(res.statusCode(),200);
    }

    @Test(priority = 7)
    public void deleteUser(){
        Response res = given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/user/anjali17");
        res.prettyPrint();
    }




    //Store
    @Test(priority = 8)
    public void placeOrder(){
        Response res = given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"petId\": 1,\n" +
                        "  \"quantity\": 0,\n" +
                        "  \"shipDate\": \"2024-08-12T09:42:41.292Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": true\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/store/order");
        res.prettyPrint();
       orderid=res.path("id");
        res.then().statusCode(200);
        Assert.assertEquals(res.statusCode(),200);
    }

    @Test(priority = 9)
    public void getOrderById(){
        Response res = given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/store/order/"+orderid);
        res.prettyPrint();
        res.then().statusCode(200);
        Assert.assertEquals(res.statusCode(),200);
    }

    @Test(priority = 10)
    public void getInventory(){
        Response res = given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .when()
                .get(" https://petstore.swagger.io/v2/store/inventory");
        res.prettyPrint();
        res.then().statusCode(200);
        Assert.assertEquals(res.statusCode(),200);
    }

    @Test(priority = 11)
    public void removeOrder(){
        Response res = given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/store/order/1");
        res.prettyPrint();
        res.then().statusCode(200);
        Assert.assertEquals(res.statusCode(),200);
    }



    //Pet
    @Test(priority = 12)
    public void addpet(){
        Response res = given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 1,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/pet");
        res.prettyPrint();
        petid=res.path("id");
        res.then().statusCode(200);
        Assert.assertEquals(res.statusCode(),200);
    }

    @Test(priority = 13)
    public void uploadFile(){
        File file =new File("C:\\Users\\DELL\\Downloads\\penguine.png");
        Response res = given()
                .header("Accept","application/json")
                .header("Content-Type"," multipart/form-data")
                .multiPart(file)
                .when()
                .post(" https://petstore.swagger.io/v2/pet/1/uploadImage");
        res.prettyPrint();
        res.then().statusCode(200);
        Assert.assertEquals(res.statusCode(),200);
    }

    @Test(priority = 15)
    public void updatePetWithForm(){
        Response res = given()
                .header("Accept","application/json")
                .header("Content-Type"," application/x-www-form-urlencoded")
                .when()
                .post("https://petstore.swagger.io/v2/pet/2");
        res.prettyPrint();
        res.then().statusCode(200);
        Assert.assertEquals(res.statusCode(),200);
    }

    @Test(priority = 12)
    public void getPetById(){
        Response res = given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/pet/"+petid);
        res.prettyPrint();
        res.then().statusCode(200);
        Assert.assertEquals(res.statusCode(),200);
    }

    @Test(priority = 14)
    public void findPetsByStatus(){
        Response res = given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");
        res.prettyPrint();
        res.then().statusCode(200);
        Assert.assertEquals(res.statusCode(),200);
    }

    @Test(priority = 16)
    public void updatePet(){
        Response res = given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"Dognation\"\n" +
                        "  },\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .put("https://petstore.swagger.io/v2/pet");
        res.prettyPrint();
        res.then().statusCode(200);
        Assert.assertEquals(res.statusCode(),200);
    }

    @Test(priority = 17)
    public void removePet() {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/pet/1");
        res.prettyPrint();
        res.then().statusCode(200);
//        Assert.assertEquals(res.statusCode(), 200);
    }
}
