import java.util.Date;
import java.util.UUID;

/**
 * 
 *  UserContactSubscription.java
 * @author Gavin Leo-Rhynie
 * 
 * Copyright May 3, 2014 
 */

/**
 * @author Gavin Leo-Rhynie
 *
 */
public class UserContactSubscription {

	private UUID userId;
	private UUID contactId;
	private UUID subscriptionId;
	private Date approvalDate; // placeholder for other meta data
	
	public UserContactSubscription( User user, SynchableContact contact, Date approvalDate ) {
		
		this.userId = user.getUniqueId();
		this.contactId = contact.getUniqueId();
		this.subscriptionId = UUID.randomUUID();
		this.approvalDate = approvalDate;
	}
	
	public UserContactSubscription( UUID userId, UUID contactId, Date approvalDate ) {
		this.userId = userId;
		this.contactId = contactId;
		this.subscriptionId = UUID.randomUUID();
		this.approvalDate = approvalDate;
	}
	
	public UUID getSubscriptionUniqueId() {
		return this.subscriptionId;
	}
	public UUID getUserUniqueId() {
		return this.userId;
	}
	
	public UUID getContactUniqueId() {
		return this.contactId;
	}
	
	public Date getApprovalDate() {
		return this.approvalDate;
	}
	
	public UserContactSubscription clone() {
		UserContactSubscription copy = new UserContactSubscription( this.userId, this.contactId, this.approvalDate );
		copy.subscriptionId          = this.subscriptionId;
		return copy;
	}
}
