package ImageProcessing.Lineaire;

import ImageProcessing.Fourier.Fourier;
import ImageProcessing.Complexe.*;
import ImageProcessing.Utilities.MyCast;
public class FiltrageLineaireGlobal {

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
