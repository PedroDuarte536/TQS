package com.tqs.lab3.ex2.lab3_2;

import java.util.List;

import com.tqs.lab3.ex2.lab3_2.data.Car;
import com.tqs.lab3.ex2.lab3_2.data.CarRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CarRestControllerTemplateIT {

  @LocalServerPort
  int randomServerPort;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private CarRepository repository;

  @AfterEach
  public void resetDb() {
    repository.deleteAll();
  }

  @Test
  void whenValidInput_thenCreateCar() {
    Car punto = new Car("Fiat", "Punto");
    restTemplate.postForEntity("/api/car", punto, Car.class);

    List<Car> found = repository.findAll();
    assertThat(found).extracting(Car::getModel).containsOnly("Punto");
  }

  @Test
  void givenCars_whenGetCars_thenStatus200() {
    repository.saveAndFlush(new Car("Fiat", "Punto"));
    repository.saveAndFlush(new Car("BMW", "X3"));

    ResponseEntity<List<Car>> response = restTemplate.exchange("/api/car", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {});

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).extracting(Car::getModel).containsExactly("Punto", "X3");

  }
  
}
