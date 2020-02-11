package assignment2_refactored;

import assignment2_refactored.Enums.SizeType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * SizeTest.java
 * Tests the following:
 * - getters are working properly.
 * - equals, hashcode, and toString are working probably.
 */
public class SizeTest {
  private Size cabinetFront;
  private Size copyCabinetFront;
  private Size quarterCabinet;
  private Size halfCabinet;
  private Size wardrobeCabinet;


  @Before
  public void setUp() throws Exception {
    cabinetFront = new Size(72, 36);
    copyCabinetFront = new Size(72, 36);
    quarterCabinet = new Size(18, 36, 18);
    halfCabinet = new Size(36, 36, 18);
    wardrobeCabinet = new Size(72, 36, 18);
  }

  @Test
  public void getHeight() {
    Assert.assertEquals((Integer) 18, quarterCabinet.getHeight());
    Assert.assertEquals((Integer) 72, cabinetFront.getHeight());
  }

  @Test
  public void getWidth() {
    Assert.assertEquals((Integer)36, quarterCabinet.getWidth());
    Assert.assertEquals((Integer)36, cabinetFront.getWidth());
  }

  @Test
  public void getDepth() {
    Assert.assertEquals((Integer)18, quarterCabinet.getDepth());
    Assert.assertEquals((Integer)0, cabinetFront.getDepth());
  }

  @Test
  public void getSizeName() {
    Assert.assertEquals(SizeType.WARDDROBE, wardrobeCabinet.getSizeName());
    Assert.assertEquals(SizeType.HALF, halfCabinet.getSizeName());
    Assert.assertEquals(SizeType.QUARTER, quarterCabinet.getSizeName());
  }

  @Test
  public void testEqualsSameObj() {
    Assert.assertTrue(cabinetFront.equals(copyCabinetFront));
  }

  @Test
  public void testToString() {
    Assert.assertEquals("Size{sizeName=QUARTER, height=18, width=36, depth=18}",
        quarterCabinet.toString());
    Assert.assertEquals("Size{sizeName=WARDDROBE, height=72, width=36, depth=0}",
        cabinetFront.toString());
  }
}