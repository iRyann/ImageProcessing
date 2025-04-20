package ImageProcessing.Lineaire;

public class FiltrageLineaireLocal {
    public static int[][] filtreMasqueConvolution(int[][] image, double [][] masque){

        int rows = image.length;
        int columns = image[0].length;
        int kernelSize = masque.length;

        int[][] imageFiltered = new int[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                imageFiltered[i][j] = 0;
            }
        }
        
        for(int i = kernelSize / 2; i < rows - kernelSize / 2; i++){
            for(int j = kernelSize / 2; i < columns - kernelSize / 2 ; j++){

                float sum = 0.f;

                for(int k = 0; k < kernelSize; k++){
                    for(int p= 0; p < kernelSize; p++){
                        sum += image[i - kernelSize + k][j - kernelSize + p] * masque[k][p];
                    }
                }

                imageFiltered[i][j] = (int) Math.clamp(sum, 0, 255);
            }
        }

        return imageFiltered;
    }

    public static int[][] filtreMoyenneur(int[][] image, int tailleMasque){

        double[][] masque = new double[tailleMasque][tailleMasque];

        for(int i = 0; i < tailleMasque; i++){
            for(int j = 0; j < tailleMasque; j++){
                masque[i][j] = 1.0 / (tailleMasque * tailleMasque);
            }
        }

        return filtreMasqueConvolution(image, masque);
    }
}
