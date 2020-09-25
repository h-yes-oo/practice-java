public class NumberCounter {
    public static void countNumbers(String str0, String str1, String str2) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        int total = Integer.parseInt(str0)*Integer.parseInt(str1)*Integer.parseInt(str2);
        String string = Integer.toString(total);
        char[] numchar = new char[] {'0','1','2','3','4','5','6','7','8','9'};
        int[] count = new int[10];

        for(int i = 0; i<string.length(); i++){
            for(int j =0; j<numchar.length; j++) {
                if (string.charAt(i) == numchar[j]) {
                    count[j]++;
                    break;
                }
            }
        }
        for(int i = 0; i<count.length; i++){
            if(count[i]<1){
                continue;
            } else{
                printNumberCount(i,count[i]);
            }
        }
    }

    private static void printNumberCount(int number, int count) {
        System.out.printf("%d: %d times\n", number, count);
    }
}
