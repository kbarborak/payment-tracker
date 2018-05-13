package com.bsc.tracker;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentStoreTest {

    @Test
    void basicUsageTest() {
        PaymentStore store = new PaymentStore();

        store.add(new Payment("USD 1000"));
        store.add(new Payment("HKD 100"));
        store.add(new Payment("USD -100"));
        store.add(new Payment("CNY 2000"));
        store.add(new Payment("HKD 200"));

        List<Payment> expected = Arrays.asList(
                new Payment("USD 900"),
                new Payment("CNY 2000"),
                new Payment("HKD 300")
        );

        assertEquals(expected, store.getSummary());
    }

    @Test
    void currenciesWithZeroBalanceShouldBeAvoidedTest() {
        PaymentStore store = new PaymentStore();

        store.add(new Payment("USD 1000"));
        store.add(new Payment("HKD 100"));
        store.add(new Payment("USD -1000"));
        store.add(new Payment("CNY 2000"));
        store.add(new Payment("HKD 200"));

        List<Payment> expected = Arrays.asList(
                new Payment("CNY 2000"),
                new Payment("HKD 300")
        );

        assertEquals(expected, store.getSummary());
    }
}
