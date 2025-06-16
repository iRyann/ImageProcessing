package ImageProcessing.Lineaire;
public class FiltrageLineaireLocal {

    /**
     * Filtre de convolution
     * @param image
     * @param masque
     * @return int[][]
     * @author Ryan Bouchou
     * @date 2025-04-20
     * @version 1.0 Première version sans gestion des bords
     * @version 1.1 Correction bug critique et ajout de la vérification de la taille du masque
     * @version 1.2 Ajout de la gestion des bords par extension de l'image
     * @version 1.3 Correction de la gestion des bords pour les masques 1x1
     * @description Filtre de convolution
     */
    public static int[][] filtreMasqueConvolution(int[][] image, double[][] masque) {
    // Validation des paramètres
        if (image == null || masque == null) {
            throw new IllegalArgumentException("Image et masque ne peuvent pas être null");
        }
        
        if (masque.length % 2 == 0) {
            throw new IllegalArgumentException("La taille du masque doit être impaire");
        }
        
        if (masque.length == 1) {
            return copyAndScale(image, masque[0][0]);
        }
        
        if (masque.length < 1) {
            throw new IllegalArgumentException("La taille du masque doit être >= 1");
        }
        
        if (masque.length > image.length || masque.length > image[0].length) {
            throw new IllegalArgumentException("La taille du masque ne peut pas être supérieure à la taille de l'image");
        }
        
        int rows = image.length;
        int columns = image[0].length;
        int kernelSize = masque.length;
        int halfKernel = kernelSize / 2;
        int[][] extendedImage = Generic.extendImage(image, halfKernel, "copy");
        int[][] imageFiltered = new int[rows][columns];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                double sum = 0.0;
                
                for (int ki = 0; ki < kernelSize; ki++) {
                    for (int kj = 0; kj < kernelSize; kj++) {
                        int imageRow = i + halfKernel + ki;
                        int imageCol = j + halfKernel + kj;
                        sum += extendedImage[imageRow][imageCol] * masque[ki][kj];
                    }
                }
                
                imageFiltered[i][j] = (int) Math.max(0, Math.min(255, Math.round(sum)));
            }
        }

        return imageFiltered;
    }
    // Méthode auxiliaire pour le cas masque 1x1
    private static int[][] copyAndScale(int[][] image, double factor) {
        int rows = image.length;
        int cols = image[0].length;
        int[][] result = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double scaled = image[i][j] * factor;
                result[i][j] = (int) Math.clamp(Math.round(scaled), 0, 255);
            }
        }
        
        return result;
    }

    /**
     * Filtre de moyenneur
     * @param image
     * @param tailleMasque
     * @return int[][]
     * @author Ryan Bouchou
     * @date 2025-04-20
     * @version 1.0 Première version sans gestion des bords
     * @version 1.1 Ajout de la vérification de la taille du masque
     * @description Filtre de moyenneur
     */
    public static int[][] filtreMoyenneur(int[][] image, int tailleMasque){

        if(tailleMasque % 2 == 0){
            throw new IllegalArgumentException("La taille du masque doit être impair.");
        }
        if(tailleMasque == 1){
            return image; // Pas de filtrage nécessaire
        }
        if(tailleMasque < 1){
            throw new IllegalArgumentException("La taille du masque doit être supérieure ou égale à 1.");
        }
        if(tailleMasque > image.length || tailleMasque > image[0].length){
            throw new IllegalArgumentException("La taille du masque ne peut pas être supérieure à la taille de l'image.");
        }

        double[][] masque = new double[tailleMasque][tailleMasque];

        for(int i = 0; i < tailleMasque; i++){
            for(int j = 0; j < tailleMasque; j++){
                masque[i][j] = 1.0 / (tailleMasque * tailleMasque);
            }
        }

        return filtreMasqueConvolution(image, masque);
    }
}
