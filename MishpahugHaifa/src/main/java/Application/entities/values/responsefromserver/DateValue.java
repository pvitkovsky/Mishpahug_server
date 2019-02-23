package Application.entities.values.responsefromserver;

public class DateValue {
    private String iso;
    private DateTimeValue datetime;

    public DateValue(String iso, DateTimeValue datetime) {
        this.iso = iso;
        this.datetime = datetime;
    }

    public DateValue() {
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public DateTimeValue getDatetime() {
        return datetime;
    }

    public void setDatetime(DateTimeValue datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "DateValue{" +
                "iso='" + iso + '\'' +
                ", datetime=" + datetime +
                '}';
    }
}
