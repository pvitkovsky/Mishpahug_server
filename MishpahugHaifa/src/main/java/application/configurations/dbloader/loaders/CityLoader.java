package application.configurations.dbloader.loaders;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.EventEntity;
import application.entities.UserEntity;
import application.entities.properties.CityEntity;
import application.entities.properties.CountryEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;

/**
 * Loads city
 */
@Slf4j
public class CityLoader implements ILoader {
	
	@Autowired 
	LoaderDependencies data;
	
	private BufferedReader br;

	public CityLoader(BufferedReader br) {
		this.br = br;
	}

	@Override
	public void load() {
		try {
			Collection<UserEntity> users = this.data.userRepository.findAll();
			for (UserEntity user : users) {
				user.setAddressEntity(null);
			}
			Collection<EventEntity> events = this.data.eventRepository.findAll();
			for (EventEntity event : events) {
				event.setAddressEntity(null);
			}
			this.data.countryRepository.deleteAll(); // cascade deleting cities and addresses
			this.data.countryRepository.flush();
			String detail;
			CountryEntity countryEntity = new CountryEntity();
			countryEntity.setName("Israel");
			this.data.countryRepository.save(countryEntity);
			while ((detail = br.readLine()) != null) {
				CityEntity cityEntity = new CityEntity();
				cityEntity.setName(detail);
				countryEntity.addCity(cityEntity);
				// no need to save city explicitly, as its save is cascaded from Country;
			}
			log.debug("DBLoadTest -> CityLoader -> In repository " + this.data.cityRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}