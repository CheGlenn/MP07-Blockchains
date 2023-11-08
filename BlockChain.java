import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

/**
 * An Implementation for block chain. Some more methods added for convenience.
 * 
 * @author Seunghyeon (Hyeon) Kim
 * @author Che Glenn
 */

public class BlockChain implements BlockChainInterface{
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+
  /* Front node */
  Node<Block> front;
  /* Last node */
  Node<Block> last;
  /**
   * Creates a block chain with front node and last node.
   * @param initial
   */
  public BlockChain(int initial) throws NoSuchAlgorithmException{
    //STUB
  } // BlockChain (int)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+
  /**
   * @return Block mined
   */
  public Block mine(int amount) throws NoSuchAlgorithmException{
    //STUB
    return new Block(0, 0, new Hash(new byte[] {}));
  } // mine (int)

  /**
   * @return the size of the blockChain
   */
  public int getSize(){
    //STUB
    return 0;
  } // getSize()

  /**
   * Adds a block to the last.
   * @return nothing
   * @throws IllegalArgumentException
   */
  public void append(Block blk) throws IllegalArgumentException{
    //STUB
  } // append (Block)

  /**
   * removes the last node
   * @return whether the last node is successfully removed
   */
  public boolean removeLast() {
    //STUB
    return false;
  } // removeLast ()

  /**
   * @return the last node's hash
   */
  public Hash getHash(){
    return this.last.value.getHash();
  } // getHash ()
  
  /**
   * @return Whether the blockChain is valid (i.e. each of the blocks are 
   * valid and the amount is never negative)
   */
  public boolean isValidBlockChain(){
    //STUB
    return true;
  } // isValidBlockChain ()

  /**
   * prints Alexis' balance and Blake's balance through the given PrintWriter.
   */
  public void printBalances(PrintWriter pen){
    //STUB
  } // printBalances(PrintWriter)

  /**
   * @return a string representation of the BlockChain which is simply the 
   * string representation of each of its blocks, earliest to latest, one 
   * per line.
   */
  public String toString(){
    //STUB
    return "";
  } // toString()

  /**
   * formats the mined block into a printable way
   * @return the formatted string.
   */
  public String stringMine(int amt) throws NoSuchAlgorithmException{
    //STUB
    return "";
  } // stringMine(int)
} // class BlockChain
