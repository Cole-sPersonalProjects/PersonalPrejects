
public interface AComputerPullStrategy {

	//determines pull number
	public int getComputerPullNumber(int sticksRemaining);
	
	//records contents of hat for AI algorithm
	public void winAndSave(boolean computerWin);

}
