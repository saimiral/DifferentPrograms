package vehicle;

public class Main {
    public static void main(String[] args) {
        Car car1 = new Car("Benz", "S-Class", 2024, 400, 3000);
        int i;
        for(i=0; i<105; i++){
            car1.drive();
            car1.stop();
        }
        System.out.println("Car information...\n");
        System.out.println(car1.toString());
    }
}
