package BlockChainPackage;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Implementation of Blocks
 * 
 * @author Seunghyeon (Hyeon) Kim
 */

public class Block implements BlockInterface{
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+
  /* number of the block */
  int num;
  /* amount transferred */
  int amount;
  /* Previous hash */
  Hash prevHash;
  /* Nonce of this block */
  long nonce;
  /* Hash of the current block */
  Hash currentHash;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Creates a block with a valid hash mined via brute force.
   * @param num
   * @param amt
   * @param prevHash
   */
  public Block(int num, int amt, Hash prevHash) throws NoSuchAlgorithmException{
    this.num = num;
    this.amount = amt;
    this.prevHash = prevHash;
    long t = 0;
    Hash temp = generate(t);
    while(!temp.isValid()){
      /* Iterate through t until the generated hash is valid. */
      temp = generate(++t);
    } // while
    this.currentHash = temp;
    this.nonce = t;
  } // Block(int, int, Hash)

  /**
   * Creates a new block with the given nonce value
   * @param num
   * @param amt
   * @param prevHash
   * @param nonce
   */
  public Block(int num, int amt, Hash prevHash, long nonce) throws NoSuchAlgorithmException{
    this.num = num;
    this.amount = amt;
    this.prevHash = prevHash;
    this.nonce = nonce;
    this.currentHash = generate(nonce);
  } // Block(int, int, Hash, long)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * @return the block number of the current block
   */
  public int getNum(){
    return this.num;
  } // getNum()

  /**
   * @return the amount of transfer that happened
   */
  public int getAmount(){
    return this.amount;
  } // getAmount()

  /**
   * @return the nonce of the current block
   */
  public long getNonce(){
    return this.nonce;
  } // getNonce()

  /**
   * @return the previous hash
   */
  public Hash getPrevHash(){
    return this.prevHash;
  } // getPrevHash()

  /**
   * @return the current hash value of the block
   */
  public Hash getHash(){
    return this.currentHash;
  } // getHash()

  /**
   * @return the String version of the block
   */
  public String toString(){
    String ret = String.format("Block %d (Amount: %d, Nonce: %d, prevHash: %s, hash: %s)", this.getNum(), this.getAmount(), this.getNonce(), this.toStrPrev(), this.getHash().toString());
    return ret;
  } // toString()

  // +-----------------+---------------------------------------------------
  // | Private Methods |
  // +-----------------+
  private String toStrPrev(){
    if(this.hasPrevious()){
      return this.getPrevHash().toString();
    } // if
    return "null";
  }

  /**
   * Converts long integers to byte arrays.
   * @param num
   * @return the byte array associated with the given num
   */
  private byte[] longToByteArr(long num){
    return ByteBuffer.allocate(8).putLong(num).array();
  } // longToByteArr(long)

  /**
   * Converts integers to byte arrays.
   * @param num
   * @return the byte array associated with the given num
   */
  private byte[] intToByteArr(int num){
    return ByteBuffer.allocate(4).putInt(num).array();
  } // intToByteArr(long)

  /**
   * @return whether the block has previous hash
   */
  private boolean hasPrevious(){
    return (this.prevHash != null);
  } // hasPrevious()

  /**
   * Generates the hash with byte array generated with given index i
   * @return the mined hash
   * @throws NoSuchAlgorithmException
   */
  private Hash generate(long i) throws NoSuchAlgorithmException{
    MessageDigest md = MessageDigest.getInstance("sha-256");
    md.update(intToByteArr(this.getNum()));
    md.update(intToByteArr(this.getAmount()));
    if(this.hasPrevious()){
      md.update(this.getPrevHash().getData());
    } // if
    md.update(longToByteArr(i));
    Hash temp = new Hash(md.digest());
    return temp;
  } // generate(long)
} // class Block
