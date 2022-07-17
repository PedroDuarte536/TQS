package com.tqs.lab7.ex2.lab7_2;

import java.io.IOException;
import java.util.List;

import com.tqs.lab7.ex2.lab7_2.data.Car;
import com.tqs.lab7.ex2.lab7_2.data.CarRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CarRestControllerRestAssuredIT {

  @LocalServerPort
  int randomServerPort;

  @Autowired
  private CarRepository repository;

  @AfterEach
  public void resetDb() {
    repository.deleteAll();
  }

  @Test
  void whenValidInput_thenCreateCar() throws IOException {
    Car punto = new Car("Fiat", "Punto");
    given().body(JsonUtils.toJson(punto)).when().post("/api/car");

    List<Car> found = repository.findAll();
    assertThat(found).extracting(Car::getModel).containsOnly("Punto");
  }

  @Test
  void givenCars_whenGetCars_thenStatus200() {
    repository.saveAndFlush(new Car("Fiat", "Punto"));
    repository.saveAndFlush(new Car("BMW", "X3"));

    get("/api/car").then().statusCode(200).body("model", hasItems("Punto", "X3"));
  }
  
}
