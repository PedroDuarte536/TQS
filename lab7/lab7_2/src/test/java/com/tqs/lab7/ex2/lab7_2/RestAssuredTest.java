package com.tqs.lab7.ex2.lab7_2;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.tqs.lab7.ex2.lab7_2.boundary.CarController;
import com.tqs.lab7.ex2.lab7_2.data.Car;
import com.tqs.lab7.ex2.lab7_2.service.CarManagerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static org.hamcrest.Matchers.*;

@WebMvcTest(CarController.class)
public class RestAssuredTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CarManagerService service;

  @BeforeEach
  void setup () {
    RestAssuredMockMvc.mockMvc(mvc);
  }

  @Test
  void whenPostCar_thenCreateCar() throws Exception {
    Car punto = new Car("Fiat", "Punto");

    when(service.save(Mockito.any())).thenReturn(punto);

    RestAssuredMockMvc
      .given().contentType(ContentType.JSON).body(JsonUtils.toJson(punto))
      .when().post("/api/car")
      .then().contentType(ContentType.JSON).status(HttpStatus.CREATED).body("model", equalTo("Punto"));

    verify(service, times(1)).save(Mockito.any());
  }

  @Test
  void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
    Car punto = new Car("Fiat", "Punto");
    Car bm = new Car("BMW", "X3");
    Car barbie = new Car("Fiat", "500");

    List<Car> allCars = Arrays.asList(punto, bm, barbie);

    when(service.getAllCars()).thenReturn(allCars);

    RestAssuredMockMvc.get("/api/car").then().contentType(ContentType.JSON).status(HttpStatus.OK)
      .body("model", hasItems(punto.getModel(), bm.getModel(), barbie.getModel()));

    verify(service, times(1)).getAllCars();
  }

  @Test
  void whenGetCarDetails_ifCarExists_thenReturnJson() throws Exception {
    Car punto = new Car("Fiat", "alex@deti.com");

    when(service.getCarDetails(Mockito.anyLong())).thenReturn(Optional.of(punto));

    RestAssuredMockMvc.get("/api/car/1").then().contentType(ContentType.JSON).status(HttpStatus.OK).body("model", equalTo(punto.getModel()));

    verify(service, times(1)).getCarDetails(Mockito.anyLong());
  }

  @Test
  void whenGetCarDetails_ifCarNotExists_thenStatusNotFound() throws Exception {
    when(service.getCarDetails(Mockito.anyLong())).thenReturn(Optional.empty());

    RestAssuredMockMvc.get("/api/car/1").then().status(HttpStatus.NOT_FOUND);

    verify(service, times(1)).getCarDetails(Mockito.anyLong());
  }
  
}
