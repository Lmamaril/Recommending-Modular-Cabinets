package assignment2_refactored;

import assignment2_refactored.Enums.Color;
import assignment2_refactored.Enums.MountType;
import java.util.Objects;


public class CustomerRequest {

  /**  NOTE:
   *  For the following, an entry of 0 means NO PREFERENCE
   *    - maxHeight
   *    - maxWidth
   *    - ShelfCount
   *    - drawerCount
   *    - budget
   *  For color, a "" (blank entry) or "null" means NO PREFERENCE.
   *  For a mount, a "" (blank entry) or "null" means FLOOR_OR_WALL (signifying non-preference)
   */
  private Integer maxHeight; // Maximum height dimension of the cabinet. Measured in inches
  private Integer maxWidth; // Space to fill cabinet(s), measured in inches
  private Integer shelfCount;
  private Integer drawerCount;
  private Color color;
  private MountType mount;
  private Double budget; // budget of a cabinet.
  private Integer numOfCabinets; // Number of 36" cabinets needed to fit the customer requested width.

  private final Integer MIN_INTEGER_ENTRY_VALUE = 0; // Min cabinet value the customer can enter.
  private final Integer MAX_CABINET_HEIGHT_ENTRY_VALUE = 72; // Max height value the customer can enter.
  private final Integer MAX_CABINET_WIDTH_ENTRY_VALUE = 400;  // Max width value the customer
                                                              // can enter for an order.
  private final Integer MAX_BUDGET_VALUE = 5000; // Max amount of cabinet item budget that the request can be.
  private final Integer CABINET_WIDTH_SIZE = 36; // Width of all cabinets.

  public CustomerRequest() {
    this.maxHeight = 0;
    this.maxWidth = 0;
    this.shelfCount = 0;
    this.drawerCount = 0;
    this.color = Color.NO_PREFERENCE;
    this.mount = MountType.FLOOR_OR_WALL; // equivalent to no preference
  }

  /**
   * Constructor with individual fields of the class as arguments.
   * @param maxHeight Integer Request for max height (inches) one cabinet must be. 0 means no preference.
   * @param maxWidth Integer Request for max width (inches) that the cabinets must fit into. 0 means no preference.
   * @param shelfCount Integer Request for shelf count (ie. If a request ask for 3 shelves, the furniture
   *                   recommended will be any cabinet with 3 or more shelves). 0 means no preference.
   * @param drawerCount Integer Request for drawer count (ie. If a request ask for 4 drawers, the furniture
   *                    recommended will be any cabinet with 4 or more drawers). 0 means no preference.
   * @param color String Request for one color only. "" or "null" means no preference
   * @param mount String Request for a mount type. String is required for input,
   *              but will be converted to a MountType
   * @param budget Double Request for the budget price of the CABINET ONLY.
   */
  public CustomerRequest( Integer maxHeight, Integer maxWidth, Integer shelfCount, Integer drawerCount,
      String color, String mount, Double budget) {
    setMaxHeight(maxHeight);
    setMaxWidth(maxWidth);
    setShelfCount(shelfCount);
    setDrawerCount(drawerCount);
    setColor(color);
    setMount(mount);
    setBudget(budget);
  }

  /**
   * Copy constructor for a CustomerRequest.
   * @param otherCustomer Other CustomerRequest object to be copied into this object.
   */
  public CustomerRequest(CustomerRequest otherCustomer) {
    if (otherCustomer == null) {
      throw new IllegalArgumentException("Cannot create customer object.");
    }
    this.maxHeight = otherCustomer.getMaxHeight();
    this.maxWidth = otherCustomer.getMaxWidth();
    this.shelfCount = otherCustomer.getShelfCount();
    this.drawerCount = otherCustomer.getDrawerCount();
    this.color = otherCustomer.getColor();
    this.mount = otherCustomer.getMount();
    this.budget = otherCustomer.getBudget();
    this.numOfCabinets = otherCustomer.getNumOfCabinets();
  }


  /**
   * Returns the max height the cabinet must be
   * @return maxHeight Integer request for cabinet's max height in inches.
   */
  public Integer getMaxHeight() {
    return this.maxHeight;
  }

  /**
   * Sets the max height of the Customer in inches.
   * @param maxHeight Integer max height of the requested cabinet
   * @throws IllegalArgumentException Thrown when the maxHeight is less than the Min
   * Integer entry value.
   */
  public void setMaxHeight(Integer maxHeight) {
    if (maxHeight < MIN_INTEGER_ENTRY_VALUE ||
        maxHeight >  MAX_CABINET_HEIGHT_ENTRY_VALUE) {
      throw new IllegalArgumentException();
    }
    this.maxHeight = maxHeight;
  }

  /**
   * Returns the max width that Customer requested for a cabinet in inches.
   * @return maxWidth Integer Max width of cabinet in inches.
   */
  public Integer getMaxWidth() {
    return this.maxWidth;
  }

  /**
   * Set the maxWidth of the Customer in inches.
   * @param maxWidth Integer maximum width. Must
   * @throws IllegalArgumentException Thrown if maxWidth is negative.
   */
  public void setMaxWidth(Integer maxWidth) {
    if (maxWidth < MIN_INTEGER_ENTRY_VALUE ||
        maxWidth >  MAX_CABINET_WIDTH_ENTRY_VALUE) {
      throw new IllegalArgumentException();
    }
    this.maxWidth = maxWidth;
    setNumOfCabinets(maxWidth);
  }

  /**
   * Returns the number of cabinets that will fill the requested width.
   * @return numOfCabinets Integer that represents the number of cabinets to fit the width.
   */
  public Integer getNumOfCabinets() {
    return this.numOfCabinets;
  }

  /**
   * Function relies on the setWidth function to set the number of cabinets.
   * @param maxWidth Maximum width in inches that the customer would like to fill up for the order.
   */
  private void setNumOfCabinets(Integer maxWidth) {
    if (maxWidth < CABINET_WIDTH_SIZE + CABINET_WIDTH_SIZE) {
      numOfCabinets = 1;
    }
    else {
      numOfCabinets = maxWidth / CABINET_WIDTH_SIZE;
    }
  }
  /**
   * Returns the Shelf Counts requested. (ie. If a request ask for 3 shelves, the furniture
   * recommended will be any cabinet with 3 or more shelves.)
   * @return shelfCount Integer of minimum shelf count
   */
  public Integer getShelfCount() {
    return this.shelfCount;
  }

  /**
   * Sets the shelf count requested.
   * @param shelfCount Shelf count requested by the customer.
   */
  public void setShelfCount(Integer shelfCount) {
    if (shelfCount < MIN_INTEGER_ENTRY_VALUE) {
      throw new IllegalArgumentException();
    }
    this.shelfCount = shelfCount;
  }

  /**
   * Returns the drawer count requested.
   * @return drawerCount Drawer count requested.
   */
  public Integer getDrawerCount() {
    return this.drawerCount;
  }

  /**
   * Sets the drawer count requested.
   * @param drawerCount Shelf Count requested of the cabinet.
   */
  public void setDrawerCount(Integer drawerCount) {
    if (drawerCount < MIN_INTEGER_ENTRY_VALUE) {
      throw new IllegalArgumentException();
    }
    this.drawerCount = drawerCount;
  }

  /**
   * Returns the requested color.
   * @return color color requested for the furniture.
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Sets the requested color. Input is a color as a String type. The color is
   * then set to the type Color (defined in enum Color).
   * @param color String Color request from the client. Input is case insensitive.
   */
  public void setColor(String color) {
    if (color.isEmpty()) {
      this.color = Color.NO_PREFERENCE;
    }
    else if (color.toUpperCase().equals(Color.BONE.toString())) {
      this.color = Color.BONE;
    }
    else if (color.toUpperCase().equals(Color.BLACK.toString())) {
      this.color = Color.BLACK;
    }
    else if (color.toUpperCase().equals(Color.BROWN.toString())) {
      this.color = Color.BROWN;
    }
    else if (color.toUpperCase().equals(Color.OXBLOOD.toString())) {
      this.color = Color.OXBLOOD;
    }
    else { throw new IllegalArgumentException("Color not available."); }
  }

  /**
   * Return the Mount type.
   * @return mount MountType (either choose floor, wall, or floor or wall).
   */
  public MountType getMount() {
    return mount;
  }

  /**
   * Set the Mount type. Convert String user input using enum MountType
   * Customer Mount choices are WALL or FLOOR.
   * @param mount String input of mount. Input is case-insensitive.
   */
  public void setMount(String mount) {
    if (mount.isEmpty()) {
      this.mount = MountType.FLOOR_OR_WALL;
    }
    else if (mount.toUpperCase().equals(MountType.WALL.toString())) {
      this.mount = MountType.WALL;
    }
    else if (mount.toUpperCase().equals(MountType.FLOOR.toString())) {
      this.mount = MountType.FLOOR;
    }
    else {
      throw new IllegalArgumentException("Invalid Mount Value. Must enter 'floor' or 'wall'");
    }
  }

  /**
   * Returns the customer's budget request. Furniture recommended must be less or equal
   * to this price.
   * @return budget Double type. Customer budget price in dollars
   */
  public Double getBudget() {
    return budget;
  }

  /**
   * Sets the budget.
   * @param budget Customer budget must be non negative.
   */
  public void setBudget(Double budget) {
    if (budget < MIN_INTEGER_ENTRY_VALUE || budget > MAX_BUDGET_VALUE) {
      throw new IllegalArgumentException("Invalid budget amount.");
    }
    this.budget = budget;
  }

  /**
   * Compares with another object to see if it is equal to this object.
   * @param obj Other object being compared with.
   * @return true/false Returns true if equal, false if not.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    CustomerRequest customer = (CustomerRequest) obj;
    return Objects.equals(maxHeight, customer.maxHeight) &&
        Objects.equals(maxWidth, customer.maxWidth) &&
        Objects.equals(numOfCabinets, customer.numOfCabinets) &&
        Objects.equals(shelfCount, customer.shelfCount) &&
        Objects.equals(drawerCount, customer.drawerCount) &&
        color == customer.color &&
        mount == customer.mount &&
        Objects.equals(budget, customer.budget) ;
  }

  /**
   * Returns a hashcode
   * @return hashcode int representation of the hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(maxHeight, maxWidth, numOfCabinets, shelfCount, drawerCount, color, mount, budget);
  }

  @Override
  public String toString() {
    return "Customer{" +
        "maxHeight=" + maxHeight +
        ", maxWidth=" + maxWidth +
        ", numOfCabinets=" + numOfCabinets +
        ", shelfCnt=" + shelfCount +
        ", drawerCnt=" + drawerCount +
        ", color='" + color + '\'' +
        ", mount='" + mount + '\'' +
        ", budget=" + budget +
        '}';
  }
}
