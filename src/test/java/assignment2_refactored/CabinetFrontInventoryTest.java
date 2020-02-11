package assignment2_refactored;

import assignment2_refactored.Enums.CabinetFrontType;
import assignment2_refactored.Enums.Color;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * CabinetFrontInventory.java
 * Tests that the CabinetFrontInventory test is working properly including:
 * - Cabinet front Filter for height and color are working properly.
 */
public class CabinetFrontInventoryTest {
  private CustomerRequest defaultRequest;
  private AbstractInventory testFrontInventory;
  private List<AbstractFurniture> defaultCabFrontList;
  private List<CabinetFront> emptyCabFrontList;
  private CabinetFrontInventory differentTestFrontInventory;

  @Before
  public void setUp() throws Exception {
    AccessoryPrices accessoryPrices = new AccessoryPrices();
    defaultRequest = new CustomerRequest(36, 150, 1,
        0, "black", "floor", 2000.0 );
    defaultCabFrontList  = new ArrayList<>();
    CabinetFront testCabFront = new CabinetFront("Gaga", new Size(18, 36),
        new Color[] {Color.OXBLOOD, Color.BLACK}, 120.50, CabinetFrontType.DRAWER_FRONT,
        false, accessoryPrices);
    defaultCabFrontList.add(testCabFront);

    testFrontInventory = new CabinetFrontInventory();
    testFrontInventory.setFurnitureList(defaultCabFrontList);

    emptyCabFrontList = new ArrayList<>();

  }
  @Test
  public void testGetFurnitureList() {
    Assert.assertEquals(defaultCabFrontList, testFrontInventory.getFurnitureList());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCopyConstructor() {
    differentTestFrontInventory = null;
    testFrontInventory = new CabinetFrontInventory(differentTestFrontInventory);
  }

  /***************************************************************************************
   *  REMOVE CABINET FRONT FROM INVENTORY LIST IF CABFRONT HEIGHT DOESN'T FIT
   ****************************************************************************************/
  // NOTE: Default Test Cabinet is set at 18 inches

  /* Cabinet should not be removed since the height is valid */
  @Test
  public void checkValidHeightRequest() {
    defaultRequest.setMaxHeight(20);
    testFrontInventory.filterInventory(defaultRequest);
    Assert.assertEquals(defaultCabFrontList, testFrontInventory.getFurnitureList());
  }
  /* Expect that cabinet will be removed if non-matching */
  @Test
  public void checkHandlesNonMatchingHeightRequest() {
    defaultRequest.setMaxHeight(16);
    testFrontInventory.filterInventory(defaultRequest);
    Assert.assertEquals(emptyCabFrontList, testFrontInventory.getFurnitureList());
  }

  /* Expect that cabinet will not be removed if non-preference */
  @Test
  public void checkHandlesNoPreferenceHeightRequest() {
    defaultRequest.setMaxHeight(0);
    testFrontInventory.filterInventory(defaultRequest);
    Assert.assertEquals(defaultCabFrontList, testFrontInventory.getFurnitureList());
  }
  /***************************************************************************************
   *  REMOVE CABINET FROM INVENTORY LIST IF NO COLORS AVAILABLE,
   *  IF COLOR IS AVAILABLE, KEEP CABINET, AND REMOVE UNWANTED COLORS.
   ****************************************************************************************/
  // NOTE: Default Test Cabinet Colors are set at Color.OXBLOOD, Color.BLACK//
  // Checks that the Cab Front is not removed when there is an available color.
  @Test
  public void checkHandlesAvailColor () {
    defaultRequest.setColor("black");
    testFrontInventory.filterInventory(defaultRequest);
    Assert.assertEquals(defaultCabFrontList, testFrontInventory.getFurnitureList());
  }
  // Checks that the Cabinet Front is removed when no colors available.
  @Test
  public void checkHandlesNoColors () {
    defaultRequest.setColor("bone");
    testFrontInventory.filterInventory(defaultRequest);
    Assert.assertEquals(emptyCabFrontList, testFrontInventory.getFurnitureList());
  }

  // Checks that the Cabinet front is not removed for a no preference request.
  @Test
  public void checkHandlesNoColorPref () {
    defaultRequest.setColor("");
    testFrontInventory.filterInventory(defaultRequest);
    Assert.assertEquals(defaultCabFrontList, testFrontInventory.getFurnitureList());
  }
}