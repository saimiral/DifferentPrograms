import javax.net.ssl.HostnameVerifier;

public class vehicle {
    private String make;
    private String model;
    private int year;
    private double weight;
    private boolean needsMaintenance = false;
    protected int tripsSinceMaintenance = 0;

    public vehicle(String make, String model, int year, double weight) {
        setMake(make);
        setModel(model);
        setYear(year);
        setWeight(weight);
    }
    
    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    public void setNeedsMaintenance(boolean needsMaintenance){
        this.needsMaintenance = needsMaintenance;
    }

    public void setTripsSinceMaintenance(int tripsSinceMaintenance){
        this.tripsSinceMaintenance = tripsSinceMaintenance;
        if (tripsSinceMaintenance > 100) {
            setNeedsMaintenance(true);
        }
    }
    public String toString() {
        return"make:" + make +
        "\nmodel: " + model +
        "\nyear: " + year +
        "\nweight: " + weight +
        "\nneeds maintenance: " + needsMaintenance +
        "\ntrips since maintenance: " + tripsSinceMaintenance;
    }
}