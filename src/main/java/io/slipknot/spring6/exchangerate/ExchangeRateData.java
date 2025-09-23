package io.slipknot.spring6.exchangerate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExchangeRateData(
    String result,
    Map<String, BigDecimal> rates
) {

}
