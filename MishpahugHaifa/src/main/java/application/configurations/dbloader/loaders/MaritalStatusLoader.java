package application.configurations.dbloader.loaders;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.UserEntity;
import application.entities.properties.MaritalStatusEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;

/**
 * Loads maritalstatus
 */
@Slf4j
public class MaritalStatusLoader implements ILoader {

	@Autowired 
	LoaderDependencies data;
	
	private BufferedReader br;

	public MaritalStatusLoader(BufferedReader br) {
		this.br = br;
	}
	
	@Override
	public void load() {
		Collection<UserEntity> users = this.data.userRepository.findAll();
		for (UserEntity user : users) {
			user.setMaritalStatus(null);
		}
		try {
			this.data.maritalStatusRepository.deleteAll();
			this.data.maritalStatusRepository.flush();
			//do we need flush here?
			// need
			String detail;
			while ((detail = br.readLine()) != null) {
				MaritalStatusEntity maritalStatusEntity = new MaritalStatusEntity();
				maritalStatusEntity.setName(detail);
				this.data.maritalStatusRepository.save(maritalStatusEntity);
			}
			log.debug("DBLoadTest -> MaritalStatusLoader -> In repository " + this.data.maritalStatusRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}