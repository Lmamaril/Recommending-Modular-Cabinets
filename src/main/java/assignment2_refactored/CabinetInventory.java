package assignment2_refactored;


import assignment2_refactored.Enums.Color;
import assignment2_refactored.Enums.Feet;
import assignment2_refactored.Enums.MountType;
import assignment2_refactored.Enums.Rails;
import java.util.ArrayList;

/**
 * CabinetInventory.java
 * Represents a Cabinet Inventory inheriting a furniture List from inventory.
 * This inventory is used for filtering when making a furniture recommendation.
 */
public class CabinetInventory extends AbstractInventory {

  public CabinetInventory() {
    super();
  }

  /**
   * Copy Other Object into this object.
   * @param otherCabinetInventory CabinetInventory Object that is copied to this object.
   */
  public CabinetInventory(CabinetInventory otherCabinetInventory) {
    if (otherCabinetInventory == null) {
      throw new IllegalArgumentException("Cannot create Cabinet Inventory object.");
    }
    this.setFurnitureList(otherCabinetInventory.getFurnitureList());
  }
  /**
   * Set the default values of the cabinets (Listed in the spec)
   * Method for creating default data
   */
  public void setToDefaultFurnitureValues() {
    setFurnitureList(new ArrayList<>());
    this.accessoryPrices = new AccessoryPrices();
    // Cabinets with different drawer/shelf combinations are placed in a separate cabinet entry
    Object[][] configCabinetArray = new Object[][]{
        new Object[]{"Yosarian", 72, 36, 16,
            new Color[]{Color.BROWN, Color.BLACK, Color.BONE, Color.OXBLOOD},
            500.00,
            MountType.FLOOR, Feet.OPTIONAL, Rails.NOT_REQUIRED, 7, 0, true},

        new Object[]{"Yosarian", 36, 36, 16,
            new Color[]{Color.BROWN, Color.BLACK, Color.BONE},
            400.00,
            MountType.FLOOR_OR_WALL, Feet.OPTIONAL, Rails.STANDARD, 3, 0, false},

        new Object[]{"Yosarian", 18, 36, 16,
            new Color[]{Color.BROWN, Color.BLACK, Color.BONE},
            300.00,
            MountType.FLOOR_OR_WALL, Feet.OPTIONAL, Rails.STANDARD, 1, 0, false},

        new Object[]{"Luthien", 36, 36, 18,
            new Color[]{Color.BLACK, Color.BONE},
            250.00,
            MountType.FLOOR_OR_WALL, Feet.OPTIONAL, Rails.STANDARD, 3, 0, false},

        new Object[]{"Luthien", 36, 36, 18,
            new Color[]{Color.BLACK, Color.BONE},
            200.00,
            MountType.FLOOR_OR_WALL, Feet.OPTIONAL, Rails.STANDARD, 2, 1, false},

        new Object[]{"Luthien", 18, 36, 18,
            new Color[]{Color.BLACK, Color.BONE},
            150.00,
            MountType.FLOOR_OR_WALL, Feet.OPTIONAL, Rails.STANDARD, 1, 0, false},

        new Object[]{"Luthien", 18, 36, 18,
            new Color[]{Color.BLACK, Color.BONE},
            100.00,
            MountType.FLOOR_OR_WALL, Feet.OPTIONAL, Rails.STANDARD, 1, 0, false},

        new Object[]{"Atreides", 36, 36, 16,
            new Color[]{Color.BROWN, Color.BONE},
            80.00,
            MountType.FLOOR_OR_WALL, Feet.OPTIONAL, Rails.STYLE_SPECIFIC, 3, 0, false},

        new Object[]{"Atreides", 18, 36, 16,
            new Color[]{Color.BROWN, Color.BONE},
            75.00,
            MountType.FLOOR_OR_WALL, Feet.OPTIONAL, Rails.STYLE_SPECIFIC, 1, 0, false}
    };

    // Sets the cabinets data
    for (Object[] config : configCabinetArray) {
      this.addFurniture((new Cabinet((String) config[0], new Size((Integer) config[1], (Integer) config[2],
          (Integer) config[3]), (Color[]) config[4], (Double) config[5],
          new Mount((MountType) config[6], (Feet) config[7], (Rails) config[8]),
          (int) config[9], (int) config[10],
          (Boolean) config[11], accessoryPrices)));
    }
  }
  /**
   * Checks if the furniture can be filtered out based on the Customer request and the furniture
   * available.
   * @param request Customer request object.
   * @param furniture Furniture that is in consideration for filtering.
   * @return true/false True if the furniture does not fit the customer request and should be removed.
   *                    False for otherwise.
   */
  public Boolean checkFurnitureRemovalCriteria(CustomerRequest request, AbstractFurniture furniture){
    Cabinet cabinet = (Cabinet) furniture;
    if (// Remove if Cabinet HEIGHT if too big **/
        (request.getMaxHeight() < cabinet.getFurnitureSize().getHeight() &&
            request.getMaxHeight() > NULL_VALUE_INTEGER) ||

        // Remove Cabinet if customer existing COLOR preference does not match **/
        (!request.getColor().equals(Color.NO_PREFERENCE)) &&
            (!cabinet.getColors().contains(request.getColor())) ||

        // Check if the SHELF COUNT request matches
        (request.getShelfCount() > cabinet.getMaxShelfCount()) ||

        // Check if DRAWER COUNT matches
        (request.getDrawerCount() > cabinet.getMaxDrawerCount()) ||

        // Check if the MOUNT request matches
        (!request.getMount().equals(cabinet.getMountDetails().getCabMount())
            && !request.getMount().equals(MountType.FLOOR_OR_WALL)) ||

        // Check if the PRICE request matches
        (request.getBudget() < cabinet.getPrice())) {
      return true;
    }
    else return false;
  }

  /**
   * Compares with another object to see if the other object is equal to this
   * Inventory object.
   * @param obj The other object.
   * @return true/false True if the object compared is equal, otherwise, false.
   */
  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  /**
   * Returns the Hashcode of this Cabinet Inventory object.
   * @return hashcode int representing a hashcode.
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * Details a CabinetInventory from Inventory
   * @return String representation of a Cabinet Inventory.
   */
  @Override
  public String toString() {
    return super.toString();
  }
}
