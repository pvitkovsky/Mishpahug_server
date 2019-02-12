package Application.entities.values;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

import Application.entities.UserItem;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode


@Embeddable
public class EventRatingValue {
	
	//TODO: Bidirectional 
	//private EventItem eventItemOwner;
	
	@ElementCollection
	private Map<UserItem, Integer> ratings;

}
