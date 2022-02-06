import java.util.Scanner;

public class Main {
	

	public static void main(String args[]) {
			
			
		Scanner input = new Scanner(System.in);
		
		Board board = new Board();
		
		System.out.println("1. Person vs Person");
		System.out.println("2. Person vs Computer");
		int choice = input.nextInt();
		String c = input.nextLine();
		board.print();
		//board.play("1 G", '●');
		//board.print();
		//board.play("2 G", '○');
		//board.print();
		
			while(!(board.gameOver())) {
				
			
			
			if(choice == 1) {
				if(board.canPlay('●')) {
					System.out.println("Black Move: ");
					String blackMove = input.nextLine();
					while(blackMove.length() < 3) {
						System.out.println("Black Move: ");
						blackMove = input.nextLine();
					}
					boolean b = board.play(blackMove, '●');
					while(b == false) {
						System.out.println("Black Move: ");
						blackMove = input.nextLine() ;
						b = board.play(blackMove, '●');
					}
						
					board.print();
				}
				else
					System.out.println("PASS");
				
				if(board.canPlay('○')) {
					System.out.println("White Move :");
					String whiteMove = input.nextLine();
					while(whiteMove.length() < 3) {
						System.out.println("White Move :");
						whiteMove = input.nextLine();
					}
					boolean b = board.play(whiteMove, '○');
					while(b == false) {
						System.out.println("White Move :");
						whiteMove = input.nextLine();
						b = board.play(whiteMove, '○');
					}
					board.print();
				}
				else
					System.out.println("PASS");
				
				
			}
			if(choice == 2) {
				if(board.canPlay('●')) {
					System.out.println("Black Move: ");
					String blackMove = input.nextLine();
					while(blackMove.length() < 3) {
						System.out.println("Black Move: ");
						blackMove = input.nextLine();
					}
					boolean b = board.play(blackMove, '●');
					while(b == false) {
						System.out.println("Black Move: ");
						blackMove = input.nextLine() ;
						b = board.play(blackMove, '●');
					}
						
					board.print();
				}
				else
					System.out.println("PASS");
				
				if(board.canPlay('○')) {
					board.playWithComputer();
				}
				else
					System.out.println("PASS");
			}

			
		}
		board.result();
	}
}
