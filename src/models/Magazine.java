package models;

import interfaces.Renewable;

public class Magazine extends LibraryItem implements Renewable {

    private int issueNumber;

    public Magazine(String title, int issueNumber) {
        super(2, title, 1, 7, 3.0);

        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public double calculateFine(int overdueDays) {
        return Math.min(overdueDays * 3, 30);
    }

    @Override
    public String toString() {
        return super.toString() + "   \nIssue Number: " + issueNumber;
    }

    @Override
    public boolean renewLoan() {
        if (getNumberOfRenwals() >= getLimitRenwal()) {
            return false;
        }

        recordRenewal();
        return true;
    }

    @Override
    public int renewLimit() {
        return getLimitRenwal();
    }


}
