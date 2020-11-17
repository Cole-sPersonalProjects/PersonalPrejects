import java.io.Serializable;
import java.util.Random;

public class RandomComputerStrategy implements AComputerPullStrategy, Serializable {

	private int ALTERNATION_RANGE = 3;
	private Random rand;
	
	/**
	 * constructor, initializes Random
	 */
	public RandomComputerStrategy() {

		rand = new Random();
		
	}
	
	/**
	 * @return pullNumber, either 1 or 2 chosen randomly
	 */
	@Override
	public int getComputerPullNumber(int sticksRemaining) {
		
		int pullNumber = rand.nextInt(ALTERNATION_RANGE) + 1; //rand.next starts at 0

		//not using <= because its always smarter not to pull the last stick if possible
		if(pullNumber < sticksRemaining) 
			return pullNumber; 
		else if(sticksRemaining > 1) //if two or three left, will pull one or two
			return (sticksRemaining -1);
		else
			return 1; //pulls last stick
	}


	/**
	 * not needed for this implementation of AComputerStrategy
	 */
	@Override
	public void winAndSave(boolean computerWin) {
		
	}


}
