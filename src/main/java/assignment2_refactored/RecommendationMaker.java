package assignment2_refactored;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * RecommendationMaker.java
 * Make a recommendation by filtering an inventory, matching cabinets with cabinet fronts
 * (aka  door/drawer fronts), adding accessories, and then storing them into Recommendation
 * Objects to be sorted by total price, and printed into a JSON.
 */
public class RecommendationMaker {

  private AbstractInventory[] inventory = new AbstractInventory[2];
  private List<Recommendation> recommendationList;

  /**
   * Constructs a RecommendationMaker object using the following information.
   * @param cabInventory CabinetInventory object.
   * @param cabinetFrontInventory CabinetFrontInventory object.
   */
  public RecommendationMaker(CabinetInventory cabInventory, CabinetFrontInventory cabinetFrontInventory) {
  inventory[0] = new CabinetInventory(cabInventory);
  inventory[1] = new CabinetFrontInventory(cabinetFrontInventory);
  }

  /**
   * Makes a recommendation based on the customer requesting criteria, the inventory filtering,
   * the filtered furniture being matched, and the recommendations being sorted lowest to high.
   * @param customerRequest Customer Request Object.
   */
  public void makeRecommendation(CustomerRequest customerRequest){

    filterInventory(customerRequest);
    matchFurniture((CabinetInventory) inventory[0], (CabinetFrontInventory) inventory[1], customerRequest);
    sortByTotalPrice();
  }

  /**
   * Returns a recommendation list.
   * @return recommendationList List of Recommendation Objects, representing each furniture recommendation.
   */
  public List<Recommendation> getRecommendationList() {
    return recommendationList;
  }

  /**
   * Filters the Inventory given the criteria listed from the customer.
   * @param customerRequest CustomerRequest object.
   */
  private void filterInventory(CustomerRequest customerRequest) {
    for (int i=0; i < inventory.length; i++) {
      inventory[i].filterInventory(customerRequest);
    }
  }

  /**
   * Matches the furniture to add into the recommendation list.
   * @param cabinetInventory Cabinet Inventory Object.
   * @param cabFrontInventory Cabinet Front Inventory Object.
   * @param request CustomerRequest object.
   */
  private void matchFurniture(CabinetInventory cabinetInventory, CabinetFrontInventory cabFrontInventory,
      CustomerRequest request) {
    List<Cabinet> filteredCabList  = (List<Cabinet>)cabinetInventory.getFurnitureList();
    List<CabinetFront> filteredCabFrontList = (List<CabinetFront>)cabFrontInventory.getFurnitureList();
    recommendationList = new ArrayList<>();

    for (Cabinet filteredCab : filteredCabList) {
      for (CabinetFront filteredCabFront: filteredCabFrontList) {
        // Match Cabinet with Cabinet front based on height and color.
        if (filteredCab.getFurnitureSize().getHeight().
            equals(filteredCabFront.getFurnitureSize().getHeight()) &&
            filteredCab.getColors().
                equals(filteredCabFront.getColors())) {
          // Combine Cabinet accessories with Drawer Front accessories
          filteredCab.addAccessories(request);
          filteredCabFront.addAccessories(request);
          recommendationList.add(new Recommendation(filteredCab, filteredCabFront, request));
        }
      }
    }
  }

  /**
   * Helper function that sorts the Recommendation list by (Cabinet + Cabinet Front Price)
   */
  private void sortByTotalPrice() {
    Collections.sort(recommendationList);
  }

  /**
   * Compares with another object to see if the other object is equal to this
   * Size object.
   * @param obj The other object.
   * @return true/false True if the object compared is equal, otherwise, false.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    RecommendationMaker that = (RecommendationMaker) obj;
    return Arrays.equals(inventory, that.inventory) &&
        Objects.equals(recommendationList, that.recommendationList);
  }

  /**
   * Returns a hashcode number representing this object.
   * @return hashcode Integer representing a hashcode.
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(recommendationList);
    result = 31 * result + Arrays.hashCode(inventory);
    return result;
  }

  /**
   * Details of a RecommendationMaker
   * @return String representation of a RecommendationMaker.
   */
  @Override
  public String toString() {
    return "RecommendationMaker{" +
        "inventory=" + Arrays.toString(inventory) +
        ", recommendationList=" + recommendationList +
        '}';
  }
}
