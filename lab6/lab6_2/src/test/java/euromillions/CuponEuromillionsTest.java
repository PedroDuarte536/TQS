package euromillions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class CuponEuromillionsTest {
  
  private CuponEuromillions cuponEuromillions = new CuponEuromillions();

  @Test
  public void testFormat() {
    // Test format method
    assertEquals(cuponEuromillions.format(), "", "format as string: formatted string not as expected. ");

    cuponEuromillions.addDipToCuppon(new Dip());
    assertEquals(cuponEuromillions.format(), "Dip #1:N[] S[]\n", "format as string: formatted string not as expected. ");

    cuponEuromillions.addDipToCuppon(new Dip(new int[]{1,2,3,4,5}, new int[]{1,2}));
    assertEquals(cuponEuromillions.format(), "Dip #1:N[] S[]\nDip #2:N[  1  2  3  4  5] S[  1  2]\n", "format as string: formatted string not as expected. ");
  }

}
