package com.bsc.tracker;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ExchangeConverterTest {

    @Test
    void shouldReturnExchangePaymentTest() {
        Payment eur = new Payment("EUR 20");
        Optional<Payment> usd = new ExchangeConverter().convertToUsd(eur);

        assertEquals(new Payment("USD 23.87"), usd.get());
    }

    @Test
    void shouldReturnExchangePayment2Test() {
        Payment eur = new Payment("CNY 20");
        Optional<Payment> usd = new ExchangeConverter().convertToUsd(eur);

        assertEquals(new Payment("USD 3.16"), usd.get());
    }

    @Test
    void shouldNotConvertUsdTest() {
        Payment eur = new Payment("USD 23.28");
        Optional<Payment> usd = new ExchangeConverter().convertToUsd(eur);

        assertEquals(new Payment("USD 23.28"), usd.get());
    }

    @Test
    void shouldReturnEmptyOptionalForCurrencyWithoutRateTest() {
        Payment eur = new Payment("BWP 23.28");
        Optional<Payment> usd = new ExchangeConverter().convertToUsd(eur);

        assertFalse(usd.isPresent());
    }
}
