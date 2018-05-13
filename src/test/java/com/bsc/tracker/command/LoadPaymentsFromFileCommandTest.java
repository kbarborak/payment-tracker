package com.bsc.tracker.command;

import com.bsc.tracker.Payment;
import com.bsc.tracker.PaymentStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoadPaymentsFromFileCommandTest {

    private ByteArrayOutputStream stream;
    private PrintWriter writer;
    private PaymentStore store;
    private LoadPaymentsFromFileCommand command;

    class FakePaymentLoader extends FilePaymentLoader {
        @Override
        public List<Payment> load(String path) {
            return Arrays.asList(
                    new Payment("USD 1000"),
                    new Payment("CZK 11")
            );
        }
    }

    @BeforeEach
    void setUp() {
        stream = new ByteArrayOutputStream();
        writer = new PrintWriter(stream);
        store = new PaymentStore();
        command = new LoadPaymentsFromFileCommand(store, new FakePaymentLoader());
    }

    @Test
    void commandShouldFillStoreTest() {
        command.execute("fake", writer);

        assertEquals(2, store.getSummary().size());
        assertEquals(new Payment("USD 1000"), store.getSummary().get(0));
        assertEquals(new Payment("CZK 11"), store.getSummary().get(1));
    }
}
