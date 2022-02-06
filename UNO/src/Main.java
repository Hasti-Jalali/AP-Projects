import java.util.Scanner;

public class Main {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Which one would you like to play with ?");
		System.out.println("1. Computer");
		System.out.println("2. People");
		int choice = input.nextInt();
		while(choice > 2 || choice < 1) {
			System.out.println("Please Enter Valid Number!");
			System.out.println("1. Computer");
			System.out.println("2. People");
			choice = input.nextInt();
		}
		System.out.println("Enter The Number of Players");
		int playerNumber = input.nextInt();
		
		Play play = new Play(playerNumber);
		play.printAllPlayersCard();
		int i = 1;
		if(choice == 1) {
			boolean oneTime = false;
			while(!play.endGame()) {
				if(play.getCurrentCardType().equals("skip") && oneTime == false) {
					i = play.nextPlayer(i);
					oneTime = true;
				}
				else if(play.getCurrentCardType().equals("+2") && oneTime == false) {
					int penalty = 2;
					while(play.hasDrawCard(i)) {
						if(i == 1) {
							System.out.println("Which one do you prefer?");
							System.out.println("1. To be fined and get " + penalty + " cards");
							System.out.println("2. represent your Draw Card");
							int newChoice = input.nextInt();
							
							if(newChoice == 2){
								play.removeDrawCard(i);
								//play.printAllPlayersCard(playerNumber);
							}
							else 
								break;
							i = play.nextPlayer(i);
						}
						else {
							play.removeDrawCard(i);
							i = play.nextPlayer(i);
						}
						penalty +=2;
					}
					play.addCardToPlayer(i, penalty);
					play.printAllPlayersCard();
					i = play.nextPlayer(i);
					oneTime = true;
				}
				else if(play.getCurrentCardType().equals("+4") && oneTime == false) {
						int penalty = 4;
						while(play.hasWildPlus4(i) && play.canPlayWild(i)) {
							if(i == 1) {
								System.out.println("Which one do you prefer?");
								System.out.println("1. To be fined and get " + penalty + " cards");
								System.out.println("2. represent your Wild Card");
								
								int newChoice = input.nextInt();
								if(newChoice == 2){
									System.out.println("Choose Color :");
									System.out.println(ANSI_RED + "1. ⬤ " + ANSI_RESET);
									System.out.println(ANSI_BLUE + "2. ⬤" + ANSI_RESET);
									System.out.println(ANSI_GREEN + "3. ⬤" + ANSI_RESET);
									System.out.println(ANSI_YELLOW + "4. ⬤" + ANSI_RESET);
									play.removeWildCard(i);
									int colorIndex = input.nextInt();
									while(colorIndex < 0 || colorIndex > 4) {
										System.out.println("Enter Valid Number!");
										colorIndex = input.nextInt();
									}
									String color = (colorIndex == 1) ? "red" :(colorIndex == 2) ? "blue" : (colorIndex == 3) ? "green" : "yellow";
									play.setColor(color);
								}
								else 
									break;
								i = play.nextPlayer(i);
							}
							else {
								play.removeWildCard(i);
								//play.printAllPlayersCard(playerNumber);
								i = play.nextPlayer(i);
							}
							penalty += 4;
						}
						play.addCardToPlayer(i, penalty);
						play.printAllPlayersCard();
						i = play.nextPlayer(i);
						oneTime = true;
				}
				
				if(i == 1) {
					int lastCardsNumber = play.getNumberOfCards(i);
					System.out.println("Player Number " + i + " Turn : ");
					play.personPlay(i);
					int newCardsNumber = play.getNumberOfCards(i);
					if(lastCardsNumber >= newCardsNumber)
						oneTime = false;
					else
						oneTime = true;

				}
				else {
					System.out.println("Player Number " + i + " Turn : ");
					int lastCardsNumber = play.getNumberOfCards(i);
					play.computerPlay(i);
					int newCardsNumber = play.getNumberOfCards(i);
					if(lastCardsNumber >= newCardsNumber)
						oneTime = false;
					else
						oneTime = true;
					
				}
				
				i = play.nextPlayer(i);
				play.printAllPlayersCard();
			}
		}
		else if(choice == 2) {
			boolean oneTime = false;
			while(!play.endGame()) {
				if(play.getCurrentCardType().equals("skip") && oneTime == false) {
					i = play.nextPlayer(i);
					oneTime = true;
				}
				else if(play.getCurrentCardType().equals("+2") && oneTime == false) {
					int penalty = 2;
					while(play.hasDrawCard(i)) {
						System.out.println("Which one do you prefer?");
						System.out.println("1. To be fined and get " + penalty + " cards");
						System.out.println("2. represent your Draw Card");
						int newChoice = input.nextInt();
						
						if(newChoice == 2){
							play.removeDrawCard(i);
						}
						else {
							break;
						}
						i = play.nextPlayer(i);
						penalty +=2;
					}
					play.addCardToPlayer(i, penalty);
					play.printAllPlayersCard();
					i = play.nextPlayer(i);
					oneTime = true;
					
				}
				else if(play.getCurrentCardType().equals("+4") && oneTime == false) {
					int penalty = 4;
					while(play.hasWildPlus4(i) && play.canPlayWild(i)) {
						System.out.println("Which one do you prefer?");
						System.out.println("1. To be fined and get " + penalty + " cards");
						System.out.println("2. represent your Wild Card");
						
						int newChoice = input.nextInt();
						if(newChoice == 2){
							System.out.println("Choose Color :");
							System.out.println(ANSI_RED + "1. ⬤ " + ANSI_RESET);
								System.out.println(ANSI_BLUE + "2. ⬤" + ANSI_RESET);
							System.out.println(ANSI_GREEN + "3. ⬤" + ANSI_RESET);
							System.out.println(ANSI_YELLOW + "4. ⬤" + ANSI_RESET);
							play.removeWildCard(i);
							int colorIndex = input.nextInt();
							while(colorIndex < 0 || colorIndex > 4) {
								System.out.println("Enter Valid Number!");
								colorIndex = input.nextInt();
							}
							String color = (colorIndex == 1) ? "red" :(colorIndex == 2) ? "blue" : (colorIndex == 3) ? "green" : "yellow";
							play.setColor(color);
						}
						else 
							break;
						i = play.nextPlayer(i);
					penalty += 4;
					}
					play.addCardToPlayer(i, penalty);
					play.printAllPlayersCard();
					i = play.nextPlayer(i);	
					oneTime = true;
				}
				int lastCardsNumber = play.getNumberOfCards(i);
				System.out.println("Player Number " + i + " Turn : ");
				play.personPlay(i);
				int newCardsNumber = play.getNumberOfCards(i);
				if(lastCardsNumber >= newCardsNumber)
					oneTime = false;
				else
					oneTime = true;
				i = play.nextPlayer(i);
				play.printAllPlayersCard();
			}
		}
		play.result();
	}
}
