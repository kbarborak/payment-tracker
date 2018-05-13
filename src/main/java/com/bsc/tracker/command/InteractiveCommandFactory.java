package com.bsc.tracker.command;

import com.bsc.tracker.PaymentStore;

/**
 * Abstract factory for commands depending on user interaction.
 */
public class InteractiveCommandFactory {

    private PaymentStore store;

    public InteractiveCommandFactory(PaymentStore store) {
        this.store = store;
    }

    /**
     * @param input User input.
     * @return
     */
    public Command create(String input) {
        if (input != null && "quit".equals(input)) {
            return new QuitCommand();
        }

        return new AddPaymentCommand(store);
    }
}
