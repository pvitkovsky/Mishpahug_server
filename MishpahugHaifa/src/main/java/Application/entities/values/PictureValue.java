package Application.entities.values;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Embeddable
public class PictureValue {

	//TODO: Bidirectional  
	//private UserEntity userItemOwner;
	@Column(name = "data")
	private Blob data;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PictureValue that = (PictureValue) o;
		return data.equals(that.data);
	}

	@Override
	public int hashCode() {
		return Objects.hash(data);
	}
}
