package assignment2_refactored;

import assignment2_refactored.Enums.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * AccessoryTest.java
 * Tests the Accessory class for the following:
 * - Getters for name, quantity, and price.
 * - Equals, Hashcode, and ToSring functions.
 */
public class AccessoryTest {

  private Accessory <Rails> testAccessory;
  private Accessory <Rails> copyTestAccessory;
  private Accessory <AccessoryName> diffTestAccessory;

  @Before
  public void setUp() throws Exception {
    testAccessory = new Accessory(Rails.STANDARD, 1,  3.22);
    copyTestAccessory = new Accessory(Rails.STANDARD, 1,  3.22);
    diffTestAccessory = new Accessory(AccessoryName.WALL_FIXTURE, 1, 5.44);
  }

  @Test
  public void getName() {
    Assert.assertEquals(Rails.STANDARD, testAccessory.getName());
  }

  @Test
  public void getQuantity() {
    Assert.assertEquals((Integer)1, testAccessory.getQuantity());
  }

  @Test
  public void getPrice() {
    Assert.assertEquals((Double)3.22, testAccessory.getPrice());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullCopyConstructor() {
    diffTestAccessory = null;
    testAccessory = new Accessory(diffTestAccessory);
  }

  @Test
  public void testValidCopyConstructor() {
    testAccessory = new Accessory(diffTestAccessory);
    Assert.assertEquals(AccessoryName.WALL_FIXTURE,testAccessory.getName());
    Assert.assertEquals((Integer)1,testAccessory.getQuantity());
    Assert.assertEquals((Double)5.44, testAccessory.getPrice());
  }


  @Test
  public void testEqualsSameObj() {
    Assert.assertTrue(testAccessory.equals(copyTestAccessory));
  }

  @Test
  public void testEqualsDiffObj() {
    Assert.assertFalse(testAccessory.equals(diffTestAccessory));
  }

  @Test
  public void testEqualsNullObj() {
    diffTestAccessory = null;
    Assert.assertFalse(testAccessory.equals(diffTestAccessory));
  }

  @Test
  public void testHashcode() {
    Assert.assertEquals(testAccessory.hashCode(),copyTestAccessory.hashCode());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("Accessory{name='STANDARD'quantity='1', price=3.22}",testAccessory.toString());
  }
}