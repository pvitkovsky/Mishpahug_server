package Application.entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode

@Embeddable
public class EventRatingValue {
	
	@ElementCollection
	private List<Integer> rating;

	public EventRatingValue(List<Integer> rating) {
		super();
		this.rating = rating;
	}
}
