package com.bsc.tracker.command;

import com.bsc.tracker.Payment;
import com.bsc.tracker.PaymentStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandExecutorTest {

    private ByteArrayOutputStream stream;
    private PrintWriter writer;
    private PaymentStore store;
    private CommandExecutor executor;

    @BeforeEach
    void setUp() {
        stream = new ByteArrayOutputStream();
        writer = new PrintWriter(stream);
        store = new PaymentStore();
        executor = new CommandExecutor(writer);
    }

    @Test
    void executorShouldExecuteCommandAndHandleFlushTest() {
        PrintPaymentsCommand command = new PrintPaymentsCommand(store);

        store.add(new Payment("USD 1000"));
        executor.execute(command, null);

        assertEquals("\nPayments summary\n----------------\nUSD 1000\n\n", stream.toString());
        assertEquals(1, store.getSummary().size());
    }

    @Test
    void executorShouldDealWithExceptionTest() {
        AddPaymentCommand command = new AddPaymentCommand(store);

        executor.execute(command, "No way!");

        assertTrue(stream.size() > 0);
        assertEquals(0, store.getSummary().size());
    }
}
