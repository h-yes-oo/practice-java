public class Ex {
    public static void main(String[] args){
        String inputA = "0O 3X 4O 4X 7X 1O 6X 5O 6O 9X";
        String inputB = "2X 2O 3O 9O 5X 1X 8O 7O 8X 0X";
        char[] aShape = new char[10];
        int[] aNum = new int[10];
        char[] bShape = new char[10];
        int[] bNum = new int[10];
        for(int i = 0; i<10; i++){
            aShape[i]=inputA.charAt(3*i+1);
        }
        for(int i = 0; i<10; i++){
            aNum[i]=inputA.charAt(3*i)-48;
        }

        for(int i = 0; i<10; i++) {
            System.out.print(aNum[i]+" ");
        }



    }



}
