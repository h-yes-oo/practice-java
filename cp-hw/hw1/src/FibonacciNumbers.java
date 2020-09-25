public class FibonacciNumbers {
    public static void printFibonacciNumbers(int n) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        int[] f = new int[40];
        f[0]=0;
        f[1]=1;
        for(int i = 2; i<n;i++){
            f[i]=f[i-1]+f[i-2];
        }
        for(int j = 0; j<n-1;j++){
            System.out.print(f[j]+" ");
        }
        System.out.print(f[n-1]);

    }
}
