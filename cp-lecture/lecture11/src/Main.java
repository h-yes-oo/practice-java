public class Main {


    //main Method
    public static void main(String[] args) {
        Parent parent = new Parent();
        Parent child = new Child();
        parent.print(); //Parent.print()
        child.print();  //Parent.print()
    }
}

class Parent {
    static void print() {
        System.out.println("Parent.print()");
    }
}
class Child extends Parent {
    static void print() {
        System.out.println("Child.print()");
    }
}