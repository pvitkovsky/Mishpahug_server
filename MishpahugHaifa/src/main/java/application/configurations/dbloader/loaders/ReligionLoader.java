package application.configurations.dbloader.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import application.configurations.dbloader.DB_test_loader_fixed;
import application.configurations.dbloader.LoaderDependencies;
import application.entities.UserEntity;
import application.entities.properties.ReligionEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * Loads Religion
 */
@Slf4j
public class ReligionLoader implements ILoader {

	@Autowired 
	LoaderDependencies data;
	
	private BufferedReader br;

	public ReligionLoader(BufferedReader br) {
		this.br = br;
	}
	
	@Override
	public void load() {
		try {
			Collection<UserEntity> users = this.data.userRepository.findAll();
			for (UserEntity user : users) {
				user.setReligion(null);
			}
			this.data.religionRepository.deleteAll();
			this.data.religionRepository.flush();
			// do we need flush here?
			// need
			String detail;
			while ((detail = br.readLine()) != null) {
				ReligionEntity religionEntity = new ReligionEntity();
				religionEntity.setName(detail);
				log.debug("DBLoadTest -> ReligionLoader -> " + religionEntity);
				this.data.religionRepository.save(religionEntity);
			}
			log.debug("DBLoadTest -> ReligionLoader -> In repository " + this.data.religionRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}