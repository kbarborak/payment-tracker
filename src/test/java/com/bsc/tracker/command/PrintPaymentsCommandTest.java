package com.bsc.tracker.command;

import com.bsc.tracker.Payment;
import com.bsc.tracker.PaymentStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PrintPaymentsCommandTest {

    private ByteArrayOutputStream stream;
    private PrintWriter writer;
    private PaymentStore store;
    private PrintPaymentsCommand command;

    @BeforeEach
    void setUp() {
        stream = new ByteArrayOutputStream();
        writer = new PrintWriter(stream);
        store = new PaymentStore();
        command = new PrintPaymentsCommand(store);
    }

    @Test
    void commandShouldPrintEmptyMessageTest() {
        command.execute(null, writer);
        writer.flush();

        assertEquals("\nPayments summary\n----------------\nNo payments yet or nothing with non-zero balance.\n\n", stream.toString());
    }

    @Test
    void commandShouldPrintStoreTest() {
        store.add(new Payment("USD 10"));
        store.add(new Payment("CZK 5"));
        store.add(new Payment("GBP 15"));

        command.execute(null, writer);
        writer.flush();

        assertEquals("\nPayments summary\n----------------\nUSD 10\nCZK 5\nGBP 15\n\n", stream.toString());
    }

    @Test
    void commandShouldNotRequireExitTest() {
        assertFalse(command.isRequiredExit());
    }
}
