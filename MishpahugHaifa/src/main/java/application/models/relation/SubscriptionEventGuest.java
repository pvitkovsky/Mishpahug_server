package application.models.relation;

import application.models.event.EventEntity;
import application.models.user.UserEntity;

/**
 * Bounded context of Event and User on Relation
 */
public class SubscriptionEventGuest {
	
	private Integer SubscriptionEventOwnerId;
	
	private Integer SubscriptionEventGuestId;
	
	protected void checkEventAndID() {
		
		if( SubscriptionEventOwnerId == null ) { //TODO: events should always be consistent!
			throw new IllegalStateException("Trying to create a subscription for event that is in the inconsistent state");
		}
		if (SubscriptionEventOwnerId.equals(SubscriptionEventGuestId)) {
			throw new IllegalArgumentException("Trying to subscribe to the owned event");
		}

	}

	public SubscriptionEventGuest(EventEntity event, UserEntity guest) {
		super();
		SubscriptionEventOwnerId = event.getUserEntityOwner().getId();
		SubscriptionEventGuestId = guest.getId();
	}
	
	

}
