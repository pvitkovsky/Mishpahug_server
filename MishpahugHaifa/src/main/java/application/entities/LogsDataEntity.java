package application.entities;


import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//TODO: hashcode, unique, toString
@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@ToString(exclude = "userActor")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class LogsDataEntity {

	@Id
	@SequenceGenerator(name="buiness_logs_seq", sequenceName="logs_data_entity_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="buiness_logs_seq")
	private Long id; 
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_actor")
	//TODO: unidirectional safety check;
	private UserEntity userActor;
	
	@Column(name = "date", nullable = false)
	private LocalDate date;

	@Column(name = "time", nullable = false)
	private LocalTime time;

	@Column(name = "text_description")
	private String description;


}
