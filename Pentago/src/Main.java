import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		char firstPlayer;
		char secondPlayer;
		
		String firstPlayerName = new String();
		String secondPlayerName = new String();
		
		Random random = new Random();
		int r = random.nextInt(1);
		if(r == 0) {
			firstPlayer = 'r';
			firstPlayerName = "Red";
			secondPlayer = 'b';
			secondPlayerName = "Blue";
		}
		else {
			firstPlayer = 'b';
			firstPlayerName = "Blue";
			secondPlayer = 'r';
			secondPlayerName = "Red";
		}
		
		Board board = new Board();
		
		System.out.println("1. Person vs Person");
		System.out.println("2. Person vs Computer");
		int choice = input.nextInt();
		String c = input.nextLine();
		
		board.print();
		
		while(true) {
			
			
			if(choice == 1) {
				System.out.println(firstPlayerName + " turn : ");
				System.out.print("Choose a board to put your stone : ");
				int firstPlayerBoard = input.nextInt();
				while(firstPlayerBoard > 4 || firstPlayerBoard < 0) {
					System.out.print("Choose a valid board : ");
					firstPlayerBoard = input.nextInt();
				}	
				System.out.print("Where do you want to put your stone : ");
				int firstPlayerLocation = input.nextInt();
				while(firstPlayerLocation > 9 || firstPlayerLocation < 0) {
					System.out.print("Choose a valid location : ");
					firstPlayerLocation = input.nextInt();
				}
				
				while (! board.addDiskToBoard(firstPlayer, firstPlayerBoard, firstPlayerLocation)) {
					System.out.print("Choose a board to put your stone : ");
					firstPlayerBoard = input.nextInt();
					while(firstPlayerBoard > 4 || firstPlayerBoard < 0) {
						System.out.print("Choose a valid board : ");
						firstPlayerBoard = input.nextInt();
					}	
					System.out.print("Where do you want to put your stone : ");
					firstPlayerLocation = input.nextInt();
					while(firstPlayerLocation > 9 || firstPlayerLocation < 0) {
						System.out.print("Choose a valid location : ");
						firstPlayerLocation = input.nextInt();
					}
				}
				board.print();
				if(board.endGame())
					break;
				int newChoice = 1;
				if(board.notToRotate()) {
					System.out.println("Would you like to rotate a board ?");
					System.out.println("1. Yes");
					System.out.println("2. No");
					newChoice = input.nextInt();
				}
				if(newChoice == 1) {
					System.out.print("Choose a board to rotate it : ");
					int firstBoardRotate = input.nextInt();
					while(firstBoardRotate > 4 || firstBoardRotate < 0) {
						System.out.print("Choose a valid board to rotate it : ");
						firstBoardRotate = input.nextInt();
					}
					System.out.println("How do you want to rotate it : ");
					System.out.println("1. Clockwise");
					System.out.println("2. Anti Clockwise");
					int firstPlayerRotateDirection = input.nextInt();
					board.rotateBoard(firstBoardRotate, firstPlayerRotateDirection);
					board.print();
					if(board.endGame())
						break;
					
				}
				System.out.println(secondPlayerName + " turn : ");
				System.out.print("Choose a board to put your stone : ");
				int secondPlayerBoard = input.nextInt();
				while(secondPlayerBoard > 4 || secondPlayerBoard < 0) {
					System.out.print("Choose a valid board : ");
					secondPlayerBoard = input.nextInt();
				}	
				System.out.print("Where do you want to put your stone : ");
				int secondPlayerLocation = input.nextInt();
				while(secondPlayerLocation > 9 || secondPlayerLocation < 0) {
					System.out.print("Choose a valid location : ");
					secondPlayerLocation = input.nextInt();
				}
				
				while (! board.addDiskToBoard(secondPlayer, secondPlayerBoard, secondPlayerLocation)) {
					System.out.print("Choose a board to put your stone : ");
					secondPlayerBoard = input.nextInt();
					while(secondPlayerBoard > 4 || secondPlayerBoard < 0) {
						System.out.print("Choose a valid board : ");
						secondPlayerBoard = input.nextInt();
					}	
					System.out.print("Where do you want to put your stone : ");
					secondPlayerLocation = input.nextInt();
					while(secondPlayerLocation > 9 || secondPlayerLocation < 0) {
						System.out.print("Choose a valid location : ");
						secondPlayerLocation = input.nextInt();
					}
				}
				board.print();
				if(board.endGame())
					break;
				newChoice = 1;
				if(board.notToRotate()) {
					System.out.println("Would you like to rotate a board ?");
					System.out.println("1. Yes");
					System.out.println("2. No");
					newChoice = input.nextInt();
				}
				if(newChoice == 1) {
					System.out.print("Choose a board to rotate it : ");
					int secondBoardRotate = input.nextInt();
					while(secondBoardRotate > 4 || secondBoardRotate < 0) {
						System.out.print("Choose a valid board to rotate it : ");
						secondBoardRotate = input.nextInt();
					}	
					System.out.println("How do you want to rotate it : ");
					System.out.println("1. Clockwise");
					System.out.println("2. Anti Clockwise");
					int secondPlayerRotateDirection = input.nextInt();
					board.rotateBoard(secondBoardRotate, secondPlayerRotateDirection);
					board.print();
					if(board.endGame())
						break;
				}
				
			}
		}
		
	}

}
