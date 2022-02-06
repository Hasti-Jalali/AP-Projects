import java.util.ArrayList;

/**
 * A class to hold detail of Player
 * @author Hasti
 *
 */
public class Player {
	
	
	private char color; //○ or ●
	ArrayList<Disk> disks;
	/**
	 * create player
	 * @param color
	 */
	public Player(char color) {
		
		disks = new ArrayList<Disk>();
		
		this.color = color;
		
	}
	/**
	 * get color of the player
	 * @return color
	 */
	public char getColor() {
		return color;
	}
	/**
	 * get all the disk that player has
	 * @return disks
	 */
	public ArrayList<Disk> getDisks(){
		return disks;
	}
	
	/**
	 * add a disk to all disks
	 * @param x
	 * @param y
	 */
	public void addDisk(int x, int y) {
		disks.add(new Disk(x,y));
	}
	/**
	 * empty the all the disks
	 */
	public void clearDisks() {
		disks.clear();
	}
	
}
