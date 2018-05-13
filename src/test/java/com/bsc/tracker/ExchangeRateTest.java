package com.bsc.tracker;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExchangeRateTest {

    @Test
    void shouldReturnExchangeRateForValidCurrencyTest() {
        assertTrue(ExchangeRate.findExchangeRate("EUR").isPresent());
        assertEquals(ExchangeRate.findExchangeRate("EUR").get(), ExchangeRate.EUR.getExchangeRate());
        assertFalse(ExchangeRate.findExchangeRate(null).isPresent());
        assertFalse(ExchangeRate.findExchangeRate("EEE").isPresent());
    }
}
