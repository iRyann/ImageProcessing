package ImageProcessing.NonLineaire;
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

    public static int[][] dilatation(int[][] image, int tailleMasque) {
        if (tailleMasque % 2 == 0 || tailleMasque < 1) {
            throw new IllegalArgumentException("La taille du masque doit être impaire et ≥ 1");
        }

        int half = tailleMasque / 2;
        int rows = image.length;
        int cols = image[0].length;
        int[][] res = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int max = 0;

                for (int dx = -half; dx <= half; dx++) {
                    for (int dy = -half; dy <= half; dy++) {
                        int ni = i + dx;
                        int nj = j + dy;

                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols && image[ni][nj] > 0) {
                            max = image[ni][nj];
                            if (max == 255) {
                                break;
                            }
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
        return dilatation(erosion(image, tailleMasque), tailleMasque);
    }
    public static int[][] fermeture(int [][] image,int tailleMasque){
        return erosion(dilatation(image, tailleMasque), tailleMasque);
    }
}
