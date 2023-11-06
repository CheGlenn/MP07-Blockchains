package BlockChainPackage;
public interface BlockInterface {
  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * @return the number of the block in the chain
   */
  public int getNum();
    
  /**
   * @return amount transferred to the account
   */
  public int getAmount();

  /**
   * @return the nonce of the block
   */
  public long getNonce();

  /**
   * @return the previous hash
   */
  public Hash getPrevHash();

  /**
   * @return the current hash
   */
  public Hash getHash();

  /**
   * @return the String version of block
   */
  public String toString();
} // interface BlockInterface
