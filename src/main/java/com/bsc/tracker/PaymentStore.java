package com.bsc.tracker;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * In-memory payment store aggregating incoming payments by currency.
 */
public class PaymentStore {

    private Map<String, Payment> store = Collections.synchronizedMap(new LinkedHashMap<>());

    /**
     * Add new payment into the store.
     *
     * @param payment
     */
    public void add(Payment payment) {
        synchronized (store) {
            if (!store.containsKey(payment.getCurrency())) {
                store.put(payment.getCurrency(), payment);
                return;
            }

            Payment summaryForCurrency = store.get(payment.getCurrency());

            store.remove(payment.getCurrency());
            store.put(payment.getCurrency(), summaryForCurrency.add(payment));
        }
    }

    /**
     * Returns list of aggregated payments (currencies with zero balance are ignored).
     *
     * @return
     */
    public List<Payment> getSummary() {
        synchronized (store) {
            return store
                    .entrySet()
                    .stream()
                    .map(entry -> entry.getValue())
                    .filter(payment -> !(new BigDecimal(payment.getAmount()).equals(BigDecimal.ZERO)))
                    .collect(Collectors.toList());
        }
    }
}
