package Application.entities;

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
@Table(name = "userrating")
public class UserRatingItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@ElementCollection
	private List<Integer> rating;

	public UserRatingItem() {
	}

	public UserRatingItem(Integer userId) {
		super();
		this.userId = userId;
		this.rating = new ArrayList<Integer>();
	}

	public UserRatingItem(Integer userId, List<Integer> rating) {
		super();
		this.userId = userId;
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "UserRatingItem [userId=" + userId + ", rating=" + rating + "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Integer> getRating() {
		return Collections.synchronizedList(rating);
	}

	public void setRating(List<Integer> rating) {
		this.rating = rating;
	}

}
