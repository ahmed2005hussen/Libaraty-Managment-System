package payments;


public class Wallet implements PaymentStrategy {

    private final String walletNumber;

    public Wallet(String walletNumber) {
        this.walletNumber = walletNumber;
    }

    private boolean details() {

        if (walletNumber.length() != 11) {
            System.out.println("Invalid Number, should be 11 numbers");
            return false;
        }

        if (!walletNumber.matches("\\d+")) {
            System.out.println("Invalid Number, should be digits only ");
            return false;
        }

        return true;
    }

    @Override
    public boolean pay(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment amount");
            return false;
        }

        if (!details()) {
            return false;
        }

        return true;
    }
}
