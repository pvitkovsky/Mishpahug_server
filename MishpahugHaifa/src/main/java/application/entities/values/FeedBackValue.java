package application.entities.values;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"comment", "dateTime", "rating"})
public class FeedBackValue {

    @Column(length = 255)
    @NotNull
    private String comment;

    private LocalDateTime dateTime;

    @Range(min = 1, max = 5)
    private Integer rating;

}

