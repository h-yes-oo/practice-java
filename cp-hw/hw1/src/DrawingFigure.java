public class DrawingFigure {
    public static void drawFigure(int n) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        String[] array= new String[] {"////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\","////////////////********\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\", "////////////****************\\\\\\\\\\\\\\\\\\\\\\\\", "////////************************\\\\\\\\\\\\\\\\", "////********************************\\\\\\\\", "****************************************"};
        for(int i = 0; i<n; i++){
            System.out.println(array[i]);
        }
    }
}
