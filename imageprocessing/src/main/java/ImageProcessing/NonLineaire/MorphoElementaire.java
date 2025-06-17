package ImageProcessing.NonLineaire;
import ImageProcessing.Lineaire.Generic;

public class MorphoElementaire {

    public static int[][] erosion(int[][] image, int tailleMasque) {
        int half = tailleMasque / 2;
        int[][] extended = Generic.extendImage(image, half, "copy");
        int[][] res = new int[image.length][image[0].length];

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                int min = 255;
                for (int dx = 0; dx < tailleMasque; dx++) {
                    for (int dy = 0; dy < tailleMasque; dy++) {
                        int val = extended[i + dx][j + dy];
                        if (val < min) {
                            min = val;
                        }
                    }
                }
                res[i][j] = min;
            }
        }
        return res;
    }


    public static int[][] dilatation(int[][] image, int tailleMasque) {
        int half = tailleMasque / 2;
        int[][] extended = Generic.extendImage(image, half, "copy");
        int[][] res = new int[image.length][image[0].length];

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                int max = 0;
                for (int dx = 0; dx < tailleMasque; dx++) {
                    for (int dy = 0; dy < tailleMasque; dy++) {
                        int val = extended[i + dx][j + dy];
                        if (val > max) {
                            max = val;
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
