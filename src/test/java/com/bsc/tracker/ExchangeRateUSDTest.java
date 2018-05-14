package com.bsc.tracker;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExchangeRateUSDTest {

    @Test
    void shouldReturnExchangeRateForValidCurrencyTest() {
        assertTrue(ExchangeRateUSD.findRate("EUR").isPresent());
        assertEquals(ExchangeRateUSD.findRate("EUR").get(), ExchangeRateUSD.EUR.getRate());
        assertFalse(ExchangeRateUSD.findRate(null).isPresent());
        assertFalse(ExchangeRateUSD.findRate("EEE").isPresent());
    }
}
