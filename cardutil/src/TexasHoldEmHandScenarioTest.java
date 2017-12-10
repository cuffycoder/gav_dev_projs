import static org.junit.Assert.*;

import org.junit.Test;


public class TexasHoldEmHandScenarioTest {

	@Test
	public void testScenarioBasic() {
		String [][] knownPocketCards = { { "KC", "QS" }, { "KD", "JD" }, { "10S", "9S" } };

		TexasHoldEmHandScenario scenario = new TexasHoldEmHandScenario( knownPocketCards, 5 );
		
		System.out.println( scenario.scenarioDescription() );
		
		scenario.runSimulations( 100, false );
	}

}
