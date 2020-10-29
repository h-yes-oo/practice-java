class Parent {
    String name = "John";
    int age = 30;
    static void hello() {
        System.out.println("Hello, I am a parent!");
    }

    void printInfo() {
        System.out.println("Name: "+name+" Age: "+age);
    }

    @Override
    public String toString() {
        String info = "Name: "+name+" Age: "+age;
        return info;
    }
}
class Child extends Parent {
    static void hello() {
        System.out.println("Hello, I am a child!");
    }
}

class Point{
    int x = 0, y = 0;
    void move(int dx, int dy){
        x += dx;
        y += dy;
    }
}

class SlowPoint extends Point{
    int xLimit = 10;
    int yLimit = 10;
    @Override
    void move(int dx, int dy){
        super.move(limit(dx, xLimit), limit(dy,yLimit));
    }
    static int limit(int d, int limit){
        return d > limit ? limit : d < -limit? -limit: d;
    }
}

class Son extends Parent {
    static void hello() {System.out.println("I am a son.");}
}

class Daughter extends Parent {
    static void hello() {System.out.println("I am a daughter.");}
}

public class Test {
    public static void main(String[] args){
//        Child c = new Child();
//        Parent p = new Parent();
//
////        System.out.println(c instanceof Object);
////        System.out.println(p instanceof Child);
//
//        p.hello();
//        c.hello();

//        Point p = new SlowPoint();
//        p.move(32,-32);
//        System.out.println("("+p.x+","+p.y+")");

//        Child c1 = new Child();
//        Parent c2 = new Child();
//
//        c1.hello();
//        c2.hello();

//        Parent[] pArray = new Parent[4];
//        pArray[0] = new Son();
//        pArray[1] = new Son();
//        pArray[2] = new Daughter();
//        pArray[3] = new Daughter();
//
//        for ( Parent p : pArray ){
//            p.hello();
//        }
//
//        Parent p = new Parent();
//        p.printInfo();
//        System.out.println(p);
        Child c1 = new Child();
        Parent c2 = new Child();
        c1.hello();
        c2.hello();

    }
}
