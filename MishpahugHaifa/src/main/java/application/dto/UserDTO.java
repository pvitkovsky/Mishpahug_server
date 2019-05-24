package application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

import application.entities.UserEntity;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
     
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

    public UserDTO(UserEntity user) {
    	super();
        this.id = user.getId();
    	this.firstName = user.getFirstName();
    	this.lastName = user.getLastName();
    	this.phoneNumber = user.getPhoneNumber();
    	this.eMail = user.getEMail();
    	this.userName = user.getUserName();
    	this.dayOfBirth = user.getDateOfBirth();
    	this.gender = user.getGender().toString();
    	this.kichenType = user.getKitchenType().toString();
    	this.religion = user.getReligion().toString();
    	this.maritalStatus = user.getMaritalStatus().toString();
    }

}
