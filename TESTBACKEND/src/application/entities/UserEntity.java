package entities;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class UserEntity {
	private Integer id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String eMail;
	private String userName;
	private String encrytedPassword;
	private LocalDate dateOfBirth;
}
