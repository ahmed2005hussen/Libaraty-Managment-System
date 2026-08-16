package models;

import enums.ItemStatus;
import enums.MembershipType;
import interfaces.Renewable;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private final static String libararyName = "Bayt AL Hekma";
    private static double administrativeCharge = 10.0;
    List<Member> members;
    List<LibraryItem> libraryItems;

    public Library() {
        this.members = new ArrayList<>();
        this.libraryItems = new ArrayList<>();
    }

    public void showItems() {

        if (libraryItems.isEmpty()) {
            System.out.println("We don't have any items right now ");
            System.out.println("-------------------------");
            return;
        }

        int count = 1;
        for (LibraryItem l : libraryItems) {
            System.out.print((count++) + ". ");
            System.out.println(l);
        }
        System.out.println("-------------------------");
    }

    public Member registerMember(String name, MembershipType membershipType) {

        Member member = new Member(name, membershipType);
        members.add(member);
        return member;

    }

    public Member findMemberById(long id) {

        if (members.isEmpty()) return null;

        for (Member m : members) {
            if (m.getMembershipId() == id) return m;
        }

        return null;
    }

    public LibraryItem findItemById(long id) {

        if (libraryItems.isEmpty()) return null;

        for (LibraryItem l : libraryItems) {
            if (l.getItemId() == id) return l;
        }

        return null;
    }

    public void borrow(int memberId, int itemId) {

        Member m = findMemberById(memberId);
        LibraryItem l = findItemById(itemId);
        if (m == null || l == null) {
            if (m == null) {
                System.out.println("Member does not exist ");
                System.out.println("----------------------");
                return;
            }
            System.out.println("Item does not exist ");
            System.out.println("----------------------");
            return;
        }

        if (!m.canBorrow()) {
            System.out.println("This member can not borrow");
            System.out.println("---------------------------");
            return;
        }

        if (!l.canBorrowed()) {
            System.out.println("This item can not be borrowed");
            System.out.println("---------------------------");
            return;
        }

        m.borrowing(l);
        l.lend(m);

        System.out.println("Item borrowed successfully.");
        System.out.println("---------------------------");

    }

    public void returnItem(LibraryItem item, int overdueDays) {

        if (!item.isBorrowed()) {
            System.out.println("This item is not currently on loan.");
            System.out.println("-----------------------------------");
            return;
        }

        long memberId = item.getBorrowerID();

        Member member = findMemberById(memberId);

        if (member == null) {
            System.out.println("Borrower member not found.");
            System.out.println("---------------------------");
            return;
        }

        double fine = item.calculateFine(overdueDays);

        double waiver = fine * member.getMembershipType().getWaiverRate();

        double finalFine = fine - waiver;

        double totalCharge = finalFine + administrativeCharge;

        if (overdueDays == 0) {
            totalCharge = 0;
        }

        if (totalCharge > 0) {
            member.addFine(totalCharge);
        }

        member.returnBorrowing(item);

        item.returnItem();

        System.out.println("\n Return Details: ");
        System.out.println("Fine: " + fine + " EGP");
        System.out.println("Waiver: " + waiver + " EGP");
        System.out.println("Administrative charge: "
                + (overdueDays == 0 ? 0 : administrativeCharge) + " EGP");
        System.out.println("Total charged: " + totalCharge + " EGP");
        System.out.println("Item returned successfully");
        System.out.println("-----------------------------");
    }


    public void renewLoan(LibraryItem item) {

        if (!(item instanceof Renewable renewable)) {
            System.out.println("This item cannot be renew ");
            System.out.println("---------------------------");
            return;
        }

        if (!item.isBorrowed()) {
            System.out.println("This item is not on loan");
            System.out.println("-------------------------");
            return;
        }

        if (renewable.renewLoan()) {
            System.out.println("Loan renewed successfully ");
        } else {
            System.out.println("you can not renewal, renewal limit has reached");
        }

        System.out.println("---------------------------");
    }


    public void showItemsByStatus(ItemStatus status) {

        if (status == null) {
            System.out.println("Invalid input ");

            System.out.println("----------------");
            return;
        }
        boolean found = false;

        for (LibraryItem item : libraryItems) {

            if (item.getItemStatus() == status) {
                System.out.println(item);
                System.out.println("---------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No items with status: " + status);
            System.out.println("---------------------------");
        }
    }


    public void displayAllMembers() {
        if (members.isEmpty()) {
            System.out.println("We don't have any members yet");
            System.out.println("-----------------------------");
            return;
        }

        System.out.println("Our members: ");

        for (Member m : members) {
            System.out.println(m);
            System.out.println("-------------");
        }

    }

    public void libraryReport() {

        int catalogueSize = libraryItems.size();

        int itemsOnLoan = 0;

        double totalOutstanding = 0.0;

        for (LibraryItem item : libraryItems) {
            if (item.getItemStatus() == ItemStatus.ON_LOAN) {
                itemsOnLoan++;
            }
        }

        for (Member member : members) {
            totalOutstanding += member.getBalanceOwed();
        }

        double loanRate = 0.0;

        if (catalogueSize > 0) {
            loanRate = ((double) itemsOnLoan / catalogueSize) * 100;
        }

        double projectedFines = 0.0;

        for (LibraryItem item : libraryItems) {

            if (item.getItemStatus() == ItemStatus.ON_LOAN) {
                projectedFines += item.calculateFine(5);
            }
        }

        System.out.println("\nLibrary Report: ");
        System.out.println("Library name: " + libararyName);
        System.out.println("Catalogue size: " + catalogueSize);
        System.out.println("Items ever added: " + LibraryItem.getCountItem());
        System.out.println("Items on loan: " + itemsOnLoan);
        System.out.println("Loan rate: "+ loanRate);
        System.out.println("Total outstanding: " + totalOutstanding + " EGP");
        System.out.println("Projected fines (5 days): " + projectedFines + " EGP");
        System.out.println("------------------------------------");
    }

    public void markItemAsLost(LibraryItem item) {

        if (item.markAsLost()) {
            System.out.println("Item marked as lost successfully.");
        } else {
            System.out.println(
                    "Item cannot be marked as lost,It must be AVAILABLE."
            );
        }

        System.out.println("---------------------------");
    }

    public static String getLibararyName() {
        return libararyName;
    }

    public static double getAdministrativeCharge() {
        return administrativeCharge;
    }

}
