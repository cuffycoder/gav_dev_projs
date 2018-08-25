import java.util.Random;
import java.util.ArrayList;

public class DeckOfCards {

	private ArrayList<PlayingCard> cardsInDeck;
	private static Random randomizer = new Random();
	private int[] dealingOrder = new int[52];
	private int dealingOrderCurrPos;
	
	public DeckOfCards() {
		
		String []cardNames = { "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH", "AH",
				               "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD", "AD",
				               "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS", "AS",
				               "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC", "AC",
		};
		
		cardsInDeck   = new ArrayList<PlayingCard>( 52 );
		
		for( String cardName : cardNames )
			cardsInDeck.add( PlayingCard.getInstance( cardName ) );
		
		// by default, dealing order is sorted deck
		for( int i = 0; i < 52; i++ )
			dealingOrder[ i ] = i;
		
		dealingOrderCurrPos = 0;
	}
	
	public void shuffle() {
		int temp;
		int numCards = cardsInDeck.size();
		
		for( int currPos = 0; currPos < numCards; currPos++ )
		{
			int newPos = randomizer.nextInt(52);
			
			temp = dealingOrder[newPos];
			
			dealingOrder[newPos] = dealingOrder[currPos];
			dealingOrder[currPos] = temp;
		}
		
		dealingOrderCurrPos = 0;
	}
	
	/**
	 *  excludeSpecificCard
	 */
	public void excludeSpecificCard( String cardName )
	{
		PlayingCard cardToExclude = PlayingCard.getInstance(cardName);
		
		// find the card
		for( int i = dealingOrderCurrPos; i < 52; i++ ) {
			if( cardToExclude == cardsInDeck.get( dealingOrder[ i ] ) ) {
				int temp = dealingOrder[i];
				dealingOrder[i] = dealingOrder[ dealingOrderCurrPos ];
				dealingOrder[ dealingOrderCurrPos ] = temp;
				dealingOrderCurrPos++;
				break;
			}	
		}
	}

	public PlayingCard pop() {
		
		PlayingCard dealtCard;
		
		dealtCard = cardsInDeck.get( dealingOrder[ dealingOrderCurrPos ] );
		
		dealingOrderCurrPos++;
		
		return dealtCard;
	}
		
	public void reset() {
		shuffle();
	}
	
	public void showDeck() {
		
		for( int i = 0; i < cardsInDeck.size(); i++ )
			System.out.printf( "%s\n", cardsInDeck.get( i ).toString() );
	}
}
