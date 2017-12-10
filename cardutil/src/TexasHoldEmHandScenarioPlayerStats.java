import java.util.HashMap;

/**
 * 
 */

/**
 * @author gavin
 *
 */
public class TexasHoldEmHandScenarioPlayerStats {
	/*
	 *  Track for each player:
	 *  - Win, Lose, Push
	 *  - Map of how often got each hand type (straight flush, etc.) was dealt, how often it won, lost, pushed
	 */

	
	public static final String PLAYER_STAT_WIN   = "WIN";
	public static final String PLAYER_STAT_PUSH  = "PUSH";
	public static final String PLAYER_STAT_LOSE  = "LOSE";
	
	private HashMap<String,HashMap<String,Integer>> playerStats;
	
	public TexasHoldEmHandScenarioPlayerStats()
	{
		// set all counters to 0
		initializeStats();
	}
	
	public void initializeStats()
	{
		// initialize the HandTypesStats
		playerStats = new HashMap<String,HashMap<String, Integer>>();
				
		for( String handType : PokerHand.PokerHandTypes ) 
		{
			HashMap<String,Integer> handTypeInitStats = new HashMap<String,Integer>();
			handTypeInitStats.put( PLAYER_STAT_WIN,   0 );
			handTypeInitStats.put( PLAYER_STAT_PUSH,  0 );
			handTypeInitStats.put( PLAYER_STAT_LOSE,  0 );
					
			playerStats.put( handType, handTypeInitStats );
		}
	}
	
	/*
	 * recordResult()
	 * updates the stats according to whether the player won, lost, pushed and the actual hand
	 */
	public void recordResult( String result, PokerHand pokerHand )
	{
		HashMap<String,Integer> countersForHandType = playerStats.get( pokerHand.handType() );
		
		countersForHandType.put( result, countersForHandType.get( result ) + 1 );
		
		playerStats.put( pokerHand.handType(), countersForHandType );
	}
	
	public void printStats()
	{
		Integer totalWin  = 0;
		Integer totalPush = 0;
		Integer totalLose = 0;
		
		for( String handType : PokerHand.PokerHandTypes ) 
		{
			HashMap<String,Integer> statsForHandType = playerStats.get( handType );
			totalWin  += statsForHandType.get(PLAYER_STAT_WIN);
			totalPush += statsForHandType.get(PLAYER_STAT_PUSH);
			totalLose += statsForHandType.get(PLAYER_STAT_LOSE);
			
			System.out.println( handType + " : " + statsForHandType.get(PLAYER_STAT_WIN).toString() + " ; " + statsForHandType.get(PLAYER_STAT_PUSH).toString() + " ; " + statsForHandType.get( PLAYER_STAT_LOSE ).toString()  );
		}
		
		System.out.println( "TOTAL : " + totalWin.toString() + " ; " + totalPush.toString() + " ; " + totalLose.toString() + "\n"  );
	}
}
