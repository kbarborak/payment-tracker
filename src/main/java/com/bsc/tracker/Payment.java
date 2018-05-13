package com.bsc.tracker;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Payment representation with mainly ISO 4217 currencies and RMB.
 */
public class Payment {

    private Money money;

    /**
     * @param payment Payment in format "CURRENCY_CODE AMOUNT", e.g. "USD 10".
     * @throws IllegalArgumentException When payment string is invalid.
     */
    public Payment(String payment) {
        try {
            money = Money.parse(Optional.ofNullable(payment).orElse(""));
        } catch (IllegalArgumentException | ArithmeticException e) {
            throw new IllegalArgumentException(String.format("Invalid payment '%s' (caused by %s - %s).", payment, e.getClass().getSimpleName(), e.getMessage()), e);
        }
    }

    /**
     * @param currency Currency code.
     * @param amount   Payment amount.
     * @throws IllegalArgumentException When amount is null.
     */
    public Payment(String currency, BigDecimal amount) {
        this(currency + " " + Optional.ofNullable(amount).orElseThrow(() -> new IllegalArgumentException("Argument amount cannot be null.")).toString());
    }

    /**
     * Returns new Payment object whose value is this + payment from argument.
     *
     * @param payment Payment to be added to this payment.
     * @return this + payment
     * @throws IllegalArgumentException When payment object is null or currencies are different.
     */
    public Payment add(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Argument payment cannot be null.");
        }

        if (!getCurrency().equals(payment.getCurrency())) {
            throw new IllegalArgumentException("Cannot add payment with different currency.");
        }

        BigDecimal currentAmount = new BigDecimal(getAmount());
        BigDecimal amountToAdd = new BigDecimal(payment.getAmount());

        return new Payment(getCurrency(), currentAmount.add(amountToAdd));
    }

    /**
     * @return Amount of this payment.
     */
    public String getAmount() {
        try {
            return money.getAmount().toBigIntegerExact().toString();
        } catch (ArithmeticException e) {
            return money.getAmount().toString();
        }
    }

    /**
     * @return Currency code of this payment.
     */
    public String getCurrency() {
        return money.getCurrencyUnit().getCurrencyCode();
    }

    /**
     * Convert to another currency
     *
     * @param currency
     * @param exchangeRate
     * @return
     */
    public Payment convert(CurrencyUnit currency, BigDecimal exchangeRate) {
        Money cenvertedMoney = money.convertedTo(currency, exchangeRate, RoundingMode.HALF_DOWN);
        return new Payment(cenvertedMoney.getCurrencyUnit().getCode(), cenvertedMoney.getAmount());
    }

    @Override
    public String toString() {
        return getCurrency() + " " + getAmount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Objects.equals(money, payment.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
