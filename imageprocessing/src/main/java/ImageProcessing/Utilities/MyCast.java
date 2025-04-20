package ImageProcessing.Utilities;

public class MyCast {
    
    /**
     * Convert a 2D double array to a 2D int array.
     * 
     * @param array the 2D double array to convert
     * @return the converted 2D int array
     */
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

    /**
     * Convert a 2D int array to a 2D double array.
     * 
     * @param array the 2D int array to convert
     * @return the converted 2D double array
     */
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
