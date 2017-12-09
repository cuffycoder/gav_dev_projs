import static org.junit.Assert.*;

import org.junit.Test;


public class TexasHoldEmHandScenarioTest {

	@Test
	public void testScenarioBasic() {
		TexasHoldEmHandScenario scenario = new TexasHoldEmHandScenario();
		String [][] knownPocketCards = { { "KC", "QS" }, { "KD", "JD" }, { "10S", "9S" } };
		
		scenario.setNumPlayers(3);
		scenario.setKnownPocketCards(  knownPocketCards );
		
		System.out.println( scenario.scenarioDescription() );
		
		scenario.runSimulations( 1000000, false );
		
		System.out.println( scenario.resultPlayerWinCountsGet() );
	}

}
