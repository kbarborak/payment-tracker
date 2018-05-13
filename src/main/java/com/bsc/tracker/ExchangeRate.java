package com.bsc.tracker;

import java.math.BigDecimal;
import java.util.Optional;

public enum ExchangeRate {

    AUD(new BigDecimal("16.142")),
    BRL(new BigDecimal("6.005")),
    BGN(new BigDecimal("13.040")),
    CNY(new BigDecimal("3.375")),
    DKK(new BigDecimal("3.424")),
    EUR(new BigDecimal("25.505")),
    PHP(new BigDecimal("40.771")),
    HKD(new BigDecimal("2.722")),
    HRK(new BigDecimal("3.452")),
    INR(new BigDecimal("31.734")),
    IDR(new BigDecimal("1.532")),
    ISK(new BigDecimal("20.837")),
    ILS(new BigDecimal("5.992")),
    JPY(new BigDecimal("19.553")),
    ZAR(new BigDecimal("1.741")),
    KRW(new BigDecimal("2.003")),
    CAD(new BigDecimal("16.784")),
    HUF(new BigDecimal("8.098")),
    MYR(new BigDecimal("5.409")),
    MXN(new BigDecimal("1.110")),
    XDR(new BigDecimal("30.511")),
    NOK(new BigDecimal("2.673")),
    NZD(new BigDecimal("14.906")),
    PLN(new BigDecimal("5.989")),
    RON(new BigDecimal("5.503")),
    RUB(new BigDecimal("34.660")),
    SGD(new BigDecimal("16.022")),
    SEK(new BigDecimal("2.485")),
    CHF(new BigDecimal("21.374")),
    THB(new BigDecimal("67.053")),
    TRY(new BigDecimal("4.998")),
    USD(new BigDecimal("21.370")),
    GBP(new BigDecimal("28.983")),
    CZK(new BigDecimal("1"));

    private BigDecimal exchangeRate;

    ExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public static Optional<BigDecimal> findExchangeRate(String currencyCode) {
        try {
            return Optional.of(valueOf(currencyCode).exchangeRate);
        } catch (NullPointerException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
