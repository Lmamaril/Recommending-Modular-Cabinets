package assignment2_refactored;

import assignment2_refactored.Enums.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the CustomerRequest Lab
 * This tests the following:
 * - Tests handling incorrect input for setting maxheight, maxwidth, shelfcount , drawercount,
 *   color, mount, numOfCabinets, and budget
 * - Tests equals, hashcode, and equals
 */
public class CustomerRequestTest {

  private CustomerRequest noPreferenceRequest;
  private CustomerRequest validInputRequest1;
  private CustomerRequest validInputRequest2;
  private CustomerRequest copyValidInputRequest2;

  @Before
  public void setUp() throws Exception {
    noPreferenceRequest = new CustomerRequest(0, 0, 0, 0,
        "", "", 0.0);
    validInputRequest1 = new CustomerRequest(37, 40, 1,
        2, "oxblood", "floor", 2000.0 );
    validInputRequest2 = new CustomerRequest(18, 80, 4,
        2, "black", "wall", 2000.0 );
    copyValidInputRequest2 =  new CustomerRequest(18, 80, 4,
        2, "black", "wall", 2000.0 );

  }

  @Test
  public void getMaxHeight() {
    Assert.assertEquals((Integer) 0, noPreferenceRequest.getMaxHeight());
  }

  @Test (expected = IllegalArgumentException.class)
  public void setNegativeMaxHeight() {
    validInputRequest1.setMaxHeight(-1);
  }

  @Test
  public void getMaxWidth() {
    Assert.assertEquals((Integer)40, validInputRequest1.getMaxWidth());
  }

  @Test (expected = IllegalArgumentException.class)
  public void setNegativeMaxWidth() {
    validInputRequest1.setMaxWidth(-2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void setMaxWidthTooLarge() {
    validInputRequest1.setMaxWidth(500);
  }

  @Test
  public void testGetNumCabinets() {
    Assert.assertEquals((Integer)2, validInputRequest2.getNumOfCabinets());
    Assert.assertEquals((Integer)1, validInputRequest1.getNumOfCabinets());
  }

  @Test
  public void getShelfCount() {
    Assert.assertEquals((Integer)1, validInputRequest1.getShelfCount());
  }

  @Test (expected = IllegalArgumentException.class)
  public void setShelfCount() {
    noPreferenceRequest.setShelfCount(-7);
  }

  @Test
  public void getDrawerCount() {
    Assert.assertEquals((Integer)2, validInputRequest1.getDrawerCount());
  }

  @Test (expected = IllegalArgumentException.class)
  public void setDrawerCount() {
    validInputRequest1.setDrawerCount(-3);
  }

  @Test
  public void setColorSetAsBlack() {
    validInputRequest1.setColor("black");
    Assert.assertEquals(Color.BLACK, validInputRequest1.getColor() );
  }

  @Test
  public void setColorSetAsBone() {
    validInputRequest1.setColor("Bone");
    Assert.assertEquals(Color.BONE, validInputRequest1.getColor() );
  }

  @Test
  public void setColorAsOxblood() {
    validInputRequest1.setColor("OXBLOOD");
    Assert.assertEquals(Color.OXBLOOD, validInputRequest1.getColor() );
  }

  @Test
  public void setColorAsNoPreference() {
    validInputRequest1.setColor("");
    Assert.assertEquals(Color.NO_PREFERENCE, validInputRequest1.getColor() );
  }

  @Test
  public void setMountAsFloor() {
    validInputRequest1.setMount("floor");
    Assert.assertEquals(MountType.FLOOR, validInputRequest1.getMount());
  }

  @Test (expected = IllegalArgumentException.class)
  public void setColorAsInvalid() {
    CustomerRequest invalidColor = new CustomerRequest(37, 40, 1,
        2, "green", "floor", 2000.0 );
  }

  @Test
  public void setMountAsWall() {
    validInputRequest1.setMount("wall");
    Assert.assertEquals(MountType.WALL, validInputRequest1.getMount());
  }

  /* Test setting a mount to no preference (FLOOR_OR_WALL), Input must be empty String*/
  @Test
  public void setMountAsNoPreference() {
    validInputRequest2.setMount("");
    Assert.assertEquals(MountType.FLOOR_OR_WALL, validInputRequest2.getMount());
  }

  @Test (expected = IllegalArgumentException.class)
  public void setMountAsInvalidInput() {
    validInputRequest1.setMount("Ceiling");
  }

  @Test
  public void getBudget() {
    noPreferenceRequest.setBudget(520.0);
    Assert.assertEquals((Double)520.0, noPreferenceRequest.getBudget());
  }

  @Test (expected = IllegalArgumentException.class)
  public void setLowInvalidBudget() {
    validInputRequest2.setBudget(-5.0);
  }
  @Test (expected = IllegalArgumentException.class)
  public void setHighInvalidBudget() {
    validInputRequest2.setBudget(2000000.80);
  }

  @Test
  public void testEquals() {
    Assert.assertTrue(validInputRequest2.equals(copyValidInputRequest2));
  }

  @Test
  public void testHashcode() {
    Assert.assertEquals(validInputRequest2.hashCode(), copyValidInputRequest2.hashCode());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("Customer{maxHeight=37, maxWidth=40, numOfCabinets=1, "
        + "shelfCnt=1, drawerCnt=2, color='OXBLOOD', mount='FLOOR', budget=2000.0}", validInputRequest1.toString());
  }
}