package Application.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="logsdata")
public class LogsDataItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private LocalTime time;
    private Integer user_id;
    private String action;
    private String data;

    public LogsDataItem(LocalDate date, LocalTime time, Integer user_id, String action, String data) {
        this.date = date;
        this.time = time;
        this.user_id = user_id;
        this.action = action;
        this.data = data;
    }

    public LogsDataItem() {
    }

    @Override
    public String toString() {
        return "LogsDataItem{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", user_id=" + user_id +
                ", action='" + action + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
