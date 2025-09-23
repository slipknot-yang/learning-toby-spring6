package io.slipknot.spring6;

import io.slipknot.spring6.exchangerate.WebApiExchangeRateProvider;
import io.slipknot.spring6.exchangerate.CachedExchangeRateProvider;
import io.slipknot.spring6.payment.ExchangeRateProvider;
import io.slipknot.spring6.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {

  @Bean
  public PaymentService paymentService() {
    return new PaymentService(cachedExchangeRateProvider());
  }

  @Bean
  public ExchangeRateProvider exchangeRateProvider() {
    return new WebApiExchangeRateProvider();
  }

  @Bean
  public ExchangeRateProvider cachedExchangeRateProvider() {
    return new CachedExchangeRateProvider(exchangeRateProvider());
  }

}
