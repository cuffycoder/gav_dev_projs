/**
 * 
 *  SynchableBase.java
 * @author Gavin Leo-Rhynie
 * 
 * Copyright Mar 23, 2014 
 */
import java.util.UUID;

/*
 * SynchableBase: abstract class that handles generation of unique Id for all
 * synchable classes. SynchableContact inherits from this
 */
public abstract class SynchableBase {
	
		private UUID uniqueID;
		
		public SynchableBase() {
			this.uniqueID      = UUID.randomUUID();
		}
		
		public String getSynchableId() {
			return this.uniqueID.toString();
	    }
		
}
