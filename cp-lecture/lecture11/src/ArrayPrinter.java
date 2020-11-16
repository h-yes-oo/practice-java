public class ArrayPrinter {
    public static <E> void printArray(E[] elements){
        for(E elemet : elements){
            System.out.print(elemet.toString()+" ");
        }
    }

    public static void main(String[] args){
        Integer[] intArray = { 1, 1, 2, 3, 5, 8, 13 };
        Character[] charArray = { 'C', 'S', 'E', 'C', 'P' };
        ArrayPrinter.printArray(intArray);
        ArrayPrinter.<Character>printArray(charArray);
    }
}



