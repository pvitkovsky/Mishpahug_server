package application.entities.randomgeneration;

import application.entities.UserEntity;
import net.bytebuddy.utility.RandomString;

import java.util.Random;

public class RandomEntities {

    public static final String[] FIRSTNAMES =
            {"John", "Robert", "Jackob", "Thomas", "Edward", "William", "Henry", "George", "Gregory", "Charles"};
    public static final String[] LASTNAMES =
            {"Johnson", "Smith", "Lee", "Linn", "Fox", "Simpson", "Ford", "Piper", "Moor", "Philips"};
    static Random random = new Random();
    static RandomString randStr = new RandomString(8);

    public static String randomNickNameFromSet() {
        return FIRSTNAMES[random.nextInt(RandomEntities.FIRSTNAMES.length)];
    }

    public static String randomPhoneNumber() {
        return null;
    }

    public static String randomEmail() {
        return randStr.nextString() + "@" + randStr.nextString() + ".edu";
    }

    public static UserEntity randomUserEntity() {
    	return UserEntity.builder()
    			.userName(randomNickNameFromSet())
    			.eMail(randomEmail())
    			.build();
    }


}

	

