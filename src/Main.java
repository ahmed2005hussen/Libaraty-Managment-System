import enums.ItemStatus;
import enums.MembershipType;
import models.Library;
import models.LibraryItem;
import models.Member;
import payments.PaymentType;

import java.util.Scanner;

class Main {
    Library library = new Library();

    Scanner sc = new Scanner(System.in);

    int menu() {
        System.out.println("1. View catalogue");
        System.out.println("2. Register member");
        System.out.println("3. Borrow item");
        System.out.println("4. Return item");
        System.out.println("5. Renew loan");
        System.out.println("6. Search item by ID");
        System.out.println("7. View items by status");
        System.out.println("8. Pay outstanding fines");
        System.out.println("9. View all members");
        System.out.println("10. Library report");
        System.out.println("11. Mark item as lost");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

        return sc.nextInt();
    }

    void registerMember() {
        System.out.print("Enter member name: ");
        String memberName = sc.nextLine();

        int choice = 0;

        while (true) {
            System.out.println("Choose membership type: ");
            System.out.println("1. STUDENT\n" +
                    "2. STAFF\n" +
                    "3. PUBLIC\n");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice >= 1 && choice <= 3) break;

            System.out.println("Invalid input, enter number between 1 - 3");
        }

        MembershipType membershipType = MembershipType.getMembership(choice);

        Member member = library.registerMember(memberName, membershipType);

        System.out.println("Register successfully, ");
        System.out.println(member);
        System.out.println("-----------------------");
    }

    void borrowItem() {
        System.out.print("Enter member ID: ");
        int memberID = sc.nextInt();
        sc.nextLine();

        System.out.print("Our Items: ");
        library.showItems();

        System.out.print("Enter item ID");
        int itemId = sc.nextInt();
        sc.nextLine();

        library.borrow(memberID, itemId);
    }

    void returnItem() {

        System.out.print("Enter item ID: ");
        int itemId = sc.nextInt();
        sc.nextLine();
        LibraryItem item = library.findItemById(itemId);

        if (item == null) {
            System.out.println("This item does not exist");
            System.out.println("-----------------------");
            return;
        }

        System.out.print("Enter overdue days: ");
        int overdueDays = sc.nextInt();
        sc.nextLine();

        if (overdueDays < 0) {
            System.out.println("Overdue days cannot be negative ");
            System.out.println("--------------------------------");
            return;
        }
        library.returnItem(item, overdueDays);

    }

    void renewLoan() {
        System.out.print("Enter item ID: ");
        int itemId = sc.nextInt();
        sc.nextLine();

        LibraryItem item = library.findItemById(itemId);

        if (item == null) {
            System.out.println("This item does not exist.");
            System.out.println("---------------------------");
            return;
        }

        library.renewLoan(item);
    }

    void findItem() {
        System.out.print("Enter item ID: ");
        int itemId = sc.nextInt();
        sc.nextLine();

        LibraryItem item = library.findItemById(itemId);

        if (item == null) {
            System.out.println("This item does not exist.");
            System.out.println("---------------------------");
            return;
        }
        System.out.println(item);
        System.out.println("--------------------");

    }

    void viewItemsByStatus() {

        System.out.println("Choose status:");
        System.out.println("1. AVAILABLE");
        System.out.println("2. ON_LOAN");
        System.out.println("3. LOST");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        ItemStatus status = ItemStatus.getItemStatusById(choice);

        library.showItemsByStatus(status);
    }

    void pay() {

        System.out.print("Enter member ID: ");
        int memberId = sc.nextInt();
        sc.nextLine();

        Member member = library.findMemberById(memberId);

        if (member == null) {
            System.out.println("This member does not exist");
            return;
        }

        System.out.print("Enter payment amount: ");

        // i checked on the negative amount inside the makePayment function :)

        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.println("1. VISA");
        System.out.println("2. WALLET");
        System.out.print("Choose payment method: ");

        int choice = sc.nextInt();
        sc.nextLine();

        PaymentType paymentType = PaymentType.getPaymentTypeById(choice);

        if (paymentType == null) {
            System.out.println("Invalid payment type.");
            return;
        }

        member.makePayment(amount, paymentType);
    }

    void markItemAsLost() {

        System.out.print("Enter item ID: ");
        int itemId = sc.nextInt();
        sc.nextLine();

        LibraryItem item = library.findItemById(itemId);

        if (item == null) {
            System.out.println("This item does not exist.");
            System.out.println("---------------------------");
            return;
        }

        library.markItemAsLost(item);
    }

    void main(String[] args) {
        System.out.println("    Welcome in" + Library.getLibararyName());
        System.out.println("    -------------------------------- ");

        loop:
        while (true) {
            int choice = menu();
            sc.nextLine();
            switch (choice) {

                case 1 -> library.showItems();

                case 2 -> registerMember();

                case 3 -> borrowItem();

                case 4 -> returnItem();

                case 5 -> renewLoan();

                case 6 -> findItem();

                case 7 -> viewItemsByStatus();

                case 8 -> pay();

                case 9 -> library.displayAllMembers();

                case 10 -> library.libraryReport();

                case 11 -> markItemAsLost();

                case 0 -> {
                    System.out.println("Good bye :)");
                    break loop;
                }

                default -> System.out.println("Wrong input enter number between 0 - 11 ");
            }


        }


    }
}