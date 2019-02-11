package Application.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eventrating")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
public class EventRatingItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private EventItem eventItem;
	@ElementCollection
	private List<Integer> rating;

	public EventRatingItem(EventItem eventItem) {
		super();
		this.eventItem = eventItem;
		this.rating = new ArrayList<Integer>();
	}

	public EventRatingItem(EventItem eventItem, List<Integer> rating) {
		super();
		this.eventItem = eventItem;
		this.rating = rating;
	}
}
