public class MatrixFlip {
    public static void printFlippedMatrix(char[][] matrix) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        int row = matrix.length;
        int column = matrix[0].length;
        for(int i = row-1; i >= 0; i--){
            for(int j =column-1; j>=0; j--){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}