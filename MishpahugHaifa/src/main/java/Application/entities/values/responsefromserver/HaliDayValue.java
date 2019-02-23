package Application.entities.values.responsefromserver;

import java.util.Arrays;

public class HaliDayValue {
    private String name;
    private String description;
    private DateValue date;
    private String[] type;

    public HaliDayValue(String name, String description, DateValue date, String[] type) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateValue getDate() {
        return date;
    }

    public void setDate(DateValue date) {
        this.date = date;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public HaliDayValue() {
    }

    @Override
    public String toString() {
        return "HaliDayValue{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", type=" + Arrays.toString(type) +
                '}';
    }
}
