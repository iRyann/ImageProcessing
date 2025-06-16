package ImageProcessing.Contours;

import org.junit.Test;
import static org.junit.Assert.*;

public class ContoursLineaireTest {
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
    public void testGradientPrewittHorizontal() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] result = ContoursLineaire.gradientPrewitt(image, 0);
        assertNotNull(result);
        assertEquals(3, result.length);
        assertEquals(3, result[0].length);
    }

    @Test
    public void testGradientPrewittVertical() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] result = ContoursLineaire.gradientPrewitt(image, 1);
        assertNotNull(result);
        assertEquals(3, result.length);
        assertEquals(3, result[0].length);
    }

    @Test
    public void testGradientSobelHorizontal() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] result = ContoursLineaire.gradientSobel(image, 0);
        assertNotNull(result);
        assertEquals(3, result.length);
        assertEquals(3, result[0].length);
    }

    @Test
    public void testGradientSobelVertical() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] result = ContoursLineaire.gradientSobel(image, 1);
        assertNotNull(result);
        assertEquals(3, result.length);
        assertEquals(3, result[0].length);
    }

    @Test
    public void testLaplacien4() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] result = ContoursLineaire.laplacien4(image);
        assertNotNull(result);
        assertEquals(3, result.length);
        assertEquals(3, result[0].length);
    }

    @Test
    public void testLaplacien8() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] result = ContoursLineaire.laplacien8(image);
        assertNotNull(result);
        assertEquals(3, result.length);
        assertEquals(3, result[0].length);
    }

    @Test
    public void testGradientPrewittHorizontalExpected() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] expected = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        }; // Le Prewitt horizontal sur cette image donne 0 partout (bord copy)
        int[][] result = ContoursLineaire.gradientPrewitt(image, 0);
        assertTrue(matricesEqual(expected, result));
    }

    @Test
    public void testGradientPrewittVerticalExpected() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] expected = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        }; // Idem, Prewitt vertical sur cette image donne 0 partout
        int[][] result = ContoursLineaire.gradientPrewitt(image, 1);
        assertTrue(matricesEqual(expected, result));
    }

    @Test
    public void testGradientSobelHorizontalExpected() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] expected = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        }; // Sobel horizontal sur cette image donne 0 partout
        int[][] result = ContoursLineaire.gradientSobel(image, 0);
        assertTrue(matricesEqual(expected, result));
    }

    @Test
    public void testGradientSobelVerticalExpected() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] expected = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        }; // Sobel vertical sur cette image donne 0 partout
        int[][] result = ContoursLineaire.gradientSobel(image, 1);
        assertTrue(matricesEqual(expected, result));
    }

    @Test
    public void testLaplacien4Expected() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] expected = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        }; // Laplacien 4 sur cette image donne 0 partout
        int[][] result = ContoursLineaire.laplacien4(image);
        assertTrue(matricesEqual(expected, result));
    }

    @Test
    public void testLaplacien8Expected() {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] expected = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        }; // Laplacien 8 sur cette image donne 0 partout
        int[][] result = ContoursLineaire.laplacien8(image);
        assertTrue(matricesEqual(expected, result));
    }
}
