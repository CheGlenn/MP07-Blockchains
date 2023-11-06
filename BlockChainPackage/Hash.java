package BlockChainPackage;
import java.util.Arrays;

public class Hash implements HashInterface{
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+
  /* Hex array */
  private final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();
  /* data that the hash object contains */
  byte[] data;
  
  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+
  
  /**
   * initializes the hash
   * @param data
   */
  public Hash(byte[] data){
    this.data = Arrays.copyOf(data, data.length);
  } // Hash(byte[])

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * returns the copy of the byte array that the hash object has.
   */
  public byte[] getData(){
    return Arrays.copyOf(this.data, this.data.length);
  } // getData()

  /**
   * Goes through the hash object, and checks whether first three bytes are
   * 0s and whether the length is more than or equal to 3.
   * @return whether the hash is valid 
   */
  public boolean isValid(){
    /* Checks if the length is valid */
    if(this.data.length >= 3){
      /* Checks if first three lengths are valid */
      for(int i = 0; i < 3; i++){
        /* if 0, continue the loop */
        if(this.data[i] == 0){
          continue;
        }
        /* Otherwise, return false */
        return false;
      }
      /* When going through the loop successfully, return true. */
      return true;
    }
    return false;
  } // isValid()

  /**
   * Returns the string version of a hash object with 
   * @return the hash object
   */
  public String toString(){
    return byteToHex(this.data);
  } // toString()

  /**
   * @param other
   * @return whether the input object is structually equal to the argument.
   */
  public boolean equals(Object other){
    if((other.getClass() == Hash.class)){
      Hash temp = (Hash) other;
      if(Arrays.equals(this.data, temp.getData())){
        return true;
      }
      return false;
    }
    return false;
  } // equals(Object)

  // +-----------------+---------------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * @param bytes
   * @return The string version of the Hex-decimal of bytes.
   */
  private String byteToHex(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];
    int j = 0;
    for(byte bt : bytes){
      int temp = Byte.toUnsignedInt(bt);
      hexChars[2*j] = HEX_ARRAY[(int)(temp/16)];
      hexChars[2*j+1] = HEX_ARRAY[temp%16];
      j++;
    }
    return new String(hexChars);
  } // byteToHex(byte[])
} // Hash
