package Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import Application.entities.EventEntity;
import Application.entities.EventGuestRelation;
import Application.entities.UserEntity;
import Application.repo.EventGuestRepository;
import Application.repo.EventRepository;
import Application.repo.UserRepository;

/**
 * Load the production DB with integration test data; TODO: make it a profile
 */
@Component
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
		System.out.println("Loaded successfully");
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
			for (EventEntity event : eventRepo.findAll()) {
				EventGuestRelation subscription = new EventGuestRelation();
				subscription.subscribe(userRepo.getOne(0), event);
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
				return null;
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
				String detail;
				while ((detail = empdtil.readLine()) != null) {
					String[] eventAttributes = detail.split(",");
					EventEntity event = new EventEntity();
					event.setDate(LocalDate.parse(eventAttributes[0].replaceAll("/", ""), DateTimeFormatter.ISO_DATE));
					event.setTime(LocalTime.parse(eventAttributes[1], DateTimeFormatter.ISO_LOCAL_TIME));
					event.setNameOfEvent(eventAttributes[2]);
					userRepo.getOne(0).makeOwner(event);// TODO: random user;
				}
				empdtil.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
