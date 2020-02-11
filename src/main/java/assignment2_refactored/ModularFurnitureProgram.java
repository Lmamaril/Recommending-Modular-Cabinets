package assignment2_refactored;

import java.io.IOException;
import org.json.simple.parser.ParseException;

/**
 * This program reads the CustomerRequsts.json for the customer preference,
 * stores available store .furniture information in an Inventory,
 * filters the inventory, and matches the furniture to print a recommendations list in ascending
 * order by total price of items.
 */
public class ModularFurnitureProgram {

  public static void main(String args[]) throws IOException, ParseException {

    // Read a new customerRequest
    FileIO fileIO = new FileIO();
    CustomerRequest customerRequest = new CustomerRequest(fileIO.readRequest("CustomerRequests.json"));

    // Obtain Inventory to collect recommendation from
    CabinetInventory cabinetInventory = new CabinetInventory();
    CabinetFrontInventory cabinetFrontInventory = new CabinetFrontInventory();
    cabinetInventory.setToDefaultFurnitureValues();
    cabinetFrontInventory.setToDefaultFurnitureValues();

    // Create a recommendation
    RecommendationMaker recommendationMaker = new RecommendationMaker(cabinetInventory, cabinetFrontInventory);
    recommendationMaker.makeRecommendation(customerRequest);
    fileIO.printRecommendation((recommendationMaker.getRecommendationList()));
  }
}
