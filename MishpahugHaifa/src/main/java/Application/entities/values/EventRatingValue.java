package Application.entities.values;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

import Application.entities.EventItem;
import Application.entities.UserItem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode

@Embeddable
public class EventRatingValue {
	
	private EventItem eventItemOwner;
	@ElementCollection
	private Map<UserItem, Integer> ratings;

	public EventRatingValue(Map<UserItem, Integer> rating) {
		super();
		this.ratings = rating;
	}
}
