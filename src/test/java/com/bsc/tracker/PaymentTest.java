package com.bsc.tracker;

import org.joda.money.CurrencyUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    private Payment simplePayment;
    private Payment simpleFractionalPayment;
    private Payment simpleNegativePayment;
    private Payment simpleNegativeFractionalPayment;

    @BeforeEach
    void setUp() {
        simplePayment = new Payment("USD 10");
        simpleFractionalPayment = new Payment("USD 10.25");
        simpleNegativePayment = new Payment("USD -10");
        simpleNegativeFractionalPayment = new Payment("USD -10.25");
    }

    @Test
    void paymentShouldHaveGettersTest() {
        assertEquals("10", simplePayment.getAmount());
        assertEquals("USD", simplePayment.getCurrency());
    }

    @Test
    void paymentShouldHaveToStringTest() {
        assertEquals("USD 10", simplePayment.toString());
        assertEquals("USD 10.25", simpleFractionalPayment.toString());
        assertEquals("USD -10", simpleNegativePayment.toString());
        assertEquals("USD -10.25", simpleNegativeFractionalPayment.toString());
    }

    @Test
    void paymentShouldAcceptAnotherCurrencyThanUSDTest() {
        assertDoesNotThrow(() -> new Payment("CZK 10"));
        assertDoesNotThrow(() -> new Payment("USD 1000"));
        assertDoesNotThrow(() -> new Payment("HKD 15.20"));
        assertDoesNotThrow(() -> new Payment("CNY 5"));
    }

    @Test
    void paymentShouldNotAcceptInvalidPaymentTest() {
        assertThrows(IllegalArgumentException.class, () -> new Payment(null));
        assertThrows(IllegalArgumentException.class, () -> new Payment(""));
        assertThrows(IllegalArgumentException.class, () -> new Payment("p0,0"));
    }

    @Test
    void paymentShouldNotAcceptInvalidAmountTest() {
        assertThrows(IllegalArgumentException.class, () -> new Payment("USD p"));
        assertThrows(IllegalArgumentException.class, () -> new Payment("USD 1,5"));
        assertThrows(IllegalArgumentException.class, () -> new Payment("USD "));
    }

    @Test
    void paymentShouldAcceptValidAmountTest() {
        assertDoesNotThrow(() -> new Payment("USD 1000"));
        assertDoesNotThrow(() -> new Payment("USD -1000"));
        assertDoesNotThrow(() -> new Payment("USD 15.20"));
    }

    @Test
    void paymentShouldNotAcceptInvalidCurrencyTest() {
        assertThrows(IllegalArgumentException.class, () -> new Payment("1000"));
        assertThrows(IllegalArgumentException.class, () -> new Payment(" 1000"));
        assertThrows(IllegalArgumentException.class, () -> new Payment("ABC 1000"));
    }

    @Test
    void paymentShouldWorkWithMinorUnitTest() {
        assertEquals("BHD 10", new Payment("BHD 10").toString());
        assertEquals("BHD 10.200", new Payment("BHD 10.2").toString());
        assertEquals("BHD 10.200", new Payment("BHD 10.20").toString());
        assertEquals("BHD 10.200", new Payment("BHD 10.200").toString());
        assertThrows(IllegalArgumentException.class, () -> new Payment("BHD 10.2555"));
    }

    @Test
    void paymentShouldCreateNewPaymentAsAdditionTest() {
        assertEquals(new Payment("USD 15"), new Payment("USD 10").add(new Payment("USD 5")));
        assertEquals(new Payment("USD 5"), new Payment("USD 10").add(new Payment("USD -5")));
        assertEquals(new Payment("USD 20.50"), new Payment("USD 20").add(new Payment("USD 0.5")));
    }

    @Test
    void paymentShouldRespectMoneyScaleTest() {
        assertEquals(new Payment("BHD 15.325"), new Payment("BHD 15").add(new Payment("BHD 0.325")));
        assertEquals(new Payment("USD 20.50"), new Payment("USD 20").add(new Payment("USD 0.5")));
    }

    @Test
    void paymentShouldHaveConstructorForBigDecimalTest() {
        assertEquals(new Payment("USD 20"), new Payment("USD", new BigDecimal("20")));
        assertEquals(new Payment("USD 20.33"), new Payment("USD", new BigDecimal("20.33")));
        assertThrows(IllegalArgumentException.class, () -> new Payment(null, new BigDecimal("20.33")));
        assertThrows(IllegalArgumentException.class, () -> new Payment("", new BigDecimal("20.33")));
        assertThrows(IllegalArgumentException.class, () -> new Payment("USD", null));
    }

    @Test
    void paymentShouldConvertToAnotherCurrencyTest() {
        assertEquals(new Payment("USD 23.80"), new Payment("EUR", new BigDecimal("20")).convert(CurrencyUnit.USD, new BigDecimal("1.19")));
    }
}
