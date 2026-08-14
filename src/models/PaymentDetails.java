package models;

import java.time.LocalDate;

public class PaymentDetails {
    private long id;
    private double amountPay;
    private double restAmount;
    private String paymentType;
    private LocalDate payDate;


    private static long count = 0;

    private long createId() {
        return ++count;
    }

    public PaymentDetails(double amountPay, double restAmnount,
                          LocalDate payDate, String paymentType) {
        this.amountPay = amountPay;
        this.restAmount = restAmnount;
        this.payDate = payDate;
        this.id = createId();
        this.paymentType = paymentType;
    }


    public long getId() {
        return id;
    }

    public double getAmountPay() {
        return amountPay;
    }

    public void setAmountPay(double amountPay) {
        this.amountPay = amountPay;
    }

    public double getRestAmount() {
        return restAmount;
    }

    public void setRestAmount(double restAmount) {
        this.restAmount = restAmount;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public static long getCount() {
        return count;
    }

    public static void setCount(long count) {
        PaymentDetails.count = count;
    }

    @Override
    public String toString() {
        return "Payment Details\n" +
                "  ID: #" + id + "\n" +
                "  Payment amount: " + amountPay +
                "  \nRest amount: " + restAmount +
                "  \nPayment type: " + paymentType +
                "  \nPayment date: " + payDate;
    }

}
