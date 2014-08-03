import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

/**
 * 
 *  SynchableDatabaseTest.java
 * @author Gavin Leo-Rhynie
 * 
 * Copyright Apr 3, 2014 
 */

/**
 * @author Gavin Leo-Rhynie
 *
 */
public class SynchableDatabaseTest {

	@Test
	public void testUserGetterSetters() {
		
		User testUser = new User( "helloworld@yahoo.com" );
		UUID testUserId = testUser.getUniqueId();
		
		SynchableDatabase db = SynchableDatabase.getCurrentDatabase();
		
		assertEquals( "Search for non-existent user returns null", null, db.getUserFromID(testUserId));
		
		// add user
		db.addUser( testUser );
		
		User testUserCopy = db.getUserFromID(testUserId);
		
		assertEquals( "Copy has same UserName", testUser.getUserName(), testUserCopy.getUserName() );
		assertEquals( "Copy has same UserID",   testUser.getUniqueId(), testUserCopy.getUniqueId() );
		
		// change the copy, ensure it doesn't change the contents of the database
		testUserCopy.setUserName( "foobar" );
	
	}
	
	@Test
	public void testContactGetterSetters() {
		
		SynchableContact testContact = new SynchableContact();
		UUID testContactId = testContact.getUniqueId();
		
		testContact.setFirstName("Benny");
		testContact.setLastName("Alvarez");
		testContact.setEmailAddressPersonal("lunchtogether@babylonclub.com");
		
		SynchableDatabase db = SynchableDatabase.getCurrentDatabase();
		
		assertEquals( "Search for non-existent contact returns null", null, db.getContactFromID(testContactId));
		
		// add contact
		db.addContact( testContact );
		
		SynchableContact testContactCopy = db.getContactFromID(testContactId);
		
		assertEquals( "Copy has same FirstName", testContact.getFirstName(), testContactCopy.getFirstName() );
		assertEquals( "Copy has same LastName",  testContact.getLastName(),  testContactCopy.getLastName() );
		assertEquals( "Copy has same eMailAddress", testContact.getEmailAddressPersonal(), testContactCopy.getEmailAddressPersonal() );

		assertEquals( "Copy has same UniqueID",   testContact.getUniqueId(), testContactCopy.getUniqueId() );
	}

	
	@Test
	public void testUserContactSubscription() {
		
		User testUser = new User( "testuser@foo.com" );
		SynchableDatabase db = SynchableDatabase.getCurrentDatabase();
		db.addUser( testUser );
		
		SynchableContact contact1 = new SynchableContact();
		contact1.setFirstName( "Caspar" );
		contact1.setLastName( "Gomez" );
		db.addContact( contact1 );
		
		SynchableContact contact2 = new SynchableContact();
		contact2.setFirstName( "Elvira" );
		contact2.setLastName( "Montana" );
		db.addContact( contact2 );
		
		Date approvalDate = new Date( System.currentTimeMillis() );
		
		UserContactSubscription subscription1 = new UserContactSubscription( testUser, contact1, approvalDate );
		db.addUserContactSubscription( subscription1 );
		
		Set<SynchableContact> testUserSubscriptions = db.getSubscribedContactsForUser( testUser );
		
		assertEquals( "1 subscribed contact returned", testUserSubscriptions.size(), 1 );
		
		for( SynchableContact returnedContact : testUserSubscriptions ) {
			assertEquals( "Subscribed contact First Name equals that of original", returnedContact.getFirstName(), "Caspar" );
			assertEquals( "Subscribed contact Last Name equals that of original", returnedContact.getLastName(), "Gomez" );	
			assertEquals( "Subscribed contact Personal eMail equals that of original", returnedContact.getEmailAddressPersonal(), "" );	
		}
		
		// Now try updating the contact and see if we get the new values
		contact1.setEmailAddressPersonal( "a.cockroach@scarface.com" );
		db.addContact( contact1 );
		
		Set<SynchableContact> testUserSubscriptionsAfterContactUpdate = db.getSubscribedContactsForUser( testUser );
		
		assertEquals( "1 subscribed contact returned", testUserSubscriptionsAfterContactUpdate.size(), 1 );
		
		for( SynchableContact returnedContact : testUserSubscriptionsAfterContactUpdate ) {
			assertEquals( "Subscribed contact First Name equals that of original", returnedContact.getFirstName(),            "Caspar" );
			assertEquals( "Subscribed contact Last Name equals that of original",  returnedContact.getLastName(),             "Gomez" );	
			assertEquals( "Subscribed contact Personal eMail is updated",          returnedContact.getEmailAddressPersonal(), "a.cockroach@scarface.com" );	
		}
		
		/*
		UserContactSubscription subscription2 = new UserContactSubscription( testUser, contact2, approvalDate );
		db.addUserContactSubscription( subscription2 );
		*/
	}
}
