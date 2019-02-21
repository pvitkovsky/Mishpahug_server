package Application.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="kichentype")
//@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@ToString

public class KichenTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    public KichenTypeEntity(String name) {
        this.name = name;
    }

    public KichenTypeEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KichenTypeEntity that = (KichenTypeEntity) o;
        return id.equals(that.id) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
