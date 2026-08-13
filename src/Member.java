public class Member {

    private String name;
    private final long membershipId;
    private final MembershipType membershipType;

    private double balanceOwed;
    private int numberOfItemsHeld;

    private static int count = 0;


    public Member(String name,
                  MembershipType membershipType) {

        this.name = name;
        this.membershipId = ++count;
        this.membershipType = membershipType;
        this.balanceOwed = 0.0;
        numberOfItemsHeld = 0;
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

    public boolean addBalanceOwed(double amount) {
        if (amount <= 0) return false;

        this.balanceOwed += amount;
        return true;
    }


    public int getNumberOfItemsHeld() {
        return numberOfItemsHeld;
    }

    public void setNumberOfItemsHeld(int numberOfItemsHeld) {
        this.numberOfItemsHeld = numberOfItemsHeld;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Member.count = count;
    }

    //Name
    //• Membership ID, which does not change
    //• Membership type, which does not change
    //• Balance owed
    //• Number of items currently held
}
