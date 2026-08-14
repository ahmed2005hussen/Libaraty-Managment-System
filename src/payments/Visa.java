package payments;

import java.util.Scanner;

public class Visa implements PaymentStrategy {
    private Scanner sc = new Scanner(System.in);
    private boolean details(){
        System.out.println("Enter the 14 number of your visa: ");
        String visa = sc.next();

        if(visa.length() != 14){
            System.out.println("Invalid Number, should be 14 numbers");
            return false;
        }

        if(!visa.matches("\\d+")){
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
