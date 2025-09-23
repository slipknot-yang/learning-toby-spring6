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
  private BigDecimal exRate;
  private BigDecimal convertedAmount;
  private LocalDateTime validUntil;


}
