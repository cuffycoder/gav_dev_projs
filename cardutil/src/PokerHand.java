
public class PokerHand {

	private PlayingCard []cards;
	private long score;
	
	// constants defining scores for hand types
	static final long SCORE_STRAIGHT_FLUSH  = (long) 9e10;
	static final long SCORE_FOUR_OF_A_KIND  = (long) 8e10;
	static final long SCORE_FULL_HOUSE      = (long) 7e10;
	static final long SCORE_FLUSH           = (long) 6e10;
	static final long SCORE_STRAIGHT        = (long) 5e10;
	static final long SCORE_THREE_OF_A_KIND = (long) 4e10;
	static final long SCORE_TWO_PAIR        = (long) 3e10;
	static final long SCORE_PAIR            = (long) 2e10;
	static final long SCORE_HIGH_CARD       = (long) 1e10;
	
	// constants defining hand types
	static final String STRAIGHT_FLUSH    = "Straight Flush";
	static final String FOUR_OF_A_KIND    = "Four of a Kind";
	static final String FULL_HOUSE        = "Full House";
	static final String FLUSH             = "Flush";
	static final String STRAIGHT          = "Straight";
	static final String THREE_OF_A_KIND   = "Three of a Kind";
	static final String TWO_PAIR          = "Two Pair";
	static final String PAIR              = "Pair";
	static final String HIGH_CARD         = "High";
	 
	
	public PokerHand( String card1, String card2, String card3, String card4, String card5 ) {
	
		cards = new PlayingCard[ 5 ];
		
		cards[ 0 ] = new PlayingCard( card1 );
		cards[ 1 ] = new PlayingCard( card2 );
		cards[ 2 ] = new PlayingCard( card3 );
		cards[ 3 ] = new PlayingCard( card4 );
		cards[ 4 ] = new PlayingCard( card5 );
		
		setScore();
	}
	
	public PokerHand( PlayingCard []cardsInput ) {
		
		cards = new PlayingCard[5];
		
		for( int i = 0; i < cardsInput.length; i++ )
			cards[ i ] = new PlayingCard( cardsInput[i].getNum(), cardsInput[i].getSuit() );
		
		setScore();
	}
	
	public void setHand( PlayingCard []cardsInput ) {
		for( int i = 0; i < cardsInput.length; i++ )
			cards[ i ].setCard( cardsInput[i].getNum(), cardsInput[i].getSuit() );
		
		setScore();
	}
	
	public void setHand( String card0, String card1, String card2, String card3, String card4 ) {
		cards[ 0 ].setCard( card0 );
		cards[ 1 ].setCard( card1 );
		cards[ 2 ].setCard( card2 );
		cards[ 3 ].setCard( card3 );
		cards[ 4 ].setCard( card4 );
	}
	
	private void setScore() {
	
		// the cardMap will show how many cards in each number position; easy to identify pairs
		int []cardMap = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		
		boolean isFlush = true;
		boolean isStraight = false;
		char    lastSuit   = ' ';
		
		for( int i = 0; i < cards.length; i++ )
		{
			int cardNum = cards[i].getNum();
			
			cardMap[ cardNum ]++;
			
			if( ' ' != lastSuit && lastSuit != cards[i].getSuit() )
				isFlush = false;
			
			lastSuit = cards[i].getSuit(); 
		}
		
		// now go through the map and look for pairs, straights, etc.
		int fourOfKind = 0;
		int threeOfKind = 0;
		int []pairs = { 0, 0 };
		int []singles = { 0, 0, 0, 0, 0 };
		
		for( int i = cardMap.length - 1; i >= 0; i-- )
		{
			switch( cardMap[i] ) {
			case 4: 
				fourOfKind = i;
				break;
			case 3:
				threeOfKind = i;
				break;
			case 2:
				if( pairs[ 0 ] != 0 )
					pairs[ 1 ] = i;
				else
					pairs[ 0 ] = i;
				break;
			case 1:
				for( int j = 0; j < singles.length; j++ )
					if( singles[ j ] == 0 )
					{
						singles[ j ] = i;
						break;
					}
				break;
			}	
		}
		
		// check for a straight
		if( singles[4] != 0 )
		{
			if( singles[ 0 ] - singles[ 4 ] == 4 )
				isStraight = true;
			
			if( singles[ 0 ] == PlayingCard.ACE && singles[ 4 ] == PlayingCard.TWO && singles[ 1 ] == PlayingCard.FIVE )
				isStraight = true;
		}
		
		long cardScore = 0;
		
		if( isFlush && isStraight )
		{
			cardScore = SCORE_STRAIGHT_FLUSH;
			
			if( singles[0] == PlayingCard.ACE && singles[ 4 ] == PlayingCard.TWO )
				cardScore += (long) ( singles[ 1 ] * 1e8 );
			else
				cardScore += (long) ( singles[ 0 ] * 1e8 );
		}
		else if( fourOfKind != 0 )
		{
			cardScore = (long) SCORE_FOUR_OF_A_KIND;
			
			cardScore += (long) ( fourOfKind * 1e8 );
			cardScore += (long) ( singles[0] * 1e6 );
		}
		else if( threeOfKind != 0 && pairs[0] != 0 ) 
		{
			cardScore = (long) SCORE_FULL_HOUSE;
			
			cardScore += (long) ( threeOfKind * 1e8 );
			cardScore += (long) ( pairs[0] * 1e6 );
		}
		else if( isFlush )
		{
			cardScore = (long) SCORE_FLUSH;
			
			cardScore += (long) ( singles[ 0 ] * 1e8 );
			cardScore += (long) ( singles[ 1 ] * 1e6 );
			cardScore += (long) ( singles[ 2 ] * 1e4 );
			cardScore += (long) ( singles[ 3 ] * 1e2 );
			cardScore += (long) ( singles[ 4 ] );
		}
		else if( isStraight )
		{
			cardScore = (long) SCORE_STRAIGHT;
			
			if( singles[0] == PlayingCard.ACE && singles[ 4 ] == PlayingCard.TWO )
				cardScore += (long) ( singles[ 1 ] * 1e8 );
			else
				cardScore += (long) ( singles[ 0 ] * 1e8 );			
		}
		else if( threeOfKind != 0 )
		{
			cardScore = (long) SCORE_THREE_OF_A_KIND;
			
			cardScore += (long) ( threeOfKind * 1e8 );
			cardScore += (long) ( singles[ 0 ] * 1e6 );
			cardScore += (long) ( singles[ 1 ] * 1e4 );
		}
		else if( pairs[ 0 ] != 0 && pairs[ 1 ] != 0 )
		{
			cardScore = (long) SCORE_TWO_PAIR;
			cardScore += (long) ( pairs[0] * 1e8 );
			cardScore += (long) ( pairs[1] * 1e6 );
			cardScore += (long) ( singles[0] * 1e4 );
		}
		else if( pairs[ 0 ] != 0 )
		{
			cardScore = (long) SCORE_PAIR;
			cardScore += (long) ( pairs[0] * 1e8 );
			cardScore += (long) ( singles[ 0 ] * 1e6 );
			cardScore += (long) ( singles[ 1 ] * 1e4 );
			cardScore += (long) ( singles[ 2 ] * 1e2 );
		}
		else
		{
			cardScore = SCORE_HIGH_CARD;
			
			cardScore += (long) ( singles[ 0 ] * 1e8 );
			cardScore += (long) ( singles[ 1 ] * 1e6 );
			cardScore += (long) ( singles[ 2 ] * 1e4 );
			cardScore += (long) ( singles[ 3 ] * 1e2 );
			cardScore += (long) ( singles[ 4 ] );
		}
		
		score = cardScore;				
	}
	
	public long getScore() {
		return( score );
	}
	
	public String handType() {
		
		if( score > SCORE_STRAIGHT_FLUSH )
			return( STRAIGHT_FLUSH );
		else if( score > SCORE_FOUR_OF_A_KIND )
			return( FOUR_OF_A_KIND );
		else if( score > SCORE_FULL_HOUSE )
			return( FULL_HOUSE );
		else if( score > SCORE_FLUSH )
			return( FLUSH );
		else if( score > SCORE_STRAIGHT )
			return( STRAIGHT );
		else if( score > SCORE_THREE_OF_A_KIND )
			return( THREE_OF_A_KIND );
		else if( score > SCORE_TWO_PAIR )
			return( TWO_PAIR );
		else if( score > SCORE_PAIR )
			return( PAIR );
		else 
			return( HIGH_CARD );
	}
	
	public String toString() {
		String result = new String( "[ " );
		
		for( int i = 0; i < cards.length; i++ ) {
			result += cards[ i ].toString();
			
			if( i < cards.length - 1 )
				result += ", ";
			else
				result += " ]";
		}
		
		return( result );
	}
}
