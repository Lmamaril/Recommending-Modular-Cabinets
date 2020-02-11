package assignment2_refactored;

import assignment2_refactored.Enums.*;
import java.util.Objects;


/**
 * Mount.java
 * Contains information for Cabinet Mount details.
 */
public class Mount {
  private MountType cabMount;
  private Feet feetRequirement;
  private Rails  railsRequirement;

  /**
   * Constructor for the Mount Class.
   * @param cabMount Cabinet mount type (refer to MountType enum).
   * @param feetRequirement Cabinet feet requirement (refer to Feet enum).
   * @param railsRequirement Rail requirement (refer to Rails enum).
   */
  public Mount(MountType cabMount, Feet feetRequirement, Rails railsRequirement) {
    this.cabMount = cabMount;
    this.feetRequirement= feetRequirement;
    this.railsRequirement = railsRequirement;
  }

  /**
   * Copy Constructor for the Mount Class.
   * @param otherMountObject The object to copy details into this object.
   */
  public Mount(Mount otherMountObject) {
    if (otherMountObject == null) {
      throw new IllegalArgumentException("Mount object cannot be created in the copy constructor.");
    }
    this.cabMount = otherMountObject.getCabMount();
    this.feetRequirement = otherMountObject.getFeetRequirement();
    this.railsRequirement = otherMountObject.getRailsRequirement();
  }

  /**
   * Returns the cabinet mount type
   * @return cabMount Floor only, wall only , Either Floor or wall
   */
  public MountType getCabMount() {
    return cabMount;
  }

  /**
   * Returns the feet requirement for the cabinet.
   * @return feetRequirement Feet requirement is required, optional, or not required/
   */
  public Feet getFeetRequirement() {
    return feetRequirement;
  }

  /**
   * Returns the rail requirement for a furniture type/
   * @return railsRequirement Rail requirement is either Brand-Specific, Standard, or Not Required.
   */
  public Rails getRailsRequirement() {
    return railsRequirement;
  }

  /**
   * Compares with another object to see if the other object is equal to this
   * Mount object.
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
    Mount mount = (Mount) obj;
    return cabMount.equals(mount.cabMount) &&
        feetRequirement.equals(mount.feetRequirement) &&
        railsRequirement.equals(mount.railsRequirement);
  }

  /**
   * Returns a hashcode
   * @return hashcode Integer representing hashcode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(cabMount, feetRequirement, railsRequirement);
  }

  /**
   * Return a string representation of Mount information.
   * @return  Mount information
   */
  @Override
  public String toString() {
    return "Mount{" +
        "cabMount=" + cabMount +
        ", feetRequirement=" + feetRequirement +
        ", railsRequirement=" + railsRequirement +
        '}';
  }
}

