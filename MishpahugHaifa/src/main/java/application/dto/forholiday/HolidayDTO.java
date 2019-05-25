package application.dto.forholiday;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HolidayDTO {
    private String name;
    private String description;
    private String date;
    private String locations;
    private String states;
    private String[] type;
}
