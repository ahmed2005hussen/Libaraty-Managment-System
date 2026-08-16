package models;

public class DVD extends LibraryItem {

    private double runtime;
    public DVD(String title , double runtime) {
        super(3, title, 0 , 3 , 15.0);
        this.runtime = runtime;
    }

    public double getRuntime() {
        return runtime;
    }

    public void setRuntime(double runtime) {
        this.runtime = runtime;
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 15;
    }

    @Override
    public String toString() {
        return super.toString()+ "    \nRuntime: " + runtime ;
    }
}
