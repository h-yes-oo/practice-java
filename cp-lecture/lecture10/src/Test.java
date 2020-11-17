interface A {
    int x = 1; //public, static, final
    void func(int x); //abstract method
    default void func2(int x) {}; //abstract method
}

class B implements A {
    public void func(int x){
        System.out.println(x);
    }
}

abstract class Person {
    String description;
    String name;
    private int age;
    Person(String description, String name, int age){
        this.description = description;
        this.name = name;
        this.age = age;
    }
    public void printName() {
        System.out.println(description + " " + name);
    }
    abstract void work();
    abstract void play();
}

class BusinessMan extends Person {
    BusinessMan(String description, String name, int age){
        super(description,name,age);
    }

    public void work(){
        System.out.println("Dev all Day");
    }
    public void play(){
        System.out.println("Talk a walk with Coco");
    }
}

class Pointt {
    float x,y;
    void move(int dx, int dy) { x+=dx; y+=dy;}
    void move(float dx, float dy) { x+=dx; y+=dy;}
    public String toString() {return "("+x+", "+y+")";}
}

class Point {
    int x = 0, y = 0;
    int color;
    void move(int dx, int dy) { x += dx; y += dy; }
    int getX() { return x; }
    int getY() { return y; }
    static void show(int x, int y) {
        System.out.println("(" + x + ", " + y + ")");
    }
    static void show(float x, float y) {
        System.out.println("(" + x + ", " + y + ")");
    }
}

class RealPoint extends Point {
    float x = 0.0f, y = 0.0f;
    void move(int dx, int dy) { move((float)dx, (float)dy); }
    void move(float dx, float dy) { x += dx; y += dy; }
    int getX() { return (int)Math.floor(x); }
    int getY() { return (int)Math.floor(y); }
}

class Wrapper<T> {
    T obj;
    void add(T obj){
        this.obj =obj;
    }
    T get() {
        return obj;
    }
}


public class Test {
    public static void main(String[] args){
        Wrapper<Integer> m = new Wrapper<Integer>();
        m.add(2);
        System.out.println(m.get());
        Pointt p = new Pointt();
        p.move(3,0);
        p.move(0.0f, 4.0f);
        System.out.println(p);
        Person hyesoo = new BusinessMan("Happy","Hyesoo",25);
        hyesoo.work();
        hyesoo.play();
    }
}
