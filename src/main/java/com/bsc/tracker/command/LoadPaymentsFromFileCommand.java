package com.bsc.tracker.command;

import com.bsc.tracker.PaymentStore;

import java.io.IOException;
import java.io.PrintWriter;

public class LoadPaymentsFromFileCommand implements Command {

    private PaymentStore store;
    private FilePaymentLoader loader;

    public LoadPaymentsFromFileCommand(PaymentStore store, FilePaymentLoader loader) {
        this.store = store;
        this.loader = loader;
    }

    @Override
    public Boolean isRequiredExit() {
        return false;
    }

    /**
     * @param input  Input argument.
     * @param output Console output.
     * @throws RuntimeException
     */
    @Override
    public void execute(String input, PrintWriter output) {
        try {
            loader.load(input).stream().forEach(payment -> store.add(payment));
        } catch (IllegalArgumentException | IOException e) {
            throw new RuntimeException(String.format("Cannot load payments from file (caused by %s - %s).", e.getClass().getSimpleName(), e.getMessage()));
        }
    }
}
