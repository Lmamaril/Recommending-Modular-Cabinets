package assignment2_refactored;

import assignment2_refactored.Enums.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * CabinetFrontTest.java
 * Tests the accessories functions
 * Tests getter for the frontType (ie. Door or Drawer Front) and Handle Included information.
 * Tests toString, hashcode, and equals.
 */
public class CabinetFrontTest {
  private CustomerRequest defaultCustomerRequest;
  private CabinetFront testDoor;
  private CabinetFront copyTestDoor;
  private CabinetFront testDrawerFront;
  private CabinetFront nullDoor = null;

  @Before
  public void setUp() throws Exception {
    defaultCustomerRequest = new CustomerRequest(72, 40, 1,
        1, "bone", "floor", 2000.0 );
    AccessoryPrices accessoryPrices = new AccessoryPrices();
    Color[] sampleColorArray = new Color[] {Color.BLACK, Color.BROWN};
    testDoor = new CabinetFront("Slothrop", new Size(72, 36), sampleColorArray,
        150.00, CabinetFrontType.DOOR, false, accessoryPrices);
    copyTestDoor = new CabinetFront("Slothrop", new Size(72, 36), sampleColorArray,
        150.00, CabinetFrontType.DOOR, false, accessoryPrices);
    testDrawerFront = new CabinetFront("Slothrop", new Size(60, 36), sampleColorArray,
        150.00, CabinetFrontType.DRAWER_FRONT, false, accessoryPrices);
  }

  @Test
  public void getFrontType() {
    Assert.assertEquals(CabinetFrontType.DOOR, testDoor.getFrontType() );
  }

  @Test
  public void getHandleIncluded() {
    Assert.assertEquals(false,testDoor.getHandleIncluded());
  }

  @Test
  public void addDoorAccessories() {
    testDoor.addAccessories(defaultCustomerRequest);
    List<Accessory> doorFront = new ArrayList<>();
    Accessory doorHandle = new Accessory(AccessoryName.DOOR_HANDLE, 1, 8.0);
    Accessory doorHinge= new Accessory(AccessoryName.DOOR_HINGE, 1, 1.0);
    doorFront.add(doorHinge);
    doorFront.add(doorHandle);
    Assert.assertEquals(doorFront, testDoor.getAccessories());
  }

  @Test
  public void addDrawerFrontAccessories() {
    testDrawerFront.addAccessories(defaultCustomerRequest);
    List<Accessory> drawerFront = new ArrayList<>();
    Accessory drawerHandle = new Accessory(AccessoryName.DRAWER_HANDLE, 1, 4.0);
    drawerFront.add(drawerHandle);
    Assert.assertEquals(drawerFront,testDrawerFront.getAccessories());
  }

  @Test
  public void testEquals() {
    Assert.assertTrue(testDoor.equals(copyTestDoor));
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(testDoor.hashCode(), copyTestDoor.hashCode());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("Furniture{style='Slothrop', furnitureSize=Size{sizeName=WARDDROBE, "
        + "height=72, width=36, depth=0}, colors=[BLACK, BROWN], furniturePrice=150.0, "
        + "accessories=[]}CabinetFront{type='DOOR', handleIncluded=false}", testDoor.toString());
  }
}