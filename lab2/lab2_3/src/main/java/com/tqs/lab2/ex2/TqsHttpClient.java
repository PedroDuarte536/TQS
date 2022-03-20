package com.tqs.lab2.ex2;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class TqsHttpClient implements ISimpleHttpClient {

  public TqsHttpClient() {
    super();
  }

  @Override
  public String doHttpGet(String url) {
    try {
      return EntityUtils.toString(HttpClients.createDefault().execute(new HttpGet(url)).getEntity());
    } catch (Exception e) {
      return null;
    }
  }
  
}
