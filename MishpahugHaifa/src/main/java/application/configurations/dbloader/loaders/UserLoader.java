package application.configurations.dbloader.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.UserEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * Loads users
 */
@Slf4j
@Transactional
public class UserLoader implements ILoader {

	@Autowired 
	LoaderDependencies data;

	private BufferedReader br;

	public UserLoader(BufferedReader br) {
		this.br = br;
	}
	
	@Override
	public void load() {
		try {
			this.data.userRepository.findAll().forEach(UserEntity::putIntoDeletionQueue);
			this.data.userRepository.deleteAll();
			this.data.userRepository.flush();
			//do we need flush here?
			// need
			// https://stackoverflow.com/questions/49595852/deleteall-in-repository-randomly-causes-constraintviolationexception
			String detail;
			while ((detail = br.readLine()) != null) {
				String[] data = detail.split("!");
				UserEntity user = new UserEntity(data[0].split("@")[0], data[0]);
				user.setFirstName(data[1]);
				user.setLastName(data[2]);
				user.setDateOfBirth(LocalDate.parse(data[3]));
				Random ran = new Random();
				user.setPhoneNumber(data[4]+ ran.nextInt(9)+ ran.nextInt(9)+ ran.nextInt(9)+ ran.nextInt(9)+ ran.nextInt(9)+ ran.nextInt(9)+ ran.nextInt(9));
				user.setEncrytedPassword(DigestUtils.md5Hex(data[0].split("@")[0]));
				user.activate();
				log.debug("DBLoadTest -> UserLoader -> userentity = " + user);
				this.data.userRepository.save(user);
			}
			log.debug("DBLoadTest -> UserLoader -> In repository " + this.data.userRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}