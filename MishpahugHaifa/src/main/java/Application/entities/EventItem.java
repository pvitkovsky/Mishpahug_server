package Application.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eventlist")
public class EventItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate date;
	private LocalTime time;
	private String name;
	private Integer userId;
	private Integer kichenTypeId;
	@Enumerated(EnumType.STRING)
	private EventStatus Status;

	public enum EventStatus {
		CREATED, PENDING, COMPLETE, CANCELED
	}

	public EventItem() {
	}

	public EventItem(Integer id, LocalDate date, LocalTime time, String name, Integer userId, Integer kichenTypeId) {
		this.id = id;
		this.date = date;
		this.time = time;
		this.name = name;
		this.userId = userId;
		this.kichenTypeId = kichenTypeId;
	}

	@Override
	public String toString() {
		return "EventItem [id=" + id + ", date=" + date + ", time=" + time + ", name=" + name + ", userId=" + userId
				+ ", kichenTypeId=" + kichenTypeId + ", Status=" + Status + "]";
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getKichenTypeId() {
		return kichenTypeId;
	}

	public void setKichenTypeId(Integer kichenTypeId) {
		this.kichenTypeId = kichenTypeId;
	}

	public EventStatus getStatus() {
		return Status;
	}

	public void setStatus(EventStatus status) {
		Status = status;
	}
}
