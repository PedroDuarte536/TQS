package com.tqs.lab7.ex1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;


public class RestAssuredTest {

  private String url = "https://jsonplaceholder.typicode.com";

  @Test
  public void listAllIsAvailable() {

    get(url + "/todos").then().statusCode(200);

  }

  @Test
  public void verifyFourthTodo() {

    get(url + "/todos/4").then().body("title", equalTo("et porro tempora"));

  }

  @Test
  public void verifyIdsExist198And199() {

    get(url + "/todos").then().body("id", hasItems(198, 199));

  }
  
}
