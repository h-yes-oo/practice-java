public class Calculator {
    Adder adder;
    Multiplier multiplier;
    int battery;
    int price;
    Calculator(int battery){
        this.battery = battery;
        this.adder = new Adder(battery);
        this.multiplier = new Multiplier(battery);
        this.price = this.adder.price + this.multiplier.price;
    }
    int specialOp(int opd1, int opd2){
        if (battery > 0){
            System.out.println(--battery + " blocks of battery is left");
            return adder.add(opd1, opd2) - multiplier.multiply(opd1,opd2);
        } else{
            System.out.println("The battery is dead!!! Wrong Result!!!");
            return 0;
        }
    }
    private class Adder {
        int price;
        Adder(int battery){
            this.price = 500 * battery;
        }
        int add(int opd1, int opd2){
            return opd1 + opd2;
        }
    }
    private class Multiplier {
        int price;
        Multiplier(int battery){
            this.price = 1000 * battery;
        }
        int multiply(int opd1, int opd2){
            return opd1 * opd2;
        }
    }
}
