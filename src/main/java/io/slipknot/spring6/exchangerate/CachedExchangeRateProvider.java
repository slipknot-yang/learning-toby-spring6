package io.slipknot.spring6.exchangerate;

import io.slipknot.spring6.payment.ExchangeRateProvider;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExchangeRateProvider implements ExchangeRateProvider {

  private final ExchangeRateProvider target;

  private BigDecimal cachedExchangeRate;
  private LocalDateTime cacheExpiryTime;


  public CachedExchangeRateProvider(ExchangeRateProvider target) {
    this.target = target;
  }

  @Override
  public BigDecimal getExchangeRate(String currency) {
    if (cachedExchangeRate == null || cacheExpiryTime.isBefore(LocalDateTime.now())) {
      cachedExchangeRate = target.getExchangeRate(currency);
      cacheExpiryTime = LocalDateTime.now().plusSeconds(3);

      System.out.printf("Cache hit: %s", currency);
    }
    return cachedExchangeRate;
  }
}
