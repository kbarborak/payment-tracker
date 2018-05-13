package com.bsc.tracker.command;

import com.bsc.tracker.ExchangeConverter;
import com.bsc.tracker.Payment;
import com.bsc.tracker.PaymentStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PrintPaymentsWithUsdCommandTest {

    private ByteArrayOutputStream stream;
    private PrintWriter writer;
    private PaymentStore store;
    private PrintPaymentsCommand command;

    @BeforeEach
    void setUp() {
        stream = new ByteArrayOutputStream();
        writer = new PrintWriter(stream);
        store = new PaymentStore();
        command = new PrintPaymentsWithUsdCommand(store, new ExchangeConverter());
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

        command.execute(null, writer);
        writer.flush();

        assertEquals("\nPayments summary\n----------------\nUSD 10\n\n", stream.toString());
    }

    @Test
    void commandShouldShowUsdConversionTest() {
        store.add(new Payment("CZK 10"));

        command.execute(null, writer);
        writer.flush();

        assertEquals("\nPayments summary\n----------------\nCZK 10 (USD 0.47)\n\n", stream.toString());
    }

    @Test
    void commandShouldShowUsdConversionsTest() {
        store.add(new Payment("CZK 10"));
        store.add(new Payment("EUR 20"));

        command.execute(null, writer);
        writer.flush();

        assertEquals("\nPayments summary\n----------------\nCZK 10 (USD 0.47)\nEUR 20 (USD 23.87)\n\n", stream.toString());
    }

    @Test
    void commandShouldNotRequireExitTest() {
        assertFalse(command.isRequiredExit());
    }
}
