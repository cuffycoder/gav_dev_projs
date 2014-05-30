/**
 * 
 *  SynchableContactTest.java
 * @author Gavin Leo-Rhynie
 * 
 * Copyright Mar 23, 2014 
 */

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.UUID;

import org.junit.Test;


public class SynchableContactTest {

	@Test
	public void testSettersAndGetters() {
		
		SynchableContact emptyContact = new SynchableContact();
		
		// firstName
		assertEquals( "firstName should be empty", "", emptyContact.getFirstName() );
		emptyContact.setFirstName( "John" );
		assertEquals( "firstName should be John", "John", emptyContact.getFirstName() );

		assertNotNull( "Should have non-null SynchableName", emptyContact.getSynchableId());
	}

	@Test
	public void testUniqueness() {
		HashMap<UUID,String> allContactIDs = new HashMap<UUID,String>();
		
		for( int i = 0; i < 10000; i++ ) {
			SynchableContact hopeIsUnique = new SynchableContact();
			
			assertEquals( "ID of new Contact should not exist", false, allContactIDs.containsKey( hopeIsUnique.getSynchableId()));
			allContactIDs.put(hopeIsUnique.getSynchableId(), "" );
		}
			
	}
}
