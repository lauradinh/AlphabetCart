public class AlphabetList implements SortedListADT<Cart> {
  private static final Cart MIN_CART = new Cart("A"); // The smallest cart that can be added to this
                                                      // sorted list
  private static final Cart MAX_Cart = new Cart("Z"); // The largest cart that can be added to this
                                                      // sorted list
  private LinkedCart head; // head of this doubly linked list
  private LinkedCart tail; // tail of this doubly linked list
  private int size; // size of this list
  private int capacity; // maximum number of carts which can be stored in this list

  /**
   * Creates an empty doubly linked list of carts with a capacity equals to 26
   */
  public AlphabetList() {
    this.capacity = 26;
    size = 0;
    head = null;
    tail = null;
  }

  /**
   * Creates an empty doubly linked list of carts with a given capacity
   * 
   * @param capacity maximum number of carts to be connected in this list of carts
   * @throws IllegalArgumentException with the following error message "The capacity of this list
   *                                  must be a non-zero a positive integer." if capacity is zero or
   *                                  negative
   */
  public AlphabetList(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException(
          "The capacity of this list must be a non-zero a positive integer.");
    } else {
      this.capacity = capacity;
    }
    size = 0;
    head = null;
    tail = null;
  }

  /**
   * Returns a String representation of this list. Note that the implementation details of this
   * method is provided in the assignment's specification.
   * 
   * @return a String representation of this list
   */
  public String toString() {
    String string = "This list contains " + size + " cart(s)";
    if (size == 0) {
      return string;
    }
    string += ": [ ";
    LinkedCart currentCart = head;
    while (currentCart != null) {
      string += currentCart.getCart().toString() + " ";
      currentCart = currentCart.getNext();
    }
    string += "]";
    return string;

  }

  /**
   * Checks whether this list is empty.
   * 
   * @return true if the list is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    if (size == 0 && head == null && tail == null) {
      return true;
    }
    return false;
  }

  /**
   * Returns the capacity of this list in terms of maximum number of carts which can be stored in
   * it.
   * 
   * @return the capacity of this list
   */
  public int capacity() {
    return capacity;
  }

  /**
   * Adds a new cart to this sorted list.
   * 
   * @param newCart - to add to this list
   * @throws IllegalArgumentException with a descriptive error message if newCart does not carry one
   *                                  upper-case letter in the range "A" .. "Z" or if this list
   *                                  contains already a cart carrying the same letter. The
   *                                  descriptive error messages for these two cases can be
   *                                  respectively "Can only add carts carrying one upper-case
   *                                  alphabetic letter in the range A .. Z.", and "Cannot duplicate
   *                                  letters or carts in this list."
   * @throws IllegalStateException    with the following error message "This list is full. Cannot
   *                                  add another cart." if this list reached its capacity
   */
  @Override
  public void add(Cart newCart) {
    if (size == capacity) {
      throw new IllegalStateException("This list is full. Cannot add another cart");
    }
    if (newCart == null || MIN_CART.compareTo(newCart) > Math.abs(25)) {
      throw new IllegalArgumentException(
          "Can only add carts carrying one upper-case alphabetic letter in the range A .. Z.");
    }
    if (isEmpty()) {
      head = new LinkedCart(newCart);
      tail = head;
      size++;
      return;
    }
    if (head.getCart().compareTo(newCart) > 0) {
      LinkedCart nextCart = head;
      head = new LinkedCart(newCart, tail, nextCart);
      size++;
      return;
    }
    LinkedCart temp = head;

    while (temp.getNext() != null) {
      if (temp.getCart().compareTo(newCart) == 0) {
        throw new IllegalArgumentException("Cannot duplicate letters or carts in this list.");
      }
      if (temp.getCart().compareTo(newCart) < 0
          && temp.getNext().getCart().compareTo(newCart) > 0) {
        LinkedCart nextCart = new LinkedCart(newCart, temp, temp.getNext());
        temp.getNext().setPrevious(nextCart);
        temp.setNext(nextCart);
        temp = temp.getNext();
      }
      temp = temp.getNext();
    }


    if (tail.getCart().compareTo(newCart) < 0 && tail.getNext() == null) {
      LinkedCart newTail = new LinkedCart(newCart, tail, null);
      tail.setNext(newTail);
      tail = newTail;
    }
    size++;



  }

  /**
   * Returns the size of this list
   * 
   * @return the number of carts linked in this list
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Removes all the carts from this list. This list must be empty after this method returns.
   */
  @Override
  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Returns the cart at position index of this list without removing it
   * 
   * @param index - of the cart to return
   * @return the cart of this sorted list at the given index
   * @throws IndexOutOfBoundsException with the following error message "Invalid index." if index is
   *                                   less than 0 or index is greater or equal to size()
   */
  @Override
  public Cart get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
    LinkedCart cart = head;
    for (int i = 0; i < index; i++) {
      cart = cart.getNext();
    }
    return cart.getCart();
  }

  /**
   * Returns the index of the cart carrying data within this list
   * 
   * @param findCart - cart to find in this list
   * @return the index of findCart within this list or -1 if this list does not contain that cart.
   */
  @Override
  public int indexOf(Cart findCart) {
    int index = -1;
    LinkedCart temp = head;
    while (temp.getNext() != null) {
      if (temp.getCart().compareTo(findCart) == 0) {
        index++;
        return index;
      }
      index++;
      temp = temp.getNext();
    }
    if (temp.getCart().compareTo(findCart) == 0) {
      index++;
      return index;
    }
    return -1;
  }

  /**
   * Returns and removes the cart from this sorted list at the given index position
   * 
   * @param index - of the cart to be removed
   * @return the removed cart
   * @throws IndexOutOfBoundsException - with the following error message "Invalid index." if index
   *                                   is less than 0 or index is larger or equal to size()
   */
  @Override
  public Cart remove(int index) {
    if (index < 0 || index >= size || size == 0) {
      throw new IndexOutOfBoundsException("Invalid index.");
    }
    LinkedCart temp;
    if (size == 1) {
      temp = head;
      head = null;
      tail = null;
    } else if (index == 0) {
      temp = head;
      head = head.getNext();
      head.setPrevious(null);
      temp.setNext(null);
      temp.setPrevious(null);
    } else if (index != size - 1) {
      temp = head;
      for (int i = 0; i < index; i++) {
        temp = temp.getNext();
      }
      LinkedCart next = temp.getNext();
      LinkedCart previous = temp.getPrevious();
      next.setPrevious(previous);
      previous.setNext(next);
      temp.setNext(null);
      temp.setPrevious(null);
    } else {
      temp = tail;
      LinkedCart previous = temp.getPrevious();
      previous.setNext(null);
      temp.setNext(null);
      temp.setPrevious(null);
      tail = previous;
    }
    size--;
    return temp.getCart();
  }

  /**
   * Reads the contents of this list in the forward direction starting from its head.
   * 
   * @return a String representation of the contents of this list read in the forward direction or
   *         an empty string if this list is empty. For instance, if the list contains the following
   *         letters "A", "B", "D", "M", and "O". This method MUST return the following string
   *         "ABDMO".
   */
  public String readForward() {
    String string = "";
    LinkedCart currentCart = head;
    while (currentCart != null) {
      string += currentCart.getCart().toString();
      currentCart = currentCart.getNext();
    }
    return string;
  }

  /**
   * Reads the contents of this list in the backward direction starting from its tail
   * 
   * @return a String representation of the contents of this list read in the backward direction or
   *         an empty string if this list is empty. For instance, if the list contains the following
   *         letters "A", "B", "D", "M", and "O". This method MUST return the following string
   *         "OMDBA".
   */
  public String readBackward() {
    String string = "";
    LinkedCart currentCart = tail;
    if (head.getCart().compareTo(tail.getCart()) == 0) {
      return tail.getCart().toString();
    }
    for (int i = 0; i < size - 1; i++) {
      string += currentCart.getCart().toString();
      currentCart = currentCart.getPrevious();
    }
    string += head.getCart();
    return string;
  }
}
