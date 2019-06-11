package application.dto.forholiday;

import lombok.*;

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
