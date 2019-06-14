package application.configurations.dbloader.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import application.configurations.dbloader.DB_test_loader_fixed;
import application.configurations.dbloader.LoaderDependencies;
import application.entities.UserEntity;
import application.entities.properties.GenderEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * Loads gender
 */
@Slf4j
public class GenderLoader implements ILoader {

	@Autowired 
	LoaderDependencies data;
	
	private BufferedReader br;

	public GenderLoader(BufferedReader br) {
		this.br = br;
	}
	
	@Override
	public void load() {
		try {
			Collection<UserEntity> users = this.data.userRepository.findAll();
			for (UserEntity user : users) {
				user.setGender(null);
			}
			this.data.genderRepository.deleteAll();
			this.data.genderRepository.flush();
			//do we need flush here?
			// need
			String detail;
			while ((detail = br.readLine()) != null) {
				GenderEntity genderEntity = new GenderEntity();
				genderEntity.setName(detail);
				log.debug("DBLoadTest -> GenderLoader -> " + genderEntity);
				this.data.genderRepository.save(genderEntity);
			}
			log.debug("DBLoadTest -> GenderLoader -> In repository " + this.data.genderRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}