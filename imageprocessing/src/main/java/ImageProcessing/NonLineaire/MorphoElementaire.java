package ImageProcessing.NonLineaire;

import ImageProcessing.Complexe.MatriceComplexe;

import javax.management.RuntimeErrorException;

import ImageProcessing.Complexe.Complexe;
import ImageProcessing.Fourier.Fourier;
import ImageProcessing.Utilities.MyCast;

public class MorphoElementaire {

    public static int[][] erosion(int [][] image,int tailleMasque){
        int half = tailleMasque / 2;
        int[][] res = new int[image.length][image[0].length];
        
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if (i < half || i >= image.length - half || j < half || j >= image[0].length - half) {
                    res[i][j] = 0;
                    continue;
                }

                int min = 255;
                for (int k = -half; k <= half; k++) {
                    for (int l = -half; l <= half; l++) {
                        if (image[i + k][j + l] < min) {
                            min = image[i + k][j + l];
                        }
                    }
                }
                res[i][j] = min;
            }
        }
        
        return res;
    }

    public static int[][] dilatation(int [][] image,int tailleMasque){
        // TODO - implement MorphoElementaire.dilatation
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public static int[][] ouverture(int [][] image,int tailleMasque){
        // TODO - implement MorphoElementaire.ouverture
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public static int[][] fermeture(int [][] image,int tailleMasque){
        // TODO - implement MorphoElementaire.fermeture
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
