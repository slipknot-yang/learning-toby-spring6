package io.slipknot.spring6.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Policy Layer
 * - DIP: 상위 레벨 모듈은 하위 수준의 모듈에 의존해서는 안된다
 * - 상위 레벨 모듈(Policy Layer) 즉, payment 모듈은 하위 수준(Mechanism Layer) 즉, exchangerate 모듈에 의존해서는 안된다.
 * - 현재 payment.PaymentService는 exchangerate.ExchangeRateProvider를 의존하는데, 상위 레벨 모듈은 하위 레빌 모듈에 의존하고 있다.
 * - 따라서 인터페이스 소유권의 역전(인터페이스 모듈 위치 변경)이 필요하다. exchangeRateProvider 인터페이스는 payment 모듈에 존재하면 DIP 원칙을 만족한다.
 */
public class PaymentService {

  private final ExchangeRateProvider exchangeRateProvider;

  public PaymentService(ExchangeRateProvider exchangeRateProvider) {
    this.exchangeRateProvider = exchangeRateProvider;
  }

  public Payment prepare(Long orderId, String currency, BigDecimal amount) {
    BigDecimal exchangeRate = exchangeRateProvider.getExchangeRate(currency);
    return new Payment(orderId, currency, amount, exchangeRate, amount.multiply(exchangeRate), LocalDateTime.now().plusDays(1));
  }
}
