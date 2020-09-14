import java.util.Scanner;

public class ArrayPrinter {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int numInput = scanner.nextInt();

        String[] list = new String[numInput];
        for (int i = 0; i < numInput; i++){
            list[i] = scanner.next();
        }
        for (String string : list){
            System.out.print(string+' ');
        }
        System.out.println();
        for (int i = numInput-1; i >=0; i--){
            System.out.print(list[i]+' ');
        }
        System.out.println();
    }
}
