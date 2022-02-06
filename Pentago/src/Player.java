import java.util.ArrayList;

public class Player {
	
	private ArrayList<Integer> firstBoardStones;
	private ArrayList<Integer> secondBoardStones;
	private ArrayList<Integer> thirdBoardStones;
	private ArrayList<Integer> fourthBoardStones;
	char color;
	
	public Player(char color) {
		
		firstBoardStones = new ArrayList<>();
		secondBoardStones = new ArrayList<>();
		thirdBoardStones = new ArrayList<>();
		fourthBoardStones = new ArrayList<>();
		
		for(int i = 0; i < 9; i++) {
			firstBoardStones.add(0);
			secondBoardStones.add(0);
			thirdBoardStones.add(0);
			fourthBoardStones.add(0);
		}
		
		this.color = color;
		
	}
	
	public void addStone(int board, int location) {
		if(board == 1)
			firstBoardStones.set(location - 1, 1);
		else if(board == 2)
			secondBoardStones.set(location - 1, 1);
		else if(board == 3)
			thirdBoardStones.set(location - 1, 1);
		else if(board == 4)
			fourthBoardStones.set(location - 1, 1);
		
	}
	
	public void changeStoneLocation(int board, int location, int newLocation) {
		
		ArrayList<Integer> stones = (board == 1) ? firstBoardStones : (board == 2) ? secondBoardStones : (board == 3) ? thirdBoardStones : fourthBoardStones;
		
		int tmp = stones.get(location - 1);
		stones.set(location - 1, stones.get(newLocation - 1));
		stones.set(newLocation - 1, tmp);
	}
	
	public char getplayerColor() {
		return color;
	}
	
	/*public ArrayList<Integer> getSameBoardStones(int board){
		
		ArrayList<Integer> stones = (board == 1) ? firstBoardStones : (board == 2) ? secondBoardStones : (board == 3) ? thirdBoardStones : fourthBoardStones;
		
	}*/
}
