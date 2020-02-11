package assignment2_refactored;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * AccessoryPricesTest.java
 * Tests the getters and copy constructor of AccessoryPrices Class
 */
public class AccessoryPricesTest {
  private AccessoryPrices accessoryPrices = new AccessoryPrices();

  @Test
  public void getMountPrice() {
    Assert.assertEquals((Double)15.0, accessoryPrices.getMountPrice());
  }

  @Test
  public void getDoorHandlesPrice() {
    Assert.assertEquals((Double)8.0, accessoryPrices.getDoorHandlesPrice());
  }

  @Test
  public void getDrawerHandlesPrice() {
    Assert.assertEquals((Double)4.0, accessoryPrices.getDrawerHandlesPrice());
  }

  @Test
  public void getDoorHingesPrice() {
    Assert.assertEquals((Double)1.0, accessoryPrices.getDoorHingesPrice());
  }

  @Test
  public void getRailsPrice() {
    Assert.assertEquals((Double)10.0, accessoryPrices.getRailsPrice());
  }

  @Test
  public void getFixAttachmentPrice() {
    Assert.assertEquals((Double)1.0, accessoryPrices.getFixAttachmentPrice());
  }

  @Test
  public void getCornerFeetPrice() {
    Assert.assertEquals((Double).50, accessoryPrices.getCornerFeetPrice());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCopyConstructor() {
    AccessoryPrices otherAccessoryPricesObj = null;
    accessoryPrices = new AccessoryPrices(otherAccessoryPricesObj);
  }
}