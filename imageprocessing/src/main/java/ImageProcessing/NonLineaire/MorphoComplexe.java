package ImageProcessing.NonLineaire;
import ImageProcessing.Lineaire.Generic;
import ImageProcessing.NonLineaire.MorphoElementaire;

public class MorphoComplexe {
    
    public static int[][] dilatationGeodesique(int[][] image, int[][] masqueGeodesique, int nbIterations) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            throw new IllegalArgumentException("Image vide ou nulle");
        }
        if (masqueGeodesique == null || masqueGeodesique.length == 0 || masqueGeodesique[0].length == 0) {
            throw new IllegalArgumentException("Masque geodesique vide ou nulle");
        }
        if (image.length != masqueGeodesique.length || image[0].length != masqueGeodesique[0].length) {
            throw new IllegalArgumentException("L'image et le masque geodesique doivent avoir la même taille");
        }
        if (nbIterations < 1) {
            throw new IllegalArgumentException("Le nombre d'iterations doit être supérieur à 0");
        }
        if (Generic.kindImage(image) != Generic.kindImage(masqueGeodesique)) {
            throw new IllegalArgumentException("L'image et le masque geodesique doivent être de même type");
        }

        int width = image[0].length;
        int height = image.length;

        int tailleElemStructurant = 3;

        int[][] res = new int[height][width];
        
        for (int i = 0; i < image.length; i++) {
            System.arraycopy(image[i], 0, res[i], 0, image[i].length);
        }

        for (int k = 0; k < nbIterations; k++) {
            res = MorphoElementaire.dilatation(res, tailleElemStructurant);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (masqueGeodesique[i][j] == 0) {
                        res[i][j] = 0;
                    }
                }
            }
        }
        
        return res;
    }

    public static int[][] reconstructionGeodesique(int[][] image, int[][] masqueGeodesique) {
        if (image == null || masqueGeodesique == null ||
            image.length == 0 || masqueGeodesique.length == 0 ||
            image[0].length != masqueGeodesique[0].length ||
            image.length != masqueGeodesique.length) {
            throw new IllegalArgumentException("Images invalides ou de tailles incompatibles");
        }
    
        int height = image.length;
        int width = image[0].length;
    
        int[][] res = new int[height][width];
        for (int i = 0; i < height; i++) {
            System.arraycopy(image[i], 0, res[i], 0, width);
        }
    
        boolean changed;
        do {
            int[][] precedent = res;
            res = dilatationGeodesique(res, masqueGeodesique, 1);
    
            changed = false;
            for (int i = 0; i < height && !changed; i++) {
                for (int j = 0; j < width && !changed; j++) {
                    if (res[i][j] != precedent[i][j]) {
                        changed = true;
                    }
                }
            }
        } while (changed);
    
        return res;
    }
    

    public static int[][] filtreMedian(int[][] image, int tailleMasque) {
        int half = tailleMasque / 2;
        int[][] res = new int[image.length][image[0].length];

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if (i < half || i >= image.length - half || j < half || j >= image[0].length - half) {
                    res[i][j] = 0;
                    continue;
                }

                int[] window = new int[tailleMasque * tailleMasque];
                int index = 0;
                for (int dx = -half; dx <= half; dx++) {
                    for (int dy = -half; dy <= half; dy++) {
                        window[index++] = image[i + dx][j + dy];
                    }
                }
                java.util.Arrays.sort(window);
                res[i][j] = window[window.length / 2];
            }
        }

        return res;
    }
}
