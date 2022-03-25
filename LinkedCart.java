public class LinkedCart {
  private Cart CART; // data field of this linked cart
  private LinkedCart previous; // reference to the previous linked cart in a list of carts
  private LinkedCart next; // reference to the next linked car in a list of carts

  /**
   * constructor of LinkedCart with only cart
   * 
   * @param cart holds cargo
   */
  public LinkedCart(Cart cart) {
    CART = cart;
    previous = null;
    next = null;
  }

  /**
   * constructor of LinkedCart with cart, previous, and next
   * 
   * @param cart     holds cargo
   * @param previous previous cart
   * @param next     cart
   */
  public LinkedCart(Cart cart, LinkedCart previous, LinkedCart next) {
    CART = cart;
    this.previous = previous;
    this.next = next;
  }

  /**
   * Accessor for previous
   * 
   * @return previous
   */
  public LinkedCart getPrevious() {
    return previous;
  }

  /**
   * Mutator for previous
   * 
   * @param previous
   */
  public void setPrevious(LinkedCart previous) {
    this.previous = previous;
  }

  /**
   * Accessor for next
   * 
   * @return next
   */
  public LinkedCart getNext() {
    return next;
  }

  /**
   * Mutator for next
   * 
   * @param next
   */
  public void setNext(LinkedCart next) {
    this.next = next;
  }

  /**
   * Accessor for CART
   * 
   * @return CART
   */
  public Cart getCart() {
    return CART;
  }

}
