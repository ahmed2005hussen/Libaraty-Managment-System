package enums;

public enum ItemStatus {

    AVAILABLE,
    ON_LOAN,
    LOST ;

    public static ItemStatus getItemStatusById(int id) {
        switch (id) {
            case 1 -> {
                return ItemStatus.AVAILABLE;
            }
            case 2 -> {
               return ItemStatus.ON_LOAN;
            }
            case 3 -> {
                return ItemStatus.LOST;
            }
            default -> {
                return null;
            }
        }
    }
}
