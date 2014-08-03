import java.util.UUID;

/**
 * 
 *  UserContactOwnership.java
 * @author Gavin Leo-Rhynie
 * 
 * Copyright Apr 13, 2014 
 */

/**
 * @author Gavin Leo-Rhynie
 *
 */


public class UserContactOwnership {

	private UUID userUniqueId;
	private UUID contactUniqueId;
	
	public UserContactOwnership( UUID userId, UUID contactId ) {
		this.userUniqueId    = userId;
		this.contactUniqueId = contactId;
	}
}
