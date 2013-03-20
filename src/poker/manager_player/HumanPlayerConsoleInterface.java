package poker.manager_player;


import java.util.Scanner;

import poker.hand_card.Card;
import poker.hand_card.Hand;

/**
 * A HumanPayer plays poker and needs input to make its card replacement decisions
 *
 */
public class HumanPlayerConsoleInterface implements HumanPlayerListener {	//TODO: needs refactoring
  /* Problem (with line above):    ?????
  Dm: Reliance on default encoding (DM_DEFAULT_ENCODING)
  Found a call to a method which will perform a byte to String (or String to byte) conversion, and will assume
  that the default platform encoding is suitable. This will cause the application behaviour to vary between platforms.
  Use an alternative API and specify a charset name or Charset object explicitly
  */
  
  private Scanner scanner = new Scanner(System.in);  //for now  think about closing it etc..
  private Hand hand = null;
  
  public void onReceiveCards(Hand hand){
    this.hand = hand;
    System.out.println("Your hand is:");
    System.out.println(hand);
  }
  
  public int getCountOfCardsToSwap(int max) {
    System.out.println("How many cards would you like to swap (max " + max + ")?");
    int swap = 0;
    
    boolean needValid = true;
    boolean needInt = true;
    
    while (needValid) {
	    while (needInt) {
	      String s = scanner.nextLine();
	      try{
	          swap = Integer.parseInt(s);
	          needInt = false;
	      }
	      catch(NumberFormatException ex){
	          System.out.println("Please enter a single numerical digit e.g. 1");
	      }
	    }
	    if (swap>max|| swap<0) {
	    	System.out.println("That is not a valid selection, you cannot swap more than " + max + " cards");
	        System.out.println("How many cards would you like to swap (max " + max + ")?");
	        needInt = true;
	    } else {
	    	needValid = false;
	    }
    }
    return swap;
  }
  
  public Card selectCardsToRemove() {
    System.out.println("Which card would you like to swap (From 1 - 5)?");	// 5 to be hand size?
    int swap = 0;

    boolean needValid = true;
    boolean needInt = true;
    
    while (needValid) {
	    while (needInt) {
	      String s = scanner.nextLine();
	      try{
	          swap = Integer.parseInt(s);
	          needInt = false;
	      }
	      catch(NumberFormatException ex){
	          System.out.println("Please enter a single numerical digit e.g. 1");
	      }
	    }
	    if (swap>5|| swap<1) {
	    	System.out.println("That is not a valid selection, it was not between 1 and 5");
	        System.out.println("Which card would you like to swap (From 1 - 5)?");
	        needInt = true;
	    } else {
	    	needValid = false;
	    }
    }
    return hand.getCardAt(swap);
  }
}