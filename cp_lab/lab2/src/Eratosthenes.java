import java.util.Scanner;

public class Eratosthenes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        while(input != 0){
            boolean prime[] = new boolean[input+1];
            for(int i = 2; i < input; i++){
                prime[i] = true;
            }

            for(int p = 2; p*p <= input; p++){
                if(prime[p] == true){
                    for(int i = p*2; i<= input; i +=p)
                        prime[i] = false;
                }
            }
            for(int i = 2; i<= input; i++){
                if(prime[i] == true){
                    System.out.print(i + " ");
                }
            }
            System.out.println();
            input = scanner.nextInt();
        }
        scanner.close();
    }
}
