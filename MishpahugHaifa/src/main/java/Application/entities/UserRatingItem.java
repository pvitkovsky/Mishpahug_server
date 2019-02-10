package Application.entities;

import lombok.*;

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
@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRatingItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@ElementCollection
	private List<Integer> rating;


}
