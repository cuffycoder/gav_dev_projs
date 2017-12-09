import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;


public class TexasHoldEmPlayerHandTest {

	@Test
	
	public void testBestHandTypes() {
		
		HashMap<String, String[][]> testHands = new HashMap<String, String[][]>();
		
		testHands.put( PokerHand.STRAIGHT_FLUSH, new String[][] {	
				{ "KC", "AS",      "AH",  "KH", "QH", "JH", "10H" },
				{ "KC", "AS",      "AD",  "KD", "QD", "JD", "10D" },
				{ "2D", "7C",      "AH",  "KH", "QH", "JH", "10H" },
				{ "10D", "KC",     "2H",  "3H", "4H", "5H", "6H"  },
				{ "10D", "KC",     "AH",  "2H", "3H", "4H", "5H"  },
				
		}  );
		
		/*
		testHands.put( "Full House", new String[][] {	
				{ "AH",  "AD", "AS", "KH",  "KC" },
				{ "2H",  "2C", "2D", "JH",  "JD" },

		}  );
		
		testHands.put( "Flush", new String[][] {	
				{ "AH",  "KH", "QH", "JH",  "9H" },
				{ "5H", "AH", "KH", "JH",  "QH" },
				{ "AD",  "2D", "3D", "4D",  "6D" },
				{ "2D",  "4D", "6D", "8D", "10D" },
		}  );
		
		testHands.put( "Straight", new String[][] {	
				{ "AH",  "KH", "QH", "JH", "10D" },
				{ "AH",  "KH", "QH", "JC", "10D" },
				{ "AH",  "KH", "QS", "JH", "10D" },
				{ "10H", "AH", "KH", "JH",  "QS" },
				{ "AD",  "2D", "3D", "4D",  "5S" },
				{ "2D",  "3D", "4D", "5D",  "AS" },
				{ "2D",  "3D", "4D", "5D",  "6S" },
				{ "7D",  "3D", "4D", "5D",  "6S" },
				{ "7D",  "8D", "4D", "5D",  "6H" },
				{ "7D",  "8D", "9D", "5D",  "6H" },
				{ "7D",  "8D", "9D", "10D", "6H" },
				{ "7D",  "8D", "9D", "10D", "JH" },
				{ "QD",  "8D", "9D", "10D", "JH" },
				{ "QD",  "KD", "9D", "10D", "JS" },
				{ "QD",  "KD", "AD", "10D", "JC" }	
		}  );
		*/
		testHands.forEach( ( handType, hands ) -> {
			for( String[] cardsAsStrings : hands ) {
				PlayingCard[] cards = new PlayingCard[7];
				for( int i = 0; i < 7; i++ )
					cards[i] =  PlayingCard.getInstance( cardsAsStrings[i] );
					
				TexasHoldEmPlayerHand hand = new TexasHoldEmPlayerHand( cards );
				
				System.out.println( hand.toString() + ".handType() == " + handType );
				assertEquals( handType, hand.bestHand().handType() );
			} } );
 				
		}

	public void test() {
		fail("Not yet implemented");
	}

}
