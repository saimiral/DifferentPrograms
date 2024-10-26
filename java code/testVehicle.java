public class testVehicle{
    public static void main() {
        car c1 = new car("Ford", "Mustang", 1965, 2000.0, 300);
        car c2 = new car("Benz", "CLS63", 2012, 2000.0, 600);
        car c3 = new car("Dodge", "Demon", 2023, 3000.0, 1071);

        c1.drive();
        c2.drive();
        c3.drive();

        c1.stop();
        c2.stop();
        c3.stop();

        System.out.println("car specs: " + c1.super.toString();)
        c2.toString();
        c3.toString();
    }
}