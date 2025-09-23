package io.slipknot.spring6.payment;

import static org.assertj.core.api.Assertions.assertThat;

import io.slipknot.spring6.exchangerate.WebApiExchangeRateProvider;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * PaymentService 테스트의 문제점
 *
 * <li>1. 우리가 제어할 수 없는 외부 시스템에 문제가 생기면?
 * <li>2. ExchangeProvider가 제공하는 환율값으로 계산한 것인가?
 * <li>3. 환율 유효시간 계산은 정확한가?
 */
class PaymentServiceTest {

  @Test
  @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족했는지 검증")
  void prepare() {
    ExchangeRateProvider exchangeRateProvider = new WebApiExchangeRateProvider();
    PaymentService paymentService = new PaymentService(exchangeRateProvider);
    Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    // 환율정보 가져온다.
    assertThat(payment.getExchangeRate()).isNotNull(); // WebApiExchangeRateProvider를 사용하므로 매번 환율이 변경되므로 isNotNull으로만 확인

    // 원화환산금액 계산
    assertThat(payment.getConvertedAmount()).isEqualTo(BigDecimal.TEN.multiply(payment.getExchangeRate()));

    // 원화환산금액의 유효시간 계산
    assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
  }
}