package assignment2_refactored;

import java.util.Objects;

/**
 * Accessory.java
 * An accesory Object that may be recommended depending on cabinet/cabinet front requirements
 */
public class Accessory <T extends Enum <T>> {

  private T name;
  private Integer quantity;
  private Double price;

  /**
   * Constructor for a name input only. The quantity is set to 1. The price is then set to a default $0.
   * @param name Name of the accessory.
   */
  Accessory(T name) {
    this.name = name;
    quantity = 1;
    this.price = 0.0;
  }

  /**
   * Constructor with name, quantity, and price details given..
   * @param name Generic Object that extend Enum. Represents the name of an accessory (ie. RAILS_STANDARD)
   * @param quantity Integer representing the quantity recommended for the project.
   * @param price Double representing the amount of money that the accessory costs.
   */
  Accessory(T name, Integer quantity, Double price) {
    this.name = name;
    this.quantity = quantity;
    this.price = price;
  }

  /**
   * Copy Constructor for the Accessory Object.
   */
  Accessory(Accessory otherAccessory) {
    if (otherAccessory == null) {
      throw new IllegalArgumentException("Cannot create an Accessory Object.");
    }
    this.name = (T)otherAccessory.name;
    this.quantity = otherAccessory.quantity;
    this.price = otherAccessory.price;
  }

  /**
   * Returns the name of an accessory
   * @return name Name of the accessory
   */
  public T getName() {
    return this.name;
  }

  /**
   * Returns the quantity of this accessory needed.
   * @return quantity The number this accessory object recommended in the order.
   */
  public Integer getQuantity() {
    return quantity;
  }

  /**
   * Get the price
   * @return price Name of the price
   */
  public Double getPrice() {
    return price;
  }


  /**
   * Compares equality with this Accessory Object with another object
   * @param obj Other object being compared with.
   * @return true/false True if the objects compared with are equal, otherwise false.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Accessory accessory = (Accessory) obj;
    return Objects.equals(name, accessory.name) &&
        Objects.equals(quantity, accessory.quantity) &&
        Objects.equals(price, accessory.price);
  }

  /**
   * Returns the hashcode
   * @return Hashcode int representing the hashcode of this Accessory object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, quantity, price);
  }

  /**
   * Information of an Accessory Object
   * @return String representation of an Accessory
   */
  @Override
  public String toString() {
    return "Accessory{" +
        "name='" + name.toString() + '\'' +
        "quantity='" + quantity + '\'' +
        ", price=" + price +
        "}";
  }
}
