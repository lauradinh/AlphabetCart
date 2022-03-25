
public class AlphabetListTester {

  /**
   * This method should test and make use of at least the LinkedCart constructor, an accessor
   * (getter) method, and a mutator (setter) method defined in the LinkedCart class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedCart() {
    // test 1 - one argument constructor
    LinkedCart cart1 = new LinkedCart(new Cart("A"));
    if (!cart1.getCart().getCargo().equals("A")) {
      return false;
    }
    // test 2 - 3 argument constructor
    LinkedCart cart2 =
        new LinkedCart(new Cart("B"), new LinkedCart(new Cart("A")), new LinkedCart(new Cart("C")));
    if (!cart2.getCart().getCargo().equals("B") || cart2.getNext().getCart().equals("C")
        || cart2.getNext().getCart().equals("A")) {
      return false;
    }

    return true;
  }

  /**
   * This method checks for the correctness of both AlphabetList constructors and the instance
   * method isEmpty() when called on an empty alphabet list object.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListConstructorIsEmpty() {
    // empty constructor
    AlphabetList letters = new AlphabetList();
    if (!letters.isEmpty()) {
      return false;
    }
    // constructor with parameters
    return true;
  }

  /**
   * This method checks for the correctness of the AlphabetList(int) constructor when passed a
   * negative int value.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListConstructorBadInput() {
    try {
      AlphabetList letters = new AlphabetList(-5);
    } catch (IllegalArgumentException e) {
      if (!e.getMessage()
          .equals("The capacity of this list must be a non-zero a positive integer.")) {
        System.out.println("Does not catch the correct IllegalArgumentException");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Does not catch the corret exception");
      return false;
    }
    return true;
  }


  /**
   * This method checks for the correctness of the AlphabetList.add() method when it is passed bad
   * inputs. This method must consider at least the test scenarios provided in the detailed
   * description of these javadocs. (1) Try adding a null to the list; (2) Try adding a cart which
   * carries a non upper-case alphabet letter, for instance "Hello" or "1" or "a". (3) Try adding a
   * duplicate cart to the list.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListAddBadInput() {
    AlphabetList letters = new AlphabetList();
    // null added to list
    try {
      letters.add(null);
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals(
          "Can only add carts carrying one upper-case alphabetic letter in the range A .. Z.")) {
        System.out.println("Does not catch the correct IllegalArgumentException: 1");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Does not catch the correct exception");
      e.printStackTrace();
      return false;
    }
    // non upper-case alphabet letter
    try {
      letters.add(new Cart("a"));
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals(
          "Can only add carts carrying one upper-case alphabetic letter in the range A .. Z.")) {
        System.out.println("Does not catch the correct IllegalArgumentException: 2");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Does not catch the correct exception");
      e.printStackTrace();
      return false;
    }
    // duplicate cart
    letters.add(new Cart("B"));
    try {
      letters.add(new Cart("B"));
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Cannot duplicate letters or carts in this list.")) {
        System.out.println("Does not catch the correct IllegalArgumentException: 3");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Does not catch the correct exception");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * This method checks for the correctness of the AlphabetList.add() considering at least the test
   * scenarios provided in the detailed description of these javadocs. (1) Try adding a cart to an
   * empty list; (2) Try adding a cart which is expected to be added at the head of a non-empty
   * alphabet list; (3) Try adding a cart which is expected to be added at the end of a non-empty
   * alphabet list; (4) Try adding a cart which is expected to be added at the middle of a non-empty
   * alphabet list. For each of those scenarios, make sure that the size of the list is
   * appropriately updated after a call without errors of the add() method, and that the contents of
   * the list is as expected whatever if list is read in the forward or backward directions. You can
   * also check the correctness of AlphabetList.get(int), AlphabetList.indexOf(Cart), and
   * AlphabetList.size() in this test method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListAdd() {
    // check if size is correct after every add
    AlphabetList letters = new AlphabetList();
    // empty cart
    letters.add(new Cart("F"));
    if (!letters.readForward().equals("F")) {
      System.out
          .println("Did not print out F as expected. Instead prints " + letters.readForward());
      return false;
    }
    if (!letters.readBackward().equals("F")) {
      System.out
          .println("Did not print out F as expected. Instead prints " + letters.readBackward());
    }
    if (letters.size() != 1) {
      System.out
          .println("Size is incorrect. Should be 1 but instead prints out " + letters.size());
    }
    // add at head
    letters.add(new Cart("A"));
    if (!letters.readForward().equals("AF")) {
      System.out.println("Did not print AF as expected. Instead prints " + letters.readForward());
      return false;
    }
    if (!letters.readBackward().equals("FA")) {
      System.out
          .println("Did not print out FA as expected. Instead prints " + letters.readBackward());
    }
    if (letters.size() != 2) {
      System.out
          .println("Size is incorrect. Should be 2 but instead prints out " + letters.size());
    }
    // add to the end
    letters.add(new Cart("Z"));
    if (!letters.readForward().equals("AFZ")) {
      System.out.println("Did not print AFZ as expected. Instead prints " + letters.readForward());
      return false;
    }
    if (!letters.readBackward().equals("ZFA")) {
      System.out
          .println("Did not print out ZFA as expected. Instead prints " + letters.readBackward());
    }
    if (letters.size() != 3) {
      System.out
          .println("Size is incorrect. Should be 3 but instead prints out " + letters.size());
    }
    // add to the middle
    letters.add(new Cart("B"));
    if (!letters.readForward().equals("ABFZ")) {
      System.out.println("Did not print ABFZ. Instead prints " + letters.readForward());
      return false;
    }
    if (!letters.readBackward().equals("ZFBA")) {
      System.out
          .println("Did not print out ZFBA as expected. Instead prints " + letters.readBackward());
    }
    if (letters.size() != 4) {
      System.out
          .println("Size is incorrect. Should be 4 but instead prints out " + letters.size());
    }
    return true;
  }

  /**
   * This method checks for the correctness of the AlphabetList.remove() considering at least the
   * test scenarios provided in the detailed description of these javadocs. (1) Try removing a cart
   * from an empty list or pass a negative index to AlphabetList.remove() method; (2) Try removing a
   * cart (at position index 0) from a list which contains only one cart; (3) Try to remove a cart
   * (position index 0) from a list which contains at least 2 carts; (4) Try to remove a cart from
   * the middle of a non-empty list containing at least 3 carts; (5) Try to remove the cart at the
   * end of a list containing at least two carts. (6) Try to remove a cart from a list containing
   * only one cart. For each of those scenarios, make sure that the size of the list is
   * appropriately updated after a call without errors of the add() method, and that the contents of
   * the list is as expected whatever if list is read in the forward or backward directions.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListRemove() {
    AlphabetList letters = new AlphabetList();

    // remove from empty list
    try {
      letters.remove(1);
    } catch (IndexOutOfBoundsException e) {
      if (!e.getMessage().equals("Invalid index.")) {
        System.out.println("Does not catch correct IndexOutOfBoundsException");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Does not catch the correct exception");
      return false;
    }
    // pass negative index
    try {
      letters.remove(-4);
    } catch (IndexOutOfBoundsException e) {
      if (!e.getMessage().equals("Invalid index.")) {
        System.out.println("Does not catch correct IndexOutOfBoundsException");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Does not catch the correct exception");
      return false;
    }

    letters.add(new Cart("A"));

    // remove a cart at index 0 with one cart
    if (!letters.remove(0).getCargo().equals("A")) {
      System.out.println("Did not remove A");
      return false;
    }
    if (letters.size() != 0) {
      System.out.println("Size should be 0");
      return false;
    }

    letters.add(new Cart("B"));
    letters.add(new Cart("Z"));
    // remove a cart at index 0 with two carts
    if (!letters.remove(0).getCargo().equals("B")) {
      System.out.println();
      System.out.println("Could not remove B");
      System.out.println(letters.readForward());
      return false;
    }
    if (!letters.readForward().equals("Z")) {
      System.out.println("Does not print forward Z. Instead " + letters.readForward());
      return false;
    }
    if (!letters.readBackward().equals("Z")) {
      System.out.println("Does not print backwards Z. Instead " + letters.readBackward());
      return false;
    }
    if (letters.size() != 1) {
      System.out.println("Size should be 1");
      return false;
    }

    letters.add(new Cart("A"));
    letters.add(new Cart("B"));
    letters.add(new Cart("C"));
    // remove a cart from the middle with 3 carts
    if (!letters.remove(1).getCargo().equals("B")) {
      System.out.println("Could not remove B");
      System.out.println(letters.readForward());
      return false;
    }
    if (letters.size() != 3) {
      System.out.println("Size should be 3");
      return false;
    }
    if (!letters.readForward().equals("ACZ")) {
      System.out.println("Does not print forward ACZ. Instead " + letters.readForward());
      return false;
    }
    if (!letters.readBackward().equals("ZCA")) {
      System.out.println("Does not print backwards ZCA. Instead " + letters.readBackward());
      return false;
    }
    // remove a cart from the end with two carts
    if (!letters.remove(2).getCargo().equals("Z")) {
      System.out.println("Could not remove Z");
      System.out.println(letters.readBackward());
      System.out.println(letters.readForward());
      return false;
    }
    if (letters.size() != 2) {
      System.out.println("Size should be 2");
      return false;
    }
    if (!letters.readForward().equals("AC")) {
      System.out.println("Does not print forward AC. Instead " + letters.readForward());
      return false;
    }
    if (!letters.readBackward().equals("CA")) {
      System.out.println("Does not print backwards CA. Instead " + letters.readBackward());
      return false;
    }

    // 5 carts and removes first one
    letters.add(new Cart("B"));
    letters.add(new Cart("D"));
    letters.add(new Cart("E"));
    if (!letters.remove(0).getCargo().equals("A")) {
      System.out.println("Could not remove A");
      System.out.println(letters.readForward());
      return false;
    }
    if (!letters.readForward().equals("BCDE")) {
      System.out.println("Does not print forward BCDE. Instead " + letters.readForward());
      return false;
    }
    if (!letters.readBackward().equals("EDCB")) {
      System.out.println("Does not print backwards EDCB. Instead " + letters.readBackward());
      return false;
    }
    if (letters.size() != 4) {
      System.out.println("Size should be 4");
      return false;
    }
    if (!letters.remove(letters.size() - 1).getCargo().equals("E")) {
      System.out.println("Could not remove E");
      System.out.println(letters.readForward());
      return false;
    }
    if (letters.size() != 3) {
      System.out.println("Size should be 3");
      return false;
    }
    return true;
  }

  /**
   * This method checks if AlphabetList.clear() clears all LinkedCarts from AlphabetList
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListClear() {
    AlphabetList letters = new AlphabetList();

    letters.add(new Cart("B"));
    letters.add(new Cart("Z"));
    letters.add(new Cart("G"));
    letters.add(new Cart("Q"));
    letters.add(new Cart("A"));
    letters.add(new Cart("R"));
    letters.clear();
    if (letters.size() != 0 || !letters.readForward().equals("")) {
      return false;
    }
    return true;
  }

  /**
   * This method checks if AlphabetList.indexOf() returns the correct index
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListIndexOf() {
    AlphabetList letters = new AlphabetList();
    letters.add(new Cart("B"));
    letters.add(new Cart("Z"));
    letters.add(new Cart("G"));
    letters.add(new Cart("Q"));
    letters.add(new Cart("A"));
    letters.add(new Cart("R"));
    // Invalid index
    if (letters.indexOf(new Cart("L")) != -1) {
      System.out.println("Did not return -1 for invalid cart");
      return false;
    }
    // Valid index
    if (letters.indexOf(new Cart("A")) != 0) {
      System.out.println("Did not return 0 for valid cart");
      return false;
    }
    return true;

  }

  /**
   * This method checks if AlphabetList.get() returns the correct cart
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListGet() {
    AlphabetList letters = new AlphabetList();

    letters.add(new Cart("B"));
    letters.add(new Cart("Z"));
    letters.add(new Cart("G"));
    letters.add(new Cart("Q"));
    letters.add(new Cart("A"));
    letters.add(new Cart("R"));
    if (!letters.get(5).getCargo().equals("Z")) {
      return false;
    }
    if (!letters.get(0).getCargo().equals("A")) {
      return false;
    }
    return true;
  }

  /**
   * This method calls all the test methods defined and implemented in your AlphabetListTester
   * class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    if (!testAlphabetListAddBadInput()) {
      return false;
    }
    if (!testLinkedCart()) {
      return false;
    }
    if (!testAlphabetListConstructorIsEmpty()) {
      return false;
    }
    if (!testAlphabetListAdd()) {
      return false;
    }
    if (!testAlphabetListClear()) {
      return false;
    }
    if (!testAlphabetListIndexOf()) {
      return false;
    }
    if (!testAlphabetListGet()) {
      return false;
    }
    if (!testAlphabetListRemove()) {
      return false;
    }
    if (!testAlphabetListAddBadInput()) {
      return false;
    }
    if (!testAlphabetListRemove()) {
      return false;
    }

    return true;
  }


  /**
   * Driver method defined in this AlphabetListTester class
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    // System.out.println("Testing testLinkedCart(): " + testLinkedCart());
    // System.out.println(
    // "testing testAlphabetListConstructorIsEmpty(): " + testAlphabetListConstructorIsEmpty());
    // System.out.println(
    // "testing testAlphabetListConstructorBadInput(): " + testAlphabetListConstructorBadInput());
    // System.out.println("testing testAlphabetListAdd(): " + testAlphabetListAdd());
    // System.out.println("testing testAlphabetListClear(): " + testAlphabetListClear());
    // System.out.println("testing testAlphabetListIndexOf(): " + testAlphabetListIndexOf());
    // System.out.println("testing testAlphabetListGet(): " + testAlphabetListGet());
    // System.out.println("testing testAlphabetListRemove() " + testAlphabetListRemove());
    // System.out.println("testing testAlphabetListAddBadInput() " + testAlphabetListAddBadInput());
    // System.out.println("testing testAlphabetListRemove(): " + testAlphabetListRemove());
     System.out.println("Running all tests: " + runAllTests());

  }
}
