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

    public static int[][] rehaussement(int[][] image, int[] courbeTonale){
        int[][] newImage = new int[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                newImage[i][j] = courbeTonale[image[i][j]];
            }
        }
        return newImage;
    }

    public static int[] creeCourbeTonaleLineaireSaturation(int smin, int smax){
        int[] courbeTonale = new int[256];
        for (int i = 0; i < 256; i++) {
            if (i < smin) {
                courbeTonale[i] = 0;
            } else if (i > smax) {
                courbeTonale[i] = 255;
            } else {
                courbeTonale[i] = (int) ((i - smin) * 255.0 / (smax - smin));
            }
        }
        return courbeTonale;
    }

    public static int[] creeCourbeTonaleGamma(double gamma){
        int[] courbeTonale = new int[256];
        for (int i = 0; i < 256; i++) {
            courbeTonale[i] = (int) (255 * Math.pow(i / 255.0, gamma));
        }
        return courbeTonale;
    }

    public static int[] creeCourbeTonaleNegatif(){
        int[] courbeTonale = new int[256];
        for (int i = 0; i < 256; i++) {
            courbeTonale[i] = 255 - i;
        }
        return courbeTonale;
    }

    public static int[] creeCourbeTonaleEgalisation(int[][] image){
        int[] histo = Histogramme256(image);
        int[] courbeTonale = new int[256];
        int totalPixels = image.length * image[0].length;
        double cumulativeSum = 0.0;
        
        for (int i = 0; i < 256; i++) {
            cumulativeSum += histo[i];
            courbeTonale[i] = (int) Math.round((cumulativeSum / totalPixels) * 255);
        }
        
        return courbeTonale;
    }
}
