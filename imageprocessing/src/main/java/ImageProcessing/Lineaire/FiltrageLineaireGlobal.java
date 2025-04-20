package ImageProcessing.Lineaire;

import ImageProcessing.Fourier.Fourier;
import ImageProcessing.Complexe.*;
import ImageProcessing.Utilities.MyCast;

public class FiltrageLineaireGlobal {

    /**
     * Filtre passe bas idéal
     * @param image
     * @param frequenceCoupure
     * @return int[][]
     * @author Ryan Bouchou
     * @date 2025-04-20
     * @version 1.0
     * @description Filtre passe bas idéal
     */
    public static int[][] filtrePasseBasIdeal(int[][] image, int frequenceCoupure){

        int row = image.length;
        int column = image[0].length;

        MatriceComplexe fourierImage = Fourier.Fourier2D(MyCast.toDoubleArray(image));

        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                double d = Math.sqrt(Math.pow(i - row / 2.d, 2) + Math.pow(j - column / 2.d, 2));
                if(d > frequenceCoupure){
                    fourierImage.set(i, j, new Complexe(0, 0));
                }
            }
        }

        return MyCast.toIntArray(Fourier.InverseFourier2D(fourierImage).getPartieReelle());
    }

    /**
     * Filtre passe haut idéal
     * @param image
     * @param frequenceCoupure
     * @return int[][]
     * @author Ryan Bouchou
     * @date 2025-04-20
     * @version 1.0
     * @description Filtre passe haut idéal
     */
    public static int[][] filtrePasseHautIdeal(int[][] image, int frequenceCoupure) {
        int row = image.length;
        int column = image[0].length;

        MatriceComplexe fourierImage = Fourier.Fourier2D(MyCast.toDoubleArray(image));

        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                double d = Math.sqrt(Math.pow(i - row / 2.d, 2) + Math.pow(j - column / 2.d, 2));
                if(d < frequenceCoupure){
                    fourierImage.set(i, j, new Complexe(0, 0));
                }
            }
        }

        return MyCast.toIntArray(Fourier.InverseFourier2D(fourierImage).getPartieReelle());
    }

    /**
     * Filtre passe bas Butterworth
     * @param image
     * @param frequenceCoupure
     * @param ordre
     * @return int[][]
     * @author Ryan Bouchou
     * @date 2025-04-20
     * @version 1.0
     * @description Filtre passe bas Butterworth
     */
    public static int[][] filtrePasseBasButterworth(int[][] image, int frequenceCoupure, int ordre) {
        int row = image.length;
        int column = image[0].length;
    
        MatriceComplexe fourierImage = Fourier.Fourier2D(MyCast.toDoubleArray(image));
    
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                double d = Math.sqrt(Math.pow(i - row / 2.0, 2) + Math.pow(j - column / 2.0, 2));
                double h = 1.0 / (1.0 + Math.pow(d / frequenceCoupure, 2.d * ordre));
                fourierImage.get(i, j).multiplie(new Complexe(h, 0));
            }
        }
    
        return MyCast.toIntArray(Fourier.InverseFourier2D(fourierImage).getPartieReelle());
    }
    
    /**
     * Filtre passe haut Butterworth
     * @param image
     * @param frequenceCoupure
     * @param ordre
     * @return int[][]
     * @author Ryan Bouchou
     * @date 2025-04-20
     * @version 1.0
     * @description Filtre passe haut Butterworth
     */
    public static int[][] filtrePasseHautButterworth(int[][] image, int frequenceCoupure, int ordre){

        int row = image.length;
        int column = image[0].length;

        MatriceComplexe fourierImage = Fourier.Fourier2D(MyCast.toDoubleArray(image));

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                double d = Math.sqrt(Math.pow(i - row / 2.0, 2) + Math.pow(j - column / 2.0, 2));
                double h = 1.0 / (1.0 + Math.pow(d / frequenceCoupure, -2.d * ordre));
                fourierImage.get(i, j).multiplie(new Complexe(h, 0));
            }
        }

        return MyCast.toIntArray(Fourier.InverseFourier2D(fourierImage).getPartieReelle());
    }

}
