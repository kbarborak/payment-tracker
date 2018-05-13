package com.bsc.tracker.command;

import com.bsc.tracker.Payment;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FilePaymentLoaderTest {

    @Test
    void loaderTest() {
        List<Payment> payments = new LinkedList<>();

        try {
            payments = new FilePaymentLoader().load(this.getClass().getResourceAsStream("/payments.txt"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(5, payments.size());
        assertEquals(new Payment("USD 1000"), payments.get(0));
        assertEquals(new Payment("USD -1200"), payments.get(4));
    }
}
