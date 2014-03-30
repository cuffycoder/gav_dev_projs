/**
 * 
 *  User.java
 * @author Gavin Leo-Rhynie
 * 
 * Copyright Mar 23, 2014 
 */

import java.util.UUID;

public class User {

	private UUID uniqueID;
	private String userName;
	
	public User( String userName ) {
		this.uniqueID = UUID.randomUUID();
		this.userName = userName;
	}

	/**
	 * @return the unique userId
	 */
	public String getUserId() {
		return uniqueID.toString();
	}

	/**
	 * @param uniqueID the uniqueID to set
	 */
	public void setUniqueID(UUID uniqueID) {
		this.uniqueID = uniqueID;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
