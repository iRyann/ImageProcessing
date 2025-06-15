package ImageProcessing.Utilities;

public class MatrixHelper {

    public static double[][] getDoubleMatrixFromText(String text) {

        // Creation de la matrice depuis son format texte
        String[] lignes = text.split(";");
        int tailleMat = lignes.length;
//            System.out.println(tailleMat);
//            for (int i = 0; i < tailleMat; i++) {
//                System.out.println(lignes[i]);
//            }

        double[][] matrice = new double[tailleMat][tailleMat];

        for (int i = 0; i < lignes.length; i++) {
            String[] colonne = lignes[i].split(",");
//                System.out.println(lignes[i]);
            if (colonne.length != tailleMat) {
                // erreur
                System.out.println("Erreur : la matrice n'est pas carré");
            }

            for (int j = 0; j < colonne.length; j++) {
                matrice[j][i] = Double.parseDouble(colonne[j]);
            }
        }

        return matrice;
    }

    public static int[][] getIntMatrixFromText(String text) {

        // Creation de la matrice depuis son format texte
        String[] lignes = text.split(";");
        int tailleMat = lignes.length;
//            System.out.println(tailleMat);
//            for (int i = 0; i < tailleMat; i++) {
//                System.out.println(lignes[i]);
//            }

        int[][] matrice = new int[tailleMat][tailleMat];

        for (int i = 0; i < lignes.length; i++) {
            String[] colonne = lignes[i].split(",");
//                System.out.println(lignes[i]);
            if (colonne.length != tailleMat) {
                // erreur
                System.out.println("Erreur : la matrice n'est pas carré");
            }

            for (int j = 0; j < colonne.length; j++) {
                matrice[j][i] = Integer.parseInt(colonne[j]);
            }
        }

        return matrice;
    }
}
