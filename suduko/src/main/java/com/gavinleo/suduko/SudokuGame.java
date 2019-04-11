package com.gavinleo.suduko;

import java.util.ArrayList;

public class SudokuGame {

	static final int BOARD_SIZE = 9;
	static final int NUM_BLOCKS = BOARD_SIZE;
	static final int NUM_MIN = 1;
	static final int NUM_MAX = 9;
	
	private int board[][];
	// TODO: the second dimension should be a hash instead of an array
	private int rowNumPresence[][];
	private int colNumPresence[][];
	private int blockNumPresence[][];
	
	private int logLevel = 0;
	
	public SudokuGame() {
		board = new int[BOARD_SIZE][BOARD_SIZE];
		rowNumPresence = new int[BOARD_SIZE][BOARD_SIZE];
		colNumPresence = new int[BOARD_SIZE][BOARD_SIZE];
		blockNumPresence = new int[BOARD_SIZE][BOARD_SIZE];

		// blank the board
		for( int row = 0; row < BOARD_SIZE; row++ )
			for( int col = 0; col < BOARD_SIZE; col++ ) 
				board[row][col] = 0; 
			
		// initialize the presence maps
		for( int num = NUM_MIN; num <= NUM_MAX; num++ ) {
			for( int row = 0; row < BOARD_SIZE; row++ )
				rowNumPresence[row][num-1] = 0;
			
			for( int col = 0; col < BOARD_SIZE; col++ )
				colNumPresence[col][num-1] = 0;
			
			for( int block = 0; block < NUM_BLOCKS; block++ )
				blockNumPresence[ block ][ num-1 ] = 0;
		}		
	}

	public void setLogLevel( int level ) {
		logLevel = level;
	}
	
	public void initializeBoard( int [][]boardInput ) {
						
		for( int r = 0; r < BOARD_SIZE; r++ ) {
			for( int c = 0; c < BOARD_SIZE; c++ )
				this.setCellValue(r, c, boardInput[r][c] );
		}
	}
		
	public void setCellValue( int row, int col, int val ) {		
		board[ row ][ col ] = val;
		
		if( val == 0 ) 
			return;
		
		// update the presence Maps
		rowNumPresence[row][val-1] = 1;
		colNumPresence[col][val-1] = 1;
		
		int blockId = this.blockNumberFromRowCol(row, col);
		blockNumPresence[blockId][val-1] = 1;
	}

	public ArrayList<Integer> potentialValuesForCell( int row, int col ) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for( int candidate = NUM_MIN; candidate <= NUM_MAX; candidate++ ) {
			
			// Candidates must satisfy the following
			
			// Can't already be in the same block
			if( blockNumPresence[this.blockNumberFromRowCol(row, col)][ candidate - 1] != 0 )
				continue;
			
			// Can't already be in the same row
			if( rowNumPresence[row][candidate - 1] != 0 )
				continue;
			
			// Can't already be in the same column
			if( colNumPresence[col][candidate - 1] != 0 )
				continue;
			
			result.add( candidate );
		}
		
		return( result );
	}
	
	private int blockNumberFromRowCol( int row, int col ) {
					
		return ( 3 * ( row / 3 ) ) + ( col / 3 );
	}
	
	public ArrayList<int[]> soleCandidates( boolean update ) {
		
		ArrayList<int[]> positionsWithSoleCandidates = new ArrayList<int[]>();
		
		for( int row = 0; row < BOARD_SIZE; row++ )
			for( int col = 0; col < BOARD_SIZE; col++ ) {
				if( board[row][col] == 0 )
				{
					ArrayList<Integer> candidates = this.potentialValuesForCell( row,  col );
					
					if( candidates.size() == 1 ) {
						int instruction[] = { row, col, candidates.get(0) };
						
						positionsWithSoleCandidates.add( instruction );
					}
				}
		}
		
		if( update ) {
			
			positionsWithSoleCandidates.forEach( (instruction) -> {
				if( logLevel >= 1 )
					System.out.printf( "Sole Candidate: Updating [%d,%d] = %d\n", instruction[0], instruction[1], instruction[2] );
			
				this.setCellValue( instruction[0], instruction[1], instruction[2] );
				});
		}
		
		return positionsWithSoleCandidates;
	}
	
	
	public String toString() {
		String horizontalLine = "+---+---+---+---+---+---+---+---+---+";
		String blankLine      = "|           |           |           |";
		
		String asString = "";
		
		for( int row = 0; row < BOARD_SIZE; row++ ) {
			
			if( 0 == ( row % 3) )
				asString += horizontalLine + "\n";
			else
				asString += blankLine + "\n";
			
			for( int col = 0; col < BOARD_SIZE; col++ ) {
				
				String cellAsString;
				
				if( board[row][col] == 0 )
					cellAsString = " ";
				else
					cellAsString = Integer.toString( board[row][col]);
				
				if( 0 == ( col % 3) )
					asString += "| " + cellAsString + " ";
				else 
					asString += "  " + cellAsString + " ";
			}
			asString += "|\n";
			
		}
		
		asString += horizontalLine;
		return asString;

	}
}
