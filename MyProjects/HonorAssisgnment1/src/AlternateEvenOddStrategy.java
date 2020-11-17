import java.io.Serializable;

public class AlternateEvenOddStrategy implements AComputerPullStrategy, Serializable{
	
	private boolean isEven;
	
	/**
	 * constructor sets isEven to true
	 */
	public AlternateEvenOddStrategy() {
		
		isEven = true;
		
	}
	
	/**
	 * @param int sticksRemaining
	 * @return pull number 1 or 2
	 * will start with 1 since it alternates first
	 */
	@Override
	public int getComputerPullNumber(int sticksRemaining) {
		
		int pullNumber;
		
		if(sticksRemaining <= 0) {
			return -1;
		}
		
		alternate();
		
		if(isEven) {
			pullNumber = 2;
		}
		else {
			pullNumber = 1;
		}
		
		if(pullNumber <= sticksRemaining) //doesn't try to over-pull
			return pullNumber; 
		else
			return 1;
		
	}

	/**
	 * switches the value of boolean isEven
	 */
	private void alternate() {
		if(isEven) {
			isEven = false;
		}
		else {
			isEven = true;
		}
	}

	/**
	 * @return boolean is Even
	 */
	public boolean getIsEven() {
		return isEven;
	}

	/**
	 * not needed for this implementation of AComputerStrategy
	 */
	@Override
	public void winAndSave(boolean computerWin) {
		
	}


}