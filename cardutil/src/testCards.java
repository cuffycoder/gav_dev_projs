

public class testCards {
	

	private static String [][] getTestCases() {
		String [][]testCases = {
				{  "AH",  "KH",  "QH",  "JH", "10H", PokerHand.STRAIGHT_FLUSH  },
				{  "KH",  "QH",  "JH", "10H",  "9H", PokerHand.STRAIGHT_FLUSH  },
				{  "2H",  "3H",  "4H",  "5H",  "6H", PokerHand.STRAIGHT_FLUSH  },
				{  "AH",  "2H",  "3H",  "4H",  "5H", PokerHand.STRAIGHT_FLUSH  },
				
				{  "AH",  "AS",  "AC",  "AD",  "3C", PokerHand.FOUR_OF_A_KIND },
				{  "KH",  "KS",  "KC",  "KD",  "QC", PokerHand.FOUR_OF_A_KIND },
				{  "KH",  "KS",  "KC",  "KD",  "3C", PokerHand.FOUR_OF_A_KIND },
				{  "2H",  "2S",  "2C",  "2D",  "9C", PokerHand.FOUR_OF_A_KIND },
				{  "2H",  "2S",  "2C",  "2D",  "3C", PokerHand.FOUR_OF_A_KIND },

				{  "KH",  "QS",  "KS",  "KD",  "QD", PokerHand.FULL_HOUSE      },
				{  "KH",  "JS",  "KS",  "KD",  "JD", PokerHand.FULL_HOUSE      },
				{  "JH",  "AS",  "JS",  "AD",  "JD", PokerHand.FULL_HOUSE      },
				{  "JH",  "2S",  "JS",  "2D",  "JD", PokerHand.FULL_HOUSE      },

				{  "AH",  "KH",  "QH",  "JH",  "9H", PokerHand.FLUSH           },
				{  "AH",  "KH",  "QH", "10H",  "9H", PokerHand.FLUSH           },
				{  "AH",  "QH",  "JH", "10H",  "9H", PokerHand.FLUSH           },
				{  "AH",  "2H",  "7H",  "4H",  "5H", PokerHand.FLUSH           },
				{  "KH",  "2H",  "3H",  "4H",  "5H", PokerHand.FLUSH           },
				{  "QH",  "JH",  "9H",  "7H",  "5H", PokerHand.FLUSH           },

				{  "AH",  "KH",  "QH",  "JH", "10S", PokerHand.STRAIGHT        },
				{  "KH",  "QH",  "JH", "10H",  "9S", PokerHand.STRAIGHT        },
				{  "3D",  "4C",  "5C",  "6D",  "7C", PokerHand.STRAIGHT        },
				{  "2D",  "3C",  "4C",  "5D",  "6C", PokerHand.STRAIGHT        },
				{  "AD",  "2C",  "3C",  "4D",  "5C", PokerHand.STRAIGHT        },				
				
				{  "KH",  "QS",  "KS",  "KD",  "9C", PokerHand.THREE_OF_A_KIND },
				{  "KH",  "QS",  "KS",  "KD",  "8C", PokerHand.THREE_OF_A_KIND },
				{  "KH",  "JS",  "KS",  "KD",  "8C", PokerHand.THREE_OF_A_KIND },
				{  "3H",  "AS",  "3S",  "3D",  "KC", PokerHand.THREE_OF_A_KIND },

				{  "KH",  "QS",  "KS",  "9D",  "9C", PokerHand.TWO_PAIR        },
				{  "KH",  "JS",  "KS",  "9D",  "9C", PokerHand.TWO_PAIR        },
				{  "QH",  "JS",  "QS",  "9D",  "9C", PokerHand.TWO_PAIR        },
				{  "QH",  "AS",  "QS",  "8D",  "8C", PokerHand.TWO_PAIR        },
				{  "JH",  "AS",  "JS",  "8D",  "8C", PokerHand.TWO_PAIR        },

				{  "AH",  "QS",  "AS",  "9D",  "8C", PokerHand.PAIR            },
				{  "KH",  "QS",  "KS",  "9D",  "8C", PokerHand.PAIR            },
				{  "KH",  "QS",  "KS",  "8C",  "3D", PokerHand.PAIR            },
				{  "KH",  "QS",  "KS",  "8C",  "2D", PokerHand.PAIR            },
				{  "8H",  "QS",  "9S",  "8C",  "2D", PokerHand.PAIR            },
				{  "8H",  "2S",  "9S",  "7C",  "2D", PokerHand.PAIR            },

				{  "KH",  "AD",  "2H",  "3D",  "4H", PokerHand.HIGH_CARD       },
				{  "JD",  "QH",  "7S",  "3D",  "KC", PokerHand.HIGH_CARD       },
				{  "9C",  "7D",  "6H",  "3C",  "2D", PokerHand.HIGH_CARD       },
				{  "2H",  "3C",  "5C",  "6C",  "8C", PokerHand.HIGH_CARD       },
				{  "2H",  "3C",  "5C",  "6C",  "7C", PokerHand.HIGH_CARD       },
				{  "2H",  "3C",  "4C",  "6C",  "7C", PokerHand.HIGH_CARD       },
				{  "2H",  "3C",  "4C",  "5C",  "7C", PokerHand.HIGH_CARD       },

		};
		
		return( testCases );
	}
	
	private static void testRankedHands() {
		String [][]testCases = getTestCases();
		PokerHand prevHand = null;
		PokerHand currHand = null;
		
		PokerHand []rankedHands = new PokerHand[ testCases.length ];
		
		for( int i = 0; i < testCases.length; i++ )
		{
			rankedHands[ i ] = new PokerHand( testCases[ i ][ 0 ], testCases[ i ][ 1 ], testCases[ i ][ 2 ], testCases[ i ][ 3 ], testCases[ i ][ 4 ] ); 
		}
		
		for( int i = 1; i < rankedHands.length; i++ )
		{
			if( currHand == null ) 
			{
				currHand = new PokerHand( 
						testCases[ i ][ 0 ], 
						testCases[ i ][ 1 ], 
						testCases[ i ][ 2 ], 
						testCases[ i ][ 3 ], 
						testCases[ i ][ 4 ] ); 
				
				prevHand = new PokerHand( 
						testCases[ i - 1 ][ 0 ], 
						testCases[ i - 1 ][ 1 ], 
						testCases[ i - 1 ][ 2 ], 
						testCases[ i - 1 ][ 3 ], 
						testCases[ i - 1 ][ 4 ] ); 
			}
			else
			{
				currHand.setHand( 
						testCases[ i ][ 0 ], 
						testCases[ i ][ 1 ], 
						testCases[ i ][ 2 ], 
						testCases[ i ][ 3 ], 
						testCases[ i ][ 4 ] );
				
				prevHand.setHand( 
						testCases[ i - 1 ][ 0 ], 
						testCases[ i - 1 ][ 1 ], 
						testCases[ i - 1 ][ 2 ], 
						testCases[ i - 1 ][ 3 ], 
						testCases[ i - 1 ][ 4 ] ); 
			}
			
			if( currHand.getScore() >= prevHand.getScore() )
				System.out.printf( "***** ASSERTION FAILED: " );
			else
				System.out.printf( "ASSERTION PASSED: " );
			
			System.out.printf( "%s (%s) %d < %s (%s) %d\n", currHand, currHand.handType(), currHand.getScore(), prevHand, prevHand.handType(), prevHand.getScore() );
		}
		
	}
	

	private static void testHandTypes() {
		String [][]testCases = getTestCases();
				
		for( int currCase = 0; currCase < testCases.length; currCase++ )
		{
			PokerHand testHand = new PokerHand( 
					testCases[ currCase ][ 0 ],					
					testCases[ currCase ][ 1 ],
					testCases[ currCase ][ 2 ],
					testCases[ currCase ][ 3 ],
					testCases[ currCase ][ 4 ] );
			
			System.out.printf( "Hand: %s\n", testHand.toString() );
			
			// Check that the hand type is the expected one
			if( testHand.handType() != testCases[ currCase ][ 5 ] )
				System.out.printf( "***** ASSERTION FAILED: handType = %s, expected %s\n\n\n", testHand.handType(), testCases[ currCase ][ 5 ] );
			else
				System.out.printf( "ASSERTION PASSED: handType = %s\n", testHand.handType() );
		
			// Ensure that the order of the cards doesn't matter by creating
			// a second hand with different order and ensuring the score and hand type
			// are consistent
			PokerHand testHandAlternate = new PokerHand(
					testCases[ currCase ][ 4 ],					
					testCases[ currCase ][ 3 ],
					testCases[ currCase ][ 2 ],
					testCases[ currCase ][ 1 ],
					testCases[ currCase ][ 0 ] );
						
			System.out.printf( "Equivalent Hand: %s\n", testHandAlternate.toString() );

			if( testHandAlternate.getScore() != testHand.getScore() )
				System.out.printf( "***** ASSERTION FAILED: scores not consistent %d vs %d\n\n\n", testHand.getScore(), testHandAlternate.getScore() );
			else
				System.out.printf( "ASSERTION PASSED: scores consistent = %d\n", testHand.getScore() );	
		}
	}
	
	/**
	 * testCardOrderEquivalence() - generates the same hand but in
	 * a different order. Ensures that the score and the hand types are
	 * equivalent
	 */
	private static void testCardOrderEquivalence() {
		String [][]testCases = getTestCases();
		int [][]cardOrders = { { 0, 1, 2, 3, 4 }, { 3, 0, 1, 4, 2 }, { 4, 3, 1, 0, 2 } };
		PokerHand []equivHands = new PokerHand[ cardOrders.length ];

		
		for( int currCase = 0; currCase < testCases.length; currCase++ )
		{
			for( int i = 0; i < cardOrders.length; i++ )
				equivHands[ i ] = new PokerHand( 
					testCases[ currCase ][ cardOrders[i][0] ],					
					testCases[ currCase ][ cardOrders[i][1] ],
					testCases[ currCase ][ cardOrders[i][2] ],
					testCases[ currCase ][ cardOrders[i][3] ],
					testCases[ currCase ][ cardOrders[i][4] ] );
			
			for( int i = 1; i < equivHands.length; i++ )
			{
				if( equivHands[ i ].getScore() != equivHands[ i - 1 ].getScore() 
						|| equivHands[ i ].handType() != equivHands[ i - 1 ].handType() )
					System.out.printf( "*** ASSERTION FAILED: %s (%s) %d != %s (%s) %d\n\n", equivHands[ i - 1 ].toString(),
							equivHands[ i - 1 ].handType(), equivHands[ i - 1 ].getScore(), equivHands[ i ].toString(),
							equivHands[ i ].handType(), equivHands[ i ].getScore() );
				else
					System.out.printf( "ASSERTION PASSED: %s == %s\n", equivHands[ i - 1 ], equivHands[ i ] );
			}
			
		}
	}
	
	private static void playHoldEm() {
		String [][]testHands = { 
				/*
				{ "AH", "2H" },
				{ "2H", "3H" },
				{ "3H", "4H" },
				{ "4H", "5H" },
				{ "5H", "6H" },
				{ "6H", "7H" },
				{ "7H", "8H" },
				{ "8H", "9H" },
				{ "9H", "10H" },
				{ "10H", "JH" },
				{ "JH", "QH" },
				{ "QH", "KH" },
				{ "KH", "AH" },
				
				{ "2H", "2C" },
				{ "3H", "3C" },
				{ "4H", "4C" },
				{ "5H", "5C" },
				{ "6H", "6C" },
				{ "7H", "7C" },
				{ "8H", "8C" },
				{ "9H", "9C" },
				{ "10H", "10C" },
				{ "JH", "JC" },
				{ "QH", "QC" },
				{ "KH", "KC" },
				{ "AH", "AC" },
*/
				{ "AH", "KH" },
				{ "2H", "2C" },
		};
	}
	
	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args ) {
		
		if( false )
		{
			testHandTypes();
			testRankedHands();
			testCardOrderEquivalence();
		}
		
		playHoldEm();
		
	//	TexasHoldEmSimulatorGUI gui = new TexasHoldEmSimulatorGUI();
		
	}	
				
	}
