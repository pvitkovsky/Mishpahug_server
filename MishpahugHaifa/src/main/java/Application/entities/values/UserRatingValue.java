package Application.entities.values;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

import Application.entities.UserItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class UserRatingValue {

	//TODO: Bidirectional 
	//private EventItem eventItemOwner;
	@ElementCollection
	private Map<UserItem, Integer> rating;


}
