package Application.entities.values;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Embeddable
public class PictureValue {

	//TODO: Bidirectional  
	//private UserItem userItemOwner;
	
	private String data;
    

}
