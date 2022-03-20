package com.tqs.lab2.ex2;

import java.util.Locale;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;

public class AddressResolver {

  private ISimpleHttpClient httpClient;

  public AddressResolver(ISimpleHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  public Optional<Address> findAddressForLocation (double latitude, double longitude) {
    if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) return Optional.empty();

    String json = httpClient.doHttpGet(String.format(Locale.getDefault(), "http://open.mapquestapi.com/geocoding/v1/reverse?key=uXSAVwYWbf9tJmsjEGHKKAo0gOjZfBLQ&location=%s,%s&includeRoadMetadata=true", latitude, longitude));
    
    JSONObject data;
    String road, state, cirty, zio, houseNumber;

    try {
      data = new JSONObject(json).getJSONArray("results").getJSONObject(0).getJSONArray("locations").getJSONObject(0);

      road = data.getString("street");
      state = data.getString("adminArea3");
      cirty = data.getString("adminArea5");
      zio = data.getString("postalCode");
      houseNumber = data.getString("adminArea6");
    } catch (JSONException e) {
      return Optional.empty();
    }

    return Optional.of(new Address(road, state, cirty, zio, houseNumber));
  }
  
}
