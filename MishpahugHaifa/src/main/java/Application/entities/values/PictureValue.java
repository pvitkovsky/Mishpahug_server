package Application.entities.values;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Embeddable
public class PictureValue {

	//TODO: Bidirectional  
	//private UserEntity userItemOwner;
	@Column(name = "data")
	private Blob data;
    

}
