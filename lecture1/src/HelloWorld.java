import java.util.*;
public class HelloWorld {
    public static void main (String[] args) {
        Scanner sc = new Scanner ( System.in);

        System.out.print("ID: ");
        int id = sc.nextInt();
        //sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.println("Name: " + name + ", ID: " + id);
    }
}
