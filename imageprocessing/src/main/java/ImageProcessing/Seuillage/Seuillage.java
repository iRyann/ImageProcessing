package ImageProcessing.Seuillage;

public class Seuillage {
    public static int[][] seuillageSimple(int[][] image, int seuil){
        int rows = image.length;
        int columns = image[0].length;
        int[][] imageSeuillee = new int[rows][columns];
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(image[i][j] >= seuil){
                    imageSeuillee[i][j] = 0;
                } else {
                    imageSeuillee[i][j] = 255;
                }
            }
        }
        
        return imageSeuillee;
    }

    public static int[][] seuillageDouble(int[][] image,int seuil1, int seuil2){
        if (seuil1 > seuil2) {
            throw new IllegalArgumentException("seuil1 doit être <= seuil2");
        }
        int rows = image.length;
        int columns = image[0].length;
        int[][] imageSeuillee = new int[rows][columns];
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if (image[i][j] <= seuil1) {
                    imageSeuillee[i][j] = 0;
                } else if (image[i][j] <= seuil2) {
                    imageSeuillee[i][j] = 128;
                } else {
                    imageSeuillee[i][j] = 255;
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
            if (count1 == 0 || count2 == 0) {
                // Cas dégénéré : impossible de séparer en deux classes
                return seuillageSimple(image, seuil);
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
                    imageSeuillee[i][j] = 255;
                }
            }
        }
        return imageSeuillee;
    }

    public static int[][] seuillageAutomatiqueSafe(int[][] image) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            throw new IllegalArgumentException("Image vide ou nulle");
        }
        
        // Calcul du seuil initial (moyenne)
        long sum = 0;
        int totalPixels = image.length * image[0].length;
        
        for(int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                sum += image[i][j];
            }
        }
        int seuil = (int)(sum / totalPixels);

        boolean changed = true;
        int iterations = 0;
        final int MAX_ITERATIONS = 100; // Protection contre boucles infinies
        
        do {
            if (++iterations > MAX_ITERATIONS) {
                System.err.println("Seuillage automatique: convergence non atteinte après " + MAX_ITERATIONS + " itérations");
                break;
            }
            
            long sum1 = 0, sum2 = 0;
            int count1 = 0, count2 = 0;

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

            // Protection contre division par zéro
            if (count1 == 0 || count2 == 0) {
                break; // Cas dégénéré, garder le seuil actuel
            }
            
            int newSeuil = (int)((sum1 / count1 + sum2 / count2) / 2);

            if(newSeuil == seuil) {
                changed = false;
            } else {
                seuil = newSeuil;
            }
        } while(changed);

        return seuillageSimple(image, seuil);
    }
}
