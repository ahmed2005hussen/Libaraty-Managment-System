package models;


import interfaces.Renewable;

public class Book extends LibraryItem implements Renewable {

    private String author;
    private int pageCount;

    public Book(String title , String author , int pageCount) {
        super(1, title, 2 , 14 , 5.0);
        this.author = author;
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 5;
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


    @Override
    public String toString() {
        return
                "   \nAuthor: " + author + '\n' +
                "   page Count:" + pageCount;
    }


}
