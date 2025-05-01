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
    
}
