package models;

import enums.MembershipType;
import payments.Payment;
import payments.PaymentType;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Member {


    private String name;
    private final long membershipId;
    private final MembershipType membershipType;

    private double balanceOwed;

    private static int count = 0;

    private List<LibraryItem> heldItems;

    private Invoice invoice;

    public Member(String name,
                  MembershipType membershipType) {

        this.name = name;
        this.membershipId = ++count;
        this.membershipType = membershipType;
        this.balanceOwed = 0.0;
        heldItems = new ArrayList();
        invoice = new Invoice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMembershipId() {
        return membershipId;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public double getBalanceOwed() {
        return balanceOwed;
    }

    public boolean addFine(double amount) {
        if (amount <= 0) return false;

        this.balanceOwed += amount;
        return true;
    }

    public void makePayment(double amount, PaymentType paymentType) {

        if (amount <= 0) {
            System.out.println("Invalid amount");
            return;
        }

        if (balanceOwed - amount < 0) {
            System.out.println("Too much payment");
            return;
        }

        Payment payment = new Payment(paymentType.createStrategy());
        boolean isPay = payment.pay(amount);

        if (isPay) {
            balanceOwed -= amount;
            System.out.println("Payment Done ");
            invoice.createInvoice(amount, balanceOwed,
                    LocalDate.now(), paymentType.toString());

        }

    }

    public void printPayments() {
        invoice.printInvoices();
    }

    public boolean canBorrow() {
        return heldItems.size() < 3 && balanceOwed <= 100;
    }

    public void borrowing(LibraryItem libraryItem) {

        if (!canBorrow()) {
            System.out.println("You can not borrow");
            return;
        }

        heldItems.add(libraryItem);
        System.out.println("Added ");
    }

    public boolean returnBorrowing(LibraryItem libraryItem) {
        if (!heldItems.contains(libraryItem)) {
            System.out.println("This item is not borrowed ");
            return false;
        }

        heldItems.remove(libraryItem);
        System.out.println("Removed");
        return true;
    }

    public int getNumberOfItemsHeld() {
        return heldItems.size();
    }

    public static int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Member Details\n" +
                "  Name: " + name + "\n" +
                "  Membership ID: " + membershipId + "\n" +
                "  Membership Type: " + membershipType + "\n" +
                "  Balance Owed: " + balanceOwed + " EGP\n" +
                "  Borrowed Items: " + heldItems + "\n" +
                "  Payment History:\n" + invoice;
    }
}
