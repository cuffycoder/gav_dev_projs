
public class TexasHoldEmHand {

	private PlayingCard []cards;
	
	public TexasHoldEmHand( PlayingCard []cardsInput ) {
		cards = new PlayingCard[ 7 ];
		
		for( int i = 0; i < cardsInput.length; i++ )
			cards[ i ] = new PlayingCard( cardsInput[ i ].getNum(), cardsInput[ i ].getSuit() );		
	}
	
	public PokerHand bestHand() {
		PokerHand bestSoFar = null;
		PokerHand currHand  = null;
		PlayingCard []cardsInHand = new PlayingCard[ 5 ];
		
		for( int i = 0; i < cards.length - 1; i++ )
		{
			for( int j = i + 1; j < cards.length; j++ )
			{
				int h = 0;
				for( int c = 0; c < cards.length; c++ )
				{
					if( c != i && c != j )
						cardsInHand[ h++ ] = new PlayingCard( cards[ c ].getNum(), cards[ c ].getSuit() );
				}

				if( currHand == null )
					currHand = new PokerHand( cardsInHand );
				else
					currHand.setHand( cardsInHand );
				
				if( bestSoFar == null )
					bestSoFar = new PokerHand( cardsInHand );
				else
				{
					if( currHand.getScore() > bestSoFar.getScore() )
						bestSoFar.setHand( cardsInHand );
				}
			}
		}	
		
		return( bestSoFar );
	}

}
