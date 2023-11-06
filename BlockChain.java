
import java.io.PrintStream;
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

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+
  /**
   * Creates a block chain with front node and last node.
   * @param initial
   */
  public BlockChain(int initial) throws NoSuchAlgorithmException{
    this.front = new Node<Block>(new Block(0, initial, null), null);
    this.last = this.front;
  } // BlockChain (int)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+
  /**
   * @return Block mined
   */
  public Block mine(int amount) throws NoSuchAlgorithmException{
    return new Block(this.getSize(), amount, this.last.value.getHash());
  } // mine (int)

  /**
   * @return the size of the blockChain
   */
  public int getSize(){
    return (this.last.value.getNum()-this.front.value.getNum())+1;
  } // getSize()

  /**
   * Adds a block to the last.
   * @return nothing
   * @throws IllegalArgumentException
   */
  public void append(Block blk) throws IllegalArgumentException{
    if(!blk.getHash().isValid() || blk.getPrevHash() == null){
      throw new IllegalArgumentException();
    } // if
    this.last.next = new Node<Block>(blk, null);
    this.last = this.last.next;
  } // append (Block)

  /**
   * removes the last node
   * @return whether the last node is successfully removed
   */
  public boolean removeLast() {
    if(this.getSize() == 1){
      return false;
    } // if
    Node<Block> temp = this.front;
    for(temp = this.front; temp.next != null; temp = temp.next){
      if(temp.next.equals(this.last)){
        temp.next = null;
        this.last = temp;
        return true;
      } // if
    } // for
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
    long alexisBalance = this.front.value.getAmount();
    long blakeBalance = 0;
    for(Node<Block> temp = this.front.next; temp != null; temp = temp.next){
      if(!temp.value.getHash().isValid()){
        return false;
      } // if
      alexisBalance += temp.value.getAmount();
      blakeBalance -= temp.value.getAmount();
      if(alexisBalance < 0 || blakeBalance < 0){
        return false;
      } // if
    } // for
    return true;
  } // isValidBlockChain ()

  /**
   * prints Alexis' balance and Blake's balance through the given PrintWriter.
   */
  public void printBalances(PrintStream pen){
    long alexisBalance = this.front.value.getAmount();
    long blakeBalance = 0;
    for(Node<Block> temp = this.front.next; temp != null; temp = temp.next){
      alexisBalance += temp.value.getAmount();
      blakeBalance -= temp.value.getAmount();
    } // for
    pen.println(String.format("Alexis: %d, Blake: %d\n", alexisBalance, blakeBalance));
  } // printBalances(PrintWriter)

  /**
   * @return a string representation of the BlockChain which is simply the 
   * string representation of each of its blocks, earliest to latest, one 
   * per line.
   */
  public String toString(){
    String ret = "";
    for(Node<Block> temp = this.front; temp != null; temp = temp.next){
      ret += temp.value.toString();
      ret += "\n";
    } // for
    return ret;
  } // toString()

  /**
   * formats the mined block into a printable way
   * @return the formatted string.
   */
  public String stringMine(int amt) throws NoSuchAlgorithmException{
    Block temp = this.mine(amt);
    return String.format("amount = %d, nonce = %d\n", temp.getAmount(), temp.getNonce());
  } // stringMine(int)
} // class BlockChain
