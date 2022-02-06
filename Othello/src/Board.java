
/**
 * A class o hold detail of the board
 * @author Hasti
 * @version 1.0
 */
public class Board {
	
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	private char[][] map;
	private final int row = 8;
	private final int collumn = 8;
	
	private int move;
	private Disk moveDisk;
	
	private Player white;
	private Player black;
	/**
	 * create board
	 */
	public Board() {
		
		map = new char[row][collumn];
			for(int i = 0; i < row ; i++) {
			for(int j = 0; j < collumn ; j++) {
				map[i][j] = ' ';
			}
		}
		map[3][3] = '○';
		map[3][4] = '●';
		map[4][3] = '●';
		map[4][4] = '○';
		
		white = new Player('○');
		black = new Player('●');
		white.addDisk(3,3);
		white.addDisk(4, 4);
		black.addDisk(3, 4);
		black.addDisk(4, 3);
		
	}
	/**
	 * print the board
	 */
	public void print() {
		System.out.print(ANSI_CYAN);
		System.out.println("      White : " + white.getDisks().size() + "      Black : " + black.getDisks().size());
		System.out.println("   _______________________________");
		
		System.out.print(ANSI_RESET);
		System.out.println("    A   B   C   D   E   F   G   H");
	
		for(int i = 0; i < (2 * row + 1); i++) { 
			if(i % 2 == 0) {
				System.out.print("  ");
				for(int j = 0 ; j < (4 * collumn + 1); j++) {
					if(j % 2 == 0) {
						System.out.print(ANSI_CYAN);
						System.out.print("*");
						System.out.print(ANSI_RESET);
					}
					else
						System.out.print(' ');
				}
			}
			else {
				System.out.print((i -1)/2 + 1 + " ");
				for(int j = 0 ; j < (4 * collumn + 1); j++) {
					if(j % 4 == 0) {
						System.out.print(ANSI_CYAN);
						System.out.print("*");
						System.out.print(ANSI_RESET);
					}
					else if(j % 2 == 1) 
						System.out.print(' ');
					else {
						int x = (i - 1) / 2;
						int y = (j - 2) / 4;
						System.out.print(map[x][y]);
					}
				}
			}
			System.out.println();
		}
	}
	/**
	 * check that the game is over or not
	 * @return true or false
	 */
	public boolean gameOver() {
		
		if(!(this.canPlay(white.getColor())) && (!this.canPlay(black.getColor()))) {
			return true;
		}
		/*
		for(int i = 0 ; i < row; i++) {
			for(int j = 0; j < collumn; j++) {
				if(map[i][j] == ' ')
					return false;
			}
		}
		return true;
		*/
		return false;
	}
	/**
	 * a player play her/his turn
	 * @param play
	 * @param player
	 * @return true or false
	 */
	public boolean play(String play, char player) {
		char[] c = play.toCharArray();
		int x = (int)(c[0] - '1');
		int y = (int)(c[2] - 'A');
		boolean canPlay = false;
		if(map[x][y] == ' ') {
			//check left
			if(y - 1 >= 0 && (map[x][y - 1] == ' ' ||  map[x][y - 1] == player));
			else if(y - 1 >= 0){
				char otherPlayer = map[x][y - 1];
				int newY = y - 2;
				while(newY >= 0) {
					if(map[x][newY] == player) {
						canPlay = true;
						for(int i = 1 ; i < y - newY; i++)
							map[x][newY + i] = player;
						
						break;
					}
					else if(map[x][newY] == otherPlayer) {
						newY--;
					}
					else
						break;
				}
			}
			
			//check right
			if(y + 1 < 8 && (map[x][y + 1] == ' ' || map[x][y + 1] == player));
			else if(y + 1 < 8){
				char otherPlayer = map[x][y + 1];
				int newY = y + 2;
				while(newY < 8) {
					if(map[x][newY] == player) {
						canPlay = true;
						for(int i = 1; i < newY - y; i++)
							map[x][newY - i] = player;
						break;
					}
					else if(map[x][newY] == otherPlayer) {
						newY++;
					}
					else
						break;
				}
			}
			
			//check down
			if(x + 1 < 8 && (map[x + 1][y] == player || map[x + 1][y] == ' '));
			else if(x + 1 < 8){
				char otherPlayer = map[x + 1][y];
				int newX = x + 2;
				while(newX < 8) {
					if(map[newX][y] == player) {
						canPlay = true;
						for(int i = 1; i < newX - x; i++)
							map[newX - i][y] = player;
						break;
					}
					else if(map[newX][y] == otherPlayer)
						newX++;
					else
						break;
				}
			}
			
			//check up
			if(x - 1 >=0 && (map[x - 1][y] == player || map[x - 1][y] ==' '));
			else if(x - 1 >= 0) {
				char otherPlayer = map[x - 1][y];
				int newX = x - 2;
				while(newX >= 0) {
					if(map[newX][y] == player) {
						canPlay = true;
						for(int i = 1; i < x - newX; i++) {
							map[newX + i][y] = player;
						}
						break;
					}
					else if(map[newX][y] == otherPlayer)
						newX--;
					else
						break;
				}
			}
			
			//check ⬈
			if((x - 1 >= 0) && (y + 1 < 8) && (map[x - 1][y + 1] == player || map[x - 1][y + 1] == ' '));
			else if((x - 1 >= 0) && (y + 1 < 8)) {
				char otherPlayer = map[x - 1][y + 1];
				int newX = x - 2;
				int newY = y + 2;
				
				while(newX >= 0 && newY < 8) {
					if(map[newX][newY] == player) {
						canPlay = true;
						for(int i = 1; i < x - newX ;i++) {
							map[newX + i][newY - i] = player;
						}
						break;
					}
					else if(map[newX][newY] == otherPlayer) {
						newX--;
						newY++;
					}
					else
						break;
				}
			}
			
			//check ⬉
			if((x - 1 >= 0) && (y - 1 >= 0) && (map[x - 1][y - 1] == player || map[x - 1][y - 1] == ' '));
			else if((x - 1 >= 0) && (y - 1 >= 0)) {
				char otherPlayer = map[x - 1][y - 1];
				int newX = x - 2;
				int newY = y - 2;
				
				while(newX >= 0 && newY >= 0) {
					if(map[newX][newY] == player) {
						canPlay = true;
						for(int i = 1; i < x - newX; i++) {
							map[newX + i][newY + i] = player;
						}
						break;
					}
					else if(map[newX][newY] == otherPlayer) {
						newX--;
						newY--;
					}
					else
						break;
				}
			}
			
			//check ⬋
			if((x + 1 < 8) && (y - 1 >= 0) && (map[x + 1][y - 1] == player || map[x + 1][y - 1] == ' '));
			else if((x + 1 < 8) && (y - 1 >= 0)) {
				char otherPlayer = map[x + 1][y - 1];
				int newX = x + 2;
				int newY = y - 2;
				
				while(newX < 8 && newY >= 0) {
					if(map[newX][newY] == player) {
						canPlay = true;
						for(int i = 1; i < newX - x; i++) {
							map[newX - i][newY + i] = player;
						}
						break;
					}
					else if(map[newX][newY] == otherPlayer) {
						newX++;
						newY--;
					}
					else
						break;
				}
			}
			
			//check ⬊
			if((x + 1 < 8) && (y + 1 < 8) && (map[x + 1][y + 1] == player || map[x + 1][y + 1] == ' '));
			else if((x + 1 < 8) && (y + 1 < 8)) {
				char otherPlayer = map[x + 1][y + 1];
				int newX = x + 2;
				int newY = y + 2;
				
				while(newX < 8 && newY < 8) {
					if(map[newX][newY] == player) {
						canPlay = true;
						for(int i = 1; i < newX - x; i++) {
							map[newX - i][newY - i] = player;
						}
						break;
					}
					else if(map[newX][newY] == otherPlayer) {
						newX++;
						newY++;
					}
					else
						break;
				}
			}
			
		}

		if(canPlay == true)
			map[x][y] = player;
		
		white.clearDisks();
		black.clearDisks();
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < collumn ; j++) {
				if(map[i][j] == '○')
					white.addDisk(i,j);
				else if(map[i][j] == '●')
					black.addDisk(i,j);
			}
		}
		
		
		return canPlay;
	}
	
	
	/**
	 * check that a player can play or not
	 * @param player
	 * @return true or false
	 */
	public boolean canPlay(char player) {
		move = 0;
		int newMove= 0;
		Player p;
		char otherPlayer;
		if(player == white.getColor()) {
			p = white;
			otherPlayer = black.getColor();
			}	
		else {
			p = black;
			otherPlayer = white.getColor();
		}
		boolean canPlay = false;
		for(Disk disk : p.getDisks()) {
			int x = disk.getX();
			int y = disk.getY();
			//check left
			if(y - 1 >= 0 && (map[x][y - 1] == ' ' ||  map[x][y - 1] == player));
			else if(y - 1 >= 0){
				int newY = y - 2;
				while(newY >= 0) {
					if(map[x][newY] == ' ') {
						canPlay = true;
						newMove = y - newY - 1;
						if(newMove > move) {
							move = newMove;
							moveDisk = new Disk(x,newY);
						}
						newMove = 0;
						break;
					}
					else if(map[x][newY] == otherPlayer) {
						newY--;
					}
					else
						break;
				}
			}
			
			//check right
			if(y + 1 < 8 && (map[x][y + 1] == ' ' || map[x][y + 1] == player));
			else if(y + 1 < 8){
				int newY = y + 2;
				while(newY < 8) {
					if(map[x][newY] == ' ') {
						canPlay = true;
						newMove = newY - y - 1;
						if(newMove > move) {
							move = newMove;
							moveDisk = new Disk(x,newY);
						}
						newMove = 0;
						break;
					}
					else if(map[x][newY] == otherPlayer) {
						newY++;
					}
					else
						break;
				}
			}
			
			//check down
			if(x + 1 < 8 && (map[x + 1][y] == player || map[x + 1][y] == ' '));
			else if(x + 1 < 8){
				int newX = x + 2;
				while(newX < 8) {
					if(map[newX][y] == ' ') {
						canPlay = true;
						newMove = newX - x - 1;
						if(newMove > move) {
							move = newMove;
							moveDisk = new Disk(newX,y);
						}
						newMove = 0;
						break;
					}
					else if(map[newX][y] == otherPlayer)
						newX++;
					else
						break;
				}
			}
			
			//check up
			if(x - 1 >=0 && (map[x - 1][y] == player || map[x - 1][y] ==' '));
			else if(x - 1 >= 0) {
				int newX = x - 2;
				while(newX >= 0) {
					if(map[newX][y] == ' ') {
						newMove = x - newX - 1;
						if(newMove > move) {
							move = newMove;
							moveDisk = new Disk(newX,y);
						}
						newMove = 0;
						canPlay = true;
						break;
					}
					else if(map[newX][y] == otherPlayer)
						newX--;
					else
						break;
				}
			}
			
			//check ⬈
			if((x - 1 >= 0) && (y + 1 < 8) && (map[x - 1][y + 1] == player || map[x - 1][y + 1] == ' '));
			else if((x - 1 >= 0) && (y + 1 < 8)) {
				int newX = x - 2;
				int newY = y + 2;
				
				while(newX >= 0 && newY < 8) {
					if(map[newX][newY] == ' ') {
						canPlay = true;
						newMove = x - newX - 1;
						if(newMove > move) {
							move = newMove;
							moveDisk = new Disk(newX,newY);
						}
						newMove = 0;
						break;
					}
					else if(map[newX][newY] == otherPlayer) {
						newX--;
						newY++;
					}
					else
						break;
				}
			}
			
			//check ⬉
			if((x - 1 >= 0) && (y - 1 >= 0) && (map[x - 1][y - 1] == player || map[x - 1][y - 1] == ' '));
			else if((x - 1 >= 0) && (y - 1 >= 0)) {
				int newX = x - 2;
				int newY = y - 2;
				while(newX >= 0 && newY >= 0) {
					if(map[newX][newY] == ' ') {
						canPlay = true;
						newMove = x - newX - 1;
						if(newMove > move) {
							move = newMove;
							moveDisk = new Disk(newX,newY);
						}
						newMove = 0;
						break;
					}
					else if(map[newX][newY] == otherPlayer) {
						newX--;
						newY--;
					}
					else
						break;
				}
			}
			
			//check ⬋
			if((x + 1 < 8) && (y - 1 >= 0) && (map[x + 1][y - 1] == player || map[x + 1][y - 1] == ' '));
			else if((x + 1 < 8) && (y - 1 >= 0)) {
				int newX = x + 2;
				int newY = y - 2;
				
				while(newX < 8 && newY >= 0) {
					if(map[newX][newY] == ' ') {
						canPlay = true;
						newMove = newX - x - 1;
						if(newMove > move) {
							move = newMove;
							moveDisk = new Disk(newX,newY);
						}
						newMove = 0;
						break;
					}
					else if(map[newX][newY] == otherPlayer) {
						newX++;
						newY--;
					}
					else
						break;
				}
			}
			
			//check ⬊
			if((x + 1 < 8) && (y + 1 < 8) && (map[x + 1][y + 1] == player || map[x + 1][y + 1] == ' '));
			else if((x + 1 < 8) && (y + 1 < 8)) {
				int newX = x + 2;
				int newY = y + 2;
				
				while(newX < 8 && newY < 8) {
					if(map[newX][newY] == ' ') {
						canPlay = true;
						newMove = newX - x - 1;
						if(newMove > move) {
							move = newMove;
							moveDisk = new Disk(newX,newY);
						}
						newMove = 0;
						break;
					}
					else if(map[newX][newY] == otherPlayer) {
						newX++;
						newY++;
					}
					else
						break;
				}
			}
			
		}
		
		return canPlay;
	}
	/**
	 * print the result of the game
	 */
	public void result() {
		
		
		int blackResult = black.getDisks().size();
		int whiteResult = white.getDisks().size();
		
		System.out.println("● ×" + blackResult);
		System.out.println("○ ×" + whiteResult);
		
		if(blackResult > whiteResult)
			System.out.println("Black Wins!");
		else if(blackResult < whiteResult)
			System.out.println("White  Wins!");
		else
			System.out.println("Nobody Wins!!");
	}
	
	public void playWithComputer() {
		
		if(canPlay(white.getColor())) {
			char x = (char) ((char) moveDisk.getX() + '1');
			char y = (char)((char) moveDisk.getY() + 'A');
			String play =  x + " " + y ;
			play(play, white.getColor());
		}
		
	}
}
