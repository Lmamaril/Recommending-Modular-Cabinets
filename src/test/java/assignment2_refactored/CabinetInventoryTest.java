package assignment2_refactored;
import assignment2_refactored.Enums.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the CabinetInventory Class for the following:
 * - Removes by color properly, height, shelf count, drawer count, and price properly.
 */
public class CabinetInventoryTest {

  private AbstractInventory testInventory = new CabinetInventory();
  private CustomerRequest defaultCustomerRequest;
  private List<Cabinet> defaultCabinetList = new ArrayList<>();
  private List<Cabinet> emptyCabinetList = new ArrayList<>();

  @Before
  public void setUp() throws Exception {
    defaultCustomerRequest = new CustomerRequest(72, 0, 0,
        0, "brown", "", 2000.0);
    AccessoryPrices accessoryPrices = new AccessoryPrices();
    Cabinet yossarianTest = new Cabinet("Yosarian", new Size(72, 72, 16),
        new Color[]{Color.BROWN, Color.BLACK, Color.BONE},
        350.00, new Mount(MountType.FLOOR, Feet.REQUIRED, Rails.STANDARD) , 7, 0, true, accessoryPrices);

    defaultCabinetList.add(yossarianTest);
    testInventory.setFurnitureList(defaultCabinetList);
  }

  /***************************************************************************************
   *  REMOVE CABINET FROM INVENTORY LIST IF CABINET HEIGHT DOESN'T FIT
   ****************************************************************************************/
  // NOTE: Default Test Cabinet is set at 72 inches

  /* Cabinet should not be removed since the height is valid */
  @Test
  public void checkHandlesMatchingHeightRequest() {
    defaultCustomerRequest.setMaxHeight(72);
    testInventory.filterInventory(defaultCustomerRequest);
    Assert.assertEquals(defaultCabinetList, testInventory.getFurnitureList());
  }
  /* Expect that cabinet will be removed if non-matching */
  @Test
  public void checkHandlesNonMatchingHeightRequest() {
    defaultCustomerRequest.setMaxHeight(18);
    testInventory.filterInventory(defaultCustomerRequest);
    Assert.assertEquals(emptyCabinetList, testInventory.getFurnitureList());
  }

  /* Expect that cabinet will not be removed if non-preference */
  @Test
  public void checkHandlesNoPreferenceHeightRequest() {
    defaultCustomerRequest.setMaxHeight(0);
    testInventory.filterInventory(defaultCustomerRequest);
    Assert.assertEquals(defaultCabinetList, testInventory.getFurnitureList());
  }

  /***************************************************************************************
   *  REMOVE CABINET FROM INVENTORY LIST IF NO COLORS AVAILABLE,
   *  IF COLOR IS AVAILABLE, KEEP CABINET, AND REMOVE UNWANTED COLORS.
   ****************************************************************************************/
  // NOTE: Default Test Cabinet Colors are set at Color.BROWN, Color.BLACK, Color.BONE //
  @Test
  public void checkHandlesAvailableColor () {
    defaultCustomerRequest.setColor("bone");
    testInventory.filterInventory(defaultCustomerRequest);
    Assert.assertEquals(defaultCabinetList, testInventory.getFurnitureList());
  }

  @Test
  public void checkHandlesNoAvailableColors () {
    defaultCustomerRequest.setColor("oxblood");
    testInventory.filterInventory(defaultCustomerRequest);
    Assert.assertEquals(emptyCabinetList, testInventory.getFurnitureList());
  }

  @Test
  public void checkHandlesNoPreferenceForColors () {
    defaultCustomerRequest.setColor("");
    testInventory.filterInventory(defaultCustomerRequest);
    Assert.assertEquals(defaultCabinetList, testInventory.getFurnitureList());
  }

  /***************************************************************************************
   *  REMOVE CABINET FROM INVENTORY LIST IF CABINET SHELF COUNT INSUFFICIENT
   ****************************************************************************************/
  // NOTE: Default Test Cabinet Shelf Count is set at 7  //

  /* Test result if customer has no preference for a cabinet shelf*/

  @Test
  public void checkHandlesMatchingShelfCount () {
    defaultCustomerRequest.setShelfCount(1);
    testInventory.filterInventory(defaultCustomerRequest);
    Assert.assertEquals(defaultCabinetList, testInventory.getFurnitureList());
  }

  @Test
  public void checkHandlesNonMatchingShelfCount () {
    defaultCustomerRequest.setShelfCount(8);
    testInventory.filterInventory(defaultCustomerRequest);
    Assert.assertEquals(emptyCabinetList, testInventory.getFurnitureList());
  }

  @Test
  public void checkHandlesNoPreferenceShelfCount () {
    defaultCustomerRequest.setShelfCount(0);
    testInventory.filterInventory(defaultCustomerRequest);
    Assert.assertEquals(defaultCabinetList, testInventory.getFurnitureList());
  }

  /***************************************************************************************
   *  REMOVE CABINET FROM INVENTORY LIST IF MOUNT TYPES DOES NOT MATCH
   ****************************************************************************************/
  // NOTE: Default Test Cabinet Mount is set as FLOOR
  @Test
  public void checkValidMount() {
    defaultCustomerRequest.setMount("floor");
    testInventory.filterInventory(defaultCustomerRequest);
    Assert.assertEquals(defaultCabinetList, testInventory.getFurnitureList());
  }

  @Test
  public void checkInValidMount() {
    defaultCustomerRequest.setMount("wall");
    testInventory.filterInventory(defaultCustomerRequest);
    Assert.assertEquals(emptyCabinetList, testInventory.getFurnitureList());
  }

  @Test
  public void checkNoPreferenceMount() {
    defaultCustomerRequest.setMount("");
    testInventory.filterInventory(defaultCustomerRequest);
    Assert.assertEquals(defaultCabinetList, testInventory.getFurnitureList());
  }

  /***************************************************************************************
   *  REMOVE CABINET FROM INVENTORY LIST IF CABINET PRICE IS TOO EXPENSIVE
   ****************************************************************************************/
  // NOTE: Default Test Price is set as $350
  @Test
  public void checkExpensivePrice() {
    defaultCustomerRequest.setBudget(300.00);
    testInventory.filterInventory(defaultCustomerRequest);
    Assert.assertEquals(emptyCabinetList, testInventory.getFurnitureList());
  }

  /***************************************************************************************
   *  REMOVE CABINET FROM INVENTORY LIST IF DRAWER COUNT IS INSUFFIENT
   ****************************************************************************************/
  // NOTE: Default Test DrawerCount is set at 0
  @Test
  public void checkInsufficientDrawer() {
    defaultCustomerRequest.setDrawerCount(10);
    testInventory.filterInventory(defaultCustomerRequest);
    Assert.assertEquals(emptyCabinetList, testInventory.getFurnitureList());
  }

}