package vehicle;

public class Vehicle {

    private String make;
    private String model;
    private int year;
    private int weight;
    private boolean needsMaintenance = false;
    private int tripsSinceMaintenance = 0;
    
    public Vehicle(){
        System.out.println("No info was given\n");
    }
    
    public Vehicle(String make, String model, int year, int weight){
        this.make = make;
        this.model = model;
        this.year = year;
        this.weight = weight;
    }
    
    public void setMake(String make){
        this.make = make;
    }
    
    public void setModel(String model){
        this.model = model;
    }
    
    public void setYear(int year){
        this.year = year;
    }
    
    public void setWeight(int weight){
        this.weight = weight;
    }
    
    public void setMaintenance(int tripsSinceMaintenance){
        this.tripsSinceMaintenance = tripsSinceMaintenance;
    }
    
    public void setNeedsMaintenance(boolean needsMaintenance){
        this.needsMaintenance = needsMaintenance;
    }    
    
    public int getMaintenance(){
        return tripsSinceMaintenance;
    }
    
    public String getMake(){
        return make;
    }
    
    public String getModel(){
        return model;
    }
    
    public int getYear(){
        return year;
    }
    
    public int getWeight(){
        return weight;
    }
    
    @Override
    public String toString(){
        return ("Car make: " + getMake() + ", car model: " + getModel() + ", car year: " + getYear() + ", car weight: " + getWeight());
    }
    
}
