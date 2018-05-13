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
    public Optional<Payment> convertToUsd(Payment payment) {
        if (!ExchangeRate.findExchangeRate(payment.getCurrency()).isPresent()) {
            return Optional.empty();
        }

        if (payment.getCurrency().equals("USD")) {
            return Optional.of(payment);
        }

        BigDecimal rateToCzk = ExchangeRate.findExchangeRate(payment.getCurrency()).get();
        Payment czk = payment.convert(CurrencyUnit.getInstance("CZK"), rateToCzk);

        BigDecimal czkToUsdRate = new BigDecimal("1").divide(ExchangeRate.USD.getExchangeRate(), 4, RoundingMode.HALF_DOWN);

        return Optional.of(czk.convert(CurrencyUnit.USD, czkToUsdRate));
    }
}
