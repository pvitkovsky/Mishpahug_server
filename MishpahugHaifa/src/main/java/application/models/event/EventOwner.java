package application.models.event;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "owner_id")),
})
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Embeddable
public class EventOwner {

	private Integer id;

	public EventOwner(@NotNull Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	} 

}
