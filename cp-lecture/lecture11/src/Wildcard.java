import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;

public class Wildcard {
    private static Double reduce(ArrayList<? extends Number> num){
        double sum =0.0;
        for(Number n:num ){ sum = sum + n.doubleValue();}
        return sum;
    }

    public static void addNumbers(List<? super Integer> list){
        for(Object n : list){
            System.out.print(n.toString() + " ");
        }
    }

    static void printCollection(Collection<?> c){
        for (Object o : c) {System.out.println(o);}
    }

    public static void main(String[] args){
        Collection<String> cs = new ArrayList<String>();
        cs.add("hello");
        cs.add("world");
        printCollection(cs);
        // initialCapacity: 5
        ArrayList<Integer> arrayList = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) { arrayList.add(i); }
        arrayList.add(5);
//        List<Integer> l1 = Arrays.asList(1,2,3);
//        addNumbers(l1); // 1 2 3
//        List<Number> l2 = Arrays.asList(1.0,2.0,3.0);
//        addNumbers(l2); // 1.0 2.0 3.0
//        ArrayList<Integer> l1 = new ArrayList<>();
//        l1.add(10);
//        l1.add(20);
//        System.out.println("sumint "+reduce(l1)); //sumint 30.0
    }
}
