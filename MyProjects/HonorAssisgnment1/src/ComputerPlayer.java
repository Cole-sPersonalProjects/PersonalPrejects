import java.io.Serializable;

public class ComputerPlayer implements Serializable{
	
	private AComputerPullStrategy strategyChoice;
	private int totalGames;
	private int computerWins;
	private String NAME;

	/**
	 * Constructs a computer player with strategyChoice and NAME
	 * @param strategyChoice
	 * @param NAME
	 */
	public ComputerPlayer(AComputerPullStrategy strategyChoice, String NAME) {
	
		this.strategyChoice = strategyChoice;
		this.NAME = NAME;
		
	}
	
	/**
	 * 
	 * @param sticksRemaining
	 * @return the AI computer pull
	 */
	public int getComputerPull(int sticksRemaining) {
		
		return strategyChoice.getComputerPullNumber(sticksRemaining);
		
	}

	/**
	 * 
	 * @return NAME
	 */
	public String getStrategyName() {

		return NAME;
	}

	/**
	 * 
	 * @return the strategyChoice
	 */
	public Object getStrategyChoice() {
		
		return strategyChoice;
	}

	/**
	 * records wins and losses, saves data in hats
	 * @param isAComputerWin
	 */
	public void recordGame(boolean isAComputerWin) {
		
		strategyChoice.winAndSave(isAComputerWin);
		
		if(isAComputerWin)
			recordWin();
		else
			recordLoss();
	}
	
	/**
	 * 
	 * @return totalGames
	 */
	public int getTotalGameCount() {
		return totalGames;
	}


	/**
	 * 
	 * @return computerWins
	 */
	public int getComputerWins() {
		return computerWins;
	}
	
	/**
	 * records a win, increments computerWins and totalGames
	 */
	public void recordWin() {
		computerWins++;
		totalGames++;
	}
	
	/**
	 * records a loss by incrementing totalGames
	 */
	public void recordLoss() {
		totalGames++;
	}

	/**
	 * resets computerWins
	 */
	public void resetWinCount() {
		computerWins = 0;
	}

	/**
	 * resets totalGames
	 */
	public void resetGameCount() {
		totalGames = 0;
	}

}
