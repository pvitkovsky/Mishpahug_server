package application.configurations.dbloader.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import application.configurations.dbloader.LoaderDependencies;
import application.models.event.EventEntity;
import application.models.event.EventRepository;
import application.models.user.UserEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * Loads events and sets owners randomly
 */
@Slf4j
@Transactional
public class EventLoaderFixed implements ILoader {

	@Autowired
	LoaderDependencies data;
	
	private BufferedReader br;

	public EventLoaderFixed(BufferedReader br) {
		this.br = br;
	}
	
	@Override
	public void load() {
		try {
			this.data.eventRepository.findAll().forEach(EventEntity::putIntoDeletionQueue);
			this.data.eventRepository.deleteAll();
			this.data.eventRepository.flush();
			String detail;
			List<UserEntity> userEntityList = this.data.userRepository.findAll();
			while ((detail = br.readLine()) != null) {
				String[] eventAttributes = detail.split(",");		
				Integer index = Integer.parseInt(eventAttributes[3]);	
				UserEntity owner = userEntityList.get(index);
				EventEntity event = new EventEntity(owner.getId(),
						LocalDate.parse(eventAttributes[0].replaceAll("/", "-"), DateTimeFormatter.ISO_DATE),
						LocalTime.parse(eventAttributes[1], DateTimeFormatter.ISO_LOCAL_TIME));
				event.setNameOfEvent(eventAttributes[2]);
				this.data.eventRepository.save(event);
				log.warn("DBLoadTest -> EventLoaderFixed -> " + event);
			}
			br.close();
			log.warn("DBLoadTest -> EventLoaderFixed -> In repository " + this.data.eventRepository.findAll().size()
					+ " records");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}