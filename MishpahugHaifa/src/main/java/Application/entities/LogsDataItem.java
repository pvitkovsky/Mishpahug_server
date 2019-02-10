package Application.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogsDataItem {
	
    private Integer id;
    private LocalDate date;
    private LocalTime time;
    private Integer user_id; //todo: one2one;  
    private UserActions action;
    private String description;
    
    public enum UserActions {
    	STATUS_CHANGE,
    	EMAIL_CHANGE,
    	ADDRESS_CHANGE,
    }
}
