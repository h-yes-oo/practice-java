class Point { static int x = 2;}
class Sub extends Point {
    static double x = 4.7;
    void printX() {
        System.out.println("x:" + x + ", super.x:" + super.x + ", ((Point)this).x:" + ((Point)this).x);
    }
}

class Parent {
    int var = 123;
}

class Child extends Parent{
    int var = 456;
    int getParentVar() { return super.var; }
}

class Shoes {
    String company, model;
    int size;
    Shoes(String company, String model, int size) {
        this.company = company; this.model = model; this.size = size;
    }
    @Override
    public boolean equals(Object o) {
        // Two instances are same
        if (this == o) return true;
        // Compare three fields of this and o
        if (o instanceof Shoes) {
            Shoes shoes = (Shoes) o;
            return this.company.equals(shoes.company)
                    && this.model.equals(shoes.model)
                    && this.size == shoes.size;
        } // o is not even a Shoes class instance
        return false;
    }
}

public class Test {
    public static void main(String[] args){
        Sub sub = new Sub();
        sub.printX();
        Parent parent = new Parent();
        Child child = new Child();
        System.out.println(parent.var);
        System.out.println(child.var);
        System.out.println(child.getParentVar());
        Child down = (Child)parent;
        System.out.println(down.var);
    }

}
