import java.util.Scanner;

public class XDrawing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        while(input != 0){
            for(int i = 0; i < input; i++){
                for(int j = 0; j < input; j++){
                    System.out.print(i == j || i+j == input - 1 ? 'X' : 'O');
                }
                System.out.println();
            }
            input = scanner.nextInt();
        }
        scanner.close();
    }
}
