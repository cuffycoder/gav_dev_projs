
public class PlayingCard {

	private int num;
	private char suit;
	static final char CLUBS    = 'C';
	static final char DIAMONDS = 'D';
	static final char HEARTS   = 'H';
	static final char SPADES   = 'S';
	
	static final int ACE   = 14;
	static final int KING  = 13;
	static final int QUEEN = 12;
	static final int JACK  = 11;
	static final int TEN   = 10;
	static final int NINE  = 9;
	static final int EIGHT = 8;
	static final int SEVEN = 7;
	static final int SIX   = 6;
	static final int FIVE  = 5;
	static final int FOUR  = 4;
	static final int THREE = 3;
	static final int TWO   = 2;
	
	public PlayingCard( int numInput, char suitInput ) {
		num  = numInput;
		suit = suitInput;
	}
	
	private char getSuitFromString( String cardInput ) {
		return( cardInput.charAt( cardInput.length() - 1 ) );
	}
	
	private int getNumFromString( String cardInput ) {
		int result = 0;
		
		if( cardInput.length() == 3 && cardInput.charAt( 0 ) == '1' && cardInput.charAt(1) == '0' )
			result = TEN;
		else if( cardInput.length() == 2 )
		{
			switch( cardInput.charAt(0) ) {
			case 'A': 
				result = ACE;
				break;
			case 'K':
				result = KING;
				break;
			case 'Q':
				result = QUEEN;
				break;
			case 'J':
				result = JACK;
				break;
			default:
				result = (int) cardInput.charAt(0) - (int) '0';
			}
		}
		
		return( result );
	}
	
	public PlayingCard( String cardInput ) {		
		suit = getSuitFromString( cardInput );
		num  = getNumFromString( cardInput );
	}
	
	public int getNum() {
		return( num );
	}
	
	public char getSuit() {
		return( suit );
	}
	
	public void setCard( int numInput, char suitInput ) {
		num = numInput;
		suit = suitInput;
	}
	
	public void setCard( String cardInput ) {
		suit = getSuitFromString( cardInput );
		num  = getNumFromString( cardInput );
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
