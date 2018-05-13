package com.bsc.tracker.command;

import com.bsc.tracker.ExchangeConverter;
import com.bsc.tracker.Payment;
import com.bsc.tracker.PaymentStore;

import java.util.Optional;

public class PrintPaymentsWithUsdCommand extends PrintPaymentsCommand {

    private ExchangeConverter converter;

    public PrintPaymentsWithUsdCommand(PaymentStore store, ExchangeConverter converter) {
        super(store);
        this.converter = converter;
    }

    @Override
    public String formatPayment(Payment payment) {
        StringBuilder formatted = new StringBuilder();
        Optional<Payment> usdPayment = converter.convertToUsd(payment);

        formatted.append(payment.toString());

        if (usdPayment.isPresent() && !payment.getCurrency().equals("USD")) {
            formatted.append(" (" + usdPayment.get() + ")");
        }

        return formatted.toString();
    }
}
