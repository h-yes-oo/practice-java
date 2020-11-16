import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

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

//    static void writeFile() {
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream("file.txt");
//            ByteArrayOutputStream byteArrayOutputStream =
//                    new ByteArrayOutputStream ();
//            byteArrayOutputStream.write(65);
//            byteArrayOutputStream.writeTo(fileOutputStream);
//            //byteArrayOutputStream.flush();//
//            //byteArrayOutputStream.close();
//            fileOutputStream.close(); }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    static void writeFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream ("file.txt");
            BufferedOutputStream bufferedOutputStream =
                    new BufferedOutputStream (fileOutputStream);
            String s = "Welcome to Computer Programming";
            bufferedOutputStream.write(s.getBytes());
            //bufferedOutputStream.flush();
            bufferedOutputStream.close();
            fileOutputStream.close(); }
        catch (IOException e) {
            e.printStackTrace();
        } }

    public static void main(String[] args){
        writeFile();
//        Collection<String> cs = new ArrayList<String>();
//        cs.add("hello");
//        cs.add("world");
//        printCollection(cs);
//        // initialCapacity: 5
//        ArrayList<Integer> arrayList = new ArrayList<>(5);
//        for (int i = 0; i < 5; i++) { arrayList.add(i); }
//        arrayList.add(5);
//
//
//        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(2, 3, 4, 5,6));
//        Iterator<Integer> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            int i = iterator.next();
//            if (i == 2 || i == 3) { iterator.remove(); }
//        }
//        for (int e : list) { System.out.print(e + " "); }
//        System.out.println();


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
