package ImageProcessing.Lineaire;

public class Generic {

    public enum TypeImage {
        BINARY,
        GRAY,
        COLOR
    }
    
    public static TypeImage kindImage(int[][] image) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            throw new IllegalArgumentException("Image vide ou nulle");
        }
    
        int rows = image.length;
        int cols = image[0].length;
    
        for (int i = 0; i < rows; i++) {
            if (image[i].length != cols) {
                throw new IllegalArgumentException("Toutes les lignes doivent avoir la même taille");
            }
            for (int j = 0; j < cols; j++) {
                int val = image[i][j];
                if (val < 0 || val > 255) {
                    throw new IllegalArgumentException("Pixel invalide : doit être entre 0 et 255");
                }
                if (val > 1) {
                    return TypeImage.GRAY;
                }
            }
        }
    
        return TypeImage.BINARY;
    }

    public static int[][] inverse(int[][] image) {
        int rows = image.length;
        int cols = image[0].length;
        int[][] inverseImage = new int[rows][cols];
        int maxValue = kindImage(image) == TypeImage.GRAY ? 255 : 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                inverseImage[i][j] = maxValue - image[i][j];
            }
        }
        
        return inverseImage;
    }

    public static int[][] extendImage(int[][] image, int borderSize, String borderType) {
        if (borderSize < 0) {
            throw new IllegalArgumentException("La taille de la bordure doit être positive");
        }
        if (borderType == null || (!borderType.equals("zero") && !borderType.equals("copy"))) {
            throw new IllegalArgumentException("Type de bordure invalide, doit être 'zero' ou 'copy'");
        }
        if (image == null || image.length == 0 || image[0].length == 0) {
            throw new IllegalArgumentException("Image vide ou nulle");
        }
        if (image.length < 1 || image[0].length < 1) {
            throw new IllegalArgumentException("L'image doit avoir au moins une ligne et une colonne");
        }
        if (borderType.equals("copy")) {
            return extendWithCopy(image, borderSize);
        } else {
            return extendWithZero(image, borderSize);
        }
    }
    private static int[][] extendWithZero(int[][] image, int borderSize) {
        
        int rows = image.length;
        int cols = image[0].length;
        int newRows = rows + 2 * borderSize;
        int newCols = cols + 2 * borderSize;
        
        int[][] extendedImage = new int[newRows][newCols];
        
        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < newCols; j++) {
                if (i < borderSize || i >= rows + borderSize || j < borderSize || j >= cols + borderSize) {
                    extendedImage[i][j] = 0; // Valeur par défaut pour la bordure
                } else {
                    extendedImage[i][j] = image[i - borderSize][j - borderSize];
                }
            }
        }
        
        return extendedImage;
    }

    private static int[][] extendWithCopy(int[][] image, int borderSize) {
        if (image == null || borderSize < 0) {
            throw new IllegalArgumentException("Paramètres invalides");
        }
        
        if (image.length == 0 || image[0].length == 0) {
            throw new IllegalArgumentException("Image vide");
        }
        
        int rows = image.length;
        int cols = image[0].length;
        int newRows = rows + 2 * borderSize;
        int newCols = cols + 2 * borderSize;
        int[][] extendedImage = new int[newRows][newCols];
        
        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < newCols; j++) {
                // Calculer les indices dans l'image originale
                int originalRow = i - borderSize;
                int originalCol = j - borderSize;
                
                // Appliquer l'effet miroir pour les indices qui sortent des bornes
                int srcRow = mirrorIndex(originalRow, rows);
                int srcCol = mirrorIndex(originalCol, cols);
                
                extendedImage[i][j] = image[srcRow][srcCol];
            }
        }
        
        return extendedImage;
    }

    /**
     * Calcule l'indice miroir pour une dimension donnée
     * @param index L'indice original (peut être négatif ou >= size)
     * @param size La taille de la dimension
     * @return L'indice miroir valide
     */
    private static int mirrorIndex(int index, int size) {
        if (index < 0) {
            // Indices négatifs : miroir vers la droite
            return -index;
        } else if (index >= size) {
            // Indices trop grands : miroir vers la gauche
            return 2 * size - 2 - index;
        } else {
            // Indices valides : pas de changement
            return index;
        }
    }
    
}
