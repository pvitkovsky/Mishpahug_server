package Application.entities.randomgeneration;

import java.util.Random;

import Application.entities.UserEntity;

public class RandomEntities {
	
    static Random random = new Random();

    public static final String[] FIRSTNAMES =
            {"John", "Robert", "Jackob", "Thomas", "Edward", "William", "Henry", "George", "Gregory", "Charles"};
    
    public static final String[] LASTNAMES =
            {"Johnson", "Smith", "Lee", "Linn", "Fox", "Simpson", "Ford", "Piper", "Moor", "Philips"};
    
    public static String randomNickName() {
    	return null;
    }
    
    public static String randomPhoneNumber() {
    	return null;
    }
    
    public static String randomEmail() {
    	return null;
    }
    
    public static UserEntity randomUserEntity() {
    	return UserEntity.builder()
    			.nickname(randomNickName())
    			.build();
    }

    
}

	

