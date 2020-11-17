import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComputerAITest {
	
	ComputerAIStrategy computerAIInstance;
	int sticksRemaining;
	int STARTING_STICK_COUNT_EXAMPLE = 10;

	@BeforeEach
	void setUp() throws Exception {
		computerAIInstance = new ComputerAIStrategy(STARTING_STICK_COUNT_EXAMPLE);
	}


	@Test
	void testAIFourRemaining() {
		sticksRemaining = 4;
		int output = computerAIInstance.getComputerPullNumber(sticksRemaining);
		assertTrue(output == 3 || output == 2 ||output == 1);
	}
	
	@Test
	void testAITwoRemaining() {
		
		sticksRemaining = 2;
		int output = computerAIInstance.getComputerPullNumber(sticksRemaining);
		assertTrue(output == 2 ||output == 1);
		
	}
	
	@Test
	void testAIOneRemaining() {
		
		sticksRemaining = 1;
		int output = computerAIInstance.getComputerPullNumber(sticksRemaining);
		assertTrue(output == 1);
		
	}

}
