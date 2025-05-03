package ImageProcessing.Seuillage;

import java.lang.reflect.Array;

import org.jfree.util.ArrayUtilities;

public class Seuillage {
    public static int[][] seuillageSimple(int[][] image, int seuil){
        int rows = image.length;
        int columns = image[0].length;
        int[][] imageSeuillee = new int[rows][columns];
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(image[i][j] >= seuil){
                    imageSeuillee[i][j] = 1;
                } else {
                    imageSeuillee[i][j] = 0;
                }
            }
        }
        
        return imageSeuillee;
    }

    public static int[][] seuillageDouble(int[][] image,int seuil1, int seuil2){
        int rows = image.length;
        int columns = image[0].length;
        int[][] imageSeuillee = new int[rows][columns];
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(image[i][j] <= seuil1){
                    imageSeuillee[i][j] = 0;
                } else if (seuil1 < image[i][j] && image[i][j] <= seuil2) {
                    imageSeuillee[i][j] = 1;
                } else {
                    imageSeuillee[i][j] = 2;
                }
            }
        }
        
        return imageSeuillee;
    }

    public static int[][] seuillageAutomatique(int[][] image){
        // Seuil 
        int seuil = 0;
        for(int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                seuil += image[i][j];
            }
        }
        seuil /= (image.length * image[0].length);

        boolean changed = true;
        do{
            int sum1 = 0;
            int sum2 = 0;
            int count1 = 0;
            int count2 = 0;

            for(int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[i].length; j++) {
                    if(image[i][j] <= seuil) {
                        sum1 += image[i][j];
                        count1++;
                    } else {
                        sum2 += image[i][j];
                        count2++;
                    }
                }
            }

            int newSeuil = (sum1 / count1 + sum2 / count2) / 2;

            if(newSeuil == seuil) {
                changed = false;
            } else {
                seuil = newSeuil;
            }
        }while(changed);

        int[][] imageSeuillee = new int[image.length][image[0].length];
        for(int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                if(image[i][j] <= seuil) {
                    imageSeuillee[i][j] = 0;
                } else {
                    imageSeuillee[i][j] = 1;
                }
            }
        }
        return imageSeuillee;
    }
}
