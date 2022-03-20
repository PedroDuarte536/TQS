package com.tqs.lab2.ex2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AddressResolverTest {

  private final String url = "http://open.mapquestapi.com/geocoding/v1/reverse?key=uXSAVwYWbf9tJmsjEGHKKAo0gOjZfBLQ&location=40.6318,-8.658&includeRoadMetadata=true";
  private final String response = "{\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"\u00A9 2022 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"\u00A9 2022 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"thumbMaps\":true,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":40.6318,\"lng\":-8.658}},\"locations\":[{\"street\":\"Parque Estacionamento da Reitoria - Univerisdade de Aveiro\",\"adminArea6\":\"\",\"adminArea6Type\":\"Neighborhood\",\"adminArea5\":\"Gl\u00F3ria e Vera Cruz\",\"adminArea5Type\":\"City\",\"adminArea4\":\"\",\"adminArea4Type\":\"County\",\"adminArea3\":\"Centro\",\"adminArea3Type\":\"State\",\"adminArea1\":\"PT\",\"adminArea1Type\":\"Country\",\"postalCode\":\"3810-193\",\"geocodeQualityCode\":\"P1AAA\",\"geocodeQuality\":\"POINT\",\"dragPoint\":false,\"sideOfStreet\":\"N\",\"linkId\":\"0\",\"unknownInput\":\"\",\"type\":\"s\",\"latLng\":{\"lat\":40.631803,\"lng\":-8.657881},\"displayLatLng\":{\"lat\":40.631803,\"lng\":-8.657881},\"mapUrl\":\"http://open.mapquestapi.com/staticmap/v5/map?key=uXSAVwYWbf9tJmsjEGHKKAo0gOjZfBLQ&type=map&size=225,160&locations=40.6318025,-8.657881|marker-sm-50318A-1&scalebar=true&zoom=15&rand=70663981\",\"roadMetadata\":null}]}]}";

  // Mock Http Client
  @Mock ISimpleHttpClient httpClient;

  // Inject SuT
  @InjectMocks AddressResolver resolver;


  @Test
  public void findAddressForLocation_Success () {

    // Load Mock
    when(httpClient.doHttpGet(url)).thenReturn(response);

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
