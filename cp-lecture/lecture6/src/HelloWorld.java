class IntHolder {
    int value;
    IntHolder(int value) {this.value = value; }
}


public class HelloWorld {

    static void swap(int x, int y){
        int temp = x;
        x = y;
        y = temp;
    }

    static void swap(Student x, Student y){
        Student temp = x;
        x=y;
        y=temp;
    }

    static void swap(IntHolder a, IntHolder b) {
        int temp = a.value;
        a.value = b.value;
        b.value = temp;
    }


    public static void main (String[] args){
//        Student x = new Student();
//        Student y = new Student();
//        x.ID = 1;
//        y = x;
//        y.ID = 2;
//
//        System.out.println(x.ID);
//        int ex1 =1, ex2 = 2;
//        swap(ex1,ex2);
//        System.out.println(ex1);
//        System.out.println(ex2);
//        Student st1,st2;
//        st1 = new Student();
//        st2 = new Student();
//        st1.ID = 1; st2.ID = 2;
//
//        swap(st1,st2);
//        System.out.println(st1.ID);
//        System.out.println(st2.ID);
//        IntHolder a = new IntHolder(2), b = new IntHolder(3);
//        swap(a,b);
//        System.out.println(a.value + " " +b.value);
//        FruitStore myFruitStore = new FruitStore();
//        myFruitStore.sell(3);
//        myFruitStore.getBalance();

//        Calculator cal1 = new Calculator(3);
//        Calculator cal2 = new Calculator(10);
//
//        for(int i = 0; i < 4; i++){
//            System.out.println("<result of "+i+" and "+(i+1)+">");
//            System.out.println(cal1.specialOp(i,i+1)+"\n");
//        }
//
//        System.out.println(cal2.specialOp(5,6));


        Parent parent = new Parent();
        Child child = new Child();
        parent.func();
        child.func();
        System.out.println(parent.var);
        System.out.println(child.var);
    }
}
