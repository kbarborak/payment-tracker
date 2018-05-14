package com.bsc.tracker;

import java.math.BigDecimal;
import java.util.Optional;

public enum ExchangeRateUSD {

    AUD(new BigDecimal("0.756")),
    BRL(new BigDecimal("0.278")),
    BGN(new BigDecimal("0.611")),
    CNY(new BigDecimal("0.158")),
    DKK(new BigDecimal("0.160")),
    EUR(new BigDecimal("1.196")),
    PHP(new BigDecimal("0.019")),
    HKD(new BigDecimal("0.127")),
    HRK(new BigDecimal("0.161")),
    INR(new BigDecimal("0.015")),
    IDR(new BigDecimal("0.000072")),
    ISK(new BigDecimal("0.0098")),
    ILS(new BigDecimal("0.280")),
    JPY(new BigDecimal("0.009")),
    ZAR(new BigDecimal("0.082")),
    KRW(new BigDecimal("0.0009")),
    CAD(new BigDecimal("0.782")),
    HUF(new BigDecimal("0.0038")),
    MYR(new BigDecimal("0.252")),
    MXN(new BigDecimal("0.052")),
    XDR(new BigDecimal("1.431")),
    NOK(new BigDecimal("0.125")),
    NZD(new BigDecimal("0.696")),
    PLN(new BigDecimal("0.280")),
    RON(new BigDecimal("0.258")),
    RUB(new BigDecimal("0.16")),
    SGD(new BigDecimal("0.750")),
    SEK(new BigDecimal("0.116")),
    CHF(new BigDecimal("1.0009")),
    THB(new BigDecimal("0.031")),
    TRY(new BigDecimal("0.231")),
    GBP(new BigDecimal("1.357")),
    CZK(new BigDecimal("0.047"));

    private BigDecimal exchangeRate;

    ExchangeRateUSD(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getRate() {
        return exchangeRate;
    }

    public static Optional<BigDecimal> findRate(String currencyCode) {
        try {
            return Optional.of(valueOf(currencyCode).exchangeRate);
        } catch (NullPointerException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
