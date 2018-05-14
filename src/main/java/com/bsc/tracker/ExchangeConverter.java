package com.bsc.tracker;

import org.joda.money.CurrencyUnit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

/**
 * Exchange converter.
 */
public class ExchangeConverter {

    /**
     * Convert payment to USD if exchange rate exists
     *
     * @param payment
     * @return
     */
    public Optional<Payment> convertToUSD(Payment payment) {
        if (!ExchangeRateUSD.findRate(payment.getCurrency()).isPresent()) {
            return Optional.empty();
        }

        BigDecimal usdRate = ExchangeRateUSD.findRate(payment.getCurrency()).get();

        return Optional.of(payment.convert(CurrencyUnit.USD, usdRate));
    }
}
