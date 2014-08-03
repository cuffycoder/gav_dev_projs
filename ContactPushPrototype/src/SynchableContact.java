import java.util.UUID;

/**
 * 
 *  SynchableContact.java
 * @author Gavin Leo-Rhynie
 * 
 * Copyright Mar 23, 2014 
 */

/**
 * @author Gavin Leo-Rhynie
 *
 */
public class SynchableContact extends SynchableBase {
	private UUID   uniqueId;
	private String firstName;
	private String lastName;
	private String emailAddressWork;
	private String emailAddressPersonal;
	private String phoneMobile;
	private String phoneWork;
	
	public SynchableContact() {
		this.uniqueId  = UUID.randomUUID();
		this.firstName = "";
		this.lastName = "";
		this.emailAddressPersonal = "";
		this.emailAddressWork = "";
	}


	public UUID getUniqueId() {
		return uniqueId;
	}
	
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmailAddressWork() {
		return emailAddressWork;
	}


	public void setEmailAddressWork(String emailAddressWork) {
		this.emailAddressWork = emailAddressWork;
	}


	public String getEmailAddressPersonal() {
		return emailAddressPersonal;
	}


	public void setEmailAddressPersonal(String emailAddressPersonal) {
		this.emailAddressPersonal = emailAddressPersonal;
	}


	public String getPhoneMobile() {
		return phoneMobile;
	}


	public void setPhoneMobile(String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}


	public String getPhoneWork() {
		return phoneWork;
	}


	public void setPhoneWork(String phoneWork) {
		this.phoneWork = phoneWork;
	}
	
	public SynchableContact clone() {
		SynchableContact copy = new SynchableContact();
		
		copy.emailAddressPersonal = this.emailAddressPersonal;
		copy.emailAddressWork     = this.emailAddressWork;
		copy.firstName            = this.firstName;
		copy.lastName			  = this.lastName;
		copy.phoneMobile          = this.phoneMobile;
		copy.phoneWork            = this.phoneWork;
		copy.uniqueId             = this.uniqueId;
		
		return copy;
	}
}

