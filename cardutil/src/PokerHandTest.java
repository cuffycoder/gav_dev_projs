import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;


public class PokerHandTest {

	@Test
	public void testHandTypes() {
		
		HashMap<String, String[][]> testHands = new HashMap<String, String[][]>();
		
		testHands.put( PokerHand.STRAIGHT_FLUSH, new String[][] {	
				{ "AH",  "KH", "QH", "JH", "10H" },
				{ "10H", "AH", "KH", "JH",  "QH" },
				{ "AD",  "2D", "3D", "4D",  "5D" },
				{ "2D",  "3D", "4D", "5D",  "AD" },
				{ "2D",  "3D", "4D", "5D",  "6D" },
				{ "7D",  "3D", "4D", "5D",  "6D" },
				{ "7D",  "8D", "4D", "5D",  "6D" },
				{ "7D",  "8D", "9D", "5D",  "6D" },
				{ "7D",  "8D", "9D", "10D", "6D" },
				{ "7D",  "8D", "9D", "10D", "JD" },
				{ "QD",  "8D", "9D", "10D", "JD" },
				{ "QD",  "KD", "9D", "10D", "JD" },
				{ "QD",  "KD", "AD", "10D", "JD" }
		}  );
		
		testHands.put( PokerHand.FOUR_OF_A_KIND, new String[][] {	
				{ "AH",  "AD", "AS", "AC",  "KC" },
				{ "2H",  "2C", "2D", "2S",  "JD" },

		}  );
		
		testHands.put( PokerHand.FULL_HOUSE, new String[][] {	
				{ "AH",  "AD", "AS", "KH",  "KC" },
				{ "2H",  "2C", "2D", "JH",  "JD" },

		}  );
		
		testHands.put( PokerHand.FLUSH, new String[][] {	
				{ "AH",  "KH", "QH", "JH",  "9H" },
				{ "5H", "AH", "KH", "JH",  "QH" },
				{ "AD",  "2D", "3D", "4D",  "6D" },
				{ "2D",  "4D", "6D", "8D", "10D" },
		}  );
		
		testHands.put( PokerHand.STRAIGHT, new String[][] {	
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
		
		testHands.put( PokerHand.THREE_OF_A_KIND, new String[][] {	
				{ "AH",  "AC", "AS", "KH", "QC"  },
				{ "AH",  "AC", "AS", "KH", "JC"  },
				{ "AH",  "AC", "AS", "3H", "2C"  },
				{ "2D",  "2C", "2D", "AH",  "KS" },
				{ "2D",  "2C", "2D", "KH",  "QS" },
				{ "2D",  "2C", "2D", "KH",  "3S" },
				{ "2D",  "2C", "2D", "4H",  "3S" },
		}  );
		
		//TODO: Add 2 pair, pair and high card
		
		testHands.forEach( ( handType, hands ) -> {
			for( String[] cards : hands ) {
				PokerHand hand = new PokerHand( cards[ 0 ], cards[ 1 ], cards[ 2 ], cards[ 3 ], cards[ 4 ] );
				
				System.out.println( hand.toString() + ".handType() == " + handType );
				assertEquals( handType, hand.handType() );
			} } );
 				
		}
	
	@Test
	public void testScoring() {
		
		String[][] orderedHands = new String[][] {
				// Straight Flush
				{ "AH", "KH", "QH", "JH", "10H" }, // highest possible straight flush
				{ "KH", "QH", "JH", "10H", "9H" },
				{ "QD", "JD", "10D", "9D", "8D" },
				{ "6H", "5H", "4H", "3H",  "2H" },
				{ "5H", "4H", "3H",  "2H", "AH" }, // Ace is low in this straight; lowest possible straight flush
				
				// Four of a Kind
				{ "AH", "AS", "AC", "AD", "KH"  }, // highest possible four of a kind
				{ "AH", "AS", "AC", "AD", "QH"  }, // same 4 of a kind but lower kicker
				{ "KH", "KS", "KC", "KD", "AH"  },	
				{ "2D", "2S", "2C", "2H", "AD"  },
				{ "2D", "2S", "2C", "2H", "3H"  }, // lowest possible four of a kind
				
				// Full House
				{ "AC", "AD", "AS", "KH", "KS"  }, // highest possible full house
				{ "AC", "AD", "AS", "QH", "QS"  },
				{ "KH", "KD", "KS", "AH", "AS"  },
				{ "2H", "2D", "2C", "3S", "3H"  }, // lowest possible full house 
				
				// Flush
				{ "AH", "KH", "QH", "JH", "9H"  }, // highest possible flush
				{ "7H", "5H", "4H", "3H", "2H"  }, // lowest possible flush
				
				// Straight
				{ "AH", "KH", "QH", "JH", "10S" }, // highest possible straight
				{ "6H", "5H", "3H", "4H", "2S"  },
				{ "5H", "4H", "3H", "2H", "AS"  }, // lowest possible straight (ace is low)
				
				// Three of a Kind
				{ "AH", "AS", "AD", "KC", "QC"  }, // highest possible 3 of a kind
				{ "AH", "AS", "AD", "KC", "JC"  }, // check 2nd level kicker
				{ "AH", "AS", "AD", "QC", "JC"  }, // check first level kicker
				{ "KH", "KS", "KD", "AC", "QS"  }, // kicker should not override the value of the set
				
				{ "2H", "2S", "2C", "4C", "3S"  }, // lowest possible 3 of a kind
				
				// Two Pair
				{ "AC", "AS", "KC", "KS", "QH"  }, // highest possible two pair	
				{ "AC", "AS", "KC", "KS", "JH"  }, // kicker
				{ "KC", "KS", "QC", "QS", "AH"  }, // ensure kicker does not override pairs
				{ "3C", "3H", "2C", "2S", "4S"  }, // lowest possible two pair
			
				// One pair
				{ "AH", "AC", "KC", "QH", "JD"  }, // highest possible pair
				{ "AH", "AC", "KC", "QH", "10D" }, // level 3 kicker
				{ "AH", "AC", "KC", "JH", "10D" }, // level 2 kicker
				{ "AH", "AC", "QC", "JH", "10D" }, // level 1 kicker
				
				{ "2D", "2C", "5C", "4D", "3S"  }, // lowest possible pair
				
				// High Card
				{ "AH", "QS", "JD", "10D", "9C" }, // highest possible high card
				{ "7H", "6H", "4D", "3C",  "2D" }, // lowest possible hand
		};
		
		PokerHand prevHand = null;
		
		for( String[] currHand : orderedHands ) {
			if( null == prevHand ) {
				prevHand = new PokerHand( currHand[0], currHand[1], currHand[2], currHand[3], currHand[4] );
			}
			else
			{
				PokerHand thisHand = new PokerHand( currHand[0], currHand[1], currHand[2], currHand[3], currHand[4] );
				
				System.out.println( prevHand.toString() + "(" + prevHand.handType() + ") beats " + thisHand.toString() + "(" + thisHand.handType() + ")" );
				assertTrue( prevHand.getScore() > thisHand.getScore() );
			}
		}
		
		
		// Check equivalent hand sets
		String[][][] equivalentHandSets = { 
				{
					// straight flushes
					{ "AH", "KH", "QH", "JH", "10H" },
					{ "AS", "KS", "QS", "JS", "10S" },
					{ "AC", "KC", "QC", "JC", "10C" },
					{ "AD", "KD", "QD", "JD", "10D" },
				},
				
			
		};
		
		for( String[][] currSet : equivalentHandSets ) {
			PokerHand firstHand = null;
			System.out.println( "Test that following hands are equivalent:" );
			
			for( String[] currHand : currSet ) {
				if( null == firstHand ) {
					firstHand = new PokerHand( currHand[0], currHand[1], currHand[2], currHand[3], currHand[4] );
					System.out.println( firstHand.toString() + "(" + firstHand.handType() + ")" + " Score = " + firstHand.getScore() );
				}
				else
				{
					PokerHand thisHand = new PokerHand( currHand[0], currHand[1], currHand[2], currHand[3], currHand[4] );
					System.out.println( thisHand.toString() + "(" + thisHand.handType() + ")" + " Score = " + thisHand.getScore() );
					assertEquals( thisHand.handType(), firstHand.handType() );
					assertEquals( thisHand.getScore(), firstHand.getScore() );
				}
			}
				
		}	

	}	
}


