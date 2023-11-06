package BlockChainPackage;
public interface HashInterface {
  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Retrieves the data from the hash object
   * @return the data associated with the hash block
   */
  public byte[] getData();

  /**
   * Goes through the hash object, and checks whether first three bytes are
   * 0s and whether the length is more than or equal to 3.
   * @return whether the hash is valid 
   */
  public boolean isValid();

  /**
   * Returns the string version of a hash object with 
   * @return the hash object
   */
  public String toString();

  /**
   * @param other
   * @return whether the input object is structually equal to the argument.
   */
  public boolean equals(Object other);
} // interface HashInterface