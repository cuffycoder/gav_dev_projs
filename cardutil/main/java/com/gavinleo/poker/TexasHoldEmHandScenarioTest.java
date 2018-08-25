import static org.junit.Assert.*;

import org.junit.Test;


public class TexasHoldEmHandScenarioTest {

	@Test
	public void testScenarioBasic() {
		String [][] knownPocketCards = { { "AC", "AD" }, { "2D", "7H" } };

		TexasHoldEmHandScenario scenario = new TexasHoldEmHandScenario( knownPocketCards, 2 );
		
		System.out.println( scenario.scenarioDescription() );
		
		scenario.runSimulations( 50000, false );
	}

}
