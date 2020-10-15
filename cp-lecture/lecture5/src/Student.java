class Student {
    //attributes(properties) -- member attributes
    int age;
    int year;
    int ID;
    String name;
    String department;

    static int totalNumStudent = 0;

    static void increaseStudentNum(){
        totalNumStudent++;
    }

    Student(){
        totalNumStudent++;
    }

    Student(String name){
        this.name = name;
    }

    //methods(interactions) -- member methods
    void sayHello() {
        System.out.println("Hello, Everyone!!!");
    }
    void changeAge(int age){
        this.age = age;
    }

    void printInfo() {
        System.out.println("Age: "+age+", Name: "+name);
    }
}
