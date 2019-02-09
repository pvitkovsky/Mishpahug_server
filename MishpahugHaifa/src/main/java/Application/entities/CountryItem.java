package Application.entities;

import javax.persistence.*;

@Entity
@Table(name="country")
public class CountryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public CountryItem(String name) {
        this.name = name;
    }

    public CountryItem() {
    }

    @Override
    public String toString() {
        return "CountryItem{" +
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

