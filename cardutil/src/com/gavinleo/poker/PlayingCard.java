import java.util.HashMap;


public class PlayingCard {

	private short num;
	private char suit;
	static final char CLUBS    = 'C';
	static final char DIAMONDS = 'D';
	static final char HEARTS   = 'H';
	static final char SPADES   = 'S';
	
	static final short ACE   = 14;
	static final short KING  = 13;
	static final short QUEEN = 12;
	static final short JACK  = 11;
	static final short TEN   = 10;
	static final short NINE  = 9;
	static final short EIGHT = 8;
	static final short SEVEN = 7;
	static final short SIX   = 6;
	static final short FIVE  = 5;
	static final short FOUR  = 4;
	static final short THREE = 3;
	static final short TWO   = 2;
	
	private static HashMap<String,PlayingCard> allCardsHash = new HashMap<String,PlayingCard>();
	
	static {
		allCardsHash.put( "2H",  new PlayingCard( PlayingCard.HEARTS, PlayingCard.TWO ));
		allCardsHash.put( "3H",  new PlayingCard( PlayingCard.HEARTS, PlayingCard.THREE ));
		allCardsHash.put( "4H",  new PlayingCard( PlayingCard.HEARTS, PlayingCard.FOUR ));
		allCardsHash.put( "5H",  new PlayingCard( PlayingCard.HEARTS, PlayingCard.FIVE ));
		allCardsHash.put( "6H",  new PlayingCard( PlayingCard.HEARTS, PlayingCard.SIX ));
		allCardsHash.put( "7H",  new PlayingCard( PlayingCard.HEARTS, PlayingCard.SEVEN ));
		allCardsHash.put( "8H",  new PlayingCard( PlayingCard.HEARTS, PlayingCard.EIGHT ));
		allCardsHash.put( "9H",  new PlayingCard( PlayingCard.HEARTS, PlayingCard.NINE ));
		allCardsHash.put( "10H", new PlayingCard( PlayingCard.HEARTS, PlayingCard.TEN ));
		allCardsHash.put( "JH",  new PlayingCard( PlayingCard.HEARTS, PlayingCard.JACK ));
		allCardsHash.put( "QH",  new PlayingCard( PlayingCard.HEARTS, PlayingCard.QUEEN ));
		allCardsHash.put( "KH",  new PlayingCard( PlayingCard.HEARTS, PlayingCard.KING ));
		allCardsHash.put( "AH",  new PlayingCard( PlayingCard.HEARTS, PlayingCard.ACE ));

		allCardsHash.put( "2D",  new PlayingCard( PlayingCard.DIAMONDS, PlayingCard.TWO ));
		allCardsHash.put( "3D",  new PlayingCard( PlayingCard.DIAMONDS, PlayingCard.THREE ));
		allCardsHash.put( "4D",  new PlayingCard( PlayingCard.DIAMONDS, PlayingCard.FOUR ));
		allCardsHash.put( "5D",  new PlayingCard( PlayingCard.DIAMONDS, PlayingCard.FIVE ));
		allCardsHash.put( "6D",  new PlayingCard( PlayingCard.DIAMONDS, PlayingCard.SIX ));
		allCardsHash.put( "7D",  new PlayingCard( PlayingCard.DIAMONDS, PlayingCard.SEVEN ));
		allCardsHash.put( "8D",  new PlayingCard( PlayingCard.DIAMONDS, PlayingCard.EIGHT ));
		allCardsHash.put( "9D",  new PlayingCard( PlayingCard.DIAMONDS, PlayingCard.NINE ));
		allCardsHash.put( "10D", new PlayingCard( PlayingCard.DIAMONDS, PlayingCard.TEN ));
		allCardsHash.put( "JD",  new PlayingCard( PlayingCard.DIAMONDS, PlayingCard.JACK ));
		allCardsHash.put( "QD",  new PlayingCard( PlayingCard.DIAMONDS, PlayingCard.QUEEN ));
		allCardsHash.put( "KD",  new PlayingCard( PlayingCard.DIAMONDS, PlayingCard.KING ));
		allCardsHash.put( "AD",  new PlayingCard( PlayingCard.DIAMONDS, PlayingCard.ACE ));
		
		allCardsHash.put( "2S",  new PlayingCard( PlayingCard.SPADES, PlayingCard.TWO ));
		allCardsHash.put( "3S",  new PlayingCard( PlayingCard.SPADES, PlayingCard.THREE ));
		allCardsHash.put( "4S",  new PlayingCard( PlayingCard.SPADES, PlayingCard.FOUR ));
		allCardsHash.put( "5S",  new PlayingCard( PlayingCard.SPADES, PlayingCard.FIVE ));
		allCardsHash.put( "6S",  new PlayingCard( PlayingCard.SPADES, PlayingCard.SIX ));
		allCardsHash.put( "7S",  new PlayingCard( PlayingCard.SPADES, PlayingCard.SEVEN ));
		allCardsHash.put( "8S",  new PlayingCard( PlayingCard.SPADES, PlayingCard.EIGHT ));
		allCardsHash.put( "9S",  new PlayingCard( PlayingCard.SPADES, PlayingCard.NINE ));
		allCardsHash.put( "10S", new PlayingCard( PlayingCard.SPADES, PlayingCard.TEN ));
		allCardsHash.put( "JS",  new PlayingCard( PlayingCard.SPADES, PlayingCard.JACK ));
		allCardsHash.put( "QS",  new PlayingCard( PlayingCard.SPADES, PlayingCard.QUEEN ));
		allCardsHash.put( "KS",  new PlayingCard( PlayingCard.SPADES, PlayingCard.KING ));
		allCardsHash.put( "AS",  new PlayingCard( PlayingCard.SPADES, PlayingCard.ACE ));
		
		allCardsHash.put( "2C",  new PlayingCard( PlayingCard.CLUBS, PlayingCard.TWO ));
		allCardsHash.put( "3C",  new PlayingCard( PlayingCard.CLUBS, PlayingCard.THREE ));
		allCardsHash.put( "4C",  new PlayingCard( PlayingCard.CLUBS, PlayingCard.FOUR ));
		allCardsHash.put( "5C",  new PlayingCard( PlayingCard.CLUBS, PlayingCard.FIVE ));
		allCardsHash.put( "6C",  new PlayingCard( PlayingCard.CLUBS, PlayingCard.SIX ));
		allCardsHash.put( "7C",  new PlayingCard( PlayingCard.CLUBS, PlayingCard.SEVEN ));
		allCardsHash.put( "8C",  new PlayingCard( PlayingCard.CLUBS, PlayingCard.EIGHT ));
		allCardsHash.put( "9C",  new PlayingCard( PlayingCard.CLUBS, PlayingCard.NINE ));
		allCardsHash.put( "10C", new PlayingCard( PlayingCard.CLUBS, PlayingCard.TEN ));
		allCardsHash.put( "JC",  new PlayingCard( PlayingCard.CLUBS, PlayingCard.JACK ));
		allCardsHash.put( "QC",  new PlayingCard( PlayingCard.CLUBS, PlayingCard.QUEEN ));
		allCardsHash.put( "KC",  new PlayingCard( PlayingCard.CLUBS, PlayingCard.KING ));
		allCardsHash.put( "AC",  new PlayingCard( PlayingCard.CLUBS, PlayingCard.ACE ));
	}
	
	private PlayingCard( char suitInput, short cardNumInput ) {
		num = cardNumInput;
		suit = suitInput;
	}
	
	public static PlayingCard getInstance( String cardName ) {
		return( allCardsHash.get( cardName ) );
	}
	
	public int getNum() {
		return( num );
	}
	
	public char getSuit() {
		return( suit );
	}
	
	public String toString() {
		char []asCharArray = new char[ 3 ];
				
		if( num < TEN )
		{
			asCharArray[ 0 ] = ' ';
			asCharArray[ 1 ] = (char) ( num + (int)'0');
 		}
		else if( num == 10 )
		{
			asCharArray[ 0 ] = '1';
			asCharArray[ 1 ] = '0';
		}
		else
		{
			asCharArray[ 0 ] = ' ';
			
			switch( num ) {
			case JACK:
				asCharArray[ 1 ] = 'J';
				break;
			case QUEEN:
				asCharArray[ 1 ] = 'Q';
				break;
			case KING:
				asCharArray[ 1 ] = 'K';
				break;
			case ACE:
				asCharArray[ 1 ] = 'A';
				break;
			}
		}

		asCharArray[ 2 ] = suit;
	
		return( new String( asCharArray ) );
	}
}
