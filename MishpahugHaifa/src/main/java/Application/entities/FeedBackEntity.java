package Application.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="feedback")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
public class FeedBackEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length=255)
    @NotNull
    private String comment;

    private LocalDateTime dateTime;

    @Range(min = 1, max = 5)
    private Integer rating;

    @ManyToOne
    @JsonBackReference
    private UserEntity userItem;

    @ManyToOne
    @JsonBackReference
    private EventEntity eventItem;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedBackEntity that = (FeedBackEntity) o;
        return id.equals(that.id) &&
                comment.equals(that.comment) &&
                dateTime.equals(that.dateTime) &&
                rating.equals(that.rating) &&
                userItem.equals(that.userItem) &&
                eventItem.equals(that.eventItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment, dateTime, rating, userItem, eventItem);
    }
}

