public abstract class LibraryItem {
    //• Catalogue ID
    //• Title
    //• Current status
    //• Borrower Member ID
    //• Number of renewals used

    private final long catalogueID;
    private String title;
    private ItemStatus itemStatus;
    private long borrowerID;
    private int numberOfRenwals;

    public LibraryItem(long catalogueID, String title, ItemStatus itemStatus,
                       int numberOfRenwals) {
        this.catalogueID = catalogueID;
        this.title = title;
        this.itemStatus = itemStatus;
        this.borrowerID = -1;
        this.numberOfRenwals = numberOfRenwals;
    }
}
