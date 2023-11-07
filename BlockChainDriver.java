import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Interactive program that creates block chain based on the compilation arguments.
 * 
 * @author Seunghyeon (Hyeon) Kim
 * @author Che Glenn
 * BlockChainDriver class for the interactive program for creating a block chain
 * @class BlockChainDriver
 */
public class BlockChainDriver {
  public static void main(String[] args) throws Exception{
    /* Printing streams */
    PrintWriter pen = new PrintWriter(System.out);
    PrintWriter penErr = new PrintWriter(System.err);
    BlockChainDriverCommonMethods commonMethods = new BlockChainDriverCommonMethods(pen, penErr);
    /* Initial block chain mined depending on the input amount */
    int initial = -1;
    try {
      initial = Integer.parseInt(args[0]);
      if(initial < 0){
        commonMethods.printErr("Invalid Initial Value! (Negative initial is not valid)");
        return;
      }
    } catch (Exception e) {
      commonMethods.printErr("Invalid Initial Value! : ", e);
      return;
    }
    BlockChain blockChain = new BlockChain(initial);
    /* Initializing scanner */
    Scanner scanner = new Scanner(System.in);
    String inp = "STARTING BUFFER";
    /* Running the while loop until "quit" was input. */
    do {
      /* Taking input of the command */
      commonMethods.printCmd();
      inp = scanner.nextLine();
      /* Switching input by each cases */
      switch (inp) {
        case "mine":
          /* Ask for the amount transferred */
          commonMethods.printAmountTransferred();
          /* Take in input */
          inp = scanner.nextLine();
          /* Depending on the amount transferred, prints the mined block 
             formatted for the user to be able to understand. */
          try {
            commonMethods.println(blockChain.stringMine(Integer.parseInt(inp)));
          } catch (Exception e) {
            /* When any errors are caught, print out the error. */
            commonMethods.printErr("Invalid input for the amount transferred : ", e);
          } // try/catch
          break;
        case "append":
          /* try catch used to report any invalid input for amount or nonce (non-integer value, etc.) */
          try {
            /* Ask how much transferred with the nonce value */
            commonMethods.printAmountTransferred();
            int amt = Integer.parseInt(scanner.nextLine());
            commonMethods.printNonce();
            int nonce = Integer.parseInt(scanner.nextLine());
            /* With the given values, create a temporary block */
            Block temp = new Block(blockChain.getSize(), amt, blockChain.getHash(), nonce);
            /* Append the block */
            blockChain.append(temp);
          } catch (Exception e) {
            /* When any errors are caught, print out the error. */
            commonMethods.printErr("Invalid nonce or amount transferred : ", e);
          } // try/catch
          break;
        case "remove":
          /* remove the last element of the block chain */
          blockChain.removeLast();
          break;
        case "check":
          /* depending on the validity, report whether the chain is valid or not. */
          String validity = ((blockChain.isValidBlockChain())? "valid!" : "invalid!");
          commonMethods.println("Chain is " + validity);
          break;
        case "report":
          /* Report the balanced of Alexis and Blake. */
          blockChain.printBalances(pen);
          break;
        case "help":
          commonMethods.printHelp();
          break;
        case "quit":
          commonMethods.println("Thank you for using our block chain algorithm, and have a nice day!\n");
          commonMethods.println("Final transfer history:");
          break;
        default:
          /* Any other case, consider the command is invalid. */
          commonMethods.println("Invalid Command! Please try again.");
          break;
      } // switch case
      commonMethods.printBlockChain(blockChain);
    } while (!inp.equals("quit")); // do/while
    //
    scanner.close();
  } // main (String[])
} // BlockChainDriver
