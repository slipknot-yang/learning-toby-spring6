package io.slipknot.spring6;

import io.slipknot.spring6.payment.ExchangeRateProvider;
import io.slipknot.spring6.payment.ExchangeRateProviderStub;
import io.slipknot.spring6.payment.PaymentService;
import java.math.BigDecimal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestObjectFactory {

  @Bean
  public PaymentService paymentService() {
    return new PaymentService(exchangeRateProvider());
  }

  @Bean
  public ExchangeRateProviderStub exchangeRateProvider() {
    return new ExchangeRateProviderStub(BigDecimal.valueOf(1_000));
  }

}
