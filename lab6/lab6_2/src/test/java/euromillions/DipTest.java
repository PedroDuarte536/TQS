/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package euromillions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
public class DipTest {

    private Dip instance;


    public DipTest() {
    }

    @BeforeEach
    public void setUp() {
        instance = new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 2});
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }


    @Test
    public void testConstructorFromBadArrays() {
        // todo: instantiate a dip passing valid or invalid arrays
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] { 1, 2 }, new int[] { 1, 2, 3 }));
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] { 1, 2 }, new int[] { 1, 2 }));
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] { 1, 2, 3, 4, 5 }, new int[] { 1, 2, 3 }));

        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] { 1, 2, 3, 4, 5 }, new int[] { 1, 13 }));
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] { 1, 2, 3, 4, 5 }, new int[] { 1, 0 }));

        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] { 1, 2, 3, 4, 51 }, new int[] { 1, 1 }));
        assertThrows(IllegalArgumentException.class, () -> new Dip(new int[] { 0, 2, 3, 4, 5 }, new int[] { 1, 1 }));

        new Dip(new int[] { 1, 2, 3, 4, 50 }, new int[] { 1, 12 });
    }

    @Test
    public void testFormat() {
        // note: correct the implementation of the format(), not the test...
        String result = instance.format();
        assertEquals("N[ 10 20 30 40 50] S[  1  2]", result, "format as string: formatted string not as expected. ");
    }

}
