package application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String eMail;

    private String userName;

    private LocalDate dayOfBirth;

    private String encrytedPassword;//????

    private String confirmedPassword;//????

}
