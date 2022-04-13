package com.tqs.lab3.ex2.lab3_2;

import com.tqs.lab3.ex2.lab3_2.data.Car;
import com.tqs.lab3.ex2.lab3_2.data.CarRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
public class CarRepositoryTest {
  
  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private CarRepository carRepository;

  @Test
  void whenFindPuntoByCarId_thenReturnPuntoCar() {

    Car punto = new Car("Fiat", "Punto");
    entityManager.persistAndFlush(punto);

    assertThat(carRepository.findByCarId(punto.getCarId()).getModel()).isEqualTo("Punto");

  }

  @Test
  void whenFindInvalidCarId_thenReturnNull() {

    assertThat(carRepository.findByCarId(100L)).isNull();

  }

  @Test
  void givenSetOfCars_whenFindAll_thenReturnAllCars() {
    Car punto = new Car("Fiat", "Punto");
    Car bm = new Car("BMW", "X3");
    Car barbie = new Car("Fiat", "500");

    entityManager.persist(punto);
    entityManager.persist(bm);
    entityManager.persist(barbie);
    entityManager.flush();

    List<Car> allCars = carRepository.findAll();

    assertThat(allCars).hasSize(3).extracting(Car::getModel).containsOnly(punto.getModel(), bm.getModel(), barbie.getModel());
  }

}
