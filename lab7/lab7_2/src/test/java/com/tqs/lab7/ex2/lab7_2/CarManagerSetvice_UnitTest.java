package com.tqs.lab7.ex2.lab7_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.tqs.lab7.ex2.lab7_2.data.Car;
import com.tqs.lab7.ex2.lab7_2.data.CarRepository;
import com.tqs.lab7.ex2.lab7_2.service.CarManagerService;

@ExtendWith(MockitoExtension.class)
public class CarManagerSetvice_UnitTest {
  
  @Mock(lenient = true)
  private CarRepository carRepository;

  @InjectMocks
  private CarManagerService carManagerService;

  // Test cars' instantiation
  private Car punto = new Car("Fiat", "Punto");
  private Car bm = new Car("BMW", "X3");
  private Car barbie = new Car("Fiat", "500");

  @BeforeEach
  public void setUp() {

    List<Car> allCars = Arrays.asList(punto, bm, barbie);

    Mockito.when(carRepository.save(punto)).thenReturn(punto);
    Mockito.when(carRepository.findAll()).thenReturn(allCars);
    Mockito.when(carRepository.findByCarId(0L)).thenReturn(punto);
    Mockito.when(carRepository.findByCarId(1L)).thenReturn(bm);
    Mockito.when(carRepository.findByCarId(2L)).thenReturn(barbie);

  }

  @Test
  void whenSaveCar_thenReturnsCar() {
    assertThat(carManagerService.save(punto).getModel()).isEqualTo("Punto");

    Mockito.verify(carRepository, VerificationModeFactory.times(1)).save(punto);
  }

  @Test
  void whenGetAllCars_thenReturnsAllCars() {
    List<Car> cars = carManagerService.getAllCars();

    assertThat(cars.size()).isEqualTo(3);

    assertThat(cars.get(0).getModel()).isEqualTo("Punto");
    assertThat(cars.get(1).getModel()).isEqualTo("X3");
    assertThat(cars.get(2).getModel()).isEqualTo("500");
    
    Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
  }

  @Test
  void whenGetCarDetails_ifCarExists_thenReturnsCarDetails() {
    Optional<Car> punto = carManagerService.getCarDetails(0L);

    assertThat(punto.isEmpty()).isEqualTo(false);
    assertThat(punto.get().getModel()).isEqualTo("Punto");

    Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarId(0L);
  }

  @Test
  void whenGetCarDetails_ifCarNotExists_thenReturnsEmpty() {
    assertThat(carManagerService.getCarDetails(20L)).isEmpty();

    Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarId(20L);
  }

}
