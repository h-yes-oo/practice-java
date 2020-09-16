public class HelloClass {
    public static void main(String[] args) {
        Car car = new Car(100,"Sonata");
        System.out.println("Number: " + car.carNumber + ", model: " + car.model);
        Car car2 = new Car(200,"Santafe");
        System.out.println("Number: " + car2.carNumber + ", model: " + car2.model);
    }
}

class Car {
    int carNumber;
    String model;
    Car(int number, String m) {
        this.carNumber = number;
        this.model = m;
    }
}