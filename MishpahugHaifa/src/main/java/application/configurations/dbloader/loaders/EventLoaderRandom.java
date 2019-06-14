package application.configurations.dbloader.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.EventEntity;
import application.entities.UserEntity;
import application.entities.properties.AddressEntity;
import application.entities.properties.HoliDayEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * Loads events and sets owners randomly
 */
@Slf4j
public class EventLoaderRandom implements ILoader {

	@Autowired
	LoaderDependencies data;
	
	private BufferedReader br;

	public EventLoaderRandom(BufferedReader br) {
		this.br = br;
	}
	
	@Override
	public void load() {
		try {
			this.data.logsDataRepository.deleteAll();
			this.data.eventRepository.findAll().forEach(EventEntity::putIntoDeletionQueue);
			this.data.eventRepository.deleteAll();
			this.data.eventRepository.flush();
			List<AddressEntity> addressEntityList = this.data.addressRepository.findAll();
			Integer addressEntityListCount = addressEntityList.size() - 1;
			List<HoliDayEntity> holiDayEntityList = this.data.holyDayRepository.findAll();
			Integer holiDayEntityListCount = holiDayEntityList.size() - 1;
			String detail;
			List<UserEntity> userEntityList = this.data.userRepository.findAll();
			Random r = new Random();
			while ((detail = br.readLine()) != null) {
				String[] eventAttributes = detail.split(",");
				Integer index = r.nextInt(userEntityList.size());
				UserEntity owner = userEntityList.get(index);
				EventEntity event = new EventEntity(owner,
						LocalDate.parse(eventAttributes[0].replaceAll("/", "-"), DateTimeFormatter.ISO_DATE),
						LocalTime.parse(eventAttributes[1], DateTimeFormatter.ISO_LOCAL_TIME));
				event.setNameOfEvent(eventAttributes[2]);
				event.setHoliDay(holiDayEntityList.get(r.nextInt(holiDayEntityListCount)));
				event.setAddressEntity(addressEntityList.get(r.nextInt(addressEntityListCount)));
				log.warn("DBLoadTest -> EventLoaderRandom -> " + event);
			}
			br.close();
			log.debug("DBLoadTest -> EventLoaderRandom -> In repository " + this.data.eventRepository.findAll().size()
					+ " records");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}