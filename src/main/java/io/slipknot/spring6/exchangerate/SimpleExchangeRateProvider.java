package io.slipknot.spring6.exchangerate;

import io.slipknot.spring6.payment.ExchangeRateProvider;
import java.math.BigDecimal;

public class SimpleExchangeRateProvider implements ExchangeRateProvider {

  @Override
  public BigDecimal getExchangeRate(String currency) {
    if (currency.equals("USB")) {
      return BigDecimal.valueOf(100);
    }
    throw new IllegalArgumentException("Unknown currency: " + currency);
  }
}
