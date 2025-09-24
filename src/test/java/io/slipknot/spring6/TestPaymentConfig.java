package io.slipknot.spring6;

import io.slipknot.spring6.payment.ExchangeRateProviderStub;
import io.slipknot.spring6.payment.PaymentService;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestPaymentConfig {

  @Bean
  public PaymentService paymentService() {
    return new PaymentService(exchangeRateProvider(), clock());
  }

  @Bean
  public ExchangeRateProviderStub exchangeRateProvider() {
    return new ExchangeRateProviderStub(BigDecimal.valueOf(1_000));
  }

  @Bean
  public Clock clock() {
    return Clock.fixed(Instant.now(), ZoneId.systemDefault());
  }

}
