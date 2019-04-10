package application.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "user_session")
public class UserSession {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userEntity;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "isValid", nullable = false)
    private Boolean isValid;

}
