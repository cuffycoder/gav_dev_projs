import java.util.Random;
import java.util.ArrayList;

public class DeckOfCards {

	private ArrayList<PlayingCard> cardsInDeck;
	private ArrayList<PlayingCard> cardsDealt;
	private static Random randomizer = new Random();
	
	public DeckOfCards() {
		char []suits = { PlayingCard.CLUBS, 
				         PlayingCard.DIAMONDS, 
				         PlayingCard.HEARTS, 
				         PlayingCard.SPADES };
		
		cardsInDeck   = new ArrayList<PlayingCard>( 52 );
		cardsDealt    = new ArrayList<PlayingCard>( 52 );
		
		// initialize the deck with the four suits
		for( int s = 0; s < suits.length; s++ ) 
		{
			for( int i = 2; i <= 14; i++ )
				cardsInDeck.add( (i-2) + (13 * s), new PlayingCard( i, suits[ s ] ) );
		}
		
	}
	
	public void shuffle() {
		PlayingCard temp;
		int numCards = cardsInDeck.size();
		
		for( int currPos = 0; currPos < numCards; currPos++ )
		{
			int newPos = randomizer.nextInt( numCards );
			
			temp = cardsInDeck.get( newPos );
			cardsInDeck.set(  newPos, cardsInDeck.get( currPos ) );
			cardsInDeck.set( currPos, temp );
		}
	}
	
	/**
	 *  pop
	 * @param card
	 * @return PlayingCard
	 * 
	 * returns the specified card from the deck
	 */
	public PlayingCard pop( int cardNum, char cardSuit )
	{
		
		for( int i = 0; i < cardsInDeck.size(); i++ )
			if( cardsInDeck.get( i ).getNum() == cardNum 
					&& cardsInDeck.get( i ).getSuit() == cardSuit )
			{
				PlayingCard dealtCard = cardsInDeck.get( i );
				
				cardsDealt.add( dealtCard );
				cardsInDeck.remove( i );
				
				return( dealtCard );
			}
		
		return( pop() );
	}

	public PlayingCard pop() {
		PlayingCard dealtCard = cardsInDeck.get( cardsInDeck.size() - 1 );
		
		cardsInDeck.remove( cardsInDeck.size() - 1 );
		cardsDealt.add( dealtCard );
				
		return( dealtCard );
	}
		
	public void reset() {
		
		while( cardsDealt.size() > 0 )
		{
			cardsInDeck.add( cardsDealt.get( 0 ) );
			cardsDealt.remove( 0 );
		}
		
		shuffle();
	}
	
	public void showDeck() {
		
		for( int i = 0; i < cardsInDeck.size(); i++ )
			System.out.printf( "%s\n", cardsInDeck.get( i ).toString() );
	}
}
