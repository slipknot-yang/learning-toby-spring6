package io.slipknot.spring6.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Payment {

  private Long orderId;
  private String currency;
  private BigDecimal foreignCurrencyAmount;
  private BigDecimal exchangeRate;
  private BigDecimal convertedAmount;
  private LocalDateTime validUntil;

  public static Payment prepared(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exchangeRate, LocalDateTime now) {
    BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exchangeRate);
    LocalDateTime validUtil = now.plusMinutes(30);
    return new Payment(orderId, currency, foreignCurrencyAmount, exchangeRate, convertedAmount, validUtil);
  }

}
