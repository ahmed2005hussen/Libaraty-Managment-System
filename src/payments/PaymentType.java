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
}