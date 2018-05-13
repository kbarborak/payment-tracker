package com.bsc.tracker.command;

import com.bsc.tracker.Payment;
import com.bsc.tracker.PaymentStore;

import java.io.PrintWriter;

public class PrintPaymentsCommand implements Command, Runnable {

    private PaymentStore store;

    public PrintPaymentsCommand(PaymentStore store) {
        this.store = store;
    }

    @Override
    public Boolean isRequiredExit() {
        return false;
    }

    @Override
    public void execute(String input, PrintWriter output) {
        output.append("\n");
        output.append("Payments summary\n");
        output.append("----------------\n");

        if (store.getSummary().isEmpty()) {
            output.append("No payments yet or nothing with non-zero balance.\n");
        } else {
            store.getSummary().stream().forEach(payment -> output.append(formatPayment(payment) + "\n"));
        }

        output.append("\n");
    }

    public String formatPayment(Payment payment) {
        return payment.toString();
    }

    @Override
    public void run() {
        PrintWriter writer = System.console().writer();
        execute(null, writer);
        writer.flush();
    }
}
