import java.util.*;

public class lab1 {
    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("ID: ");
//        int id = sc.nextInt();
//        sc.nextLine();
//
//        System.out.print("Name: ");
//        String name = sc.nextLine();
//
//        System.out.println("Name: "+name+" Id: "+id);

//        int x = 1;
//        int y = 2;
//
//        int temp = y;
//        y=x;
//        x=temp;
//        System.out.println("x: "+x+" y: "+y);
//        short d = 123;

        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        String firstName = "Hyesoo";
        String lastName = new String("Kim");

        System.out.println(lastName.length()); //3
        System.out.println(lastName.toUpperCase()); //KIM
        System.out.println(firstName.toLowerCase()); //hyesoo
    }
}
