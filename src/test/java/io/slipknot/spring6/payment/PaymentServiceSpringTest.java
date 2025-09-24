package io.slipknot.spring6.payment;

import static org.assertj.core.api.Assertions.assertThat;

import io.slipknot.spring6.TestObjectFactory;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceSpringTest {

  @Autowired
  private PaymentService paymentService;

  @Autowired
  private ExchangeRateProviderStub exchangeRateProviderStub;

  @Test
  @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족했는지 검증")
  void convertedAmount() {
    Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    assertThat(payment.getExchangeRate()).isEqualByComparingTo(BigDecimal.valueOf(1_000));
    assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));

    exchangeRateProviderStub.setExchangeRate(BigDecimal.valueOf(500));
    Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    assertThat(payment2.getExchangeRate()).isEqualByComparingTo(BigDecimal.valueOf(500));
    assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(5_000));
  }

}