import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ComputerPlayerFactory {

	
	private String NAME;
	
	
	/**
	 * 
	 * @param strategyType
	 *  1 is Random
	 *  2 is Even/odd
	 *  3 is AI
	 * @return new computerPlayer with the desired strategy
	 */
	public ComputerPlayer getComputerPlayer(int strategyType, int START_NUMBER) {
		
		ComputerPlayer ourPlayer = checkForExistingPlayer(strategyType, START_NUMBER);
		
		if(ourPlayer != null)
			return ourPlayer;
		
		System.out.println("No previous player of this type found, creating new");
		
		switch(strategyType) {
		case 1:
			return new ComputerPlayer(new RandomComputerStrategy(), NAME);
		case 2:
			return new ComputerPlayer(new AlternateEvenOddStrategy(), NAME);
		case 3:
			return trainNewAI(START_NUMBER);
		}
		
		return null;
		
	}

	/**
	 * checks .bin files with the proper name for a previous player
	 * @param strategyType
	 * @param START_NUMBER
	 * @return a previously saved ComputerPlayer or null
	 */
	private ComputerPlayer checkForExistingPlayer(int strategyType, int START_NUMBER) {
		// TODO Auto-generated method stub
		ObjectInputStream in=null;
		try { 
			switch(strategyType) {
			case 1:
				NAME = "randomComputerPlayer";
				break;
			case 2:
				NAME = "evenOrOddComputerPlayer";
				break;
			case 3:
				NAME = "computerAIPlayer";
				break;
				
			}
			
			in = new ObjectInputStream(new FileInputStream(NAME + START_NUMBER + ".bin"));
			
			Object o = in.readObject();
			ComputerPlayer ourOldPlayer = (ComputerPlayer) o;
			return ourOldPlayer;

		} 
		catch (ClassNotFoundException e) {
			System.out.println("Class IO had problem");
			System.exit(0);
			return null;
		}
		catch (EOFException e) {
			return null;
		}
		
		catch (IOException e) {
			return null;
		}
		
	}

	/**
	 * trains a brand new AI with starting sticks START_NUMBER
	 * @param START_NUMBER
	 * @return the new trainee AI
	 */
	private ComputerPlayer trainNewAI(int START_NUMBER) {
		
		ComputerPlayer trainee = new ComputerPlayer(new ComputerAIStrategy(START_NUMBER), NAME);
		ComputerPlayer randomTrainer = new ComputerPlayer(new RandomComputerStrategy(), NAME);
		
		for(int i = 0; i < 1000; i++) {
			int sticksRemaining = START_NUMBER;
			boolean isTraineeWin;
			while(true) {
				int pull1 = trainee.getComputerPull(sticksRemaining);
				sticksRemaining -= pull1;
				if(sticksRemaining == 0) {
					isTraineeWin = false; //trainee pulled last stick!!
					trainee.recordGame(isTraineeWin); 
					break;
				}
				int pull2 = randomTrainer.getComputerPull(sticksRemaining);
				sticksRemaining -= pull2;
				if(sticksRemaining == 0) {
					isTraineeWin = true; //random Trainer pulled last stick
					trainee.recordGame(isTraineeWin);
					break;
				}
			}
		}
		
		System.out.println("A new AI has been Trained... it won " + 
				trainee.getComputerWins() + " out of " + 
				trainee.getTotalGameCount() + " games in trianing");
		
		trainee.resetWinCount();
		trainee.resetGameCount();
		return trainee;
	}
	
	/**
	 * 
	 * @return NAME
	 */
	public String getName() {
		return NAME;
	}
}
