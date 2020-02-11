package assignment2_refactored;

import assignment2_refactored.Enums.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**\
 * Tests the Cabinet Class.
 * Tests for the following:
 * - Tests getters Mount, shelf, drawer, and wallFixRequirement
 * - Adds accessories properly
 * - IsFurnitureMatch is correctly giving True when the Cabinet front matches height and color.
 * - Tests equals, hashcode, and toString
 */
public class CabinetTest {
  private CustomerRequest request;
  private AccessoryPrices accessoryPrices = new AccessoryPrices();
  private Cabinet testCabinet;
  private Cabinet testCabinet2;
  private Cabinet copyTestCabinet;
  private CabinetFront testMatchingDoor;
  private CabinetFront nonMatchDoor;
  List<Color> colorList;
  List<Accessory> accessoryList = new ArrayList<>();

  @Before
  public void setUp() throws Exception {
    request = new CustomerRequest(37, 40, 1,
        2, "oxblood", "floor", 2000.0 );
    testCabinet = new Cabinet("Atreides", new Size(72, 36, 16),
        new Color[]{Color.BROWN},
        75.00,
        new Mount(MountType.FLOOR_OR_WALL, Feet.OPTIONAL, Rails.STYLE_SPECIFIC), 1, 0, true, accessoryPrices);
    copyTestCabinet = new Cabinet("Atreides", new Size(72, 36, 16),
        new Color[]{Color.BROWN},
        75.00,
        new Mount(MountType.FLOOR_OR_WALL, Feet.OPTIONAL, Rails.STYLE_SPECIFIC), 1, 0, true, accessoryPrices);
    colorList = new ArrayList<>();
    testCabinet2 = new Cabinet("Luthien", new Size(72, 100, 16),
        colorList, 500.00,
        new Mount(MountType.FLOOR_OR_WALL, Feet.OPTIONAL, Rails.STYLE_SPECIFIC), 1, 0, true, accessoryPrices);
    testMatchingDoor = new CabinetFront("Slothrop", new Size(72, 36), new Color[] {Color.BROWN},
        150.00, CabinetFrontType.DOOR, false, accessoryPrices);
    nonMatchDoor = new CabinetFront("Slothrop", new Size(18, 36), new Color[] {Color.BROWN},
        150.00, CabinetFrontType.DOOR, false, accessoryPrices);
    accessoryList.add(new Accessory(AccessoryName.FEET, 4, 2.00));
    accessoryList.add(new Accessory(AccessoryName.WALL_FIXTURE, 1, 1.0));
    accessoryList.add(new Accessory(AccessoryName.RAILS_ATREIDES,1, 10.0));

  }

  @Test
  public void getMountDetails() {
    Assert.assertEquals(new Mount(MountType.FLOOR_OR_WALL, Feet.OPTIONAL, Rails.STYLE_SPECIFIC), testCabinet.getMountDetails());
  }

  @Test
  public void getTestColor() {
    Assert.assertEquals(colorList, testCabinet2.getColors());
  }

  @Test
  public void getMaxShelfCount() {
    Assert.assertEquals((Integer)1, testCabinet.getMaxShelfCount());
  }

  @Test
  public void getMaxDrawerCount() {
    Assert.assertEquals((Integer)0, testCabinet.getMaxDrawerCount());
  }

  @Test
  public void getWallFixRequired() {
    Assert.assertEquals(true, testCabinet.getWallFixRequired());
  }

  @Test
  public void addAccessories() {
    testCabinet.addAccessories(request);
    Assert.assertEquals(accessoryList, testCabinet.getAccessories());
  }

  @Test
  public void isFurnitureMatch() {
    Assert.assertTrue(testCabinet.isFurnitureMatch(testMatchingDoor));
  }

  @Test
  public void isNotFurnitureMatch() {
    Assert.assertFalse(testCabinet.isFurnitureMatch(nonMatchDoor));
  }

  @Test
  public void testEqualsSameObject() {
    Assert.assertTrue(testCabinet.equals(copyTestCabinet));
  }

  @Test
  public void testEqualsDiffObject() { Assert.assertFalse(testCabinet.equals(nonMatchDoor));}

  @Test
  public void testEqualsNullObject() {
    nonMatchDoor = null;
    Assert.assertFalse(testCabinet.equals(nonMatchDoor));}

  @Test
  public void testHashCode() {
    Assert.assertEquals(testCabinet.hashCode(), copyTestCabinet.hashCode());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("Furniture{style='Atreides', "
        + "furnitureSize=Size{sizeName=WARDDROBE, height=72, width=36, depth=16}, colors=[BROWN], "
        + "furniturePrice=75.0, accessories=[]}Cabinet{mountDetails=Mount{cabMount=FLOOR_OR_WALL, "
        + "feetRequirement=OPTIONAL, railsRequirement=STYLE_SPECIFIC}, maxShelfCount=1, maxDrawerCount=0, "
        + "wallFixRequired=true}\n", testCabinet.toString());
  }
}