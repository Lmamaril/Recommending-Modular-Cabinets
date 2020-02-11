package assignment2_refactored;

import assignment2_refactored.Enums.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Recommendation.java
 * Represents a recomendation for furniture which consists of:
 * - A Cabinet with cabinet accessories
 * - A matching Cabinet Front (Door or Drawer) with accessories
 */
public class Recommendation implements Comparable {

  private String cabinetStyle;
  private String cabinetSizeName;
  private Integer cabinetHeight;
  private Integer cabinetWidth;
  private Integer cabinetDepth;
  private Integer cabinetNum;
  private  String cabinetColor;
  private Double cabinetPrice;
  private Integer shelfCount;
  private Integer drawerCount;

  private String frontType;
  private String frontStyle;
  private String frontSizeName;
  private Integer frontHeight;
  private Integer frontWidth;
  private String frontColor;
  private Double frontPrice;
  private Double totalSetPrice;
  private List<Accessory> accessories;


  /**
   * Constructor of with all fields (Cabinet, CabinetFront, and Accessory List) as arguments.
   * @param cabRec Cabinet Object.
   * @param frontRec Matching Cabinet Front Object.
   * @param request Customer Request Object.
   */
  public Recommendation(Cabinet cabRec, CabinetFront frontRec, CustomerRequest request) {

    this.cabinetStyle = cabRec.getStyle();
    this.cabinetSizeName = cabRec.getFurnitureSize().getSizeName().toString();
    this.cabinetHeight = cabRec.getFurnitureSize().getHeight();
    this.cabinetWidth = cabRec.getFurnitureSize().getWidth();
    this.cabinetDepth = cabRec.getFurnitureSize().getDepth();
    this.cabinetNum = request.getNumOfCabinets();
    setCabinetColor(cabRec.getColors());
    this.cabinetPrice = cabRec.getPrice();
    this.shelfCount = cabRec.getMaxShelfCount();
    this.drawerCount = cabRec.getMaxDrawerCount();
    this.frontType = frontRec.getFrontType().toString();
    this.frontStyle = frontRec.getStyle();
    this.frontSizeName = frontRec.getFurnitureSize().getSizeName().toString();
    this.frontHeight = frontRec.getFurnitureSize().getHeight();
    this.frontWidth = frontRec.getFurnitureSize().getWidth();
    setFrontColor(frontRec.getColors());
    this.frontPrice = frontRec.getPrice();
    setTotalSetPrice(cabRec, frontRec);
    this.accessories = new ArrayList<>();
    this.accessories.addAll(cabRec.getAccessories());
    this.accessories.addAll(frontRec.getAccessories());
  }

  /**
   * Sets the Color of the cabinet so that it is String from a list.
   * @param cabColor Color List.
   */
  private void setCabinetColor(List<Color> cabColor) {
    final int FIRST_CABINET_ITEM = 0;
    this.cabinetColor = cabColor.get(FIRST_CABINET_ITEM).toString();
  }

  /**
   * Sets the Color of the Cabinet Front
   * @param frontColor List of Color Type.
   */
  private void setFrontColor(List<Color> frontColor) {
    final int FIRST_FRONT_ITEM = 0;
    this.frontColor  = frontColor.get(FIRST_FRONT_ITEM).toString();
  }

  /**
   * Sets the total price of a recommendation.
   * @param cabRec Cabinet Recommendation Object.
   * @param frontRec Cabinet Front Recommendation Object.
   */
  private void setTotalSetPrice(Cabinet cabRec, CabinetFront frontRec) {
    Double accessoriesTotalPrice = 0.0;

    for (Accessory accessory : cabRec.getAccessories()) {
      accessoriesTotalPrice += accessory.getPrice();
    }

    for (Accessory accessory : frontRec.getAccessories()) {
      accessoriesTotalPrice += accessory.getPrice();
    }

    this.totalSetPrice = cabRec.getPrice() + frontRec.getPrice() + accessoriesTotalPrice;

  }

  /**
   * Compareto method compares between added price between Cabinet and Cabinet front combined.
   * @param obj Other object to compare
   * @return -1, 0, 1 Returns -1 if this object is less than, 1 if greater, 0 if equal
   */
  @Override
  public int compareTo(Object obj) {
    if (obj == null) {
      throw new IllegalArgumentException("Cannot compare Recommendation with null object.");
    }
    if (obj.getClass() != this.getClass()) {
      throw new IllegalArgumentException("Cannot compare Recommendation with different class object.");
    }
    Recommendation otherRecommendation = (Recommendation)obj;

    if (this.totalSetPrice < otherRecommendation.totalSetPrice) {
      return -1;
    }
    else if(this.totalSetPrice.intValue() == otherRecommendation.totalSetPrice.intValue()) {
      return 0;
    }
    else return 1;
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
    Recommendation that = (Recommendation) obj;
    return Objects.equals(cabinetStyle, that.cabinetStyle) &&
        Objects.equals(cabinetSizeName, that.cabinetSizeName) &&
        Objects.equals(cabinetHeight, that.cabinetHeight) &&
        Objects.equals(cabinetWidth, that.cabinetWidth) &&
        Objects.equals(cabinetDepth, that.cabinetDepth) &&
        Objects.equals(cabinetNum, that.cabinetNum) &&
        Objects.equals(cabinetColor, that.cabinetColor) &&
        Objects.equals(cabinetPrice, that.cabinetPrice) &&
        Objects.equals(shelfCount, that.shelfCount) &&
        Objects.equals(drawerCount, that.drawerCount) &&
        Objects.equals(frontType, that.frontType) &&
        Objects.equals(frontStyle, that.frontStyle) &&
        Objects.equals(frontSizeName, that.frontSizeName) &&
        Objects.equals(frontHeight, that.frontHeight) &&
        Objects.equals(frontWidth, that.frontWidth) &&
        Objects.equals(frontColor, that.frontColor) &&
        Objects.equals(frontPrice, that.frontPrice) &&
        Objects.equals(totalSetPrice, that.totalSetPrice) &&
        Objects.equals(accessories, that.accessories);
  }

  /**
   * Returns a hashcode.
   * @return hashcode Integer representing hashcode.
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(cabinetStyle, cabinetSizeName, cabinetHeight, cabinetWidth, cabinetDepth, cabinetNum,
            cabinetColor, cabinetPrice, shelfCount, drawerCount, frontType, frontStyle,
            frontSizeName, frontHeight, frontWidth,
            frontColor, frontPrice, totalSetPrice, accessories);
  }

  /**
   * Returns main details about a recommendation.
   * @return String representation of a recommendation.
   */
  @Override
  public String toString() {
    return "Recommendation{" +
        "cabinetStyle='" + cabinetStyle + '\'' +
        ", cabinetSizeName='" + cabinetSizeName + '\'' +
        ", cabinetHeight=" + cabinetHeight +
        ", cabinetWidth=" + cabinetWidth +
        ", cabinetDepth=" + cabinetDepth +
        ", cabinetNum=" + cabinetNum +
        ", cabinetColor='" + cabinetColor + '\'' +
        ", cabinetPrice=" + cabinetPrice +
        ", shelfCount=" + shelfCount +
        ", drawerCount=" + drawerCount +
        ", frontType='" + frontType + '\'' +
        ", frontStyle='" + frontStyle + '\'' +
        ", frontSizeName='" + frontSizeName + '\'' +
        ", frontHeight='" + frontHeight + '\'' +
        ", frontWidth='" + frontWidth + '\'' +
        ", frontColor='" + frontColor + '\'' +
        ", frontPrice=" + frontPrice +
        ", totalSetPrice=" + totalSetPrice +
        ", accessories=" + accessories +
        '}';
  }
}
