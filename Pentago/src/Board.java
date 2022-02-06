/**
 * This class hold all the stones in a board and do the command such as add stone or rotate
 * @author Hasti
 * @version 1.0
 */
public class Board {
	
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	private Player red;
	private Player blue;
	private char[][] map;
	/**
	 * create board and create a empty map
	 */
	public Board() {
		
		map = new char[6][6];
		red = new Player('r');
		blue = new Player('b');
		
		for(int i = 0; i < 6; i++) {
			for(int j =0;j < 6; j++) {
				map[i][j] = ' ';
			}
		}
		
	}
	/**
	 * add a disk to the board and check that we can add this stone in this location or not
	 * @param playerColor
	 * @param board
	 * @param location
	 * @return true or false
	 */
	public boolean addDiskToBoard(char playerColor, int board, int location) {
		
		int x = (location - 1) / 3 + (board / 3) * 3;
		int y = (location - 1 ) % 3 + ((board + 1) % 2) * 3;
		
		if(map[x][y] == ' ') {
			map[x][y] = playerColor;
			
			if(red.getplayerColor() == playerColor) 
				red.addStone(board, location);
			
			else
				blue.addStone(board, location);
			
			return true;
		}
		return false;
	}
	/**
	 * rotate a board and change the stones's location
	 * @param board
	 * @param rotateDirection
	 */
	public void rotateBoard(int board, int rotateDirection) {
		//rotate direction : 1. clockwise , 2. anti clockwise
		
		int x = (board == 1 || board == 2) ? 0 : 3;
		int y = (board == 1 || board == 3) ? 0 : 3;
		
		if(rotateDirection == 1) {
			red.changeStoneLocation(board, 1, 3);
			red.changeStoneLocation(board, 3, 9);
			red.changeStoneLocation(board, 9, 7);
			blue.changeStoneLocation(board, 1, 3);
			blue.changeStoneLocation(board, 3, 9);
			blue.changeStoneLocation(board, 9, 7);
			red.changeStoneLocation(board, 2, 6);
			red.changeStoneLocation(board, 6, 8);
			red.changeStoneLocation(board, 8, 4);
			blue.changeStoneLocation(board, 2, 6);
			blue.changeStoneLocation(board, 6, 8);
			blue.changeStoneLocation(board, 8, 4);
			
			this.changeTwoPositionInMap(x, y, x + 2, y);
			this.changeTwoPositionInMap(x + 2, y, x + 2, y + 2);
			this.changeTwoPositionInMap(x + 2, y + 2, x, y + 2);
			this.changeTwoPositionInMap(x, y + 1, x + 1, y);
			this.changeTwoPositionInMap(x + 1, y, x + 2, y + 1);
			this.changeTwoPositionInMap(x + 2, y + 1, x + 1, y + 2);
		}
		else {
			red.changeStoneLocation(board, 1, 7);
			red.changeStoneLocation(board, 7, 9);
			red.changeStoneLocation(board, 9, 3);
			blue.changeStoneLocation(board, 1, 7);
			blue.changeStoneLocation(board, 7, 9);
			blue.changeStoneLocation(board, 9, 3);
			red.changeStoneLocation(board, 2, 4);
			red.changeStoneLocation(board, 4, 8);
			red.changeStoneLocation(board, 8, 6);
			blue.changeStoneLocation(board, 2, 4);
			blue.changeStoneLocation(board, 4, 8);
			blue.changeStoneLocation(board, 8, 6);
			
			this.changeTwoPositionInMap(x, y, x, y + 2);
			this.changeTwoPositionInMap(x, y + 2, x + 2, y + 2);
			this.changeTwoPositionInMap(x + 2, y + 2, x + 2, y);
			this.changeTwoPositionInMap(x, y + 1, x + 1, y + 2);
			this.changeTwoPositionInMap(x + 1, y + 2, x + 2, y + 1);
			this.changeTwoPositionInMap(x + 2, y + 1, x + 1, y);
		}
		
		
	}
	/**
	 * when we rotate we use this method to change the position of two stones
	 * @param x
	 * @param y
	 * @param newX
	 * @param newY
	 */
	private void changeTwoPositionInMap(int x, int y, int newX, int newY) {
		
		char tmp = map[x][y];
		map[x][y] = map[newX][newY];
		map[newX][newY] = tmp;
		
	}
	/**
	 * print the board to console
	 */
	public void print() {
		for(int i = 0; i < 6 + 1; i++) {
			
			if(i == 3) {
				System.out.println(ANSI_YELLOW + "* * * * * * * * * * *" + ANSI_RESET);
			}
			else {
				System.out.print(" ");
				for(int j = 0; j < 2 * 6 + 1; j++) {
					
					if(j == 6) {
						System.out.print(ANSI_YELLOW + "*" + ANSI_RESET);
						continue;
					}
					
					int x = (i < 3) ? i : i - 1;
					int y = (j < 6) ? j /2   : j / 2- 1;
					String color = (map[x][y] == 'r') ? ANSI_RED : (map[x][y] == 'b') ? ANSI_CYAN :ANSI_RESET;
					
					if(j % 2 == 0) {
						System.out.print(color + '⬤' + ANSI_RESET);
					}
					else
						System.out.print(" ");
				}
				System.out.println();
			}
		}
		System.out.println();
	}
	/**
	 * check that when player add a stone to board the location was empty or not
	 * @param board
	 * @param location
	 * @return true or false
	 */
	public boolean isValidMove(int board, int location) {
		int x = (location - 1) / 3 + (board / 3) * 3;
		int y = (location - 1 ) % 3 + ((board + 1) % 2) * 3;
		if(map[x][y] == ' ')
			return true;
		return false;
	}
	/**
	 * check that the game is over or not
	 * @return true or false
	 */
	public boolean endGame() {
		
		boolean similarStones = false;
		char player = ' ';
		char otherPlayer = ' ';
		
		//check horizontal
		for(int i = 0; i < 6 ; i++) {
			if(map[i][1] == map[i][2] && map[i][2] == map[i][3] && map[i][3] == map[i][4] && (map[i][0] == map[i][2] || map[i][5] == map[i][2]) && map[i][1] != ' ') {
				similarStones = true;
				if(player == ' ')
					player = map[i][1];
				else if(player != map[i][1])
					otherPlayer = map[i][1];
			}
		}
		
		//check vertical
		for(int j = 0; j < 6; j++) {
			if(map[1][j] == map[2][j] && map[2][j] == map[3][j] && map[3][j] == map[4][j] &&(map[0][j] == map[2][j] || map[5][j] == map[2][j]) && map[1][j] != ' ') {
				similarStones = true;
				if(player == ' ')
					player = map[1][j];
				else if(player != map[1][j])
					otherPlayer = map[1][j];
			}
		}
		
		//check diagonal
		if(map[1][0] == map[2][1] && map[3][2] == map[2][1] && map[3][2] == map[4][3] && map[4][3] == map[5][4] && map[5][4] != ' ') {
			similarStones = true;
			if(player == ' ')
				player = map[1][0];
			else if(player != map[1][0])
				otherPlayer = map[1][0];
		}
		if(map[1][2] == map[0][1] && map[1][2] == map[2][3] && map[2][3] == map[3][4] && map[3][4] == map[4][5] && map[4][5] != ' ') {
			similarStones = true;
			if(player == ' ')
				player = map[1][2];
			else if(player != map[1][2])
				otherPlayer = map[1][2];
		}
		if(map[1][1] == map[2][2] && map[2][2] == map[3][3] && map[3][3] == map[4][4] && (map[0][0] == map[1][1] || map[5][5] == map[1][1]) && map[1][1] != ' ') {
			similarStones = true;
			if(player == ' ')
				player = map[1][1];
			else if(player != map[1][1])
				otherPlayer = map[1][1];
		}	
		if(map[0][4] == map[1][3] && map[1][3] == map[2][2] && map[2][2] == map[3][1] && map[3][1] == map[4][0] && map[4][0] != ' ') {
			similarStones = true;
			if(player == ' ')
				player = map[0][4];
			else if(player != map[0][4])
				otherPlayer = map[0][4];
		}
		if(map[1][5] == map[2][4] && map[2][4] == map[3][3] && map[3][3] == map[4][2] && map[4][2] == map[5][1] && map[5][1] != ' ') {
			similarStones = true;
			if(player == ' ')
				player = map[1][5];
			else if(player != map[1][5])
				otherPlayer = map[1][5];
			
		}	
		if(map[1][4] == map[2][3] && map[2][3] == map[3][2] && map[3][2] == map[4][1] && (map[0][5] == map[1][4] || map[5][0] == map[1][4]) && map[1][4] != ' ') {
			similarStones = true;
			if(player == ' ')
				player = map[1][4];
			else if(player != map[1][4])
				otherPlayer = map[1][4];
		}
			
		if(similarStones == false)	
			return false;
		else {
			if(player != otherPlayer && player != ' ' && otherPlayer != ' ') {
				System.out.println("No Winner !");
			}	
			else if(player != ' ') {
				if(red.getplayerColor() == player) {
					System.out.println(ANSI_RED + "* * * * * * *");
					System.out.println(ANSI_RED + "*" + ANSI_RESET +" Red Wins! " + ANSI_RED + "*" + ANSI_RESET );
					System.out.println(ANSI_RED + "* * * * * * *" + ANSI_RESET);
				}
				else {
					System.out.println(ANSI_CYAN + "* * * * * * * *");
					System.out.println(ANSI_CYAN + "*" +ANSI_RESET + " Blue Wins ! " + ANSI_CYAN + "*" +ANSI_RESET);
					System.out.println(ANSI_CYAN + "* * * * * * * *" + ANSI_RESET);
				}
			}
				
			return true;	
		}
				
	}
	/**
	 * check that a player can, not to rotate a board or not
	 * @return true or false
	 */
	public boolean notToRotate() {
		for(int k = 1; k < 5; k++) {
			int x = (k == 1 || k == 2) ? 0 : 3;
			int y = (k == 1 || k == 3) ? 0 : 3; 
			
			char newMap1[][] = new char[3][3];
			char newMap2[][] = new char[3][3];
			
			for(int i = 0; i < 3; i++) {
				for(int j = 0 ; j < 3 ; j++) {
					newMap1[i][j] = map[x + i][y + j];
					newMap2[i][j] = map[x + i][y + j];
				}
			}
			
			char tmp = newMap2[0][0];
			newMap2[0][0] = newMap2[2][0];
			newMap2[2][0] = tmp;

			tmp = newMap2[2][0];
			newMap2[2][0] = newMap2[2][2];
			newMap2[2][2] = tmp;
			
			tmp = newMap2[2][2];
			newMap2[2][2] = newMap2[0][2];
			newMap2[0][2] = tmp;
			
			tmp = newMap2[0][1];
			newMap2[0][1] = newMap2[1][0];
			newMap2[1][0] = tmp;
			
			tmp = newMap2[1][0];
			newMap2[1][0] = newMap2[2][1];
			newMap2[2][1] = tmp;
			
			tmp = newMap2[2][1];
			newMap2[2][1] = newMap2[1][2];
			newMap2[1][2] = tmp;
			
			boolean check = true;
			
			for(int i = 0 ; i < 3; i++) {
				for(int j = 0; j < 3 ; j++) {
					if(newMap1[i][j] != newMap2[i][j])
						check = false;
				}
			}
			
			if(check == true)
				return true;
		}
		return false;
	}
}
