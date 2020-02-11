package assignment2_refactored;

import assignment2_refactored.Enums.AccessoryName;
import assignment2_refactored.Enums.CabinetFrontType;
import assignment2_refactored.Enums.Color;
import assignment2_refactored.Enums.Feet;
import assignment2_refactored.Enums.MountType;
import assignment2_refactored.Enums.Rails;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * RecommendationMakerTest.java
 * Tests RecommendationMaker Class for the following:
 * - the makeRecommendation() fucnction is working properly and creating a json file with correct output.
 * - The getter for a recommendationList is correct.
 * - Tests for correctness for hashcode, equals,and toString
 */
public class RecommendationMakerTest {

  private RecommendationMaker recMaker;
  private RecommendationMaker copyRecMaker;
  private Recommendation expectedRecommendation;
  private List<Recommendation> expectedRecList;

  @Before
  public void setUp() throws Exception {
    CustomerRequest newCustomer = new CustomerRequest(72,40,0,0,"oxblood", "", 500.00);
    AccessoryPrices accessoryPrices = new AccessoryPrices();
    CabinetInventory cabinetInventory = new CabinetInventory();
    CabinetFrontInventory cabinetFrontInventory = new CabinetFrontInventory();
    cabinetInventory.setToDefaultFurnitureValues();
    cabinetFrontInventory.setToDefaultFurnitureValues();
    recMaker = new RecommendationMaker(cabinetInventory, cabinetFrontInventory);
    recMaker.makeRecommendation(newCustomer);
    Cabinet expectedCab = new Cabinet("Yosarian", new Size(72, 36, 16),
        new Color[]{Color.OXBLOOD}, 500.00,
        new Mount(MountType.FLOOR, Feet.OPTIONAL, Rails.NOT_REQUIRED), 7, 0, true, accessoryPrices);
    List <Accessory> cabAccessories = new ArrayList<>();
    cabAccessories.add(new Accessory(AccessoryName.FEET, 4, 2.0));
    cabAccessories.add(new Accessory(AccessoryName.WALL_FIXTURE, 1, 1.0));
    expectedCab.setAccessories(cabAccessories);
    List <Accessory> frontAccessories = new ArrayList<>();
    frontAccessories.add(new Accessory(AccessoryName.DOOR_HINGE, 1, 1.0));
    CabinetFront expectedCabFront = new CabinetFront("Belacqua", new Size(72, 36), new Color[]{Color.OXBLOOD}, 280.00, CabinetFrontType.DOOR, true, accessoryPrices);
    expectedCabFront.setAccessories(frontAccessories);
    expectedRecommendation = new Recommendation(expectedCab, expectedCabFront, newCustomer);
    expectedRecList = new ArrayList<>();
    expectedRecList.add(expectedRecommendation);

    copyRecMaker = new RecommendationMaker(cabinetInventory, cabinetFrontInventory);
    copyRecMaker.makeRecommendation(newCustomer);
  }

  @Test
  public void makeRecommendation() {
    Assert.assertEquals(expectedRecList, recMaker.getRecommendationList());
  }

  @Test
  public void getRecommendationList() {
    Assert.assertEquals(expectedRecList, recMaker.getRecommendationList());
  }

  @Test
  public void equals() {
    Assert.assertTrue(recMaker.equals(copyRecMaker));
  }

  @Test
  public void testHashcode() {
    Assert.assertEquals(recMaker.hashCode(), copyRecMaker.hashCode());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("RecommendationMaker{inventory=[Cabinet List{\n"
        + "Furniture{style='Yosarian', furnitureSize=Size{sizeName=WARDDROBE, height=72, width=36, depth=16}, "
        + "colors=[OXBLOOD], furniturePrice=500.0, accessories=[Accessory{name='FEET'quantity='4', price=2.0}, "
        + "Accessory{name='WALL_FIXTURE'quantity='1', price=1.0}]}Cabinet{mountDetails=Mount{cabMount=FLOOR, "
        + "feetRequirement=OPTIONAL, railsRequirement=NOT_REQUIRED}, maxShelfCount=7, maxDrawerCount=0, wallFixRequired=true}\n"
        + "}, Cabinet List{\n"
        + "Furniture{style='Belacqua', furnitureSize=Size{sizeName=WARDDROBE, height=72, width=36, "
        + "depth=0}, colors=[OXBLOOD], furniturePrice=280.0, "
        + "accessories=[Accessory{name='DOOR_HINGE'quantity='1', price=1.0}]}"
        + "CabinetFront{type='DOOR', handleIncluded=true}Furniture{style='Belacqua', "
        + "furnitureSize=Size{sizeName=HALF, height=36, width=36, depth=0}, colors=[OXBLOOD], "
        + "furniturePrice=100.0, accessories=[]}CabinetFront{type='DOOR', handleIncluded=true}Furniture{style='Belacqua', "
        + "furnitureSize=Size{sizeName=QUARTER, height=18, width=36, depth=0}, colors=[OXBLOOD], "
        + "furniturePrice=500.0, accessories=[]}CabinetFront{type='DOOR', handleIncluded=true}Furniture{style='Gaga', "
        + "furnitureSize=Size{sizeName=HALF, height=36, width=36, depth=0}, colors=[OXBLOOD], furniturePrice=80.5, "
        + "accessories=[]}CabinetFront{type='DOOR', handleIncluded=false}Furniture{style='Gaga', "
        + "furnitureSize=Size{sizeName=QUARTER, height=18, width=36, depth=0}, colors=[OXBLOOD], "
        + "furniturePrice=230.0, accessories=[]}CabinetFront{type='DOOR', handleIncluded=false}Furniture{style='Gaga', "
        + "furnitureSize=Size{sizeName=QUARTER, height=18, width=36, depth=0}, colors=[OXBLOOD], furniturePrice=120.0, "
        + "accessories=[]}CabinetFront{type='DRAWER_FRONT', handleIncluded=false}}], "
        + "recommendationList=[Recommendation{cabinetStyle='Yosarian', cabinetSizeName='WARDDROBE', "
        + "cabinetHeight=72, cabinetWidth=36, cabinetDepth=16, cabinetNum=1, cabinetColor='OXBLOOD', "
        + "cabinetPrice=500.0, shelfCount=7, drawerCount=0, frontType='DOOR', frontStyle='Belacqua', "
        + "frontSizeName='WARDDROBE', frontHeight='72', frontWidth='36', frontColor='OXBLOOD', frontPrice=280.0, "
        + "totalSetPrice=784.0, accessories=[Accessory{name='FEET'quantity='4', price=2.0}, "
        + "Accessory{name='WALL_FIXTURE'quantity='1', price=1.0}, "
        + "Accessory{name='DOOR_HINGE'quantity='1', price=1.0}]}]}", recMaker.toString());
  }

}