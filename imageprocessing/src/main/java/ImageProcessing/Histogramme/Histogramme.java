package imageprocessing.histogramme;

public class Histogramme 
{
    public static int[] Histogramme256(int mat[][])
    {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            throw new IllegalArgumentException("Image invalide");
        }
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
        if (image == null || image.length == 0 || image[0].length == 0) {
            throw new IllegalArgumentException("Image invalide");
        }
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
        if (image == null || image.length == 0 || image[0].length == 0) {
            throw new IllegalArgumentException("Image invalide");
        }
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
        if (image == null || image.length == 0 || image[0].length == 0) {
            throw new IllegalArgumentException("Image invalide");
        }
        int sum = 0;
        int total = image.length * image[0].length;
        if (total == 0) {
            throw new IllegalArgumentException("Image vide");
        }
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                sum += image[i][j];
            }
        }
        return sum / total;
    }

    public static double contraste1(int[][] image){
        if (image == null || image.length == 0 || image[0].length == 0) {
            throw new IllegalArgumentException("Image invalide");
        }
        double sum = 0;
        double mean = luminance(image);
        int total = image.length * image[0].length;
        if (total == 0) {
            throw new IllegalArgumentException("Image vide");
        }
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                sum += Math.pow(image[i][j] - mean, 2);
            }
        }
        return Math.sqrt(sum / total);
    }

    public static double contraste2(int[][] image){
        if (image == null || image.length == 0 || image[0].length == 0) {
            throw new IllegalArgumentException("Image invalide");
        }
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
        if (image == null || image.length == 0 || image[0].length == 0 || courbeTonale == null || courbeTonale.length != 256) {
            throw new IllegalArgumentException("Image ou courbe tonale invalide");
        }
        int[][] newImage = new int[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                newImage[i][j] = courbeTonale[image[i][j]];
            }
        }
        return newImage;
    }

    public static int[] creeCourbeTonaleLineaireSaturation(int smin, int smax){
        if (smax == smin) {
            throw new IllegalArgumentException("smax doit être différent de smin");
        }
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
            courbeTonale[i] = (int) Math.round(255 * Math.pow(i / 255.0, gamma));
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
        if (image == null || image.length == 0 || image[0].length == 0) {
            throw new IllegalArgumentException("Image invalide");
        }
        int[] histo = Histogramme256(image);
        int[] courbeTonale = new int[256];
        int totalPixels = image.length * image[0].length;
        if (totalPixels == 0) {
            throw new IllegalArgumentException("Image vide");
        }
        double cumulativeSum = 0.0;
        for (int i = 0; i < 256; i++) {
            cumulativeSum += histo[i];
            courbeTonale[i] = (int) Math.round((cumulativeSum / totalPixels) * 255);
        }
        return courbeTonale;
    }
}
