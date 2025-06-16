package ImageProcessing.Lineaire;

import org.junit.Test;
import static org.junit.Assert.*;

public class GenericTest {
    @Test
    public void testKindImageBinary() {
        int[][] img = {
            {0, 1},
            {1, 0}
        };
        assertEquals(Generic.TypeImage.BINARY, Generic.kindImage(img));
    }

    @Test
    public void testKindImageGray() {
        int[][] img = {
            {0, 2},
            {1, 0}
        };
        assertEquals(Generic.TypeImage.GRAY, Generic.kindImage(img));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKindImageNull() {
        Generic.kindImage(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKindImageEmpty() {
        Generic.kindImage(new int[0][0]);
    }

    @Test
    public void testInverseGray() {
        int[][] img = {
            {0, 128},
            {255, 10}
        };
        int[][] inv = Generic.inverse(img);
        assertArrayEquals(new int[]{255, 127}, inv[0]);
        assertArrayEquals(new int[]{0, 245}, inv[1]);
    }

    @Test
    public void testInverseBinary() {
        int[][] img = {
            {0, 1},
            {1, 0}
        };
        int[][] inv = Generic.inverse(img);
        assertArrayEquals(new int[]{1, 0}, inv[0]);
        assertArrayEquals(new int[]{0, 1}, inv[1]);
    }

    @Test
    public void testExtendImageZero() {
        int[][] img = {
            {1, 2},
            {3, 4}
        };
        int[][] ext = Generic.extendImage(img, 1, "zero");
        assertEquals(4, ext.length);
        assertEquals(4, ext[0].length);
        assertEquals(0, ext[0][0]);
        assertEquals(1, ext[1][1]);
        assertEquals(4, ext[2][2]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExtendImageNegativeBorder() {
        int[][] img = {
            {1, 2},
            {3, 4}
        };
        Generic.extendImage(img, -1, "zero");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExtendImageInvalidType() {
        int[][] img = {
            {1, 2},
            {3, 4}
        };
        Generic.extendImage(img, 1, "invalid");
    }

    @Test
    public void testExtendImageZeroExplicit() {
        int[][] input = {
            {1, 2},
            {3, 4}
        };
        int[][] expected = {
            {0, 0, 0, 0},
            {0, 1, 2, 0},
            {0, 3, 4, 0},
            {0, 0, 0, 0}
        };
        int[][] result = Generic.extendImage(input, 1, "zero");
        assertTrue(matricesEqual(expected, result));
    }

    @Test
    public void testExtendImageCopyMirror() {
        int[][] input = {
            {1, 2},
            {3, 4}
        };
        int[][] expected = {
            {4, 3, 4, 3},
            {2, 1, 2, 1},
            {4, 3, 4, 3},
            {2, 1, 2, 1}
        };
        int[][] result = Generic.extendImage(input, 1, "copy");
        assertTrue(matricesEqual(expected, result));
    }

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
}
