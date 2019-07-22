package application.dto;

import java.time.LocalDate;
import java.util.Map;

import application.models.user.UserEntity;
import application.utils.choices.ChoiceCategories;
import application.utils.choices.ChoiceStore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO { //TODO: optional dto;
     
    private Integer id; 
    
    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String eMail;

    private String userName;

    private LocalDate dayOfBirth;
    
    private String encryptedPassword;
    
    private String confirmedPassword;
    
    Map<ChoiceCategories, ChoiceStore> choices; //TODO maybe all UserChoices object?

    public UserDTO(UserEntity user) {
    	super();
        this.id = user.getId();
    	this.firstName = user.getFirstName();
    	this.lastName = user.getLastName();
    	this.phoneNumber = user.getPhoneNumber();
    	this.eMail = user.getEMail();
    	this.userName = user.getUserName();
    	this.dayOfBirth = user.getDateOfBirth();
    	this.encryptedPassword = user.getEncrytedPassword();
    	this.confirmedPassword = this.encryptedPassword;
    	this.choices = user.getChoices().getAll();
    }
}
