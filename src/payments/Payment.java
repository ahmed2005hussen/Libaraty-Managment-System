package payments;

public class Payment {

    private PaymentStrategy paymentStrategy;

    public Payment(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean pay(double amount){
        return paymentStrategy.pay(amount);
    }
}
