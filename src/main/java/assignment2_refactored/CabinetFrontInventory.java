package assignment2_refactored;


import assignment2_refactored.Enums.CabinetFrontType;
import assignment2_refactored.Enums.Color;
import java.util.ArrayList;

/**
 * CabinetFrontInventory class inherits the ability to store a Furniture List of Cabinet Front
 * and Checks if the Cabinet Front criteria when inventory needs to filter.
 */
public class CabinetFrontInventory extends AbstractInventory {

  public CabinetFrontInventory() {
    super();
  }

  /**
   * CabinetFront Inventory copy Constructor
   * @param cabinetFrontInventory other CabinetFrontInventory Object to copy.
   */
  public CabinetFrontInventory(CabinetFrontInventory cabinetFrontInventory) {
    if (cabinetFrontInventory == null) {
      throw new IllegalArgumentException("Cannot create CabinetFront Inventory Object.");
    }
    setFurnitureList(cabinetFrontInventory.getFurnitureList());
  }

  /**
   * Sets the Cabinet fronts
   * Created for default loading of data
   */
  public void setToDefaultFurnitureValues(){
    this.accessoryPrices = new AccessoryPrices();
    this.furnitureList = new ArrayList<>();
    Object [][] configCabFrontArray = new Object[][] {
        new Object[]{ "Slothrop", 72, 36, new Color[]{Color.BROWN, Color.BLACK}, 250.00, CabinetFrontType.DOOR, true},
        new Object[] { "Slothrop", 36, 36, new Color[]{Color.BROWN, Color.BLACK}, 100.00, CabinetFrontType.DOOR, true},
        new Object[] { "Belacqua", 72, 36, new Color[]{Color.BONE, Color.OXBLOOD}, 280.00, CabinetFrontType.DOOR, true},
        new Object[] { "Belacqua", 36, 36, new Color[]{Color.BONE, Color.OXBLOOD}, 100.00, CabinetFrontType.DOOR, true},
        new Object[] { "Belacqua", 18, 36, new Color[]{Color.BONE, Color.OXBLOOD}, 500.00, CabinetFrontType.DOOR, true},
        new Object[] { "Gaga", 36, 36, new Color[]{Color.BROWN, Color.BLACK, Color.BONE, Color.OXBLOOD}, 80.50, CabinetFrontType.DOOR, false},
        new Object[] { "Gaga", 18, 36, new Color[]{Color.BROWN, Color.BLACK, Color.BONE, Color.OXBLOOD}, 230.00, CabinetFrontType.DOOR, false},
        new Object[] { "Gaga", 18, 36, new Color[]{Color.BROWN, Color.BLACK, Color.BONE, Color.OXBLOOD}, 120.00, CabinetFrontType.DRAWER_FRONT, false},
    };

    // Sets the cabinets data
    for (Object[] config : configCabFrontArray)
      furnitureList.add(new CabinetFront((String)config[0],
          new Size((Integer)config[1], (Integer) config[2]), (Color[])config[3],(Double) config[4],
          (CabinetFrontType) config[5], (Boolean)config[6], accessoryPrices)) ;
  }

  /**
   * Checks the furniture removal criteria specific to a cabinet front
   * @param request Customer Request
   * @param furniture Furniture object that will be checked for the cabinet front.
   * @return true/false Boolean value. True if the height or color preference for cabinet front
   * does not fit.
   */
  public Boolean checkFurnitureRemovalCriteria(CustomerRequest request, AbstractFurniture furniture){
    // Criteria does not match if customer existing HEIGHT preference does not match
    if ((request.getMaxHeight() < furniture.getFurnitureSize().getHeight() &&
        request.getMaxHeight() > NULL_VALUE_INTEGER) ||

        // Criteria does not match if customer existing COLOR preference does not match
        (!request.getColor().equals(Color.NO_PREFERENCE) &&
            !furniture.getColors().contains(request.getColor()))){
      return true;
    }
    else return false;
  }

  /**
   * Compares with another object to see if the other object is equal to this
   * Cabinet Inventory object.
   * @param obj The other object.
   * @return true/false True if the object compared is equal, otherwise, false.
   */
  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  /**
   * Returns the Hashcode of this CabinetFront Inventory object.
   * @return hashcode int representing a hashcode.
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * Returns details on a cabinetFront Inventory Object.
   * @return String representation of a Cabinet Front inventory inherited from the Inventory Class.
   */
  @Override
  public String toString() {
    return super.toString();
  }
}
