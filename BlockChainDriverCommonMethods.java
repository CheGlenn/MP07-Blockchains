import java.io.PrintWriter;

/**
 * A class that factored the common methods from BlockChainDriver Class.
 * @author Seunghyeon (Hyeon) Kim
 * @class BlockChainDriverCommonMethods
 */
public class BlockChainDriverCommonMethods {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+
  /* PrintWriter of the class */
  PrintWriter pen;
  PrintWriter penErr;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Initializes the BlockChainDriverCommonMethods with given PrintWriters.
   * @param pen
   * @param penErr
   */
  public BlockChainDriverCommonMethods(PrintWriter pen, PrintWriter penErr){
    /* Normal PrintWriter */
    this.pen = pen;
    /* Error PrintWriter */
    this.penErr = penErr;
  } // BlockChainDriverCommonMethods(PrintWriter)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+
  /**
   * It prints "Amount Transferred? " by auto flushing.
   */
  public void printAmountTransferred(){
    printAutoFlush("Amount Transferred? ");
  }// printAutoFlush()
  /**
   * It prints "Nonce? " by auto flushing.
   */
  public void printNonce(){
    printAutoFlush("Nonce? ");
  } // printNonce()
  /**
   * Prints the possible commands in the program
   */
  public void printHelp(){
    this.pen.println("Valid commands:\n"+
                    "\tmine: discovers the nonce for a given transaction\n" +
                    "\tappend: appends a new block onto the end of the chain\n" + 
                    "\tremove: removes the last block from the end of the chain\n" +
                    "\tcheck: checks that the block chain is valid\n" +
                    "\treport: reports the balances of Alexis and Blake\n" +
                    "\thelp: prints this list of commands\n" +
                    "\tquit: quits the program\n");
  } // printHelp()
  /**
   * Prints "Command? " by auto flushing
   */
  public void printCmd(){
    printAutoFlush("Command? ");
  } // printCmd()

  /**
   * It prints out the given block chain
   * @param blkCn
   */
  public void printBlockChain(BlockChain blkCn){
    this.println(blkCn.toString());
  } // printBlockChain(BlockChain)

  /**
   * Prints the stringand adding a new line afterwards with autoflushing.
   * @param str
   */
  public void println(String str){
    printAutoFlush(str + "\n");
  } // println(String)

  /**
   * It prints the error with the given message forged with the error message.
   * @param message
   * @param e
   */
  public void printErr(String message, Exception e){
    penErr.print(message + e.getMessage() + "\n");
    penErr.flush();
  } // printErr(String, Exception)

  /**
   * It prints the error with the given message only.
   * @param message
   */
  public void printErr(String message){
    penErr.print(message + "\n");
    penErr.flush();
  } // printErr(String)

  // +-----------------+---------------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * It prints a phrase and flushes so that printing works without println.
   */
  private void printAutoFlush(String s){
    this.pen.print(s);
    this.pen.flush();
  }// printAutoFlush()
} // class BlockChainDriverCommonMethods