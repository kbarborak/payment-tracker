package com.bsc.tracker.command;

import com.bsc.tracker.Payment;
import com.bsc.tracker.PaymentStore;

import java.io.PrintWriter;

public class AddPaymentCommand implements Command {

    private PaymentStore store;

    public AddPaymentCommand(PaymentStore store) {
        this.store = store;
    }

    @Override
    public Boolean isRequiredExit() {
        return false;
    }

    @Override
    public void execute(String input, PrintWriter output) {
        if (input == null || "".equals(input)) {
            return;
        }

        store.add(new Payment(input));
    }
}
