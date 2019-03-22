package application.dto;

import application.entities.AddressEntity;
import application.entities.EventEntity;
import application.entities.EventGuestRelation;
import application.entities.values.PictureValue;
import application.utils.EncrytedPasswordUtils;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ToString(exclude = { "nickname", "firstName", "lastName", "phoneNumber", "eMail"})
@EqualsAndHashCode(of = { "eMail" })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder 
public class UserDTO {
	private String firstName;

	private String lastName;

	private String phoneNumber;

	private String eMail;

	private String userName;

	private String encrytedPassword;//????

}
