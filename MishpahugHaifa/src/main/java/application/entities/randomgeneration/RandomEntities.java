package application.entities.randomgeneration;

import java.util.Random;

import application.entities.UserEntity;
import net.bytebuddy.utility.RandomString;

public class RandomEntities {
	
    static Random random = new Random();
    static RandomString randStr = new RandomString(8); 

    public static final String[] FIRSTNAMES =
            {"John", "Robert", "Jackob", "Thomas", "Edward", "William", "Henry", "George", "Gregory", "Charles"};
    
    public static final String[] LASTNAMES =
            {"Johnson", "Smith", "Lee", "Linn", "Fox", "Simpson", "Ford", "Piper", "Moor", "Philips"};
    
    public static String randomNickNameFromSet() {
    	return FIRSTNAMES[random.nextInt(RandomEntities.FIRSTNAMES.length)];
    }
    
    public static String randomPhoneNumber() {
    	return null;
    }
    
    public static String randomEmail() {
    	return null;
    }
    
    public static UserEntity randomUserEntity() {
    	return UserEntity.builder() //TODO: email	
    			.build();
    }

    
}

	

