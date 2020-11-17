import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Hat implements Serializable{

	private int HAT_NUMBER;
	private int LAST_STICK = 1;
	private ArrayList<Integer> balls = new ArrayList<Integer>();
	private Random rand = new Random();
	private Integer besides = -1;
	
	/**
	 * Constructor for new Hat, HAT_NUMBER references sticksRemaining
	 * @param HAT_NUMBER
	 */
	public Hat(int HAT_NUMBER) {
		
		this.HAT_NUMBER = HAT_NUMBER;
		
		//all hats start with one of each possible pull value
		balls.add(1);
		balls.add(2);
		balls.add(3);
	}
	
	/**
	 * gets a random pull number from the hat
	 * @param sticksRemaining
	 * @return the number pulled out of hat and placed besides
	 */
	public int getRandomBall(int sticksRemaining) {
		
		//the next code may work for 1 stick remaining but it would have 
		//to run the while loop for a long time to randomly pull a 1
		if(sticksRemaining == 1)
			return LAST_STICK;
		
		while(true) {
			int indexOfBall = rand.nextInt(balls.size());
			besides  = balls.remove(indexOfBall);
			
			if(besides <= sticksRemaining) //makes sure pull number is valid
				return besides;
			else
				balls.add(besides); //if invalid, returns it to hat, randomly draws another
			//we need to keep one of each value (1,2,3) in the hat at all times
		}
		
	}
	
	/**
	 * 
	 * @return besides
	 */
	public int getBesides() {
		return besides;
	}
	
	/**
	 * add to hat double of besides
	 */
	public void addDoubleBesides() {
		balls.add(besides);
		balls.add(besides);
	}
	
	/**
	 * erases the besides
	 */
	public void eraseBesides() {
		if(besides != -1)
			balls.add(besides);
		besides = -1;
	}

	/*public String getBallList() {
		// TODO Auto-generated method stub
		String ballList = "";
		for(int i = 0; i<balls.size(); i++) {
			ballList = ballList + balls.get(i) + ", ";
		}
		return ballList;
	}*/
}
