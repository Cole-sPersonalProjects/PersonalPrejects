import java.io.Serializable;
import java.util.ArrayList;

public class ComputerAIStrategy implements AComputerPullStrategy, Serializable {

	//array list of hats, each containing values to pull from for a certain number of sticksRemaining
	private ArrayList<Hat> hats = new ArrayList<Hat>();
	
	/**
	 * constructor to set hat values
	 * @param START_NUMBER the number of sticks each game begins with
	 */
	public ComputerAIStrategy(int START_NUMBER) {
		for(int i = 0; i <= START_NUMBER; i++) {	
			hats.add(new Hat(i));
		}
	}
	
	/**
	 * @param sticksRemaining
	 * @return some valid pull number taken out of the hat
	 */
	@Override
	public int getComputerPullNumber(int sticksRemaining) {
		//System.out.println("Hat " + sticksRemaining + " has the values " + hats.get(sticksRemaining).getBallList());
		return hats.get(sticksRemaining).getRandomBall(sticksRemaining);
	}

	/**
	 * @param boolean isComputerWin
	 * saves or deletes all of the values in besides
	 */
	@Override
	public void winAndSave(boolean isComputerWin) {
		
		if(isComputerWin) 
			saveBesides();
		else
			deleteBesides();
		
	}

	/**
	 * deletes the besides for all hats
	 */
	private void deleteBesides() {
		
		for(int i = 0; i < hats.size(); i++) {
			if(hats.get(i).getBesides() !=  -1)
				hats.get(i).eraseBesides();
		}
	}

	/**
	 * saves the besides for all hats
	 */
	private void saveBesides() {
		
		for(int i = 0; i < hats.size(); i++) {
			if(hats.get(i).getBesides() !=  -1)
				hats.get(i).addDoubleBesides();
		}
	}


}
