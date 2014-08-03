import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * 
 *  SynchableDatabase.java
 * @author Gavin Leo-Rhynie
 * 
 * Copyright Mar 30, 2014 
 */

/**
 * @author Gavin Leo-Rhynie
 *
 */
/**
 * @author Gavin
 *
 */
class SynchableDatabase {

	private static SynchableDatabase synchableDb = new SynchableDatabase();
	
	/*
	 * private constructor to force singleton
	 */
	private SynchableDatabase() {};
	
	public static SynchableDatabase getCurrentDatabase() { return synchableDb; }
	
	/*
	 * UniqueId -> User, Contact, Subscription maps
	 */
	private HashMap<UUID,User> userList                = new HashMap<UUID,User>();
	private HashMap<UUID,SynchableContact> contactList = new HashMap<UUID,SynchableContact>();
	private HashMap<UUID,UserContactSubscription> subscriptionList = new HashMap<UUID,UserContactSubscription>();
	
	/*
	 * Maps for subscription indicies
	 */
	private HashMap<UUID, HashMap<UUID, UUID>> userSubscriptionIndex    = new HashMap<UUID, HashMap<UUID, UUID>>();
	private HashMap<UUID, HashMap<UUID, UUID>> contactSubscriptionIndex = new HashMap<UUID, HashMap<UUID, UUID>>();
	
	/**
	 * 
	 * @param uniqueID
	 * @return
	 */
	public User getUserFromID( UUID uniqueID ) {
		if( !this.userList.containsKey(uniqueID))
		{
			return null;
		}
		else
		{
			// return a copy so that the caller cannot mess with the official user object
			return this.userList.get( uniqueID ).clone();
		}
	}
	
	public void addUser( User user ) {
		// make a copy
		User userCopy = user.clone();
		
		this.userList.put( userCopy.getUniqueId(), userCopy );
		return;
	}
	
	public void addContact( SynchableContact contact ) {
				// make a copy
		SynchableContact contactCopy = contact.clone();
		this.contactList.put(contactCopy.getUniqueId(), contactCopy );
	}
	
	public SynchableContact getContactFromID( UUID uniqueID ) {
		if( !contactList.containsKey( uniqueID ))
		{
			return null;
		}
		else
		{
			return contactList.get( uniqueID ).clone();
		}
	}
	
	
	public void addUserContactSubscription( UserContactSubscription sub ) {
		// make a defensive copy of the subscription
		UserContactSubscription newSubscription = sub.clone();
		
		UUID subscriptionUniqueId = newSubscription.getSubscriptionUniqueId();
		UUID userUniqueId         = newSubscription.getUserUniqueId();
		UUID contactUniqueId      = newSubscription.getContactUniqueId();
		

		// add the subscription to the overall list
		this.subscriptionList.put( subscriptionUniqueId, newSubscription );
		
		// Update the User->Subscription index
		if( !userSubscriptionIndex.containsKey( userUniqueId )) {
			
			HashMap<UUID,UUID> contactSubscriptionMapForUser = new HashMap<UUID,UUID>();
			contactSubscriptionMapForUser.put( contactUniqueId, subscriptionUniqueId );
			userSubscriptionIndex.put( userUniqueId, contactSubscriptionMapForUser );
		}
		else
		{
			HashMap<UUID,UUID> updatedSubscriptionMapForUser = userSubscriptionIndex.get( userUniqueId );
			updatedSubscriptionMapForUser.put( contactUniqueId, subscriptionUniqueId );
		}
		
		// Update the Contact->Subscription index
		if( !contactSubscriptionIndex.containsKey( contactUniqueId )) {
			HashMap<UUID,UUID> userSubscriptionMapForUser = new HashMap<UUID,UUID>();
			userSubscriptionMapForUser.put( userUniqueId, subscriptionUniqueId );
			contactSubscriptionIndex.put( contactUniqueId, userSubscriptionMapForUser );	
		}
		else
		{
			HashMap<UUID,UUID> updatedSubscriptionMapForContact = contactSubscriptionIndex.get( contactUniqueId );
			updatedSubscriptionMapForContact.put( userUniqueId, subscriptionUniqueId );
		}
	}
	
	public HashSet<SynchableContact> getSubscribedContactsForUser( User user ) {
		HashSet<SynchableContact> subscribedContacts = new HashSet<SynchableContact>();
		
		if( !userSubscriptionIndex.containsKey( user.getUniqueId() )) 
			return subscribedContacts;
		
		Set<UUID> subscribedContactIds = userSubscriptionIndex.get( user.getUniqueId() ).keySet();
		
		for( UUID contactId : subscribedContactIds ) {
			SynchableContact contactForId = this.getContactFromID( contactId );
			
			if( contactForId != null ) {
				subscribedContacts.add( contactForId.clone() );
			}
		}
		
		return subscribedContacts;
	}
	
	// TODO: add a delete subscription method
	// TODO: add a check integrity method
	// TODO: add a reset for testing only
}
