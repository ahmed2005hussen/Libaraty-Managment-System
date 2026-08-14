package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice {


    private List<PaymentDetails> paymentsDetails;

    public Invoice() {
        paymentsDetails = new ArrayList();
    }

    public void createInvoice(double amountPay, double restAmnount,
                              LocalDate payDate, String paymentType) {

        PaymentDetails paymentDetails = new PaymentDetails(amountPay, restAmnount,
                payDate , paymentType);

        paymentsDetails.add(paymentDetails);

    }

    public void printInvoices() {
        if (paymentsDetails.isEmpty()) {
            System.out.println("No invoices");
            return;
        }
        for (PaymentDetails p : paymentsDetails) {
            System.out.println(p);
        }

    }

    @Override
    public String toString() {
        if (paymentsDetails.isEmpty()) {
            return "    No payments recorded";
        }

        return "    " + paymentsDetails;
    }

}
