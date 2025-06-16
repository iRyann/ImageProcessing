package ImageProcessing.NonLineaire;

import org.junit.Test;
import static org.junit.Assert.*;

public class MorphoElementaireTest {
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
    public void testErosion3x3() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] expected = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        int[][] result = MorphoElementaire.erosion(image, 3);
        assertTrue(matricesEqual(expected, result));
    }

    @Test
    public void testDilatation3x3() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] expected = {
            {0, 0, 0},
            {0, 9, 0},
            {0, 0, 0}
        };
        int[][] result = MorphoElementaire.dilatation(image, 3);
        assertTrue(matricesEqual(expected, result));
    }

    @Test
    public void testDilatationByErosion() {
        int[][] image = {
            {0, 0, 0},
            {0, 255, 0},
            {0, 0, 0}
        };
        int[][] expected = {
            {0, 0, 0},
            {0, 255, 0},
            {0, 0, 0}
        };
        int[][] result = MorphoElementaire.dilatationByErosion(image, 3);
        assertTrue(matricesEqual(expected, result));
    }

    @Test
    public void testOuverture() {
        int[][] image = {
            {0, 0, 0},
            {0, 255, 0},
            {0, 0, 0}
        };
        int[][] expected = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        int[][] result = MorphoElementaire.ouverture(image, 3);
        assertTrue(matricesEqual(expected, result));
    }

    @Test
    public void testFermeture() {
        int[][] image = {
            {0, 0, 0},
            {0, 255, 0},
            {0, 0, 0}
        };
        int[][] expected = {
            {0, 0, 0},
            {0, 255, 0},
            {0, 0, 0}
        };
        int[][] result = MorphoElementaire.fermeture(image, 3);
        assertTrue(matricesEqual(expected, result));
    }
}
