package ImageProcessing.NonLineaire;

import ImageProcessing.Complexe.MatriceComplexe;

import javax.management.RuntimeErrorException;

import ImageProcessing.Complexe.Complexe;
import ImageProcessing.Fourier.Fourier;
import ImageProcessing.Utilities.MyCast;
import ImageProcessing.Lineaire.Generic;

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
                for (int dx = -half; dx <= half; dx++) {
                    for (int dy = -half; dy <= half; dy++) {
                        if (image[i + dx][j + dy] < min) {
                            min = image[i + dx][j + dy];
                        }
                    }
                }
                res[i][j] = min;
            }
        }
        
        return res;
    }

    public static int[][] dilatation(int [][] image,int tailleMasque){
        int half = tailleMasque / 2;
        int[][] res = new int[image.length][image[0].length];

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if (i < half || i >= image.length - half || j < half || j >= image[0].length - half) {
                    res[i][j] = 0;
                    continue;
                }

                int max = 0;
                for (int dx = -half; dx <= half; dx++) {
                    for (int dy = -half; dy <= half; dy++) {
                        if (image[i + dx][j + dy] > max) {
                            max = image[i + dx][j + dy];
                        }
                    }
                }
                res[i][j] = max;
            }
        }
        
        return res;
    }

    public static int[][] dilatationByErosion(int [][] image,int tailleMasque){
        int[][] inverse = Generic.inverse(image);
        int[][] res = Generic.inverse(erosion(inverse, tailleMasque));
        return res;
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
