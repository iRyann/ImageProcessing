package ImageProcessing.NonLineaire;

import org.junit.Test;
import static org.junit.Assert.*;

public class MorphoComplexeTest {
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
    public void testDilatationGeodesique() {
        int[][] image = {
            {0, 0, 0},
            {0, 255, 0},
            {0, 0, 0}
        };
        int[][] masque = {
            {0, 255, 0},
            {255, 255, 255},
            {0, 255, 0}
        };
        int[][] expected = {
            {0, 255, 0},
            {255, 255, 255},
            {0, 255, 0}
        };
        int[][] result = MorphoComplexe.dilatationGeodesique(image, masque, 1);
        assertTrue(matricesEqual(expected, result));
    }

    @Test
    public void testReconstructionGeodesique() {
        int[][] image = {
            {0, 0, 0},
            {0, 255, 0},
            {0, 0, 0}
        };
        int[][] masque = {
            {0, 255, 0},
            {255, 255, 255},
            {0, 255, 0}
        };
        int[][] expected = {
            {0, 255, 0},
            {255, 255, 255},
            {0, 255, 0}
        };
        int[][] result = MorphoComplexe.reconstructionGeodesique(image, masque);
        assertTrue(matricesEqual(expected, result));
    }

    @Test
    public void testFiltreMedian() {
        int[][] image = {
            {1, 2, 3},
            {4, 100, 6},
            {7, 8, 9}
        };
        int[][] expected = {
            {4, 4, 6},
            {7, 6, 8},
            {8, 7, 9}
        };
        int[][] result = MorphoComplexe.filtreMedian(image, 3);
        assertTrue(matricesEqual(expected, result));
    }



    @Test(expected = IllegalArgumentException.class)
    public void testDilatationGeodesiqueInvalid() {
        MorphoComplexe.dilatationGeodesique(null, null, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFiltreMedianInvalid() {
        MorphoComplexe.filtreMedian(null, 2);
    }
}
