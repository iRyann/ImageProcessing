package imageprocessing.contours;
import imageprocessing.nonlineaire.MorphoElementaire;

/**
 * Provides non-linear contour detection methods using morphological operations.
 */
public class ContoursNonLineaire {

    /**
     * Computes the morphological gradient using erosion.
     *
     * @param image The input image as a 2D array.
     * @return The gradient image as a 2D array.
     */
    public static int[][] gradientErosion(int[][] image){
        int[][] erodedImage = MorphoElementaire.erosion(image, 3);
        int[][] res = new int[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                res[i][j] = image[i][j] - erodedImage[i][j];
            }
        }
        return res;
    }

    /**
     * Computes the morphological gradient using dilation.
     *
     * @param image The input image as a 2D array.
     * @return The gradient image as a 2D array.
     */
    public static int[][] gradientDilatation(int[][] image){
        int[][] dilatedImage = MorphoElementaire.dilatation(image, 3);
        int[][] res = new int[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                res[i][j] = dilatedImage[i][j] - image[i][j];
            }
        }
        return res;
    }

    /**
     * Computes the Beucher gradient (difference between dilation and erosion gradients).
     *
     * @param image The input image as a 2D array.
     * @return The Beucher gradient image as a 2D array.
     */
    public static int[][] gradientBeucher(int[][] image){
        int[][] dilatationGradient = gradientDilatation(image);
        int[][] erosionGradient = gradientErosion(image);
        int[][] res = new int[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                res[i][j] = dilatationGradient[i][j] - erosionGradient[i][j];
            }
        }
        return res;
    }

    /**
     * Computes the non-linear Laplacian using morphological gradients.
     *
     * @param image The input image as a 2D array.
     * @return The non-linear Laplacian image as a 2D array.
     */
    public static int[][] laplacienNonLineaire(int[][] image){
        int[][] dilatationGradient = gradientDilatation(image);
        int[][] erosionGradient = gradientErosion(image);
        int[][] res = new int[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                res[i][j] = dilatationGradient[i][j] - erosionGradient[i][j];
            }
        }
        return res;
    }
}
