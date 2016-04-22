import static org.junit.Assert.*;

import org.junit.Test;


public class TexasHoldEmHandScenarioTest {

	@Test
	public void testScenarioBasic() {
		TexasHoldEmHandScenario scenario = new TexasHoldEmHandScenario();
		String [][] knownPocketCards = { { "AH", "AS" }, { "2C", "7D" } };
		
		scenario.setNumPlayers(2);
		scenario.setKnownPocketCards(  knownPocketCards );
		
		System.out.println( scenario.scenarioDescription() );
		
		scenario.runSimulations( 1000000, false );
		
		System.out.println( scenario.resultPlayerWinCountsGet() );
	}

}
