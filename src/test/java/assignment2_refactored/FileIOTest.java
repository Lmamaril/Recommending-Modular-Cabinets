package assignment2_refactored;


import assignment2_refactored.Enums.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * FileIOTest.java
 * Tests the FileIO Class for the following:
 * - Checks if the file handles no file available situations properly
 * - Checks if the String of information to be printed is correct.
 */
public class FileIOTest {
  private FileIO testFileIO;
  private List<Recommendation> testRecommendationList;

  @Before
  public void setUp() throws Exception {
    testFileIO = new FileIO();
    CustomerRequest validInputRequest = new CustomerRequest(38, 36, 0,
        0, "bone", "", 2000.0 );
    AccessoryPrices accessoryPrices = new AccessoryPrices();
    Cabinet luthien = new Cabinet("Luthien", new Size(18, 36, 18),
        new Color[]{Color.BLACK, Color.BONE}, 100.00, new Mount (MountType.FLOOR_OR_WALL,
        Feet.OPTIONAL, Rails.STANDARD), 1, 0, false, accessoryPrices);
    CabinetFront belacqua = new CabinetFront("Belacqua", new Size(18, 36), new Color[]{Color.BONE, Color.OXBLOOD}, 500.00, CabinetFrontType.DOOR, true, accessoryPrices);
    testRecommendationList = new ArrayList<>();
    testRecommendationList.add(new Recommendation(luthien, belacqua, validInputRequest));
  }

  @Test (expected = IOException.class)
  public void readRequest() throws IOException, ParseException {
      testFileIO.readRequest("nonexistent.txt");
  }

  // Empty accessories list is ok because the addAccessories() function was not called.
  @Test
  public void printRecommendation() {
    Assert.assertEquals("{\"cabinetStyle\":\"Luthien\",\"cabinetSizeName\":\"QUARTER\","
            + "\"cabinetHeight\":18,\"cabinetWidth\":36,\"cabinetDepth\":18,\"cabinetNum\":1,"
            + "\"cabinetColor\":\"BLACK\",\"cabinetPrice\":100.0,\"shelfCount\":1,\"drawerCount\":0,"
            + "\"frontType\":\"DOOR\",\"frontStyle\":\"Belacqua\",\"frontSizeName\":\"QUARTER\","
            + "\"frontHeight\":18,\"frontWidth\":36,\"frontColor\":\"BONE\",\"frontPrice\":500.0,"
            + "\"totalSetPrice\":600.0,\"accessories\":[]}",
        testFileIO.printRecommendation(testRecommendationList));
  }
}