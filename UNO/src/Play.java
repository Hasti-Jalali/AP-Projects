import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
/**
 * A class to hold the players and all the card and other detail
 * @author Hasti
 * @version 1.0
 */
public class Play {
	
	private ArrayList<Player> players;
	private ArrayList<Card> onBoardCards;
	private Card currentCard;
	private String currentWise;
	private String currentColor;
	private Random random;
	private final HashMap<String,Integer> cardsMap;
	private Scanner input;
	private int playersNumber;
	private int[] result;
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	/**
	 * create player 
	 * @param numberOfPlayers
	 */
	public Play(int numberOfPlayers) {

		result = new int[numberOfPlayers];
		for(int i = 0 ; i < numberOfPlayers; i++) {
			result[i] = 0;
		}
		input = new Scanner(System.in);
		playersNumber = numberOfPlayers;
		players = new ArrayList<>();
		onBoardCards = new ArrayList<>();
		cardsMap = new HashMap<>();
		cardsMap.put("0",0);
		cardsMap.put("1", 1);
		cardsMap.put("2",2);
		cardsMap.put("3",3);
		cardsMap.put("4",4);
		cardsMap.put("5",5);
		cardsMap.put("6",6);
		cardsMap.put("7",7);
		cardsMap.put("8",8);
		cardsMap.put("9",9);
		cardsMap.put("reverse",10);
		cardsMap.put("skip",11);
		cardsMap.put("+2",12);
		
		onBoardCards.add(new Card("red" , "0"));
		onBoardCards.add(new Card("blue" , "0"));
		onBoardCards.add(new Card("green" , "0"));
		onBoardCards.add(new Card("yellow" , "0"));
		for(int i = 1; i < 10 ; i++) {
			onBoardCards.add(new Card("red" , "" + i));
			onBoardCards.add(new Card("red" , "" + i));
			onBoardCards.add(new Card("blue" , "" + i));
			onBoardCards.add(new Card("blue" , "" + i));
			onBoardCards.add(new Card("green" , "" + i));
			onBoardCards.add(new Card("green" , "" + i));
			onBoardCards.add(new Card("yellow" , "" + i));
			onBoardCards.add(new Card("yellow" , "" + i));
		}
		onBoardCards.add(new Card("red" , "+2"));
		onBoardCards.add(new Card("red" , "+2"));
		onBoardCards.add(new Card("blue" , "+2"));
		onBoardCards.add(new Card("blue" , "+2"));
		onBoardCards.add(new Card("green" , "+2"));
		onBoardCards.add(new Card("green" , "+2"));
		onBoardCards.add(new Card("yellow" , "+2"));
		onBoardCards.add(new Card("yellow" , "+2"));
		
		onBoardCards.add(new Card("red" , "skip"));
		onBoardCards.add(new Card("red" , "skip"));
		onBoardCards.add(new Card("blue" , "skip"));
		onBoardCards.add(new Card("blue" , "skip"));
		onBoardCards.add(new Card("green" , "skip"));
		onBoardCards.add(new Card("green" , "skip"));
		onBoardCards.add(new Card("yellow" , "skip"));
		onBoardCards.add(new Card("yellow" , "skip"));
		
		onBoardCards.add(new Card("red" , "reverse"));
		onBoardCards.add(new Card("red" , "reverse"));
		onBoardCards.add(new Card("blue" , "reverse"));
		onBoardCards.add(new Card("blue" , "reverse"));
		onBoardCards.add(new Card("green" , "reverse"));
		onBoardCards.add(new Card("green" , "reverse"));
		onBoardCards.add(new Card("yellow" , "reverse"));
		onBoardCards.add(new Card("yellow" , "reverse"));
		
		onBoardCards.add(new Card("white" , " "));
		onBoardCards.add(new Card("white" , " "));
		onBoardCards.add(new Card("white" , " "));
		onBoardCards.add(new Card("white" , " "));
		onBoardCards.add(new Card("white" , "+4"));
		onBoardCards.add(new Card("white" , "+4"));
		onBoardCards.add(new Card("white" , "+4"));
		onBoardCards.add(new Card("white" , "+4"));
		
		Card[] playerCards = new Card[7];
		random = new Random();
		for(int j = 0; j < numberOfPlayers; j++) {
			for(int i = 0 ; i < 7 ; i++) {
				int index = random.nextInt(onBoardCards.size() - 1);
				playerCards[i] = onBoardCards.get(index);
				onBoardCards.remove(index);
			}
			players.add(new Player(playerCards[0], playerCards[1],playerCards[2],playerCards[3],playerCards[4],playerCards[5],playerCards[6]));
		}
		
		int index;
		
		do {
			index = random.nextInt(onBoardCards.size() - 1);
		}
		while(onBoardCards.get(index).getColor().equals("white") || onBoardCards.get(index).getType().equals("+2")); 
			
		currentCard = onBoardCards.get(index);
		currentColor = currentCard.getColor();
		onBoardCards.remove(index);
		if(currentCard.getType().equals("reverse")) {
			currentWise = new String("upward");
		}
		else if(currentCard.getType().equals("skip")) {
			currentWise = new String("downward");
		}
		else {
			int randomWise = random.nextInt();
			if(randomWise % 2 == 1)
				currentWise = new String("downward");
			else
				currentWise = new String("upward");
			
		}
	}
	
	/**
	 * print the players' cards and the current card and the current color
	 */
	public void printAllPlayersCard() {
		System.out.println("                                                         current Wise : " + currentWise + "\n");
		for(int i = 1; i <= playersNumber; i++) {
			
			players.get(i - 1).printPlayerCards(i);
		}
		
		String colorString = currentCard.howPrintColor();

		String stringType = currentCard.howPrintType();
		System.out.println();
		System.out.print("                                                                     ");
		System.out.println(colorString + "|$$$$$$$$$$$$$$$| " + ANSI_RESET);
		System.out.print("                                                                     ");
		System.out.println(colorString + "|               | " + ANSI_RESET);
		System.out.print("                                                      current Card : ");
		System.out.println(colorString + "|" + stringType + "| " + ANSI_RESET);
		System.out.print("                                                                     ");
		System.out.println(colorString + "|               | " + ANSI_RESET);
		System.out.print("                                                                     ");
		System.out.println(colorString + "|$$$$$$$$$$$$$$$| " + ANSI_RESET);
		
		colorString = (currentColor == "red") ? ANSI_RED : (currentColor == "blue") ? ANSI_BLUE : (currentColor == "yellow") ? ANSI_YELLOW : (currentCard.getColor() == "green") ? ANSI_GREEN : ANSI_WHITE;
		System.out.println("                                                     current color : " + colorString + "⬤" + ANSI_RESET);

		
	}
	/**
	 * change the wise of the game
	 */
	private void changeWise() {
		if(currentWise.equals("upward"))
			currentWise = "downward";
		else {
			currentWise = "upward";
		}
	}
	/**
	 * computer play instead of the person
	 * @param playerIndex
	 */
	public void computerPlay(int playerIndex) {
		
		Player player = players.get(playerIndex - 1);
		
		String type = currentCard.getType();
		String color = currentColor;
		
		if(currentCard.getColor().equals("white")) {
			int bestNumber = 0;
			Card bestCard = null;
			if(player.hasColor(color)) {
				for(Card card : player.getCards()) {
					if(card.getColor().equals(color)) {
						if(bestNumber <= cardsMap.get(card.getType())) {
							bestNumber = cardsMap.get(card.getType());
							bestCard = card;
						}
					}
				}
				onBoardCards.add(currentCard);
				currentCard = bestCard;
				player.removeCard(bestCard);
				//currentColor = currentCard.getColor();
				if(currentCard.getType().equals("reverse")) {
					this.changeWise();
				}
			}
			else if(player.hasWildCard()) {
				Card card = player.getWildCard();
				onBoardCards.add(currentCard);
				currentCard = card;
				currentColor = player.chooseColor();
				player.removeCard(card);
			}
			else {
				int index = random.nextInt(onBoardCards.size() - 1);
				Card card = onBoardCards.get(index);
				player.addCard(card);
				onBoardCards.remove(index);
				if(card.getColor().equals(currentColor)) {
					this.printAllPlayersCard();
					onBoardCards.add(currentCard);
					currentCard = card;
					player.removeCard(card);
					if(currentCard.getType().equals("reverse")) {
						this.changeWise();
					}
				}
				else if(card.getColor().equals("white")){
					onBoardCards.add(currentCard);
					currentCard = card;
					currentColor = player.chooseColor();
					player.removeCard(card);
				}
			}
		}	
		else {	
			if(player.canPlay(color, type)) {
				this.playBest(player, color, type);
			}
			else if(player.hasWildCard()){
				this.playWithWildCard(player);
			}
			else {
				int index = random.nextInt(onBoardCards.size() - 1);
				player.addCard(onBoardCards.get(index));
				Card card = onBoardCards.get(index);
				onBoardCards.remove(index);
				if(card.getColor().equals(color) || card.getType().equals(type)) {
					onBoardCards.add(currentCard);
					currentCard = card;
					player.removeCard(card);
					if(currentCard.getType().equals("reverse")) {
						this.changeWise();
					}
				}
				else if(card.getColor().equals("white")){
					onBoardCards.add(currentCard);
					currentCard = card;
					player.removeCard(card);
					currentColor = player.chooseColor();
				}
			}
		}	
		
	}
	/**
	 * play the best card
	 * @param player
	 * @param color
	 * @param type
	 */
	private void playBest(Player player,String color, String type) {
		int biggestCardNumber = 0;
		Card bestCard = null;
		for(Card card : player.getCards()) {
			if(card.getColor().equals(color) || card.getType().equals(type)) {
				if(biggestCardNumber <= cardsMap.get(card.getType())) {
					biggestCardNumber = cardsMap.get(card.getType());
					bestCard = card;
				}
			}
		}
		onBoardCards.add(currentCard);
		currentCard = bestCard;
		currentColor = bestCard.getColor();
		player.removeCard(bestCard);
		if(currentCard.getType().equals("reverse")) {
			this.changeWise();
		}
		
	}
	/**
	 * play with the wild card by computer
	 * @param player
	 */
	private void playWithWildCard(Player player) {
		
		for(Card card : player.getCards()) {
			if(card.getColor().equals("white")) {
				onBoardCards.add(currentCard);
				currentCard = card;
				currentColor = player.chooseColor();
				player.removeCard(card);
				break;
			}
		}
	}
	/**
	 * check that the game is over or not
	 * @return true or false
	 */
	public boolean endGame() {
		
		for(Player player : players) {
			if(player.getCards().size() == 0)
				return true;
		}
		return false;
	}
	/**
	 * person play the game
	 * @param playerIndex
	 */
	public void personPlay(int playerIndex) {
		Player player = players.get(playerIndex - 1);
		
		String type = currentCard.getType();
		String color = currentColor;
		boolean check = false;
		if(player.canPlay(color, type)) {
			while(check == false) {
				System.out.println("Please choose one of your cards : ");
				for(int i = 0; i < player.getCards().size(); i++) {
					System.out.println((i+1) + ". Card Number " + (i+1));
				}
				int index;
				do {
					index = input.nextInt();
				} while(index < 0 || index > player.getCards().size());
				Card card = player.getCard(index);
				if(card.getType().equals(type) || card.getColor().equals(color)) {
					player.removeCard(card);
					onBoardCards.add(currentCard);
					currentCard = card;
					currentColor = card.getColor();
					check = true;
					if(currentCard.getType().equals("reverse")) {
						this.changeWise();
					}
				}
				else {
					System.out.println("Please Enter Valid Card !");
				}
			}
		}
		else if(player.hasWildCard()) {
			 
			System.out.println("Please Enter your Wild Card Index : ");
			int index;
			do {
				index = input.nextInt();
			} while(index < 0 || index > player.getCards().size() || !player.getCard(index).getColor().equals("white"));
			Card card = player.getCard(index);
			System.out.println(ANSI_RED + "1. ⬤ " + ANSI_RESET);
			System.out.println(ANSI_BLUE + "2. ⬤" + ANSI_RESET);
			System.out.println(ANSI_GREEN + "3. ⬤" + ANSI_RESET);
			System.out.println(ANSI_YELLOW + "4. ⬤" + ANSI_RESET);
			int colorIndex = input.nextInt();
			while(colorIndex < 0 || colorIndex > 4) {
				System.out.println("Enter Valid Number!");
				colorIndex = input.nextInt();
			}
			currentColor = (colorIndex == 1) ? "red" :(colorIndex == 2) ? "blue" : (colorIndex == 3) ? "green" : "yellow";
			onBoardCards.add(currentCard);
			currentCard = card;
			player.removeCard(card);
		}
		else {
			int index = random.nextInt(onBoardCards.size() - 1);
			player.addCard(onBoardCards.get(index));
			Card card = onBoardCards.get(index);
			onBoardCards.remove(index);
			if(card.getColor().equals(currentColor) || card.getType().equals(currentCard.getType())) {
				onBoardCards.add(currentCard);
				currentCard = card;
				player.removeCard(card);
				if(currentCard.getType().equals("reverse")) {
					this.changeWise();
				}
			}
			else if(card.getColor().equals("white")){
				System.out.println("Select A Color");
				System.out.println(ANSI_RED + "1. ⬤ " + ANSI_RESET);
				System.out.println(ANSI_BLUE + "2. ⬤" + ANSI_RESET);
				System.out.println(ANSI_GREEN + "3. ⬤" + ANSI_RESET);
				System.out.println(ANSI_YELLOW + "4. ⬤" + ANSI_RESET);
				int colorIndex = input.nextInt();
				while(colorIndex < 0 || colorIndex > 4) {
					System.out.println("Enter Valid Number!");
					colorIndex = input.nextInt();
				}
				currentColor = (colorIndex == 1) ? "red" :(colorIndex == 2) ? "blue" : (colorIndex == 3) ? "green" : "yellow";
				onBoardCards.add(currentCard);
				currentCard = card;
				player.removeCard(card);
			}
			
		}
		
	}
	/**
	 * return the type of the current card
	 * @return type
	 */
	public String getCurrentCardType() {
		return currentCard.getType();
	}
	/**
	 * check that a player has draw card or not
	 * @param index
	 * @return
	 */
	public boolean hasDrawCard(int index) {
		for(Card card : players.get(index - 1).getCards()) {
			if(card.getType().equals("+2"))
				return true;
		}
		return false;
	}
	/**
	 * check that the player has wild card or not
	 * @param index
	 * @return true or false
	 */
	public boolean hasWildPlus4(int index) {
		for(Card card : players.get(index - 1).getCards()) {
			if(card.getType().equals("+4"))
				return true;
		}
		return false;
	}
	/**
	 * check that player can use the wild card or not
	 * @param playerIndex
	 * @return true or false
	 */
	public boolean canPlayWild(int playerIndex) {
		
		Player player = players.get(playerIndex - 1);
		for(Card card : player.getCards()) {
			if(card.getClass().equals(currentColor))
				return false;
		}
		return true;
	}
	/**
	 * add a card to player's card
	 * @param playerIndex
	 * @param numberOfCard
	 */
	public void addCardToPlayer(int playerIndex, int numberOfCard) {
		int index;
		Player player = players.get(playerIndex - 1);
		for(int i = 0 ; i < numberOfCard; i++) {
			index = random.nextInt(onBoardCards.size() - 1);
			player.addCard(onBoardCards.get(index));
			onBoardCards.remove(index);
		}
	}
	/**
	 * remove a draw card between player's cards
	 * @param playerIndex
	 */
	public void removeDrawCard(int playerIndex) {
		Player player = players.get(playerIndex - 1);
		for(Card card : player.getCards()) {
			if(card.getType().equals("+2")) {
				onBoardCards.add(currentCard);
				currentCard = card;
				player.removeCard(card);
				currentColor = card.getColor();
				break;
			}
		}
	}
	/**
	 * remove a wild card between player's card
	 * @param playerIndex
	 */
	public void removeWildCard(int playerIndex) {
		Player player = players.get(playerIndex - 1);
		for(Card card : player.getCards()) {
			if(card.getType().equals("+4")) {
				onBoardCards.add(currentCard);
				currentCard = card;
				player.removeCard(card);
				currentColor = player.chooseColor();
				break;
			}
		}
	}
	/**
	 * set a color to currentColor
	 * @param color
	 */
	public void setColor(String color) {
		currentColor = color;
	}
	/**
	 * check that who is the next player and return the index of the player
	 * @param i
	 * @return index
	 */
	public int nextPlayer(int i){
		int index = i;
		if(currentWise.equals("upward")) {
			if(i == 1) {
				index = playersNumber;
			}
			else
				index--;
		}
		else {
			if(i == playersNumber) {
				index = 1;
			}
			else {
				index++;
			}
		}
		return index;
	}
	/**
	 * print the result of the game
	 */
	public void result() {
		
		for(int i = 0; i < playersNumber; i++) {
			for(Card card : players.get(i).getCards()) {
				result[i] += card.getCardScore();
			}
		}
		System.out.println("--------------------------------------------");
		
		
		for(int i = 0; i < playersNumber; i++) {
			System.out.printf("%2d. ", i + 1);
			System.out.print("Player Number ");
			System.out.printf("%2d", smallest(result) + 1);
			System.out.print("       |      ");
			System.out.printf("%3d\n", result[smallest(result)]);
			result[smallest(result)] = 100000;
			System.out.println("--------------------------------------------");
		}
		
	}
	/**
	 * return the smallest element of the array 
	 * @param scores
	 * @return index of the smallest element
	 */
	private int smallest(int[] scores) {
		int smallest = scores[0];
		int i = 0;
		int index = 0;
		for(i = 0 ; i < scores.length; i++) {
			if(smallest > scores[i]) {
				smallest = scores[i];
				index = i;
			}
		}
		return index;
	}
	/**
	 * return the number of the player's card
	 * @param playerIndex
	 * @return cardsNumber
	 */
	public int getNumberOfCards(int playerIndex) {
		return players.get(playerIndex - 1).numberOfCard();
	}
}
