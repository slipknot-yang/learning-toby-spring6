package io.slipknot.spring6.exchangerate;

import io.slipknot.spring6.payment.ExchangeRateProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

public class WebApiExchangeRateProvider implements ExchangeRateProvider {

  @Override
  public BigDecimal getExchangeRate(String currency) {
    String url = "https://open.er-api.com/v6/latest/" + currency;

    URI uri;
    try {
      uri = new URI(url);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }

    String response;
    try {
      HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
      try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
        response = br.lines().collect(Collectors.joining());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try {
      ObjectMapper mapper = new ObjectMapper();
      ExchangeRateData data = mapper.readValue(response, ExchangeRateData.class);
      return data.rates().get("KRW");
    } catch (JacksonException e) {
      throw new RuntimeException(e);
    }
  }
}
