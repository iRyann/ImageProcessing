package ImageProcessing.Contours;
import ImageProcessing.Lineaire.FiltrageLineaireLocal;

public class ContoursLineaire {

    public static int[][] gradientPrewitt(int[][] image,int dir){
        double[][] masque = new double[3][3];
        if (dir == 0) {
            masque[0][0] = -1; masque[0][1] = 0; masque[0][2] = 1;
            masque[1][0] = -1; masque[1][1] = 0; masque[1][2] = 1;
            masque[2][0] = -1; masque[2][1] = 0; masque[2][2] = 1;
        } else {
            masque[0][0] = -1; masque[0][1] = -1; masque[0][2] = -1;
            masque[1][0] = 0; masque[1][1] = 0; masque[1][2] = 0;
            masque[2][0] = 1; masque[2][1] = 1; masque[2][2] = 1;
        }
        return FiltrageLineaireLocal.filtreMasqueConvolution(image,masque);
    }
    public static int[][] gradientSobel(int[][] image,int dir){
        double[][] masque = new double[3][3];
        if (dir == 0) {
            masque[0][0] = -1; masque[0][1] = 0; masque[0][2] = 1;
            masque[1][0] = -2; masque[1][1] = 0; masque[1][2] = 2;
            masque[2][0] = -1; masque[2][1] = 0; masque[2][2] = 1;
        } else {
            masque[0][0] = -1; masque[0][1] = -2; masque[0][2] = -1;
            masque[1][0] = 0; masque[1][1] = 0; masque[1][2] = 0;
            masque[2][0] = 1; masque[2][1] = 2; masque[2][2] = 1;
        }
        return FiltrageLineaireLocal.filtreMasqueConvolution(image,masque);
    }
    public static int[][] laplacien4(int[][] image){
        double[][] masque = new double[3][3];
        masque[0][0] = 0; masque[0][1] = 1; masque[0][2] = 0;
        masque[1][0] = 1; masque[1][1] = -4; masque[1][2] = 1;
        masque[2][0] = 0; masque[2][1] = 1; masque[2][2] = 0;
        return FiltrageLineaireLocal.filtreMasqueConvolution(image,masque);
    }
    public static int[][] laplacien8(int[][] image){
        double[][] masque = new double[3][3];
        masque[0][0] = 1; masque[0][1] = 1; masque[0][2] = 1;
        masque[1][0] = 1; masque[1][1] = -8; masque[1][2] = 1;
        masque[2][0] = 1; masque[2][1] = 1; masque[2][2] = 1;
        return FiltrageLineaireLocal.filtreMasqueConvolution(image,masque);
    }
}
