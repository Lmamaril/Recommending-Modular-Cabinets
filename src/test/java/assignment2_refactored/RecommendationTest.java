package assignment2_refactored;

import assignment2_refactored.Enums.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * RecommendationTest.java
 * Tests the Recommendation class for the following:
 * - compareto() works properly. Recommendations compared to eachother will be based on total price.
 * - equals, hashcode, toString checked.
 */
public class RecommendationTest {
  private Recommendation testRecommendation;
  private Recommendation copyTestRecommendation;
  private Cabinet expensiveLuthien;
  private Recommendation expensiveRecommendation;
  @Before
  public void setUp() throws Exception {
    CustomerRequest validInputRequest = new CustomerRequest(38, 36, 0,
        0, "bone", "", 2000.0 );
    AccessoryPrices accessoryPrices = new AccessoryPrices();
    Cabinet luthien = new Cabinet("Luthien", new Size(18, 36, 18),
        new Color[]{Color.BLACK, Color.BONE}, 100.00, new Mount (MountType.FLOOR_OR_WALL,
        Feet.OPTIONAL, Rails.STANDARD), 1, 0, false, accessoryPrices);
    CabinetFront belacqua = new CabinetFront("Belacqua", new Size(18, 36), new Color[]{Color.BONE, Color.OXBLOOD}, 500.00, CabinetFrontType.DOOR, true, accessoryPrices);
    testRecommendation = new Recommendation(luthien, belacqua, validInputRequest);
    copyTestRecommendation = new Recommendation(luthien, belacqua, validInputRequest);

    CustomerRequest expensiveRequest = new CustomerRequest(38, 36, 0,
        0, "bone", "", 2000.0 );
    expensiveLuthien = new Cabinet("Luthien", new Size(18, 36, 18),
        new Color[]{Color.BLACK, Color.BONE}, 4500.00, new Mount (MountType.FLOOR_OR_WALL,
        Feet.OPTIONAL, Rails.STANDARD), 1, 0, false, accessoryPrices);
    CabinetFront expensiveBelacqua = new CabinetFront("Belacqua", new Size(18, 36), new Color[]{Color.BONE, Color.OXBLOOD}, 4500.00, CabinetFrontType.DOOR, true, accessoryPrices);
    expensiveRecommendation = new Recommendation(expensiveLuthien, expensiveBelacqua, expensiveRequest);

  }

  @Test
  public void compareToExpectHigherThan() {
    Assert.assertEquals(1, expensiveRecommendation.compareTo(testRecommendation));
  }

  @Test
  public void compareToExpectLowerThan() {
    Assert.assertEquals(-1, testRecommendation.compareTo(expensiveRecommendation));
  }

  @Test
  public void compareToExpectEqual() {
    Assert.assertEquals(0, testRecommendation.compareTo(copyTestRecommendation));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCompareToWithNull() {
    testRecommendation.compareTo(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCompareToWithDiffObjType(){
    testRecommendation.compareTo(expensiveLuthien);
  }

  @Test
  public void testCompareToWithDiffCost(){
    testRecommendation.compareTo(expensiveRecommendation);
  }

  @Test
  public void testValidEqualsWithCopy() {
    Assert.assertTrue(testRecommendation.equals(copyTestRecommendation));
  }

  @Test
  public void testValidEqualsWithSelf() {
    Assert.assertTrue(testRecommendation.equals(testRecommendation));
  }

  @Test
  public void testValidEqualsWithNull() {
    Assert.assertFalse(testRecommendation.equals(null));
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(testRecommendation.hashCode(), copyTestRecommendation.hashCode());
  }

  // Testing toString. It is ok that accessories are null because I did not call the addAccessories
  // function.
  @Test
  public void testToString() {
    Assert.assertEquals("Recommendation{cabinetStyle='Luthien', cabinetSizeName='QUARTER', "
        + "cabinetHeight=18, cabinetWidth=36, cabinetDepth=18, cabinetNum=1, cabinetColor='BLACK', "
        + "cabinetPrice=100.0, shelfCount=1, drawerCount=0, frontType='DOOR', frontStyle='Belacqua', "
        + "frontSizeName='QUARTER', frontHeight='18', frontWidth='36', frontColor='BONE', "
        + "frontPrice=500.0, totalSetPrice=600.0, accessories=[]}", testRecommendation.toString());
  }
}