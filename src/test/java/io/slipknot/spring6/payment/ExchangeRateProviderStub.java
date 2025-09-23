package io.slipknot.spring6.payment;

import java.math.BigDecimal;

public class ExchangeRateProviderStub implements ExchangeRateProvider {

  private BigDecimal exchangeRate;

  public ExchangeRateProviderStub(BigDecimal exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  public BigDecimal getExchangeRate() {
    return exchangeRate;
  }

  public void setExchangeRate(BigDecimal exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  @Override
  public BigDecimal getExchangeRate(String currency) {
    return exchangeRate;
  }
}
