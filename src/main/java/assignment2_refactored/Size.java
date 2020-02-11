package assignment2_refactored;

import assignment2_refactored.Enums.SizeType;
import java.util.Objects;


/**
 * Size Class contains sizes for cabinets and cabinet fronts(aka. Door and Drawer Fronts)
 * including height, width, and depth.
 * Size.java
 */
public class Size {
  private final Integer WARDROBE_HEIGHT = 72;
  private final Integer WARDROBE_WIDTH = 36;
  private final Integer HALF_HEIGHT = 36;
  private final Integer HALF_WIDTH = 36;
  private final Integer QUARTER_HEIGHT = 18;
  private final Integer QUARTER_WIDTH = 36;
  private final Integer ZERO_DEPTH = 0;

  private SizeType sizeName; // Wardrobe, Half, Quarter
  private Integer height;
  private Integer width;
  private Integer depth;

  /**
   * Constructor that sets the size of an item (Intended for Cabinet Dimensions).
   * @param height Item height in inches.
   * @param width Item width in inches.
   * @param depth Item depth in inches.
   */
  public Size(Integer height, Integer width, Integer depth) {
    this.height = height;
    this.width = width;
    this.depth = depth;
    setSizeName();
  }

  /**
   * Sets the size of an item in inches (This constructor is used for Cabinet Front Dimensions)
   * @param height Item height
   * @param width Item width.
   */
  public Size(Integer height, Integer width) {
    this.height = height;
    this.width = width;
    this.depth = ZERO_DEPTH;
    setSizeName();
  }

  /**
   * Copy Constructor for a Size Object.
   * @param otherSizeObject The other Size Object.
   */
  Size(Size otherSizeObject) {
    if (otherSizeObject == null) {
      throw new IllegalArgumentException("Cannot create Size object");
    }
    else {
      this.sizeName = otherSizeObject.getSizeName();
      this.height = otherSizeObject.getHeight();
      this.width = otherSizeObject.getWidth();
      this.depth = otherSizeObject.getDepth();
    }
  }

  /**
   * Returns the Height of the size in inches.
   * @return Integer representation of height.
   */
  public Integer getHeight() { return this.height; }

  /**
   * Returns the width.
   * @return width Width size as an Integer in inches.
   */
  public Integer getWidth() { return this.width; }

  /**
   * Returns the depth  of the furniture. All cabinet fronts and drawer fronts
   * are automatically set to zero.
   * @return depth Integer representing the furniture depth.
   */
  public Integer getDepth() { return this.depth; }

  /**
   * Returns the name of the size.
   * @return sizeName SizeType value that could be WARDROBE, HALF, QUARTER, or UNKNOWN.
   */
  public SizeType getSizeName() {
    return this.sizeName;
  }

  /**
   * Sets the name of the cabinet size to either Wardrobe, Half Height, or
   * quarter height.
   */
  private void setSizeName() {
    if (height.equals(WARDROBE_HEIGHT) && width.equals(WARDROBE_WIDTH)) {
      this.sizeName = SizeType.WARDDROBE;
    }
    else if (height.equals(HALF_HEIGHT) && width.equals(HALF_WIDTH)) {
      this.sizeName = SizeType.HALF;
    }
    else if (height.equals(QUARTER_HEIGHT) && width.equals(QUARTER_WIDTH)) {
      this.sizeName = SizeType.QUARTER;
    }
    else this.sizeName = SizeType.UNKNOWN;
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
    Size size = (Size) obj;
    return sizeName == size.sizeName &&
        Objects.equals(height, size.height) &&
        Objects.equals(width, size.width) &&
        Objects.equals(depth, size.depth);
  }

  /**
   * Returns a hashcode.
   * @return hashcode Integer representing hashcode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(sizeName, height, width, depth);
  }

  /**
   * Returns a string representation of the Size Object.
   * @return String representation of a Size object.
   */
  @Override
  public String toString() {
    return "Size{" +
        "sizeName=" + sizeName +
        ", height=" + height +
        ", width=" + width +
        ", depth=" + depth +
        '}';
  }
}

