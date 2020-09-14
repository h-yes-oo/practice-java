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
    }
}
