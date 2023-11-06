import java.io.PrintStream;
import java.util.Scanner;

/**
 * Interactive program that creates block chain based on the compilation arguments.
 * 
 * @author Seunghyeon (Hyeon) Kim
 * @author Che Glenn
 */

public class BlockChainDriver {
  public static void main(String[] args) throws Exception{
    /* Printing streams */
    PrintStream pt = System.out;
    PrintStream ptErr = System.err;
    /* Initial block chain mined depending on the input amount */
    BlockChain blockChain = new BlockChain(Integer.parseInt(args[0]));
    /* Initializing scanner */
    Scanner scanner = new Scanner(System.in);
    /* Taking input of the command */
    pt.print("Command? ");
    String inp = scanner.nextLine();
    /* Running the while loop until "quit" was input. */
    while (!inp.equals("quit")) {
      /* Switching input by each cases */
      switch (inp) {
        case "mine":
          /* Ask for the amount transferred */
          pt.print("Amount transferred? ");
          inp = scanner.nextLine();
          /* Depending on the amount transferred, prints the mined block 
             formatted for the user to be able to understand. */
          try {
            pt.println(blockChain.stringMine(Integer.parseInt(inp)));
          } catch (Exception e) {
            /* When any errors are caught, print out the error. */
            ptErr.println("Invalid input for the amount transferred : " + e.getMessage());
          } // try/catch
          break;
        case "append":
          /* try catch used to report any invalid input for amount or nonce (non-integer value, etc.) */
          try {
            /* Ask how much transferred with the nonce value */
            pt.print("Amount transferred? ");
            int amt = Integer.parseInt(scanner.nextLine());
            pt.print("Nonce? ");
            int nonce = Integer.parseInt(scanner.nextLine());
            /* With the given values, create a temporary block */
            Block temp = new Block(blockChain.getSize(), amt, blockChain.getHash(), nonce);
            /* Append the block */
            blockChain.append(temp);
          } catch (Exception e) {
            /* When any errors are caught, print out the error. */
            ptErr.println("Invalid nonce or amount transferred : " + e.getMessage());
          } // try/catch
          break;
        case "remove":
          /* remove the last element of the block chain */
          blockChain.removeLast();
          break;
        case "check":
          /* depending on the validity, report whether the chain is valid or not. */
          String validity = ((blockChain.isValidBlockChain())? "valid!" : "invalid!");
          pt.println("Chain is " + validity);
          break;
        case "report":
          /* Report the balanced of Alexis and Blake. */
          blockChain.printBalances(pt);
          break;
        case "help":
          pt.println("Valid commands:\n"+
                    "\tmine: discovers the nonce for a given transaction\n" +
                    "\tappend: appends a new block onto the end of the chain\n" + 
                    "\tremove: removes the last block from the end of the chain\n" +
                    "\tcheck: checks that the block chain is valid\n" +
                    "\treport: reports the balances of Alexis and Blake\n" +
                    "\thelp: prints this list of commands\n" +
                    "\tquit: quits the program\n");
          break;
        default:
          /* Any other case, consider the command is invalid. */
          pt.println("Invalid Command! Please try again.");
          break;
      } // switch case
      /* At the end of each interaction, report the current block chain */
      pt.println(blockChain.toString());
      /* reiterate through the command input. */
      pt.print("Command? ");
      inp = scanner.nextLine();
    } // while
    scanner.close();
  } // main (String[])
} // BlockChainDriver
