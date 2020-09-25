public class lecture3 {
    public static void main (String[] args){
        int x = 0, y = 1;

        if (x == 1 && (++x+y)%2 == 0){
        }
        System.out.println(("X : "+x));

        String[] names = new String[3];
        names[0] = "Coco";
        //names[1] = "Sunim";
        names[2] = "DH";
        int[] IDs = new int[3];

        for(int i =0; i < names.length; i++){
            System.out.println(i+"th item : "+names[i]);
        }
        System.out.println(IDs[1]);

        String[] mythoughts = new String[]{"Coco","is","So","Cute"};
        String[] thoughts = {"Coco","is","So","Cute"};
        System.out.println(mythoughts[0]);
        System.out.println(thoughts[0]);

        // 2 X 3
        int[][] exampleArray = new int[][] {{1,2,3},{4,5,6}};

        // 아래 조건을 만족하는 2-dimensional array 만들기
        // 1st row ==> 2 items
        // 2nd row ==> 3 items
        int[][] exArray = new int[2][];
        exArray[0] = new int[2];
        exArray[1] = new int[3];

        for(int i = 0; i < exArray.length; i++){
            for(int j =0; j < exArray[i].length; j++){
                System.out.print(exArray[i][j] + " ");
            }
            System.out.println();
        }

        //System.out.println(exArray[0][2]); // Overflow 일어남

        int x1 = 1;
        if(x1 == 1){
            System.out.println("Coco so cute!");
        }
    }
}
