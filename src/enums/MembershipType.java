package enums;
public enum MembershipType {
    STUDENT(0.25),
    STAFF(0.10),
    PUBLIC(0.0);

    private final double waiverRate;

    MembershipType(double waiverRate) {
        this.waiverRate = waiverRate;
    }

    public double getWaiverRate() {
        return waiverRate;
    }

    public static MembershipType getMembership(int id){
        switch (id){
            case 1 -> {
                return MembershipType.STUDENT;
            }
            case 2 -> {
                return MembershipType.STAFF;
            }
            default -> {
                return MembershipType.PUBLIC;
            }
        }
    }

}