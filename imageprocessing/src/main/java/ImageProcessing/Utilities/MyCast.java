package ImageProcessing.Utilities;

public class MyCast {
    
    public static int[][] toIntArray(double[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        int[][] intArray = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                intArray[i][j] = (int) array[i][j];
            }
        }
        return intArray;
    }

    public static double[][] toDoubleArray(int[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        double[][] doubleArray = new double[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                doubleArray[i][j] = (double) array[i][j];
            }
        }
        return doubleArray;
    }
}
