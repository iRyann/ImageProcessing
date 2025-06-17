package ImageProcessing.NonLineaire;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Classe de test pour MorphoElementaire
 * Tests adaptés au code existant sans validation d'entrée
 */
public class TestMorphoElementaire {
    
    // Images de test simples
    private int[][] imageSimple3x3;
    private int[][] imageCarree5x5;
    private int[][] imageBinaire;
    private int[][] imageNiveauGris;
    private int[][] imageUniforme;
    
    @Before
    public void setUp() {
        // Image 3x3 simple pour tests de base
        imageSimple3x3 = new int[][]{
            {0, 255, 0},
            {255, 255, 255},
            {0, 255, 0}
        };
        
        // Image 5x5 avec motif plus complexe
        imageCarree5x5 = new int[][]{
            {0, 0, 255, 0, 0},
            {0, 255, 255, 255, 0},
            {255, 255, 255, 255, 255},
            {0, 255, 255, 255, 0},
            {0, 0, 255, 0, 0}
        };
        
        // Image binaire (0 et 255 seulement)
        imageBinaire = new int[][]{
            {0, 0, 0, 1, 1},
            {0, 1, 1, 1, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 1, 1, 0}
        };
        
        // Image avec niveaux de gris variés
        imageNiveauGris = new int[][]{
            {50, 100, 150, 200},
            {75, 125, 175, 225},
            {25, 175, 100, 50},
            {200, 75, 225, 125}
        };
        
        // Image uniforme
        imageUniforme = new int[][]{
            {128, 128, 128},
            {128, 128, 128},
            {128, 128, 128}
        };
    }
    
    // ============ TESTS ÉROSION ============
    
    @Test
    public void testErosionImageSimple() {
        System.out.println("Test érosion - Image simple 3x3");
        
        // Masque 3x3 sur image 3x3
        int[][] resultat = MorphoElementaire.erosion(imageSimple3x3, 3);
        
        // Avec un masque 3x3, l'érosion doit donner le minimum global
        assertEquals("Résultat doit avoir la même taille", 3, resultat.length);
        assertEquals("Résultat doit avoir la même largeur", 3, resultat[0].length);
        
        // Vérifier que tous les pixels sont érodés vers le minimum
        for (int i = 0; i < resultat.length; i++) {
            for (int j = 0; j < resultat[0].length; j++) {
                assertTrue("Érosion doit réduire les valeurs", resultat[i][j] <= imageSimple3x3[i][j]);
            }
        }
        
        afficherResultat("Érosion 3x3", imageSimple3x3, resultat);
    }
    
    @Test
    public void testErosionMasque1x1() {
        System.out.println("Test érosion - Masque 1x1 (identité)");
        
        int[][] resultat = MorphoElementaire.erosion(imageCarree5x5, 1);
        
        // Masque 1x1 doit être l'identité
        assertArrayEquals("Masque 1x1 doit préserver l'image", imageCarree5x5, resultat);
    }
    
    @Test
    public void testErosionImageUniforme() {
        System.out.println("Test érosion - Image uniforme");
        
        int[][] resultat = MorphoElementaire.erosion(imageUniforme, 3);
        
        // Image uniforme doit rester uniforme
        for (int i = 0; i < resultat.length; i++) {
            for (int j = 0; j < resultat[0].length; j++) {
                assertEquals("Image uniforme doit rester uniforme", 128, resultat[i][j]);
            }
        }
    }
    
    // ============ TESTS DILATATION ============
    
    @Test
    public void testDilatationImageSimple() {
        System.out.println("Test dilatation - Image simple 3x3");
        
        int[][] resultat = MorphoElementaire.dilatation(imageSimple3x3, 3);
        
        assertEquals("Résultat doit avoir la même taille", 3, resultat.length);
        assertEquals("Résultat doit avoir la même largeur", 3, resultat[0].length);
        
        // Vérifier que la dilatation augmente les valeurs
        for (int i = 0; i < resultat.length; i++) {
            for (int j = 0; j < resultat[0].length; j++) {
                assertTrue("Dilatation doit augmenter les valeurs", resultat[i][j] >= imageSimple3x3[i][j]);
            }
        }
        
        afficherResultat("Dilatation 3x3", imageSimple3x3, resultat);
    }
    
    @Test
    public void testDilatationMasque1x1() {
        System.out.println("Test dilatation - Masque 1x1 (identité)");
        
        int[][] resultat = MorphoElementaire.dilatation(imageCarree5x5, 1);
        
        // Masque 1x1 doit être l'identité
        assertArrayEquals("Masque 1x1 doit préserver l'image", imageCarree5x5, resultat);
    }
    
    // ============ TESTS DUALITÉ ============
    
    @Test
    public void testDilatationByErosion() {
        System.out.println("Test dilatation par érosion (dualité)");
        
        int[][] dilatationDirecte = MorphoElementaire.dilatation(imageBinaire, 3);
        int[][] dilatationParErosion = MorphoElementaire.dilatationByErosion(imageBinaire, 3);
        
        // Les deux méthodes doivent donner le même résultat
        assertArrayEquals("Dilatation directe et par érosion doivent être identiques", 
                         dilatationDirecte, dilatationParErosion);
        
        afficherResultat("Dilatation directe", imageBinaire, dilatationDirecte);
        afficherResultat("Dilatation par érosion", imageBinaire, dilatationParErosion);
    }
    
    // ============ TESTS OUVERTURE/FERMETURE ============
    
    @Test
    public void testOuverture() {
        System.out.println("Test ouverture");
        
        int[][] resultat = MorphoElementaire.ouverture(imageCarree5x5, 3);
        
        assertEquals("Résultat doit avoir la même taille", imageCarree5x5.length, resultat.length);
        assertEquals("Résultat doit avoir la même largeur", imageCarree5x5[0].length, resultat[0].length);
        
        // L'ouverture doit être <= à l'image originale
        for (int i = 0; i < resultat.length; i++) {
            for (int j = 0; j < resultat[0].length; j++) {
                assertTrue("Ouverture doit être <= image originale", 
                          resultat[i][j] <= imageCarree5x5[i][j]);
            }
        }
        
        afficherResultat("Ouverture", imageCarree5x5, resultat);
    }
    
    @Test
    public void testFermeture() {
        System.out.println("Test fermeture");
        
        int[][] resultat = MorphoElementaire.fermeture(imageCarree5x5, 3);
        
        assertEquals("Résultat doit avoir la même taille", imageCarree5x5.length, resultat.length);
        assertEquals("Résultat doit avoir la même largeur", imageCarree5x5[0].length, resultat[0].length);
        
        // La fermeture doit être >= à l'image originale
        for (int i = 0; i < resultat.length; i++) {
            for (int j = 0; j < resultat[0].length; j++) {
                assertTrue("Fermeture doit être >= image originale", 
                          resultat[i][j] >= imageCarree5x5[i][j]);
            }
        }
        
        afficherResultat("Fermeture", imageCarree5x5, resultat);
    }
    
    // ============ TESTS PROPRIÉTÉS MATHÉMATIQUES ============
    
    @Test
    public void testIdempotenceOuverture() {
        System.out.println("Test idempotence ouverture");
        
        int[][] ouverture1 = MorphoElementaire.ouverture(imageCarree5x5, 3);
        int[][] ouverture2 = MorphoElementaire.ouverture(ouverture1, 3);
        
        // L'ouverture doit être idempotente
        assertArrayEquals("Ouverture doit être idempotente", ouverture1, ouverture2);
    }
    
    @Test
    public void testIdempotenceFermeture() {
        System.out.println("Test idempotence fermeture");
        
        int[][] fermeture1 = MorphoElementaire.fermeture(imageCarree5x5, 3);
        int[][] fermeture2 = MorphoElementaire.fermeture(fermeture1, 3);
        
        // La fermeture doit être idempotente
        assertArrayEquals("Fermeture doit être idempotente", fermeture1, fermeture2);
    }
    
    @Test
    public void testOuvertureFermetureInegalite() {
        System.out.println("Test inégalité ouverture ≤ image ≤ fermeture");
        
        int[][] ouverture = MorphoElementaire.ouverture(imageNiveauGris, 3);
        int[][] fermeture = MorphoElementaire.fermeture(imageNiveauGris, 3);
        
        for (int i = 0; i < imageNiveauGris.length; i++) {
            for (int j = 0; j < imageNiveauGris[0].length; j++) {
                assertTrue("Ouverture ≤ Image ≤ Fermeture", 
                          ouverture[i][j] <= imageNiveauGris[i][j] && 
                          imageNiveauGris[i][j] <= fermeture[i][j]);
            }
        }
    }
    
    // ============ TESTS DE PERFORMANCE ============
    
    @Test
    public void testPerformanceImageGrande() {
        System.out.println("Test performance - Image 50x50");
        
        // Créer une image plus grande
        int[][] imageGrande = new int[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                imageGrande[i][j] = (i + j) % 256;
            }
        }
        
        long debut = System.currentTimeMillis();
        int[][] resultat = MorphoElementaire.erosion(imageGrande, 5);
        long fin = System.currentTimeMillis();
        
        System.out.println("Temps d'exécution érosion 50x50 avec masque 5x5: " + (fin - debut) + "ms");
        
        assertNotNull("Résultat ne doit pas être null", resultat);
        assertEquals("Taille préservée", 50, resultat.length);
        assertEquals("Largeur préservée", 50, resultat[0].length);
    }
    
    // ============ TESTS LIMITES ============
    
    @Test
    public void testImageTresGrande() {
        System.out.println("Test image très grande - Masque maximum");
        
        int[][] imageTaille = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                imageTaille[i][j] = 100 + (i * j) % 155;
            }
        }
        
        // Masque presque aussi grand que l'image
        int[][] resultat = MorphoElementaire.erosion(imageTaille, 9);
        
        assertNotNull("Résultat ne doit pas être null", resultat);
        assertEquals("Taille préservée", 10, resultat.length);
    }
    
    // ============ MÉTHODES UTILITAIRES ============
    
    private void afficherResultat(String operation, int[][] original, int[][] resultat) {
        System.out.println("\n" + operation + ":");
        System.out.println("Original:");
        afficherMatrice(original);
        System.out.println("Résultat:");
        afficherMatrice(resultat);
        System.out.println();
    }
    
    private void afficherMatrice(int[][] matrice) {
        for (int[] ligne : matrice) {
            for (int pixel : ligne) {
                System.out.printf("%3d ", pixel);
            }
            System.out.println();
        }
    }
    
    // ============ TEST PRINCIPAL ============
    
    public static void main(String[] args) {
        System.out.println("=== Tests MorphoElementaire ===\n");
        
        TestMorphoElementaire test = new TestMorphoElementaire();
        
        try {
            test.setUp();
            
            // Exécuter tous les tests
            test.testErosionImageSimple();
            test.testErosionMasque1x1();
            test.testErosionImageUniforme();
            test.testDilatationImageSimple();
            test.testDilatationMasque1x1();
            test.testDilatationByErosion();
            test.testOuverture();
            test.testFermeture();
            test.testIdempotenceOuverture();
            test.testIdempotenceFermeture();
            test.testOuvertureFermetureInegalite();
            test.testPerformanceImageGrande();
            test.testImageTresGrande();
            
            System.out.println("=== Tous les tests sont terminés ===");
            
        } catch (Exception e) {
            System.err.println("Erreur lors des tests: " + e.getMessage());
            e.printStackTrace();
        }
    }
}