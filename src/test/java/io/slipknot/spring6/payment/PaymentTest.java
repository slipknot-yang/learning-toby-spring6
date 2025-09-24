package io.slipknot.spring6.payment;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;

class PaymentTest {

  @Test
  void createPayment() {
    Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    Payment payment = Payment.prepared(1L, "USD", TEN, valueOf(1_000), LocalDateTime.now(clock));

    assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));
    assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
  }

}