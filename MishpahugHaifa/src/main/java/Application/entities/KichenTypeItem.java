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
public class KichenTypeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public KichenTypeItem(String name) {
        this.name = name;
    }


}
