package io.slipknot.spring6.payment;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * PaymentService 테스트의 문제점
 *
 * <li>1. 우리가 제어할 수 없는 외부 시스템에 문제가 생기면?
 * <li>2. ExchangeProvider가 제공하는 환율값으로 계산한 것인가?
 * <li>3. 환율 유효시간 계산은 정확한가?
 * <p>
 * 테스트의 구성요소
 * <li>1. 테스트 대상(SUT, System Under Test). PaymentService(이 대상만 테스트하고 싶음)
 * <li>2. 테스트(Test).
 * <li>3. 협력자(Collaborator). WebApiExchangeRateProvider(테스트 대상이 아님. 즉 Stub/Imposter/Test Double(대역) 사용) --> Exchange Rate API Server(외부 시스템)
 */
class PaymentServiceTest {

  Clock clock;

  @BeforeEach
  void beforeEach() {
    clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
  }

  @Test
  @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족했는지 검증")
  void convertedAmount() {
    testAmount(valueOf(500), valueOf(5_000), clock);
    testAmount(valueOf(1_00), valueOf(10_000), clock);
    testAmount(valueOf(3_00), valueOf(30_000), clock);
  }

  @Test
  void validUtil() {
    PaymentService paymentService = new PaymentService(new ExchangeRateProviderStub(valueOf(1_000)), clock);
    Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    String expectedValidUtil = LocalDateTime.now(clock).plusMinutes(30).toString();
    assertThat(payment.getValidUntil()).isEqualTo(expectedValidUtil);
  }

  private static void testAmount(BigDecimal exchangeRate, BigDecimal convertedAmount, Clock clock) {
    ExchangeRateProvider exchangeRateProvider = new ExchangeRateProviderStub(exchangeRate);  // Stub/Imposter/Test Double(대역) 이용
    PaymentService paymentService = new PaymentService(exchangeRateProvider, clock);
    Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    // BigDecimal은 isEqualTo보다는 isEqualByComparingTo를 사용하여 검증한다.
    assertThat(payment.getExchangeRate()).isEqualByComparingTo(exchangeRate);        // 환율정보 가져온다.
    assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);  // 원화환산금액 계산
  }

}