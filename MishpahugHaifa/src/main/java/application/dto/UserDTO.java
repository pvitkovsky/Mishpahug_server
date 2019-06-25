package application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.models.user.UserEntity;
import application.models.user.UserEntity.UserChoiceCategories;
import application.utils.choices.ChoiceStore;
import application.utils.choices.IChoiceCategories;

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

    private String gender;
    
    private String kichenType;
    
    private String religion;
    
    private String maritalStatus;
    
    private String encryptedPassword;

    private String confirmedPassword;

    private List<String> subscriptions;
    
    Map<UserChoiceCategories, ChoiceStore> choices; 

    public UserDTO(UserEntity user) {
    	super();
        this.id = user.getId();
    	this.firstName = user.getFirstName();
    	this.lastName = user.getLastName();
    	this.phoneNumber = user.getPhoneNumber();
    	this.eMail = user.getEMail();
    	this.userName = user.getUserName();
    	this.dayOfBirth = user.getDateOfBirth();
    	this.confirmedPassword = user.getEncrytedPassword();
    	this.choices = user.getChoices().getAll();
    }
}
