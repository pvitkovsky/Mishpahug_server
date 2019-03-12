package Application.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="passwords")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"userEntity", "password"})
public class PasswordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userEntityId;

    private String password;
}
