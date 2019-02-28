package Application.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="religion")
@ToString
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReligionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReligionEntity that = (ReligionEntity) o;
        return id.equals(that.id) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
