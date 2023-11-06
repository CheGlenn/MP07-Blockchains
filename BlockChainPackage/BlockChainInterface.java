package BlockChainPackage;
import java.io.PrintStream;
import java.security.NoSuchAlgorithmException;

public interface BlockChainInterface {
  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * mines a new candidate block to be added to the end of the chain
   * @return the mined block
   */
  public Block mine(int amount) throws NoSuchAlgorithmException;

  /**
   * @return the size of the block chain
   */
  public int getSize();
    
  /**
   * It appends the input block at the end of the chain, but it throws an 
   * exception when the block is invalid.
   * @throws IllegalArgumentException
   */
  public void append(Block blk) throws IllegalArgumentException;

  /**
   * removes the last block from the chain, returning true. If the chain only 
   * contains a single block, then removeLast does nothing and returns false.
   * @return whether a block is successfully removed.
   */
  public boolean removeLast();

  /**
   * @return the last hash block
   */
  public Hash getHash();

  /**
   * Checks each of the blocks and whether the account value goes to negative 
   * values or not.
   * @return if it is valid or not.
   */
  public boolean isValidBlockChain();

  /**
   * prints the balances of the block chain.
   */
  public void printBalances(PrintStream pen);

  /**
   * @return the String version of block
   */
  public String toString();
} // interface BlockInterface
