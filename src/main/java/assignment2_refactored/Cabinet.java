package assignment2_refactored;

import assignment2_refactored.Enums.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cabinet extends AbstractFurniture {

private Mount mountDetails;
private Integer maxShelfCount;
private Integer maxDrawerCount; // Number of drawers
private Boolean wallFixRequired; // for earthquake safety.

    /**
     * Constructor for a Cabinet Object
     * DIFFERS FROM OTHER CONSTRUCTOR: Intakes a Color *LIST*
     * @param style Style of the Cabinet. Yossarian, Luthiem, Atreides
     * @param cabinetSize Size of the Cabinet (Height, Width, Depth)
     * @param colors Colors available for the cabinet
     * @param price Price of the cabinet
     * @param mountDetails Details of the Mount (MountType, Feet or Rails needed)
     * @param maxShelfCount Number of maximum shelves
     * @param maxDrawerCount Number of maximum drawers
     * @param wallFixRequired Indicates if the cabinets need a wall fixture attachment.
     * @param accessoryPrices AccessoryPrices objects containing Accessory Prices.
     */
    public Cabinet(String style, Size cabinetSize, List<Color> colors, Double price,
        Mount mountDetails, Integer maxShelfCount,
        Integer maxDrawerCount,  Boolean wallFixRequired, AccessoryPrices accessoryPrices) {
        super(style, cabinetSize, colors, price, accessoryPrices);
        this.mountDetails = new Mount(mountDetails);
        this.maxShelfCount = maxShelfCount;
        this.maxDrawerCount = maxDrawerCount;
        this.wallFixRequired = wallFixRequired;

        }

    /**
     * Constructor for a Cabinet Object
     * DIFFERS FROM OTHER CONSTRUCTOR: Handles a Color *ARRAY*
     * @param style Style of the Cabinet. Yossarian, Luthiem, Atreides
     * @param cabinetSize Size of the Cabinet (Height, Width, Depth)
     * @param colors Colors available for the cabinet
     * @param price Price of the cabinet
     * @param mountDetails Details of the Mount (MountType, Feet or Rails needed)
     * @param maxShelfCount Number of maximum shelves
     * @param maxDrawerCount Number of maximum drawers
     * @param wallFixRequired Indicates if the cabinets need a wall fixture attachment.
     * @param accessoryPrices AccessoryPrices objects containing Accessory Prices.
     */
    public Cabinet(String style, Size cabinetSize, Color[] colors, Double price,
        Mount mountDetails, Integer maxShelfCount,
        Integer maxDrawerCount,  Boolean wallFixRequired, AccessoryPrices accessoryPrices) {
        super(style, cabinetSize, colors, price, accessoryPrices);
        this.mountDetails = new Mount(mountDetails);
        this.maxShelfCount = maxShelfCount;
        this.maxDrawerCount = maxDrawerCount;
        this.wallFixRequired = wallFixRequired;
        }


    /**
     * Returns the Details of a Mount Object
     * @return mountDetails Includes mount type, feet required, and rails required info.
     */
    public Mount getMountDetails() {
        return new Mount(mountDetails);
        }

    /**
     * Returns the number of shelves
     * @return maxShelfCount Maximum number of shelves.
     */
    public Integer getMaxShelfCount() { return maxShelfCount; }

    /**
     * Get the maximum drawer Counts
     * @return maxDrawerCount Maximum number of drawers the cabinet has.
     */
    public Integer getMaxDrawerCount() { return maxDrawerCount; }

    /**
     * Returns a Boolean representing if a wall fix is required for the Cabinet.
     * @return wallFixRequired True if a wall fixture is required, otherwise, false.
     */
    public Boolean getWallFixRequired() { return wallFixRequired; }

    /**
     * Adds cabinet accessories to an accessories list.
     *
     * Checks if the following need to be added:
     * - Cabinet feet
     * - Wall fixture for earthquake safety
     * - Rails (for a specified style or standard type)
     */
    public void addAccessories (CustomerRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Cannot add accessories due to null CustomerRequest Object.");
        }
        List<Accessory> tempAccessoryList = new ArrayList<>();

        // Add the FEET accessory if required or optional.
        Integer totalFeetQuantity = calculateTotalFeet(request.getNumOfCabinets());
        Double feetPriceTotal = calculateTotalFeetPrice(totalFeetQuantity, super.getAccessoryPrices().getCornerFeetPrice());
        if (!mountDetails.getFeetRequirement().equals(Feet.NOT_REQUIRED)) {
        tempAccessoryList.add(new Accessory(AccessoryName.FEET, totalFeetQuantity,
            feetPriceTotal));
        }

        // Add WALL FIXTURE if required.
        if (wallFixRequired) {
        tempAccessoryList.add(new Accessory(AccessoryName.WALL_FIXTURE, 1, super.getAccessoryPrices().getFixAttachmentPrice()));
        }

        // Add a STYLE SPECIFIC MOUNT RAIL if required.
        if (mountDetails.getRailsRequirement().equals(Rails.STYLE_SPECIFIC) && super.getStyle().toUpperCase().equals("ATREIDES")) {
        tempAccessoryList.add(new Accessory(AccessoryName.RAILS_ATREIDES, 1,
        super.getAccessoryPrices().getRailsPrice()));
        }
        // Add STANDARD RAIL if required
        if (mountDetails.getRailsRequirement().equals(Rails.STANDARD)) {
        tempAccessoryList.add(new Accessory(AccessoryName.RAILS_STANDARD, 1,
        super.getAccessoryPrices().getRailsPrice()));
        }

        // Set list
        super.setAccessories(tempAccessoryList);
        }

    /**
     * Helper function Calculating the quantity of feet pieces for an accessory to add.
      * @param numOfCabinets Num of feet required for a cabinet.
     * @return totalFeet Number of feet required for the number of cabinets requested for an order.
     */
    private Integer calculateTotalFeet(Integer numOfCabinets) {
        Integer totalFeet = 1;
        if (numOfCabinets.intValue() > 0) {
            totalFeet =  2 * numOfCabinets + 2;
        }
        return totalFeet;
    }

    /**
     * Calculates the total feet price based on how many cabinets needed multiplied for the price
     * of each foot.
     * @param feetQuantity Integer for how many feet needed.
     * @param feetPrice Double value for the price of each foot in dollars and cents.
     * @return totalFeetPrice Double for the calculated price total for the number of feet needed.
     */
    private Double calculateTotalFeetPrice(Integer feetQuantity, Double feetPrice) {
        return feetQuantity * feetPrice;
    }
    /**
     * Returns information if the CabinetFront matches with this Cabinet Object
     * @param cabinetFront CabinetFrontObject to compare with this object.
     * @return true/false true if the Cabinet Front matches with this Cabinet
     *  based on height and color.
     */
     public Boolean isFurnitureMatch(CabinetFront cabinetFront) {
        if (this.getColors().equals(cabinetFront.getColors()) &&
        this.getFurnitureSize().getHeight().equals(cabinetFront.getFurnitureSize().getHeight())) {
            return true;
        }
        return false;
     }

    /**
     * Equals method for a Cabinet Object
     * @param obj The object to compare with.
     * @return true/false True if objects are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
        return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
        return false;
        }
        Cabinet cabinet = (Cabinet) obj;
        return Objects.equals(mountDetails, cabinet.mountDetails) &&
        Objects.equals(maxShelfCount, cabinet.maxShelfCount) &&
        Objects.equals(maxDrawerCount, cabinet.maxDrawerCount) &&
        Objects.equals(wallFixRequired, cabinet.wallFixRequired);
    }

    /**
     * Returns a hashcode
     * @return hashcode representation of this Cabinet object
     */
    @Override
    public int hashCode() {
        return Objects.hash(mountDetails, maxShelfCount, maxDrawerCount, wallFixRequired);
    }

    /**
     * Returns details of a cabinet Object.
     * @return String representation of a Cabinet Object
     */
    @Override
    public String toString() {
        return super.toString() +
        "Cabinet{" +
        "mountDetails=" + mountDetails +
        ", maxShelfCount=" + maxShelfCount +
        ", maxDrawerCount=" + maxDrawerCount +
        ", wallFixRequired=" + wallFixRequired +
        "}\n";
    }
}
