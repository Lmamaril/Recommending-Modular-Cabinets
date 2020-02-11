package assignment2_refactored;

import assignment2_refactored.Enums.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public abstract class AbstractInventory<T extends AbstractFurniture> {
  protected List<T> furnitureList;
  private Iterator itr;
  protected int NULL_VALUE_INTEGER = 0;
  protected T furniture;
  protected AccessoryPrices accessoryPrices;

  public abstract Boolean checkFurnitureRemovalCriteria(CustomerRequest request, T furniture);
  public abstract void setToDefaultFurnitureValues();
  /**
   * Constructor for the inventory Class
   */
  public AbstractInventory() {
    furnitureList = new ArrayList<>();
  }

  public AbstractInventory(AbstractInventory otherInventory) {
    if (otherInventory == null) {
      throw new IllegalArgumentException("Cannot create Inventory object");
    }
    this.furnitureList = otherInventory.furnitureList;
  }

  /**
   * Return a list of cabinets
   * @return cabinets Deep copied list of cabinets
   */
  public List<T> getFurnitureList() {
    return new ArrayList<>(furnitureList);
  }


  public void setFurnitureList(List<T> furniture) {
    this.furnitureList = new ArrayList<>(furniture);
  }

  public void addFurniture(T furniture) {
    this.furnitureList.add(furniture);
  }

  public void removeFurniture(T furniture) {
    this.furnitureList.remove(furniture);
  }



  /**
   *  Iterates through each cabinet, remove cabinet if unmatched configuration
   * @param request Details of the customer request.
   */
  public void filterInventory(CustomerRequest request) {

    itr = furnitureList.iterator();
    while (itr.hasNext()) {
      furniture = (T) itr.next();

      // Check if Cabinet needs to be removed
      if (checkFurnitureRemovalCriteria(request, furniture)) {
        itr.remove();
      }
      // If color is found, remove other available colors for the Cabinet
      if (furniture.getColors().contains(request.getColor())) {
        replaceColorArray( request);
      }
    }
  }

  /**
   * Helper function that removes the unavailable colors in a Cabinet
   * @param request
   */
  private void replaceColorArray( CustomerRequest request) {
    List <Color> tempColorArray = new ArrayList<>();
    tempColorArray.add(request.getColor());
    furniture.setColors(tempColorArray);
  }

  /**
   * Compares with another object to see if the other object is equal to this
   * Inventory object.
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
    AbstractInventory<?> inventory = (AbstractInventory<?>) obj;
    return Objects.equals(furnitureList, inventory.furnitureList);
  }

  /**
   * Returns the Hashcode of this Inventory object.
   * @return hashcode int representing a hashcode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(furnitureList);
  }

  /**
   * Information on an Inventory Object
   * @return String representation of an inventory containing cabinets and cabinet fronts
   */
  @Override
  public String toString() {
    String tempCabinetsString = "";
    for (T furniture: furnitureList ) {
      tempCabinetsString += (furniture);
    }

    return
        "Cabinet List{\n" + tempCabinetsString + "}";
  }
}
