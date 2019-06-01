package application.entities.template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Template", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Slf4j
public class TemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "picture")
    private String picture;

    @ElementCollection
    @CollectionTable
    @Column(name = "items")
    private Set<XYTextValue> Items = new HashSet<>();

    public TemplateEntity() {
    }
}
