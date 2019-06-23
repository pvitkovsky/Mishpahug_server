package application.models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user_session")
public class UserSession {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "isValid", nullable = false)
    private Boolean isValid;

    @Column(name = "ip", nullable = false)
    private String ip;

    @Column(name = "useragent", nullable = false)
    private String userAgent;

    @Column(name = "date", nullable = false)
    private LocalDate localDate;

    @Column(name = "time", nullable = false)
    private LocalTime localTime;


}
