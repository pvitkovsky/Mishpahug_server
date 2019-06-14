package application.configurations.dbloader.loaders;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.EventEntity;
import application.entities.UserEntity;
import application.entities.properties.KitchenTypeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;

/**
 * Loads kichentype
 */
@Slf4j
public class KichenTypeLoader implements ILoader {

	@Autowired 
	LoaderDependencies data;
	
	private BufferedReader br;

	public KichenTypeLoader(BufferedReader br) {
		this.br = br;
	}
	
	@Override
	public void load() {
		try {
			Collection<UserEntity> users = this.data.userRepository.findAll();
			for (UserEntity user : users) {
				user.setKitchenType(null);
			}
			Collection<EventEntity> events = this.data.eventRepository.findAll();
			for (EventEntity event : events) {
				event.setKitchenType(null);
			}
			this.data.kichenTypeRepository.deleteAll();
			this.data.kichenTypeRepository.flush();
			// do we need flush here?
			// need
			String detail;
			while ((detail = br.readLine()) != null) {
				KitchenTypeEntity kichenTypeEntity = new KitchenTypeEntity();
				kichenTypeEntity.setName(detail);
				log.debug("DBLoadTest -> KichenTypeLoader -> kichenentity = " + kichenTypeEntity);
				this.data.kichenTypeRepository.save(kichenTypeEntity);
			}
			log.debug("DBLoadTest -> KitchenTypeLoader -> In repository " + this.data.kichenTypeRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}