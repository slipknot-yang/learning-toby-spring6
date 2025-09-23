package io.slipknot.spring6.payment;

import java.math.BigDecimal;

/**
 * Mechanism Layer
 */
public interface ExchangeRateProvider {

  BigDecimal getExchangeRate(String currency);
}
