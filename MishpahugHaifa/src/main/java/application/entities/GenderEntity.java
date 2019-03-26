package application.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "gender",  uniqueConstraints = {
        @UniqueConstraint(columnNames = { "name" })})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "name" })
@ToString
public class GenderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
}
