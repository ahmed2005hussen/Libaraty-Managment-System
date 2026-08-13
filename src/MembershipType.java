public enum MembershipType {

    BRONZE(0),
    SILVER(0.5),
    GOLD(0.10) ;

    private double waiverRate;

    MembershipType(double waiverRate) {
        this.waiverRate = waiverRate;
    }

    public double getWaiverRate() {
        return waiverRate;
    }
}
