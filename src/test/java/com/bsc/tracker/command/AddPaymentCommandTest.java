package com.bsc.tracker.command;

import com.bsc.tracker.PaymentStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class AddPaymentCommandTest {

    private ByteArrayOutputStream stream;
    private PrintWriter writer;
    private PaymentStore store;
    private AddPaymentCommand command;

    @BeforeEach
    void setUp() {
        stream = new ByteArrayOutputStream();
        writer = new PrintWriter(stream);
        store = new PaymentStore();
        command = new AddPaymentCommand(store);
    }

    @Test
    void commandShouldPrintEmptyMessageTest() {
        command.execute("USD 1000", writer);
        assertEquals(1, store.getSummary().size());
    }

    @Test
    void commandShouldThrowExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> command.execute("Hey!", writer));
    }

    @Test
    void commandShouldNotRequireExitTest() {
        assertFalse(command.isRequiredExit());
    }

    @Test
    void commandShouldDoNothingWHenBadArgumentTest() {
        command.execute("", writer);
        command.execute(null, writer);
        assertEquals(0, store.getSummary().size());
    }
}
