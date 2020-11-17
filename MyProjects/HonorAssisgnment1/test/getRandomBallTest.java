import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class getRandomBallTest {
	
	Hat hatInstance;
	int HAT_NUMBER;

	@BeforeEach
	void setUp() throws Exception {

	}

	//the negative and null case protection is in the game class
	// we do not know if we will use this hat class for something else so rules specific to the
	// sticks game are localized in the GameOfSticks class
	
	@Test
	void testLastStickRemaining() {
		HAT_NUMBER = 1;
		hatInstance = new Hat(HAT_NUMBER);
		assertTrue(hatInstance.getRandomBall(1) == 1);
	}
	
	@Test
	void testTwoSticksRemaining() {
		HAT_NUMBER = 2;
		hatInstance = new Hat(HAT_NUMBER);
		int output = hatInstance.getRandomBall(HAT_NUMBER);
		assertTrue(output == 1 || output == 2);
	}
	
	@Test
	void testThreeSticksRemaining() {
		HAT_NUMBER = 3;
		hatInstance = new Hat(HAT_NUMBER);
		int output = hatInstance.getRandomBall(HAT_NUMBER); //can only call this method once because it does not add balls back
		assertTrue(output == 1 || output == 2 || output == 3);
	}
	
	@Test
	void testFourSticksRemaining() {
		HAT_NUMBER = 4;
		hatInstance = new Hat(HAT_NUMBER);
		int output = hatInstance.getRandomBall(HAT_NUMBER);
		assertTrue(output == 1 || output == 2 || output == 3);
	}
	
}
