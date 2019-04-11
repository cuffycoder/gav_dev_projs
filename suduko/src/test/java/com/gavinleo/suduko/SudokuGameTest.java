package com.gavinleo.suduko;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SudokuGameTest {

	@Test
	public void test() {
		
		int testBoardOld[][] = { 	
							{ 5, 3, 0,   0, 7, 0,  0, 0, 0 },
						  	{ 6, 0, 0,   1, 9, 5,  0, 0, 0 },
						  	{ 0, 9, 8,   0, 0, 0,  0, 6, 0 },
						  	
						  	{ 8, 0, 0,   0, 6, 0,  0, 0, 3 },
						  	{ 4, 0, 0,   8, 0, 3,  0, 0, 1 },
						  	{ 7, 0, 0,   0, 2, 0,  0, 0, 6 },

						  	{ 0, 6, 0,   0, 0, 0,  2, 8, 0 },
						  	{ 0, 0, 0,   4, 1, 9,  0, 0, 5 },
						  	{ 0, 0, 0,   0, 8, 0,  0, 7, 9 }
		};
		
	 int testBoard[][] = { 	
				{ 2, 0, 0,   0, 0, 0,  6, 0, 0 },
			  	{ 0, 9, 1,   0, 0, 7,  0, 5, 0 },
			  	{ 4, 0, 7,   0, 0, 9,  0, 0, 2 },
			  	
			  	{ 0, 5, 0,   0, 0, 0,  0, 6, 0 },
			  	{ 0, 2, 0,   1, 0, 0,  0, 3, 9 },
			  	{ 0, 0, 4,   0, 0, 0,  5, 0, 0 },

			  	{ 0, 0, 0,   2, 5, 0,  7, 0, 0 },
			  	{ 0, 0, 0,   0, 0, 0,  9, 0, 5 },
			  	{ 0, 1, 0,   0, 7, 6,  0, 0, 0 }
};
		
		SudokuGame myGame = new SudokuGame();
		myGame.setLogLevel( 1 );
		myGame.initializeBoard( testBoard );
		System.out.println( myGame.toString() );
		
		while( true ) {
			ArrayList<int[]> uniqueCandidates = myGame.soleCandidates( true );
			
			if( uniqueCandidates.size() == 0 ) 
				break;
			
			System.out.println( myGame.toString() );
		}
		
	}

}
