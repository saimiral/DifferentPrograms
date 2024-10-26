package vehicle;


public class Car extends Vehicle{
    boolean isDriving;
    int HP;
    
    public Car(String make, String model, int year, int HP, int weight){
        super(make,model,year,weight);
        this.HP = HP;
    }
    
    public void drive(){
        isDriving = true;
        System.out.println("You're driving your car... must be nice\n");
    }
    
    public void stop(){
        System.out.println("You stopped driving\n");
        isDriving = false;
        int trips = getMaintenance();
        if(trips < 100) setMaintenance(trips + 1);
        else{ 
            System.out.println("Oh...your car needs repairing.\n");
            setNeedsMaintenance(true);
            repair();
        }
    }
    
    public void repair(){
        System.out.println("Repairing...");
        setMaintenance(0);
        setNeedsMaintenance(false);
    }
    
    public int getHP(){
        return HP;
    }
    
    @Override
    public String toString(){
        return "Car make: " + getMake() + ", car model: " + getModel() + ", car year: " + getYear() + ", car hrspw: " + getHP() + ", car weight: " + getWeight();
    }
}
