package assignment2_refactored;

import assignment2_refactored.Enums.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * AbstractFurniture.java
 * Abstract class that is a parent of Cabinet and Cabinet Front
 */
public abstract class AbstractFurniture {
  private String style;
  private Size furnitureSize;
  private List<Color> colors;
  private Double furniturePrice;
  private List <Accessory> accessories;
  private AccessoryPrices accessoryPrices;


  /**
   * Constructor for a Furniture object. Constructor differs because
   * it takes a Color list as a parameter.
   * @param style String representing the style of the furniture.
   * @param furnitureSize Size details of the furniture.
   * @param colors List of Color Types representing the furniture color.
   * @param furniturePrice Double representing the price of the furniture.
   * @param accessoryPrices Prices of the accessories.
   */
  public AbstractFurniture(String style, Size furnitureSize, List<Color> colors, Double furniturePrice, AccessoryPrices accessoryPrices) {
    this.style = style;
    this.furnitureSize = new Size(furnitureSize);
    this.colors = new ArrayList<>(colors);
    this.furniturePrice = furniturePrice;
    this.accessories = new ArrayList<>();
    this.accessoryPrices = new AccessoryPrices();
  }

  /**
   * Constructor for Furniture Class. Differs because of the Color Array input.
   * @param style String representing the style of the furniture.
   * @param furnitureSize Size details of the furniture.
   * @param colors Array of Color Types representing the furniture color.
   * @param furniturePrice Double representing the price of the furniture.
   * @param accessoryPrices Prices of the accessories.
   */
  public AbstractFurniture(String style, Size furnitureSize, Color[] colors, Double furniturePrice, AccessoryPrices accessoryPrices) {
    this.style = style;
    this.furnitureSize = new Size(furnitureSize);
    this.colors = new ArrayList<>(Arrays.asList(colors));
    this.furniturePrice = furniturePrice;
    this.accessories = new ArrayList<>();
    this.accessoryPrices = new AccessoryPrices();
  }

  /**
   * Returns the style name
   * @return style Style name.
   */
  public String getStyle() {
    return this.style;
  }

  /**
   * Returns the furniture size details.
   * @return furnitureSize
   */
  public Size getFurnitureSize() {
    return new Size(furnitureSize);
  }

  /**
   * Returns list of colors
   * @return Returns a list of colors.
   */
  public List<Color> getColors() {
    return new ArrayList<>(this.colors);
  }

  /**
   * Sets the colors for the furniture.
   * @param colors List of Color Types that represent furniture color.
   */
  public void setColors(List <Color> colors) {
    this.colors = new ArrayList<>(colors);
  }

  /**
   * Returns the furniture price.
   * @return Double returning the furniture price.
   */
  public Double getPrice() {
    return this.furniturePrice;
  }


  /**
   * Returns an accessory list.
   * @return accessories
   */
  public List<Accessory> getAccessories() {
    return new ArrayList<>(this.accessories);
  }

  public void setAccessories(List<Accessory> accessories) {
    this.accessories = new ArrayList<>(accessories);
  }

  /**
   * Returns the inventory prices associated with an Inventory Object class.
   * @return accessoryPrices. Returns the accessory prices.
   */
  public AccessoryPrices getAccessoryPrices() {
    return new AccessoryPrices(accessoryPrices);
  }

  /**
   * Checks and Adds accessories to a list to be later added as part
   * of a recommendation.
   * @param request CustomerRequest object containing details of the customer's request.
   */
  public abstract void addAccessories(CustomerRequest request);

  /**
   * Compares with another object to see if the other object is equal to this
   * Furniture object.
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
    AbstractFurniture furniture = (AbstractFurniture) obj;
    return Objects.equals(style, furniture.style) &&
        Objects.equals(furnitureSize, furniture.furnitureSize) &&
        Objects.equals(colors, furniture.colors) &&
        Objects.equals(furniturePrice, furniture.furniturePrice);
  }

  /**
   * Returns the Hashcode of this Furniture object.
   * @return hashcode int representing a hashcode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(style, furnitureSize, colors, furniturePrice);
  }

  /**
   * Returns the details defined by this Furniture class.
   * @return String representation of a furniture object
   */
  @Override
  public String toString() {
    return "Furniture{" +
        "style='" + style + '\'' +
        ", furnitureSize=" + furnitureSize +
        ", colors=" + colors +
        ", furniturePrice=" + furniturePrice +
        ", accessories=" + accessories +
        '}';
  }
}

