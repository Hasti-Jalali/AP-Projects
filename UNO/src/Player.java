import java.util.ArrayList;
import java.util.Iterator;
/**
 * This class hold detail of the cards which player has
 * @author Hasti
 *
 */
public class Player {
	
	public static final String ANSI_RESET = "\u001B[0m";
	
	private ArrayList<Card> cards;
	
	/**
	 * create player and sets first seven cards that the play should has
	 * @param c1
	 * @param c2
	 * @param c3
	 * @param c4
	 * @param c5
	 * @param c6
	 * @param c7
	 */
	public Player(Card c1, Card c2, Card c3, Card c4, Card c5, Card c6, Card c7) {
		
		cards = new ArrayList<>();
		
		cards.add(c1);
		cards.add(c2);
		cards.add(c3);
		cards.add(c4);
		cards.add(c5);
		cards.add(c6);
		cards.add(c7);
		
	}
	/**
	 * add a new card to player's card
	 * @param newCard
	 */
	public void addCard(Card newCard) {
		cards.add(newCard);
	}
	/**
	 * remove a card from player's card
	 * @param card
	 */
	public void removeCard(Card card) {
		
		Iterator<Card> it = cards.iterator();
		
		while(it.hasNext()) {
			if(it.next().equals(card)) {
				it.remove();
				break;
			}	
		}
	}
	/**
	 * print all the card that the player has
	 * @param playerNumber
	 */
	public void printPlayerCards(int playerNumber) {
		
		int size = cards.size();
		String[] colorString = new String[size];
		String[] typeString = new String[size];
		for(int i = 0 ; i < size ; i++) {
			
			colorString[i] = cards.get(i).howPrintColor();
			typeString[i] = cards.get(i).howPrintType();
		}
		
		for(int i = 0 ; i < 5; i++) {
			
			if(i == 0 || i == 4) {
				for(int j = 0; j < size; j++) {
					if(j == 0)
						System.out.print("                  ");
					System.out.print(colorString[j] + "|$$$$$$$$$$$$$$$| " + ANSI_RESET);
				}
			}
			if(i == 1 || i == 3) {
				for(int j = 0; j < size; j++) {
					if(j == 0)
						System.out.print("                  ");
					System.out.print(colorString[j] + "|               | " + ANSI_RESET);
				}
			}
			else if(i == 2)
				for(int j = 0; j < size; j++) {
					if(j == 0)
						System.out.print("Player Number " + playerNumber + " : ");
					System.out.print(colorString[j] + "|" + typeString[j] + "| " + ANSI_RESET);
				}
			
			System.out.println();
		}
	}
	/**
	 * return the player's cards
	 * @return
	 */
	public ArrayList<Card> getCards(){
		return cards;
	}
	/**
	 * return a specific card
	 * @param index
	 * @return
	 */
	public Card getCard(int index) {
		return cards.get(index - 1);
	}
	/**
	 * define the color which is more between the cards
	 * @return color
	 */
	public String chooseColor() {
		int blue = 0;
		int green = 0;
		int red = 0;
		int yellow = 0;
		for(Card card : cards) {
			switch(card.getColor()) {
			case "blue" :
				blue++;
				break;
			case "yellow":
				yellow++;
				break;
			case "red" :
				red++;
				break;
			case "green" :
				green++;
				break;
				
			}
		}
		
		return (green >= blue && green >= red && green >= yellow) ? "green" :(blue >= green && blue >= red && blue >= yellow) ? "blue" :(red >= green && red >= blue && red >= yellow) ? "red" : "yellow";
		
	}
	/**
	 * check that the player has wild card or not
	 * @return true or false
	 */
	public boolean hasWildCard() {
		for(Card card : cards) {
			if(card.getColor().equals("white")) {
				return true;
			}
		}
		return false;
	}
	/**
	 * check that between the card any of them has the specific color or not
	 * @param color
	 * @return true or false
	 */
	public boolean hasColor(String color) {
		
		for(Card card : cards) {
			if(card.getColor().equals(color))
				return true;
		}
		return false;
	}
	/**
	 * get the wild card if there is any wild card
	 * @return wildCard
	 */
	public Card getWildCard() {
		
		for(Card card : cards) {
			if(card.getColor().equals("white")) {
				return card;
			}
		}
		return null;
	}
	/**
	 * check that between the card is there any to has the same color or type with the specific color and type
	 * @param currentColor
	 * @param currentType
	 * @return true or false
	 */
	public boolean canPlay(String currentColor, String currentType) {
		
		for(Card card : cards) {
			if(card.getColor().equals(currentColor) || card.getType().equals(currentType))
				return true;
		}
		return false;
	}
	/**
	 * return the number of the player's card
	 * @return
	 */
	public int numberOfCard() {
		return cards.size();
	}
	
}
