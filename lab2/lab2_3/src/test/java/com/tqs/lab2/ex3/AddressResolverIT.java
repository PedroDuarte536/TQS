package com.tqs.lab2.ex3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.tqs.lab2.ex2.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * AddressResolverIT
 */
public class AddressResolverIT {

  // SuT
  AddressResolver resolver;

  @BeforeEach
  public void loadSuT() {
    resolver = new AddressResolver(new TqsHttpClient());
  }

  @Test
  public void findAddressForLocation_Success() {

    // Execute Test
    Optional<Address> result = resolver.findAddressForLocation(40.6318, -8.658);

    // Verify Result
    assertTrue(result.isPresent());
    assertEquals("3810-193", result.get().getZio());
    assertEquals("Glória e Vera Cruz", result.get().getCirty());
    assertEquals("", result.get().getHouseNumber());
    assertEquals("Parque Estacionamento da Reitoria - Univerisdade de Aveiro", result.get().getRoad());
    assertEquals("Centro", result.get().getState());

  }

  @Test
  public void findAddressForLocation_NotPresent_InvalidCoordinates() {

    // Execute Test
    Optional<Address> resultMinLatitude = resolver.findAddressForLocation(-91, -8.658);
    Optional<Address> resultMaxLatitude = resolver.findAddressForLocation(91, -8.658);
    Optional<Address> resultMinLongitude = resolver.findAddressForLocation(40.6318, -181);
    Optional<Address> resultMaxLongitude = resolver.findAddressForLocation(40.6318, 181);

    // Verify Result
    assertFalse(resultMinLatitude.isPresent());
    assertFalse(resultMaxLatitude.isPresent());
    assertFalse(resultMinLongitude.isPresent());
    assertFalse(resultMaxLongitude.isPresent());

  }

}