public class car extends vehicle {
    private boolean isDriving;
    private int horsepower;

    public car(String make, String model, int year, double weight, int horsepower) {
        super(make, model, year, weight);
        this.horsepower = horsepower;
    }

    public void drive() {
        this.isDriving = true;
    }

    public void stop() {
        this.isDriving = false;
        setTripsSinceMaintenance(tripsSinceMaintenance++);
    }

    public void repair() {
        tripsSinceMaintenance = 0;
        setNeedsMaintenance(false);
    }
}