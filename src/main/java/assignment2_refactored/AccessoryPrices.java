package assignment2_refactored;

/**
 * Inventory Prices Class
 * Class for referencing to prices set for merchandise
 */
public class AccessoryPrices {
  private Double mountPrice;
  private Double doorHandlesPrice;
  private Double drawerHandlesPrice;
  private Double doorHingesPrice;
  private Double railsPrice;
  private Double fixAttachmentPrice;
  private Double cornerFeetPrice;

  /**
   * Values added for inventory (Aimed to be revised in the future, as instructed in the HW)
   */
  public AccessoryPrices() {
    mountPrice = 15.0;
    doorHandlesPrice = 8.0;
    drawerHandlesPrice = 4.0;
    doorHingesPrice = 1.0;
    railsPrice = 10.0;
    fixAttachmentPrice = 1.0;
    cornerFeetPrice =  0.50;
  }

  /**
   * Copy Constructor for InventoryPrices
   * @param otherPriceObject Other Price Object to deep copy
   */
  public AccessoryPrices(AccessoryPrices otherPriceObject) {
    if (otherPriceObject == null) {
      throw new IllegalArgumentException("Cannot create a Accessories Prices Object.");
    }
    this.mountPrice = otherPriceObject.mountPrice;
    this.doorHandlesPrice = otherPriceObject.doorHandlesPrice;
    this.drawerHandlesPrice = otherPriceObject.drawerHandlesPrice;
    this.doorHingesPrice = otherPriceObject.doorHingesPrice;
    this.railsPrice = otherPriceObject.railsPrice;
    this.fixAttachmentPrice = otherPriceObject.fixAttachmentPrice;
    this.cornerFeetPrice = otherPriceObject.cornerFeetPrice;
  }
  /**
   *  Returns the mount price
   * @return mount price
   */
  public  Double getMountPrice() {
    return mountPrice;
  }

  /**
   * Returns the getDoor handles price
   * @return DoorHandlesPrice
   */
  public Double getDoorHandlesPrice() {
    return doorHandlesPrice;
  }

  /**
   * Returns the Drawer Handle Price
   * @return drawerHandlesPrice
   */
  public Double getDrawerHandlesPrice() {
    return drawerHandlesPrice;
  }

  /**
   * Returns the door hinges prices
   * @return door hinges price
   */
  public Double getDoorHingesPrice() {
    return doorHingesPrice;
  }

  /**
   * Returns Rails Price
   * @return railsPrice
   */
  public  Double getRailsPrice() {
    return railsPrice;
  }

  /**
   * Returns the fix attachemnt
   * @return fixAttachement price
   */
  public  Double getFixAttachmentPrice() {
    return fixAttachmentPrice;
  }

  /**
   * Return the corner feet price
   * @return Corner Feet price
   */
  public  Double getCornerFeetPrice() {
    return cornerFeetPrice;
  }


}
