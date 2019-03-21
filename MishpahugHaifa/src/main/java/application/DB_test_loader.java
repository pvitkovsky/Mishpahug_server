package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.StandardSocketOptions;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import application.entities.EventEntity;
import application.entities.EventGuestRelation;
import application.entities.UserEntity;
import application.repo.EventGuestRepository;
import application.repo.EventRepository;
import application.repo.UserRepository;

/**
 * Load the production DB with integration test data; TODO: make it a Spring profile
 */
@Component
@Transactional
public class DB_test_loader implements CommandLineRunner {



	@Autowired
	UserRepository userRepo;
	@Autowired
	EventRepository eventRepo;
	@Autowired
	EventGuestRepository eventGuestRepo;

	@Override
	public void run(String... args) throws Exception {
		loadTest(MPHEntity.USER);
		loadTest(MPHEntity.EVENT);
		loadTest(MPHEntity.GUESTS);
		eventGuestRepo.findAll().forEach(System.out::println);
	}

	private void loadTest(MPHEntity entity) {

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(entity.dataFile());
		BufferedReader empdtil = new BufferedReader(new InputStreamReader(is));
		long userCount = userRepo.count();
		long eventCount = eventRepo.count();

		switch (entity) {
		case USER: {
			UserLoader loader = new UserLoader(empdtil);
			loader.load();
		}
		case EVENT: {
			EventLoader loader = new EventLoader(empdtil);
			loader.load();
		}
		case GUESTS: { 
			eventGuestRepo.deleteAll();
			UserEntity randomGuest = userRepo.findAll().get(1); //TODO:random;
			for (EventEntity event : eventRepo.findAll()) {
				EventGuestRelation subscription = new EventGuestRelation();
				if(!event.getUserEntityOwner().equals(randomGuest)) {
					subscription.subscribe(randomGuest, event);
				}
			}
		}
		}

	}

	private enum MPHEntity {
		USER {
			public String dataFile() {
				return "data_user.csv";
			}
		},
		EVENT {
			public String dataFile() {
				return "data_event.csv";
			}
		},
		GUESTS {
			public String dataFile() {
				return "data_guests_blank.csv";
			}
		};
		abstract String dataFile();
	}

	/**
	 * Loads users
	 */
	private class UserLoader {
		BufferedReader empdtil;

		public UserLoader(BufferedReader empdtil) {
			this.empdtil = empdtil;
		}
		
		void load() {
			try {
				userRepo.deleteAll();
				userRepo.flush(); //TODO: do we need flush here?
				String detail;
				while ((detail = empdtil.readLine()) != null) {
					UserEntity user = new UserEntity();
					user.setNickname(detail);
					userRepo.save(user);
				}
				empdtil.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Loads events and sets owners randomly
	 */
	private class EventLoader {
		BufferedReader empdtil;
		Long userCount = userRepo.count(); // for random Users;

		public EventLoader(BufferedReader empdtil) {
			this.empdtil = empdtil;
		}
		
		void load() {
			try {
				eventRepo.deleteAll();
				eventRepo.flush();//TODO: do we need flush here?
				String detail;
				UserEntity randomOwner = userRepo.findAll().get(0);
				while ((detail = empdtil.readLine()) != null) {
					String[] eventAttributes = detail.split(",");
					EventEntity event = new EventEntity();
					event.setDate(LocalDate.parse(eventAttributes[0].replaceAll("/", "-"), DateTimeFormatter.ISO_DATE));//TODO: h:mm, not only hh:mm
					event.setTime(LocalTime.parse(eventAttributes[1], DateTimeFormatter.ISO_LOCAL_TIME));
					event.setNameOfEvent(eventAttributes[2]);
					randomOwner.makeOwner(event);// TODO: random user;
					//eventRepo.save(event); //TODO: cascading pls;
				}
				empdtil.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
