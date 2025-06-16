package ImageProcessing.Lineaire;

import org.junit.Test;
import static org.junit.Assert.*;

public class FiltrageLineaireLocalTest {
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
    public void testFiltreMasqueConvolutionIdentite() {
        int[][] image = {
            {10, 20},
            {30, 40}
        };
        double[][] masque = {
            {1}
        };
        int[][] expected = {
            {10, 20},
            {30, 40}
        };
        int[][] result = FiltrageLineaireLocal.filtreMasqueConvolution(image, masque);
        assertTrue(matricesEqual(expected, result));
    }

    @Test
    public void testFiltreMasqueConvolutionMoyenneur3x3() {
        int[][] image = {
            {0, 0, 0},
            {0, 255, 0},
            {0, 0, 0}
        };
        double[][] masque = {
            {1.0/9, 1.0/9, 1.0/9},
            {1.0/9, 1.0/9, 1.0/9},
            {1.0/9, 1.0/9, 1.0/9}
        };
        int[][] expected = {
            {28, 28, 28},
            {28, 28, 28},
            {28, 28, 28}
        };
        int[][] result = FiltrageLineaireLocal.filtreMasqueConvolution(image, masque);
        assertTrue(matricesEqual(expected, result));
    }

    @Test
    public void testFiltreMoyenneur3x3() {
        int[][] image = {
            {0, 0, 0},
            {0, 255, 0},
            {0, 0, 0}
        };
        int[][] expected = {
            {28, 28, 28},
            {28, 28, 28},
            {28, 28, 28}
        };
        int[][] result = FiltrageLineaireLocal.filtreMoyenneur(image, 3);
        assertTrue(matricesEqual(expected, result));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFiltreMasqueConvolutionNullImage() {
        FiltrageLineaireLocal.filtreMasqueConvolution(null, new double[][]{{1}});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFiltreMasqueConvolutionNullMasque() {
        FiltrageLineaireLocal.filtreMasqueConvolution(new int[][]{{1}}, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFiltreMasqueConvolutionMasquePair() {
        FiltrageLineaireLocal.filtreMasqueConvolution(new int[][]{{1,2},{3,4}}, new double[][]{{1,1},{1,1}});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFiltreMoyenneurPair() {
        FiltrageLineaireLocal.filtreMoyenneur(new int[][]{{1,2},{3,4}}, 2);
    }
}
