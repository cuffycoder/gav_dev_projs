package gavinleo.mongo_probe;

import java.util.ArrayList;


import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;
import org.junit.Test;


public class ObservationStatsCalculatorTest {

	class TestParams {

		private ArrayList<Long> listOfLongs;
		private double expectedMean;
		private long expectedMax;
		private long expectedMin;
		private long expectedP95;
		private long expectedP99;
		private int expectedNumObservations;
		private String testName;
		private String testDescription;

		private TestParams( String in_testName, String in_testDescription, long[] inputLongs ) {
			this.listOfLongs = new ArrayList<Long>();

			for (long e : inputLongs)
				listOfLongs.add(e);
			
			testName = in_testName;
			testDescription = in_testDescription;
		}

		private void setExpectedMin(long min) {
			expectedMin = min;
		};

		private void setExpectedMax(long max) {
			expectedMax = max;
		};

		private void setExpectedMean(double mean) {
			expectedMean = mean;
		};

		private void setExpectedP95(long p95) {
			expectedP95 = p95;
		};

		private void setExpectedP99(long p99) {
			expectedP99 = p99;
		};

		private void runTest() {
			
			System.out.println( "Running: " + this.testName );
			System.out.println( this.testDescription );
			
			ObservationStatsCalculator calculator = new ObservationStatsCalculator( this.listOfLongs );
			
			HashMap<String,Double> actuals = calculator.arrayListGetStats();
			
			System.out.printf( "Minimum (Expected): %d\n", this.expectedMin );
			System.out.printf( "Minimum (Actual):   %f\n", actuals.get( "Min" ) );
			assertEquals( (double) this.expectedMin, actuals.get( "Min" ), 0.001 );

			System.out.printf( "Maximum (Expected): %d\n", this.expectedMax );
			System.out.printf( "Maximum (Actual):   %f\n", actuals.get( "Max" ));
			assertEquals( (double) this.expectedMax, actuals.get( "Max" ), 0.001 );
			
			System.out.printf( "Mean (Expected): %f\n", this.expectedMean );
			System.out.printf( "Mean (Actual):   %f\n", actuals.get( "Mean" ) );		
			assertEquals( this.expectedMean, actuals.get( "Mean" ), 0.001 );

		

			System.out.printf( "P95 (Expected): %d\n", this.expectedP95 );
			System.out.printf( "P95 (Actual):   %f\n", actuals.get( "95 Percentile" ));		
			assertEquals( (double) this.expectedP95, actuals.get( "95 Percentile" ), 0.001 );

			System.out.printf( "P99 (Expected): %d\n", this.expectedP99 );
			System.out.printf( "P99 (Actual):   %f\n", actuals.get( "99 Percentile" ));
			assertEquals( (double) this.expectedP99, actuals.get( "99 Percentile" ), 0.001 );
		}
	}

	private TestParams testEmptyList() {
		// Empty array
		long[] empty = {};
		
		TestParams emptyListTest = new TestParams("Empty List", "Boundary condition of empty list", empty );
		emptyListTest.setExpectedMax(0);
		emptyListTest.setExpectedMin(0);
		emptyListTest.setExpectedMean(0);
		emptyListTest.setExpectedP95(0);
		emptyListTest.setExpectedP99(0);
		return emptyListTest;
	}

	private TestParams testSingleElementList() {
		// Empty array
		long[] testArray = { 9 };
		
		TestParams params = new TestParams("List with single value", "Boundary condition of single element list", testArray );
		params.setExpectedMax(9);
		params.setExpectedMin(9);
		params.setExpectedMean(9);
		params.setExpectedP95(9);
		params.setExpectedP99(9);
		return params;
	}
	
	private TestParams testHundredElementSortedList() {
		// Empty array
		long[] testArray = new long[100];

		for( int i = 0; i< 100; i++ ) {
			testArray[i] = i + 1;
		}
		
		TestParams params = new TestParams("List with 1-100", "Hundred element list", testArray );
		params.setExpectedMax(100);
		params.setExpectedMin(1);
		params.setExpectedMean(50.5);
		params.setExpectedP95(95);
		params.setExpectedP99(99);
		return params;
	}
	
	private TestParams testHundredElementUnsortedList() {
		// Empty array
		long[] testArray = new long[100];

		for( int i = 0; i< 100; i++ ) {
			testArray[99-i] = i + 1;
		}
		
		TestParams params = new TestParams("List with 1-100", "Hundred element list", testArray );
		params.setExpectedMax(100);
		params.setExpectedMin(1);
		params.setExpectedMean(50.5);
		params.setExpectedP95(95);
		params.setExpectedP99(99);
		return params;
	}
	
	
	private TestParams testNElementUnsortedList( int numberOfElements, String name, String desc ) {
		// Empty array
		long[] testArray = new long[numberOfElements];

		for( int i = 0; i< numberOfElements; i++ ) {
			testArray[numberOfElements - 1 -i] = i + 1;
		}
		
		TestParams params = new TestParams( name, desc, testArray );
		params.setExpectedMax(numberOfElements);
		params.setExpectedMin(1);
		params.setExpectedMean( ( numberOfElements + 1.0 ) / 2.0 );
		params.setExpectedP95( (long) Math.ceil( numberOfElements * 0.95 ));
		params.setExpectedP99( (long) Math.ceil( numberOfElements * 0.99 ));
		return params;
	}
	
	@Test
	public void testArrayListGetStats() {
		
		TestParams tests[] = { 
				this.testEmptyList(),
			this.testSingleElementList(),
			this.testHundredElementSortedList(),
			this.testHundredElementUnsortedList(),
			this.testNElementUnsortedList( 100, "100 Elements unsorted", "Boundary condition of 100 elements" ),
			this.testNElementUnsortedList( 99, "99 Elements unsorted", "Boundary condition of 99 percentile" ),
			this.testNElementUnsortedList( 95, "95 Elements unsorted", "Boundary condition of 95 percentile" ),
			this.testNElementUnsortedList( 50, "50 elements, unsorted", "Check less than 100" ),
			this.testNElementUnsortedList( 150, "150 elements, unsorted", "Check greater than 100" )
		};

		for( TestParams t : tests ) t.runTest();		
	}

}
