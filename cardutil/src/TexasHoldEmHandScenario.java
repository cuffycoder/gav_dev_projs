import java.util.HashMap;

public class TexasHoldEmHandScenario {

	private int numPlayers;
	private PlayingCard [][]knownPocketCards;
	private int[] resultPlayerWinCounts;
	
	
	public TexasHoldEmHandScenario( String [][]knownPocketCardsInput, int numPlayersInput ) {
		knownPocketCards = new PlayingCard[ knownPocketCardsInput.length ][ 2 ];
		numPlayers = numPlayersInput;
		
		for( int i = 0; i < knownPocketCardsInput.length; i++ )
		{
			knownPocketCards[ i ][ 0 ] = PlayingCard.getInstance( knownPocketCardsInput[ i ][ 0 ] );
			knownPocketCards[ i ][ 1 ] = PlayingCard.getInstance( knownPocketCardsInput[ i ][ 1 ] );
		};
	}
	
	public TexasHoldEmHandScenario() {
	}

	public void setNumPlayers( int numPlayersInput ) {
		numPlayers = numPlayersInput;
	}
	
	public void setKnownPocketCards( String [][]knownPocketCardsInput ) {
		knownPocketCards = new PlayingCard[ knownPocketCardsInput.length ][ 2 ];
		
		for( int i = 0; i < knownPocketCardsInput.length; i++ )
		{
			knownPocketCards[ i ][ 0 ] = PlayingCard.getInstance( knownPocketCardsInput[ i ][ 0 ] );
			knownPocketCards[ i ][ 1 ] = PlayingCard.getInstance( knownPocketCardsInput[ i ][ 1 ] );
		};
		
	}
	
	public int[] resultPlayerWinCountsGet() {
		return resultPlayerWinCounts;
	}
	
	public String scenarioDescription()
	{
		String desc = new String();
		
		desc += "Number of Players: " + numPlayers + "\n";
		
		desc += "Known pocket cards:\n";
		
		for( int i = 0; i < knownPocketCards.length; i++ )
			desc += "Player " + i + ": " + knownPocketCards[ i ][0] + ", " + knownPocketCards[ i ][1] + "\n";
		
		
		return desc;
	}
	
	public void runSimulations( int numSimulations, boolean verbose ) {

		PlayingCard [][]playersPocketCards = new PlayingCard[ numPlayers ][2];
		TexasHoldEmHand []playersHands = new TexasHoldEmHand[ numPlayers ];
		PokerHand []playersBestHands = new PokerHand[ numPlayers ];
		
		PlayingCard []communityCards = new PlayingCard[5];
		PlayingCard [][]playersPocketAndCommunity = new PlayingCard[numPlayers][7];

		resultPlayerWinCounts = new int[ numPlayers ];
		HashMap <String,Integer>handTypes = new HashMap<String,Integer>();
		DeckOfCards deck = new DeckOfCards();

		// initialize win stats
		for( int i = 0; i < resultPlayerWinCounts.length; i++ )
			resultPlayerWinCounts[i] = 0;
		
		System.out.printf( "Simulating %d hands with %d players\n", numSimulations, numPlayers );
		System.out.printf( "Known pocket cards:\n" );

		for( int i = 0; i < knownPocketCards.length; i++ )
			System.out.printf( "Player %d: %s %s\n", i, knownPocketCards[ i ][0], knownPocketCards[ i ][1] );
		
		// initialize the playersHands
		for( int player = 0; player < numPlayers; player++ ) {
			playersHands[ player ] = new TexasHoldEmHand();
		}
		
		for( int gameNum = 0; gameNum < numSimulations; gameNum++ )
		{
			if( verbose )
				System.out.printf( "Game %d\n", gameNum );
			
			// puts any dealt cards back in the deck and shuffles
			deck.reset();

			// deal the pocket cards (either randomly or from the known set)
			for( int pocketCardNum = 0; pocketCardNum < 2; pocketCardNum++ )
				for( int player = 0; player < numPlayers; player++ )
				{
					if( player < knownPocketCards.length ) 
					{
						playersPocketCards[ player ][ pocketCardNum ] = knownPocketCards[ player ][ pocketCardNum ];
						deck.excludeSpecificCard( knownPocketCards[ player ][ pocketCardNum ].toString() );
					}
					else
						playersPocketCards[ player ][ pocketCardNum ] = deck.pop();
					
					// burn one
					deck.pop();
				}		
			
			if( verbose )
			{
				for( int player = 0; player < numPlayers; player++ )
					System.out.printf( "Player %d cards: %s %s\n", player, playersPocketCards[ player ][0], playersPocketCards[player][1] );
			}
			
			// deal community cards
			// TODO: allow the community cards to be known/pre-set as well
			communityCards[ 0 ] = deck.pop();
			communityCards[ 1 ] = deck.pop();
			communityCards[ 2 ] = deck.pop();
			deck.pop(); // burn
			communityCards[ 3 ] = deck.pop();
			deck.pop(); // burn
			communityCards[ 4 ] = deck.pop();

			if( verbose )
				System.out.printf( "Community:      %s %s %s  %s  %s\n", communityCards[ 0 ], 
						communityCards[ 1 ],
						communityCards[ 2 ],
						communityCards[ 3 ],
						communityCards[ 4 ] );

			for( int player = 0; player < numPlayers; player++ )
			{
				playersPocketAndCommunity[player][ 0 ] = communityCards[ 0 ];
				playersPocketAndCommunity[player][ 1 ] = communityCards[ 1 ];
				playersPocketAndCommunity[player][ 2 ] = communityCards[ 2 ];
				playersPocketAndCommunity[player][ 3 ] = communityCards[ 3 ];
				playersPocketAndCommunity[player][ 4 ] = communityCards[ 4 ];
				playersPocketAndCommunity[player][ 5 ] = playersPocketCards[player][ 0 ];
				playersPocketAndCommunity[player][ 6 ] = playersPocketCards[player][ 1 ];

				// TODO: shouldn't allocate a new class each time
				playersHands[ player ].setCards( playersPocketAndCommunity[player] );
				playersBestHands[player] = playersHands[player].bestHand();

				if( verbose )
					System.out.printf( "Player %d best hand: %s (%s)\n", player, playersBestHands[player].toString(), playersBestHands[player].handType() );
			}

			/*
			if( handTypes.containsKey( myBestHand.handType() ) )
				handTypes.put( myBestHand.handType(), handTypes.get( myBestHand.handType() ) + 1 );
			else
				handTypes.put( myBestHand.handType(), 1 );
			*/
			// find the winner
			long winningScore = 0;
			
			for( int player = 0; player < numPlayers; player++ )
				if( winningScore < playersBestHands[ player ].getScore() )
					winningScore = playersBestHands[ player ].getScore();
			
			for( int player = 0; player < numPlayers; player++ )
				if( playersBestHands[ player ].getScore() == winningScore )
				{
					resultPlayerWinCounts[ player ]++;
					
					if( verbose )
						System.out.printf( "Player %d wins!\n", player );
				}
			
			if( verbose )
				System.out.printf( "\n" );
		}

		System.out.printf( "Win stats for (from %d simulations):\n", numSimulations );
		
		for( int player = 0; player < resultPlayerWinCounts.length; player++ )
		{
			System.out.printf( "Player %d: ", player );	
			System.out.printf( "%04d  (%.2f%%)\n", resultPlayerWinCounts[ player ], ( (float) resultPlayerWinCounts[ player ] / (float) numSimulations ) * 100.0 );
		}
		
		
		System.out.printf( "%s\n\n", handTypes.toString() );

	}

}
