import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.ListIterator;

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
  /*Current node */
  Node<Block> current;
  /* Size */
  int size;
  /**
   * Creates a block chain with front node and last node.
   * @param initial
   */
  public BlockChain(int initial) throws NoSuchAlgorithmException{
    this.size = 0;
    this.front = new Node<Block>(new Block(size, initial, null), null);
    this.last = this.front;
    this.current = this.front;
    size++;
  } // BlockChain (int)


  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+
  /**
   * @return Block mined
   */
  public Block mine(int amount) throws NoSuchAlgorithmException{
    Block currentBlock = new Block(size, amount, this.last.value.getHash()); //generate new block using last values hash
    return currentBlock;
  } // mine (int)

  /**
   * @return the size of the blockChain
   */
  public int getSize(){
    return size;
  } // getSize()

  /**
   * Adds a block to the last.
   * @return nothing
   * @throws IllegalArgumentException
   */
  public void append(Block blk) throws IllegalArgumentException{
    this.last.next = new Node<Block>(blk, null); //set the next val to the block
    this.last = this.last.next; //set the last value to the new node

    if (!(this.checkAmount() >= 0) && !(this.checkAmount() >= 300)){ //check if the balance is valid
      throw new IllegalArgumentException(); //if not throw IllegalArgException
    }
    
    
  } // append (Block)

  /**
   * removes the last node
   * @return whether the last node is successfully removed
   */
  public boolean removeLast() {
    if (this.size <= 1){
      return false;
    } else{
      this.last = null; //set last value to null
      Node<Block> prev = this.iterate(); //save the node thats at the end of the shortened list
      this.last = prev; //set last to saved node
      size--;
      return true;
    }
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
    this.current = this.front; //set a current val
    while(this.current.next != null){ //check if the next is null
      if(current.value.getHash().equals(current.next.value.prevHash)){ //check if current hash is equal to next vals prevHash
        this.current = this.current.next; //set current to next
      } else{
        return false;
      }
    }
    if (!(this.checkAmount() >= 0) && !(this.checkAmount() >= 300)){ //check if balance is valid
      return false;
    }
    return true;
  } // isValidBlockChain ()

  /**
   * prints Alexis' balance and Blake's balance through the given PrintWriter.
   */
  public void printBalances(PrintWriter pen){
   this.current = this.front;
   int alexisBalance = this.front.value.amount/2; //set Alexis balance to half of the initial blocks amount
   int blakeBalance = this.front.value.amount/2; //set blakes balance to half of the initial blocks amount

   while(this.current.next != null){
    if(this.current.value.getAmount() < 0){ //check direction of transaction
      alexisBalance += this.current.value.getAmount(); //remove from Alexis account
      blakeBalance += Math.abs(this.current.value.getAmount()); //add to Blake account
    }
    else if (this.current.value.getAmount() > 0){//check direction of transaction
      alexisBalance += this.current.value.getAmount(); //add to Alexis account
      blakeBalance -= this.current.value.getAmount(); //remove from Blake account
    }
    pen.println(alexisBalance);
    pen.println(blakeBalance);
   }
  } // printBalances(PrintWriter)

  /**
   * @return a string representation of the BlockChain which is simply the 
   * string representation of each of its blocks, earliest to latest, one 
   * per line.
   */
  public String toString(){
    this.current = this.front; //set current to front
    String returnString = ""; //initialize returnString
    while(this.current.next != null){
      returnString += this.current.value.toString(); //add block to return string
      this.current = this.current.next; //set current to next node
    }
    return returnString;
  } // toString()

  /**
   * formats the mined block into a printable way
   * @return the formatted string.
   * 
  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+
   */
  public String stringMine(int amt) throws NoSuchAlgorithmException{
    //STUB
    return "";
  } // stringMine(int)

  // +---------+-----------------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * iterates through LinkedList
   * @return the last node in LinkedList
   * @credit Maria Rodriguez
   */
  public Node<Block> iterate(){
    this.current = this.front; //set current to front
    while (this.current.next != null){
      this.current = this.current.next; //set current to next
    }
    return current; //returns last val in LinkedList
  }

  /**
   * iterates through BlockChain and returns total amount from each Block
   * @return amount
   */
  public int checkAmount(){
    this.current = this.front; //set current to first node
    int amount = 0; //set amount to first block amount
    while (this.current.next != null){
      amount += this.current.value.getAmount(); //add current amount
      this.current = this.current.next; //iterate through to next node
    }
    return amount;
  }
} // class BlockChain
