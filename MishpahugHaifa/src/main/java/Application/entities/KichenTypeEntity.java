package Application.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="kichentype")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class KichenTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    public KichenTypeEntity(String name) {
        this.name = name;
    }


}
