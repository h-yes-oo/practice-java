public class lecture4 {
    public static void main(String[] args){
//        char grade = 'B';
//        switch (grade) {
//            case 'A':
//                System.out.println("Your score is 4.");
//                break;
//            case 'B':
//                System.out.println("Your score is 3.");
//                break;
//            case 'C':
//                System.out.println("Your score is 2.");
//                break;
//            case 'D':
//                System.out.println("Your score is 1.");
//                break;
//            default:
//                System.out.println("There is no grade " + grade + ".");
//        }
        int[] array = new int[3];
        array[0]=1;
        array[2]=2;

        for(int var:array){
            System.out.println(var);
        }

        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                break;
            }
            System.out.println(i);
        }

        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                continue;
            }
            System.out.println(i);
        }

        for (int i = 5; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                // System.out.print doesn't break line
                System.out.print("*");
            }
            System.out.println();
        }

        int[] nums = {23, -43, -25, 14, 36};
        int max = Integer.MIN_VALUE; // -2147483648
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        System.out.println("The max number is " + max);

        char[] chars = {'c', 'o', 'm', 'p', 'u', 't', 'e', 'r'};
        for (int i = 0; i < chars.length - 1; i++) {
            System.out.print(chars[i] + ",");
        }
        System.out.println(chars[chars.length - 1]);

    }
}
