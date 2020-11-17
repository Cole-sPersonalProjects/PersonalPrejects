import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RandomPullTest {

	RandomComputerStrategy randomInstance;
	int sticksRemaining;
	
	@BeforeEach
	void setUp() throws Exception {
		randomInstance = new RandomComputerStrategy();
	}
	
	@Test
	void testRandomFourRemaining() {
		sticksRemaining = 4;
		int output = randomInstance.getComputerPullNumber(sticksRemaining);
		assertTrue(output == 3 || output == 2 ||output == 1);
	}
	
	@Test
	void testRandomThreeRemaining() {
		sticksRemaining = 3;
		int output = randomInstance.getComputerPullNumber(sticksRemaining);
		assertTrue(output == 2 ||output == 1);
	}
	
	@Test
	void testRandomTwoRemaining() {
		sticksRemaining = 2;
		int output = randomInstance.getComputerPullNumber(sticksRemaining);
		assertTrue(output == 1); //if two sticks remaining output should always pull one since I made it a little smarter
	}
	
	@Test
	void testRandomOneRemaining() {
		sticksRemaining = 1;
		int output = randomInstance.getComputerPullNumber(sticksRemaining);
		assertTrue(output == 1); //if two sticks remaining output should always pull one since I made it a little smarter
	}


}
