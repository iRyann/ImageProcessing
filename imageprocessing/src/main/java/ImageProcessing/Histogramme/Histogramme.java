package ImageProcessing.Histogramme;

public class Histogramme 
{
    public static int[] Histogramme256(int mat[][])
    {
        int M = mat.length;
        int N = mat[0].length;
        int histo[] = new int[256];
        
        for(int i=0 ; i<256 ; i++) histo[i] = 0;
        
        for(int i=0 ; i<M ; i++)
            for(int j=0 ; j<N ; j++)
                if ((mat[i][j] >= 0) && (mat[i][j]<=255)) histo[mat[i][j]]++;
        
        return histo;
    }

    public static int minimum(int[][] image){
        int min = 255;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                if (image[i][j] < min) {
                    min = image[i][j];
                }
            }
        }
        return min;
    }

    public static int maximum(int[][] image){
        int max = 0;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                if (image[i][j] > max) {
                    max = image[i][j];
                }
            }
        }
        return max;
    }

    public static int luminance(int[][] image){
        int sum = 0;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                sum += image[i][j];
            }
        }
        return sum / (image.length * image[0].length);
    }

    public static double contraste1(int[][] image){
        double sum = 0;
        double mean = luminance(image);
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                sum += Math.pow(image[i][j] - mean, 2);
            }
        }
        return Math.sqrt(sum / (image.length * image[0].length));
    }

    public static double contraste2(int[][] image){
        int min = minimum(image);
        int max = maximum(image);
        if (max == 0){
            return 0;
        }
        else{
            return (max - min) / (double) max;
        }
    }
}
