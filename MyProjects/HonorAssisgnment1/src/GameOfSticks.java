import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameOfSticks {
	
	private ComputerPlayer ourPlayer;
	private boolean isHumanTurn = true;
	private int START_NUMBER;
	private int sticksRemaining;

	/**
	 * starts the game off
	 */
	private void playHumanGame() {
		// TODO Auto-generated method stuff
		
		sticksRemaining = START_NUMBER;
		boolean done = false;
		isHumanTurn = true;
		
		
		while(true) {
			
			System.out.println("Sticks left: " + sticksRemaining);
			
			if(isHumanTurn) {
				int pull = getHumanPull();
				pullSticks(pull);
				if(sticksRemaining == 0) {
					computerWins(); //human pulled last stick
					break;
				}
			}
			else {
				int pull = getComputerPull();
				pullSticks(pull);
				if(sticksRemaining == 0) {
					humanWins(); //computer pulled last stick
					break;
				}
			}
			
		}

	}
	
	/**
	 * signals a computer win, increments win number, calls askToContinue
	 */
	private void computerWins() {
		
		System.out.println("\nCOMPUTER WINS!");
		boolean isAComputerWin = true;
		askToContinue(isAComputerWin);
		
	}
	
	/**
	 * called won humanHas Won Game
	 */
	private void humanWins() {
		// TODO Auto-generated method stub
		System.out.println("\nHUMAN WINS!");
		boolean isAComputerWin = false;
		askToContinue(isAComputerWin);
	}


	/**
	 * Asks the player to play another game, if no calls saveOpponent()
	 * @param isAComputerWin tells us weather or not to call winAndSave which helps train the AI
	 */
	private void askToContinue(boolean isAComputerWin) {
		// TODO Auto-generated method stub
		ourPlayer.recordGame(isAComputerWin);
		System.out.println("Computer has won " + ourPlayer.getComputerWins() + 
				" of " + ourPlayer.getTotalGameCount() + 
				" using the " + ourPlayer.getStrategyName() + " Strategy");
		Scanner in = new Scanner(System.in);
		boolean invalid = true;
		boolean wantToPlay = false;
		System.out.print("\nWould you like to play another game?\nEnter Y or N: ");

		while (invalid)
		{
			String answer = in.next();
			in.nextLine();
			if (answer.equalsIgnoreCase("Y"))
			{
				invalid = false;
				wantToPlay = true;
			}
			else if (answer.equalsIgnoreCase("N"))
			{
				invalid = false;
				saveOpponent();
			}
			else
			{
				System.out.print("Please enter Y or N: ");
			}
		}
		
		if(wantToPlay)
			playHumanGame();
	}

	/**
	 * saves data for any given strategy
	 */
	private void saveOpponent() {
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ourPlayer.getStrategyName() + START_NUMBER + ".bin"));
			out.writeObject(ourPlayer);
		
			out.close();
			System.out.println("\nOpponent saved to " + ourPlayer.getStrategyName() + START_NUMBER + ".bin");
		}
		catch (IOException e) {
			System.out.println("IO Error occured");
			e.printStackTrace();
		}
	
		System.out.println("Computer has won " + ourPlayer.getComputerWins() + 
				" of " + ourPlayer.getTotalGameCount() + 
				" using the " + ourPlayer.getStrategyName() + " Strategy");
		System.out.println("Thanks for playing the Game of Sticks!");
		
	}


	/**
	 * gets the computers pull number
	 * @return the pull that the computer chooses
	 */
	private int getComputerPull() {
		// TODO Auto-generated method stub
		return ourPlayer.getComputerPull(sticksRemaining);
	}

	/**
	 * pulls sticks if possible
	 * @param pull removes sticks from table
	 */
	private void pullSticks(int pull) {
		// TODO Auto-generated method stub
		
		if(canPull(pull)) {
		
			sticksRemaining -= pull;
			
			if(!isHumanTurn)
				System.out.println("Opponent Chose " + pull);
			
			nextTurn();
		}
		else if(isHumanTurn){
			System.out.println("Invalid, there are only " + sticksRemaining + " sticks left");
		}
		else {
			System.out.println("Error with computer choice...");
		}
		
	}

	/**
	 * alternates turn
	 */
	private void nextTurn() {
		// TODO Auto-generated method stub
		if(isHumanTurn) 
			isHumanTurn = false;
		else
			isHumanTurn = true;
		
	}

	/**
	 * asks human player for pull
	 * @return the pull that the human player chooses
	 */
	private int getHumanPull() {
		// TODO Auto-generated method stub
		int pull;
		System.out.print("Enter pull number between 1 and 3: ");
		Scanner input = new Scanner(System.in);
		while(true) {
			try {
				pull = input.nextInt();
				input.nextLine(); //need to clear line feed
			
				if( pull > 0 && pull <=3 && canPull(pull)) {
					return pull;
				}
				
				if(!canPull(pull))
					System.out.println("There are only " + sticksRemaining + " left of the table");
				else
					System.out.print("Invalid entry: Please enter a number between 1 and 3: ");
				
			}
			catch(InputMismatchException ex){
				input.nextLine();
				System.out.print("Invalid entry: Please enter a number between 1 and 3: ");
			}
		}
		
		
	}

	/**
	 * checks to make sure pull is valid
	 * @param pull is the number of sticks about to be pulled
	 * @return canPull
	 */
	private boolean canPull(int pull) {
		boolean canPull = false;
		if(sticksRemaining >= pull )
			return true;
		return canPull;
	}

	/**
	 * sets up starting number of sticks
	 * @return starting number of sticks
	 */
	private int askForStartingSticks() {
		// TODO Auto-generated method stub
		
		System.out.print("Enter number of sticks between 1 and 500:  ");
		Scanner input = new Scanner(System.in);
		
		while(true) {
			
			try {
				int startNumber = input.nextInt();
				input.nextLine(); //need to clear line feed
			
				if( startNumber > 0 && startNumber <= 500) {
					return startNumber;
				}
				else {
					System.out.print("Invalid entry: Please Enter A number between 1 and 500: ");
				}
			}
			catch(InputMismatchException ex){
				input.nextLine();
				System.out.print("Invalid entry: Please enter a number between 1 and 500");
			}
			
		}
	}

	/**
	 * The main program
	 * creates a game, kicks off computer set up and plays
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GameOfSticks currentGame = new GameOfSticks();
		currentGame.setUpComputer();
		currentGame.playHumanGame();
	}

	/**
	 * gets some strategy type and sends it to factory to make proper ComputerPlayer
	 */
	private void setUpComputer() {
		// TODO Auto-generated method stub
		
		int strategyType = getStrategyType();
		START_NUMBER = askForStartingSticks();
		ComputerPlayerFactory factory = new ComputerPlayerFactory();
		ourPlayer = factory.getComputerPlayer(strategyType, START_NUMBER);
		
	}

	/**
	 * gets the desired strategy type choice
	 * @return return strategy choice
	 *  1 is Random
	 *  2 is Even/odd
	 *  3 is AI
	 */
	private int getStrategyType() {
		int strategyType;
		System.out.print("Choose opponent type:\n"
				+ "1: Random\n"
				+ "2: Even_Odd\n"
				+ "3: AI\n"
				+ ">");
		
		Scanner input = new Scanner(System.in);
		
		while(true) {
			try {
				strategyType = input.nextInt();
				input.nextLine(); //need to clear line feed
			
				if( strategyType > 0 && strategyType <=3) {
					return strategyType;
				}
				
				System.out.print("Please enter a number between 1 and 3: ");
				
			}
			catch(InputMismatchException ex){
				input.nextLine();
				System.out.print("Please enter a number between 1 and 3: ");
			}
		}
		
	}

}
