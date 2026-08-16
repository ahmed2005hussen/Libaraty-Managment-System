package models;

import enums.ItemStatus;

public abstract class LibraryItem {

    private final int catalogueId;
    private final long itemId;

    private static long countItem;
    private String title;
    private ItemStatus itemStatus;
    private long borrowerID;
    private int numberOfRenwals;
    private int limitRenwal;

    private int loanPeriod;
    private double finePerDay;


    public LibraryItem(int catalogueId, String title,
                       int limitRenwal, int loanPeriod, double fine) {

        this.finePerDay = fine;
        this.loanPeriod = loanPeriod;
        this.catalogueId = catalogueId;
        this.limitRenwal = limitRenwal;
        this.title = title;
        this.itemStatus = ItemStatus.AVAILABLE;
        this.borrowerID = -1;
        this.numberOfRenwals = 0;
        this.itemId = ++countItem;
    }


    public abstract double calculateFine(int overdueDays);

    private String getCategory() {
        switch (catalogueId) {
            case 1 -> {
                return "Book";
            }
            case 2 -> {
                return "Magazine";
            }
            default -> {
                return "DVD";
            }
        }
    }

    public boolean isBorrowed() {
        return itemStatus.equals(ItemStatus.ON_LOAN);
    }

    public boolean isLost() {
        return itemStatus.equals(ItemStatus.LOST);
    }

    public boolean canBorrowed() {
        return itemStatus.equals(ItemStatus.AVAILABLE);
    }
    public boolean lend(Member member) {

        itemStatus = ItemStatus.ON_LOAN;
        borrowerID = member.getMembershipId();
        numberOfRenwals = 0;

        return true;
    }

    public void returnItem() {
        itemStatus = ItemStatus.AVAILABLE;
        borrowerID = -1;
        numberOfRenwals = 0;
    }


    public void recordRenewal() {
        numberOfRenwals++;
    }

    public boolean markAsLost() {

        if (itemStatus != ItemStatus.AVAILABLE) {
            return false;
        }

        itemStatus = ItemStatus.LOST;
        return true;
    }


    public long getItemId() {
        return itemId;
    }

    public long getCatalogueId() {
        return catalogueId;
    }

    public static long getCountItem() {
        return countItem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }

    public long getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(long borrowerID) {
        this.borrowerID = borrowerID;
    }

    public int getNumberOfRenwals() {
        return numberOfRenwals;
    }

    public void setNumberOfRenwals(int numberOfRenwals) {
        this.numberOfRenwals = numberOfRenwals;
    }

    public int getLimitRenwal() {
        return limitRenwal;
    }

    public void setLimitRenwal(int limitRenwal) {
        this.limitRenwal = limitRenwal;
    }

    public int getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(int loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public double getFinePerDay() {
        return finePerDay;
    }

    public void setFinePerDay(double finePerDay) {
        this.finePerDay = finePerDay;
    }


    @Override
    public String toString() {
        return "Library Item\n" +
                "   Catalogue: " + getCategory() + '\n' +
                "   Item Id: " + itemId + '\n' +
                "   Title: " + title + '\n' +
                "   Item Status: " + itemStatus + '\n' +
                "   Borrower ID: " + borrowerID + '\n' +
                "   Limit Number of Renwal: " + limitRenwal + '\n' +
                "   Number Of Renwals: " + numberOfRenwals + '\n' +
                "   Fine late per day: " + finePerDay + " EGP";
    }

}
