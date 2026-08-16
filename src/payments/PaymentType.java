package payments;

public enum PaymentType {
    VISA,
    WALLET;

    public PaymentStrategy createStrategy() {
        return switch (this) {
            case VISA -> new Visa();
            case WALLET -> new Wallet();
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