package com.tqs.lab3.ex3.lab3_3;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.tqs.lab3.ex3.lab3_3.boundary.CarController;
import com.tqs.lab3.ex3.lab3_3.data.Car;
import com.tqs.lab3.ex3.lab3_3.service.CarManagerService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CarController.class)
public class CarController_WithMockServiceTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CarManagerService service;

  @Test
  void whenPostCar_thenCreateCar() throws Exception {
    Car punto = new Car("Fiat", "Punto");

    when(service.save(Mockito.any())).thenReturn(punto);

    mvc.perform(
        post("/api/car")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtils.toJson(punto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.model", is("Punto")));

    verify(service, times(1)).save(Mockito.any());
  }

  @Test
  void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
    Car punto = new Car("Fiat", "Punto");
    Car bm = new Car("BMW", "X3");
    Car barbie = new Car("Fiat", "500");

    List<Car> allCars = Arrays.asList(punto, bm, barbie);

    when(service.getAllCars()).thenReturn(allCars);

    mvc.perform(
        get("/api/car").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].model", is(punto.getModel())))
        .andExpect(jsonPath("$[1].model", is(bm.getModel())))
        .andExpect(jsonPath("$[2].model", is(barbie.getModel())));

    verify(service, times(1)).getAllCars();
  }

  @Test
  void whenGetCarDetails_ifCarExists_thenReturnJson() throws Exception {
    Car punto = new Car("Fiat", "alex@deti.com");

    when(service.getCarDetails(Mockito.anyLong())).thenReturn(Optional.of(punto));

    mvc.perform(
        get("/api/car/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.model", is(punto.getModel())));

    verify(service, times(1)).getCarDetails(Mockito.anyLong());
  }

  @Test
  void whenGetCarDetails_ifCarNotExists_thenStatusNotFound() throws Exception {
    when(service.getCarDetails(Mockito.anyLong())).thenReturn(Optional.empty());

    mvc.perform(
        get("/api/car/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());

    verify(service, times(1)).getCarDetails(Mockito.anyLong());
  }
  
}
