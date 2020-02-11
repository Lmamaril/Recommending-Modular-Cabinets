package assignment2_refactored;

import assignment2_refactored.Enums.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CabinetFront.java
 * Cabinet Fronts represents either of Door or Drawer front that may match with a cabinet.
 */
public class CabinetFront extends AbstractFurniture {
  private CabinetFrontType frontType; // Door or Drawer Front
  private Boolean handleIncluded;

  /**
   * Constructor with all needed Cabinet information required as input.
   * DIFFERS FROM OTHER CONSTRUCTOR: Inputs a Color LIST
   * @param style  String representing the cabinet front style (Slothrop, Belacqua, Gaga).
   * @param cabFrontSize Height, Width, Depth (Depth is set to zero for Cabinet Fronts).
   * @param colors List of Colors available.
   * @param price Price of the cabinet front.
   * @param frontType Door Or Drawer Front.
   * @param handleIncluded Boolean describing if a handle needs to be included.
   * @param accessoryPrices Prices of the accesories.
   */
  public CabinetFront( String style, Size cabFrontSize,
      List<Color> colors,  Double price, CabinetFrontType frontType, Boolean handleIncluded, AccessoryPrices accessoryPrices ) {
    super(style, new Size(cabFrontSize), new ArrayList<>(colors), price, accessoryPrices);
    this.frontType = frontType;
    this.handleIncluded = handleIncluded;
  }

  /**
   * Another Constructor with all needed Cabinet information required as input.
   * DIFFERS FROM OTHER CONSTRUCTOR: Inputs a Color ARRAY
   * @param style  String representing the cabinet front style (Slothrop, Belacqua, Gaga).
   * @param cabFrontSize Height, Width, Depth (Depth is set to zero for Cabinet Fronts).
   * @param colors Arrayof Colors available.
   * @param price Price of the cabinet front.
   * @param frontType Door Or Drawer Front.
   * @param handleIncluded Boolean describing if a handle needs to be included.
   * @param accessoryPrices Prices of the accesories.
   */
  public CabinetFront( String style, Size cabFrontSize,
      Color[] colors,  Double price, CabinetFrontType frontType, Boolean handleIncluded, AccessoryPrices accessoryPrices ) {
    super(style, new Size(cabFrontSize), colors, price, accessoryPrices);
    this.frontType = frontType;
    this.handleIncluded = handleIncluded;
  }

  /**
   * Returns the type of Cabin front: DOOR or DRAWERFRONT
   * @return doorOrDrawerFront CabinetFrontType representing the type of cabinet front.
   */
  public CabinetFrontType getFrontType() {
    return this.frontType;
  }

  /**
   * Returns whether or not a handle needs to be included.
   * @return handleIncluded True if handle is included, false if not included.
   */
  public Boolean getHandleIncluded() {
    return handleIncluded;
  }

  /**
   * Add recommended accessories to the Cabinet Front
   * @param request CustomerRequest object containing request informations
   */
  public void addAccessories(CustomerRequest request) {
    List<Accessory> tempAccessoryList = new ArrayList<>();

    // Add the handle accessory if not included.
    if (handleIncluded.equals(false) && (frontType.equals(frontType.DRAWER_FRONT) )) {
      tempAccessoryList.add(new Accessory(AccessoryName.DRAWER_HANDLE, 1, super.getAccessoryPrices().getDrawerHandlesPrice()));
    }
    // Add Door hinge if the cabinet front is a door
    if (frontType.equals(frontType.DOOR)) {
      tempAccessoryList.add(new Accessory(AccessoryName.DOOR_HINGE, 1, super.getAccessoryPrices().getDoorHingesPrice()));
    }
    // Add Door handle if the cabinet front is a door.
    if (handleIncluded.equals(false) && (frontType.equals(frontType.DOOR))) {
      tempAccessoryList
          .add(new Accessory(AccessoryName.DOOR_HANDLE, 1, super.getAccessoryPrices().getDoorHandlesPrice()));
    }
    // Set list
    super.setAccessories(tempAccessoryList);
  }

  /**
   * Equals method for a Cabinet Front Object
   * @param obj The object being compared with.
   * @return true/false. True if the objects compared are equal.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    if (!super.equals(obj)) {
      return false;
    }
    CabinetFront that = (CabinetFront) obj;
    return Objects.equals(frontType, that.frontType) &&
        Objects.equals(handleIncluded, that.handleIncluded);
  }

  /**
   * Returns a hashcode.
   * @return hashcode int representing the hashcode of the object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), frontType, handleIncluded);
  }

  /**
   * Information of the Cabinet front
   * @return String representation of this Cabinet Front.
   */
  @Override
  public String toString() {
    return super.toString() + "CabinetFront{" +
        "type='" + frontType.toString() + '\'' +
        ", handleIncluded=" + handleIncluded +
        "}";
  }

}





