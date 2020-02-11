package assignment2_refactored;

import assignment2_refactored.Enums.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * MountTest.java
 * Tests the following:
 * - Getters for cabmount, feet requirement, rails requirement.
 * - Equals, hashcode, toString.
 */
public class MountTest {
  private Mount mountTest1;
  private Mount copyMount1;
  private Mount mountTest2;
  private Mount mountTest3;
  private Size testSize;
  @Before
  public void setUp() throws Exception {
    mountTest1 = new Mount(MountType.FLOOR_OR_WALL, Feet.NOT_REQUIRED, Rails.NOT_REQUIRED);
    copyMount1 = new Mount(MountType.FLOOR_OR_WALL, Feet.NOT_REQUIRED, Rails.NOT_REQUIRED);
    mountTest2 = new Mount(MountType.FLOOR, Feet.REQUIRED, Rails.STANDARD);
    mountTest3 = new Mount(MountType.WALL, Feet.OPTIONAL, Rails.STYLE_SPECIFIC);
    testSize = new Size(36, 36, 18);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCopyConstructorWithNull() {
    mountTest1 = new Mount(null);
  }

  @Test
  public void getCabMount() {
    Assert.assertEquals(MountType.FLOOR_OR_WALL, mountTest1.getCabMount());
    Assert.assertEquals(MountType.FLOOR, mountTest2.getCabMount());
    Assert.assertEquals(MountType.WALL, mountTest3.getCabMount());
  }

  @Test
  public void getFeetRequirement() {
    Assert.assertEquals(Feet.NOT_REQUIRED, mountTest1.getFeetRequirement());
    Assert.assertEquals(Feet.REQUIRED, mountTest2.getFeetRequirement());
    Assert.assertEquals(Feet.OPTIONAL, mountTest3.getFeetRequirement());
  }

  @Test
  public void getRailsRequired() {
    Assert.assertEquals(Rails.NOT_REQUIRED, mountTest1.getRailsRequirement());
    Assert.assertEquals(Rails.STANDARD, mountTest2.getRailsRequirement());
    Assert.assertEquals(Rails.STYLE_SPECIFIC, mountTest3.getRailsRequirement());
  }

  @Test
  public void testEqualsSameObj() {
    Assert.assertTrue(mountTest1.equals(copyMount1));
  }

  @Test
  public void testEqualsDiffObj() {
    Assert.assertFalse(mountTest1.equals(testSize));
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(mountTest1.hashCode(),copyMount1.hashCode());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("Mount{cabMount=FLOOR_OR_WALL, feetRequirement=NOT_REQUIRED, "
        + "railsRequirement=NOT_REQUIRED}", mountTest1.toString());
    Assert.assertEquals("Mount{cabMount=FLOOR, feetRequirement=REQUIRED, "
        + "railsRequirement=STANDARD}", mountTest2.toString());
    Assert.assertEquals("Mount{cabMount=WALL, feetRequirement=OPTIONAL, "
        + "railsRequirement=STYLE_SPECIFIC}", mountTest3.toString());
  }
}