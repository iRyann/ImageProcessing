package ImageProcessing.Seuillage;

import org.junit.Test;
import static org.junit.Assert.*;

public class SeuillageTest {
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
    public void testSeuillageSimple() {
        int[][] image = {
            {10, 200},
            {150, 50}
        };
        int[][] expected = {
            {255, 0},
            {0, 255}
        };
        int[][] result = Seuillage.seuillageSimple(image, 100);
        assertTrue(matricesEqual(expected, result));
    }

    @Test
    public void testSeuillageDouble() {
        int[][] image = {
            {10, 100, 200}
        };
        int[][] expected = {
            {0, 128, 255}
        };
        int[][] result = Seuillage.seuillageDouble(image, 50, 150);
        assertTrue(matricesEqual(expected, result));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSeuillageDoubleInvalid() {
        Seuillage.seuillageDouble(new int[][]{{1}}, 10, 5);
    }

    @Test
    public void testSeuillageAutomatique() {
        int[][] image = {
            {10, 200},
            {150, 50}
        };
        int[][] result = Seuillage.seuillageAutomatique(image);
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(2, result[0].length);
    }

    @Test
    public void testSeuillageAutomatiqueSafe() {
        int[][] image = {
            {10, 200},
            {150, 50}
        };
        int[][] result = Seuillage.seuillageAutomatiqueSafe(image);
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(2, result[0].length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSeuillageAutomatiqueSafeNull() {
        Seuillage.seuillageAutomatiqueSafe(null);
    }
}
