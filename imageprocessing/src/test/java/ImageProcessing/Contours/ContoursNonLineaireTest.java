package ImageProcessing.Contours;

import org.junit.Test;
import static org.junit.Assert.*;

public class ContoursNonLineaireTest {
    private boolean matricesEqual(int[][] a, int[][] b) {
        if (a == null || b == null) return a == b;
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != b[i].length) return false;
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }
    private void printMatrix(int[][] mat) {
        if (mat == null) {
            System.out.println("null");
            return;
        }
        for (int[] row : mat) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testGradientErosion() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        // Le résultat dépend de MorphoElementaire.erosion, ici on vérifie juste que la méthode ne plante pas
        int[][] res = ContoursNonLineaire.gradientErosion(image);
        assertNotNull(res);
        assertEquals(3, res.length);
        assertEquals(3, res[0].length);
    }

    @Test
    public void testGradientDilatation() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] res = ContoursNonLineaire.gradientDilatation(image);
        assertNotNull(res);
        assertEquals(3, res.length);
        assertEquals(3, res[0].length);
    }

    @Test
    public void testGradientBeucher() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] res = ContoursNonLineaire.gradientBeucher(image);
        assertNotNull(res);
        assertEquals(3, res.length);
        assertEquals(3, res[0].length);
    }

    @Test
    public void testLaplacienNonLineaire() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] res = ContoursNonLineaire.laplacienNonLineaire(image);
        assertNotNull(res);
        assertEquals(3, res.length);
        assertEquals(3, res[0].length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullImage() {
        ContoursNonLineaire.gradientErosion(null);
    }
}
