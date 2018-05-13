package com.bsc.tracker.command;

import com.bsc.tracker.PaymentStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InteractiveCommandFactoryTest {

    private InteractiveCommandFactory factory;

    @BeforeEach
    void setUp() {
        factory = new InteractiveCommandFactory(new PaymentStore());
    }

    @Test
    void factoryShouldReturnQuitCommandTest() {
        assertTrue(factory.create("quit") instanceof QuitCommand);
    }

    @Test
    void factoryShouldReturnAddPaymentCommandAsDefaultTest() {
        assertTrue(factory.create("whatever") instanceof AddPaymentCommand);
        assertTrue(factory.create("USD 1000") instanceof AddPaymentCommand);
    }

    @Test
    void factoryShouldDealWithNullTest() {
        assertTrue(factory.create(null) instanceof AddPaymentCommand);
    }
}
