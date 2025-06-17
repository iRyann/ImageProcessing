package ImageProcessing.Lineaire;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Arrays;

public class FiltrageLineaireGlobalTest {
    // Images de test
    private int[][] imageSimple;
    private int[][] imageCarree;
    private int[][] imageGradient;
    private int[][] imageTest;
    private int[][] imageBruit;

    @Before
    public void setUp() {
        imageSimple = new int[][]{
            {10, 20, 30, 40},
            {50, 60, 70, 80},
            {90, 100, 110, 120},
            {130, 140, 150, 160}
        };
        imageCarree = new int[][]{
            {0, 0, 255, 255},
            {0, 0, 255, 255},
            {255, 255, 0, 0},
            {255, 255, 0, 0}
        };
        imageGradient = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                imageGradient[i][j] = (i * 8 + j) * 4;
            }
        }
        imageTest = new int[][]{
            {10, 20, 30},
            {40, 50, 60},
            {70, 80, 90}
        };
        imageBruit = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                imageBruit[i][j] = ((i + j) % 2 == 0) ? 255 : 0;
            }
        }
    }

    // ============ TESTS DE VALIDATION DES PARAMÈTRES ============
    @Test(expected = IllegalArgumentException.class)
    public void testFiltrePasseBasIdealInvalidFreq() {
        FiltrageLineaireGlobal.filtrePasseBasIdeal(new int[][]{{1}}, 0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFiltrePasseBasIdealNullImage() {
        FiltrageLineaireGlobal.filtrePasseBasIdeal(null, 1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFiltrePasseHautIdealInvalidFreq() {
        FiltrageLineaireGlobal.filtrePasseHautIdeal(new int[][]{{1}}, 0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFiltrePasseHautIdealNullImage() {
        FiltrageLineaireGlobal.filtrePasseHautIdeal(null, 1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFiltrePasseBasButterworthInvalidFreq() {
        FiltrageLineaireGlobal.filtrePasseBasButterworth(new int[][]{{1}}, 0, 1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFiltrePasseBasButterworthInvalidOrder() {
        FiltrageLineaireGlobal.filtrePasseBasButterworth(new int[][]{{1}}, 1, 0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFiltrePasseBasButterworthNullImage() {
        FiltrageLineaireGlobal.filtrePasseBasButterworth(null, 1, 1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFiltrePasseHautButterworthInvalidFreq() {
        FiltrageLineaireGlobal.filtrePasseHautButterworth(new int[][]{{1}}, 0, 1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFiltrePasseHautButterworthInvalidOrder() {
        FiltrageLineaireGlobal.filtrePasseHautButterworth(new int[][]{{1}}, 1, 0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testValidationFrequenceNegative() {
        FiltrageLineaireGlobal.filtrePasseBasIdeal(imageSimple, -1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testValidationImageNull() {
        FiltrageLineaireGlobal.filtrePasseBasIdeal(null, 5);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testValidationOrdreNegatif() {
        FiltrageLineaireGlobal.filtrePasseBasButterworth(imageSimple, 5, -1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testValidationImageVide() {
        int[][] imageVide = new int[0][0];
        FiltrageLineaireGlobal.filtrePasseBasIdeal(imageVide, 5);
    }

    // ============ TESTS DE COMPORTEMENT ET COMPARATIFS ============
    @Test
    public void testFiltragePasseHautDifferentDePasseBas() {
        int[][] passBas = FiltrageLineaireGlobal.filtrePasseBasButterworth(imageCarree, 2, 2);
        int[][] passeHaut = FiltrageLineaireGlobal.filtrePasseHautButterworth(imageCarree, 2, 2);
        assertFalse("Passe-haut et passe-bas doivent être différents", Arrays.deepEquals(passBas, passeHaut));
    }
    @Test
    public void testComplementarite() {
        int[][] passBas = FiltrageLineaireGlobal.filtrePasseBasButterworth(imageTest, 2, 2);
        int[][] passeHaut = FiltrageLineaireGlobal.filtrePasseHautButterworth(imageTest, 2, 2);
        for (int i = 0; i < imageTest.length; i++) {
            for (int j = 0; j < imageTest[0].length; j++) {
                int somme = passBas[i][j] + passeHaut[i][j];
                int original = imageTest[i][j];
                assertTrue(Math.abs(somme - original) < 10);
            }
        }
    }
    @Test
    public void testComparaisonIdealVsButterworth() {
        int freq = 2;
        int[][] idealPasseBas = FiltrageLineaireGlobal.filtrePasseBasIdeal(imageGradient, freq);
        int[][] butterworthPasseBas = FiltrageLineaireGlobal.filtrePasseBasButterworth(imageGradient, freq, 2);
        double diffMoyenne = calculerDifferenceMoyenne(idealPasseBas, butterworthPasseBas);
        assertTrue(diffMoyenne > 1.0);
    }
    @Test
    public void testEffetOrdreButterworth() {
        int[][] ordre1 = FiltrageLineaireGlobal.filtrePasseBasButterworth(imageGradient, 2, 1);
        int[][] ordre5 = FiltrageLineaireGlobal.filtrePasseBasButterworth(imageGradient, 2, 5);
        double diff = calculerDifferenceMoyenne(ordre1, ordre5);
        assertTrue(diff > 0.5);
    }
    @Test
    public void testPasseHautSupprimeBassesFrequences() {
        int[][] imageUniforme = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                imageUniforme[i][j] = 128;
            }
        }
        int[][] resultat = FiltrageLineaireGlobal.filtrePasseHautButterworth(imageUniforme, 2, 2);
        double moyenne = calculerMoyenne(resultat);
        assertTrue(Math.abs(moyenne) < 10);
    }
    @Test
    public void testPasseBasSupprimeBruit() {
        int[][] resultat = FiltrageLineaireGlobal.filtrePasseBasButterworth(imageBruit, 1, 2);
        double varianceOriginale = calculerVariance(imageBruit);
        double varianceFiltre = calculerVariance(resultat);
        assertTrue(varianceFiltre < varianceOriginale);
    }

    // ============ TESTS DE TAILLE ET NON-NULLITÉ ============
    @Test
    public void testFiltrePasseBasIdealCall() {
        int[][] result = FiltrageLineaireGlobal.filtrePasseBasIdeal(imageTest, 1);
        assertNotNull(result);
        assertEquals(imageTest.length, result.length);
        assertEquals(imageTest[0].length, result[0].length);
    }
    @Test
    public void testFiltrePasseHautIdealCall() {
        int[][] result = FiltrageLineaireGlobal.filtrePasseHautIdeal(imageTest, 1);
        assertNotNull(result);
        assertEquals(imageTest.length, result.length);
        assertEquals(imageTest[0].length, result[0].length);
    }
    @Test
    public void testFiltrePasseBasButterworthCall() {
        int[][] result = FiltrageLineaireGlobal.filtrePasseBasButterworth(imageTest, 1, 1);
        assertNotNull(result);
        assertEquals(imageTest.length, result.length);
        assertEquals(imageTest[0].length, result[0].length);
    }
    @Test
    public void testFiltrePasseHautButterworthCall() {
        int[][] result = FiltrageLineaireGlobal.filtrePasseHautButterworth(imageTest, 1, 1);
        assertNotNull(result);
        assertEquals(imageTest.length, result.length);
        assertEquals(imageTest[0].length, result[0].length);
    }
    @Test
    public void testPerformanceImageGrande() {
        int[][] imageGrande = new int[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                imageGrande[i][j] = (i * 16 + j) % 256;
            }
        }
        long debut = System.currentTimeMillis();
        int[][] resultat = FiltrageLineaireGlobal.filtrePasseBasButterworth(imageGrande, 5, 2);
        long fin = System.currentTimeMillis();
        assertNotNull(resultat);
        assertEquals(16, resultat.length);
    }

    // ============ MÉTHODES UTILITAIRES ============
    private double calculerMoyenne(int[][] image) {
        double somme = 0;
        int count = 0;
        for (int[] ligne : image) {
            for (int pixel : ligne) {
                somme += pixel;
                count++;
            }
        }
        return somme / count;
    }
    private double calculerVariance(int[][] image) {
        double moyenne = calculerMoyenne(image);
        double sommeCarres = 0;
        int count = 0;
        for (int[] ligne : image) {
            for (int pixel : ligne) {
                sommeCarres += Math.pow(pixel - moyenne, 2);
                count++;
            }
        }
        return sommeCarres / count;
    }
    private double calculerDifferenceMoyenne(int[][] image1, int[][] image2) {
        double sommeDiff = 0;
        int count = 0;
        for (int i = 0; i < image1.length; i++) {
            for (int j = 0; j < image1[0].length; j++) {
                sommeDiff += Math.abs(image1[i][j] - image2[i][j]);
                count++;
            }
        }
        return sommeDiff / count;
    }
}