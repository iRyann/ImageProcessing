package ImageProcessing.Contours;
import ImageProcessing.NonLineaire.MorphoElementaire;

public class ContoursNonLineaire {
    
    public static int[][] gradientErosion(int[][] image){
        int[][] erodedImage = MorphoElementaire.erosion(image, 3);
        int[][] res = new int[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                res[i][j] = image[i][j] - erodedImage[i][j];
            }
        }
        return res;
    }

    public static int[][] gradientDilatation(int[][] image){
        int[][] dilatedImage = MorphoElementaire.dilatation(image, 3);
        int[][] res = new int[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                res[i][j] = dilatedImage[i][j] - image[i][j];
            }
        }
        return res;
    }

    public static int[][] gradientBeucher(int[][] image){
        int[][] dilatationGradient = gradientDilatation(image);
        int[][] erosionGradient = gradientErosion(image);
        int[][] res = new int[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                res[i][j] = dilatationGradient[i][j] - erosionGradient[i][j];
            }
        }
        return res;
    }

    public static int[][] laplacienNonLineaire(int[][] image){
        int[][] dilatationGradient = gradientDilatation(image);
        int[][] erosionGradient = gradientErosion(image);
        int[][] res = new int[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                res[i][j] = dilatationGradient[i][j] - erosionGradient[i][j];
            }
        }
        return res;
    }
}
