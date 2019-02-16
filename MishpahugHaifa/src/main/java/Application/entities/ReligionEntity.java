package Application.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="religion")
@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReligionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;


}
