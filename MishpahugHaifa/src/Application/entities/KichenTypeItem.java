package Application.entities;

import javax.persistence.*;

@Entity
@Table(name="kichentype")
public class KichenTypeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public KichenTypeItem(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public KichenTypeItem() {
    }

    @Override
    public String toString() {
        return "KichenTypeItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
