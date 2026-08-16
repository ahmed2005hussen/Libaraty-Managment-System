package payments;

public class Visa implements PaymentStrategy {

    private final String visaNumber;

    public Visa(String visaNumber) {
        this.visaNumber = visaNumber;
    }

    private boolean details() {

        if (visaNumber.length() != 14) {
            System.out.println("Invalid Number, should be 14 numbers");
            return false;
        }

        if (!visaNumber.matches("\\d+")) {
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
