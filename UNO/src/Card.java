/**
 * This class hold detail of the card such as its color and its type
 * @author Hasti
 *
 */
public class Card {
	
	private String color;
	private String type;
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	/**
	 * constructor set the color and the type of the card which they are String
	 * @param color
	 * @param type
	 */
	public Card(String color, String type) {
		
		this.color = color;
		this.type = type;
		
	}
	/**
	 * get color of the card
	 * @return color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * get type of the card
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * return the String that show how each card should be printed 
	 * @return String
	 */
	public String howPrintType() {
		
		if(this.type.equals("0") || this.type.equals("1") || this.type.equals("2") || this.type.equals("3") || this.type.equals("4") || this.type.equals("5") || this.type.equals("6") || this.type.equals("7") || this.type.equals("8") || this.type.equals("9"))
			return "       " + this.type + "       ";
		else if(this.type.equals("+2") || this.type.equals("+4"))
			return "      " + this.type + "       ";
		else if(this.type.equals("skip"))
			return "     " + this.type + "      ";
		else if(this.type.equals("reverse"))
			return "    " + this.type + "    ";
		else
			return "               ";
		
	}
	/**
	 * return the ANSI color string for print the card
	 * @return
	 */
	public String howPrintColor() {
		
		return (color == "red") ? ANSI_RED : (color == "blue") ? ANSI_BLUE : (color == "yellow") ? ANSI_YELLOW : (color == "green") ? ANSI_GREEN : ANSI_WHITE;

	}
	/**
	 * if two cards have same name and type they are equal
	 * @param card
	 * @return true or false
	 */
	public boolean equals(Card card) {
		
		if(this.color.equals(card.getColor()) && this.type.equals(card.getType()))
			return true;
		
		return false;
	}
	/**
	 * return the score of the card
	 * @return score
	 */
	public int getCardScore() {
		
		if(type.equals("0"))
			return 0;
		if(type.equals("1"))
			return 1;
		if(type.equals("2"))
			return 2;
		if(type.equals("3"))
			return 3;
		if(type.equals("4"))
			return 4;
		if(type.equals("5"))
			return 5;
		if(type.equals("6"))
			return 6;
		if(type.equals("7"))
			return 7;
		if(type.equals("8"))
			return 8;
		if(type.equals("9"))
			return 9;
		if(type.equals("9"))
			return 9;
		if(color.equals("white"))
			return 50;
		
		return 20;
		
		
	}
	
}
