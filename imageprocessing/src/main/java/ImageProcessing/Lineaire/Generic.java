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
        
        int rows = image.length;
        int cols = image[0].length;
        int newRows = rows + 2 * borderSize;
        int newCols = cols + 2 * borderSize;
        int[][] extendedImage = new int[newRows][newCols];
        
        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < newCols; j++) {
                int srcRow;
                int srcCol;
                
                if (i < borderSize) {
                    srcRow = borderSize - 1 - i;
                } else if (i >= rows + borderSize) {
                    srcRow = 2 * rows - 1 - (i - borderSize);
                } else {
                    srcRow = i - borderSize;
                }
                
                if (j < borderSize) {
                    srcCol = borderSize - 1 - j;
                } else if (j >= cols + borderSize) {
                    srcCol = 2 * cols - 1 - (j - borderSize);
                } else {
                    srcCol = j - borderSize;
                }
                
                srcRow = Math.clamp(srcRow, 0, rows - 1);
                srcCol = Math.clamp(srcCol, 0, cols - 1);
                
                extendedImage[i][j] = image[srcRow][srcCol];
            }
        }
        
        return extendedImage;
    }
    
}
