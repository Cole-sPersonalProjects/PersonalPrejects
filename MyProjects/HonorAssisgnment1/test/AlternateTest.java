import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlternateTest {
	
	AlternateEvenOddStrategy alternateInstance;
	
	@BeforeEach
	void setUp() throws Exception {
		alternateInstance = new AlternateEvenOddStrategy();
	}
	
	@Test
	void testEvenOddFourRemaining() {
		
		boolean isEven = alternateInstance.getIsEven();
		int evenOrOdd;
		
		//The getComputerPullNumber method changes isEven before returning
		if(isEven)
			evenOrOdd = 1;
		else
			evenOrOdd = 2;
		
		assertEquals(evenOrOdd, alternateInstance.getComputerPullNumber(4));
	}
	
	@Test
	void testEvenOddNegativeRemaining() {
		
		//should return -1 as default
		assertEquals(-1, alternateInstance.getComputerPullNumber(-1));
	}

	
}
