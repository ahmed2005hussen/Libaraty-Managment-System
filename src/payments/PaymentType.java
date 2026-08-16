package payments;

import java.util.Scanner;

public enum PaymentType {
    VISA,
    WALLET;

    public PaymentStrategy createStrategy(String paymentDetails) {

        return switch (this) {
            case VISA -> new Visa(paymentDetails);
            case WALLET -> new Wallet(paymentDetails);
        };
    }

    public static PaymentType getPaymentTypeById(int id) {
        return switch (id) {
            case 1 -> VISA;
            case 2 -> WALLET;
            default -> null;
        };
    }

}