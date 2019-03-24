package application.configurations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import application.entities.*;
import application.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Load the production DB with integration test data; TODO: make it a Spring profile
 */
@Component
@Transactional
public class DB_test_loader implements CommandLineRunner {



	@Autowired
	UserRepository userRepository;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	EventGuestRepository eventGuestRepository;
	@Autowired
	CityRepository cityRepository;
	@Autowired
	CountryRepository countryRepository;
	@Autowired
	MarriageStatusRepository marriageStatusRepository;
	@Autowired
	GenderRepository genderRepository;

	@Override
	public void run(String... args) throws Exception {
		loadTest(MPHEntity.EVENT);
		loadTest(MPHEntity.GUESTS);
		loadTest(MPHEntity.CITY);
		loadTest(MPHEntity.MARRIAGE);
		loadTest(MPHEntity.GENDER);
		loadTest(MPHEntity.USER);
		cityRepository.findAll().forEach(System.out::println);
	}

	private void loadTest(MPHEntity entity) {

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(entity.dataFile());
		BufferedReader empdtil = new BufferedReader(new InputStreamReader(is));
		long userCount = userRepository.count();
		long eventCount = eventRepository.count();

		switch (entity) {
		case USER: {
			UserLoader loader = new UserLoader(empdtil);
			loader.load();
		}
		case EVENT: {
			EventLoader loader = new EventLoader(empdtil);
			loader.load();
		}
		case CITY: {
			CityLoader loader = new CityLoader(empdtil);
			loader.load();
		}
		case GENDER: {
			GenderLoader loader = new GenderLoader(empdtil);
			loader.load();
		}
		case MARRIAGE:{
			MarriageStatusLoader loader = new MarriageStatusLoader(empdtil);
			loader.load();
		}
		case GUESTS: { 
			eventGuestRepository.deleteAll();
			Integer randomUserRange = userRepository.findAll().size()-1;
			Random randomUser = new Random();
			UserEntity randomGuest = userRepository.findAll().get(randomUser.nextInt(randomUserRange));
			for (EventEntity event : eventRepository.findAll()) {
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
		CITY {
			public String dataFile() {
				return "cities.csv";
			}
		},
		EVENT {
			public String dataFile() {
				return "data_event.csv";
			}
		},
		MARRIAGE {
			public String dataFile() {
				return "marriage_status.csv";
			}
		},
		GENDER {
			public String dataFile() {
				return "gender.csv";
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
				List<MarriageStatusEntity> marriageStatusEntityList = marriageStatusRepository.findAll();
				List<GenderEntity> genderEntityList = genderRepository.findAll();
				userRepository.deleteAll();
				userRepository.flush(); //TODO: do we need flush here?
				String detail;
				while ((detail = empdtil.readLine()) != null) {
					UserEntity user = new UserEntity();
					String[] data = detail.split("!");
					user.setEMail(data[0]);
					user.setFirstName(data[1]);
					user.setLastName(data[2]);
					user.setDateOfBirth(LocalDate.parse(data[3]));
					user.setPhoneNumber(data[4]);
					user.setUserName(data[0].split("@")[0]);
					user.setEnabled(true);
					Random rr = new Random();
					user.setGenderEntity(genderEntityList.get(rr.nextInt(genderEntityList.size()-1)));
					user.setMarriageStatusEntity(marriageStatusEntityList.get(rr.nextInt(marriageStatusEntityList.size()-1)));
					userRepository.save(user);
				}
				empdtil.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private class GenderLoader {
		BufferedReader empdtil;

		public GenderLoader(BufferedReader empdtil) {
			this.empdtil = empdtil;
		}

		void load() {
			try {
				genderRepository.deleteAll();
				genderRepository.flush(); //TODO: do we need flush here?
				String detail;
				while ((detail = empdtil.readLine()) != null) {
					GenderEntity genderEntity = new GenderEntity();
					genderEntity.setName(detail);
					genderRepository.save(genderEntity);
				}
				empdtil.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private class MarriageStatusLoader {
		BufferedReader empdtil;

		public MarriageStatusLoader(BufferedReader empdtil) {
			this.empdtil = empdtil;
		}

		void load() {
			try {
				marriageStatusRepository.deleteAll();
				marriageStatusRepository.flush(); //TODO: do we need flush here?
				String detail;
				while ((detail = empdtil.readLine()) != null) {
					MarriageStatusEntity marriageStatusEntity = new MarriageStatusEntity();
					marriageStatusEntity.setName(detail);
					marriageStatusRepository.save(marriageStatusEntity);
				}
				empdtil.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private class CityLoader {
		BufferedReader empdtil;

		public CityLoader(BufferedReader empdtil) {
			this.empdtil = empdtil;
		}

		void load() {
			try {
				countryRepository.deleteAll(); // cascade deleting cities and addresses
				countryRepository.flush();
				String detail;
				CountryEntity countryEntity = new CountryEntity();
				countryEntity.setName("Israel");
				countryRepository.save(countryEntity);
				while ((detail = empdtil.readLine()) != null) {
					CityEntity cityEntity = new CityEntity();
					cityEntity.setName(detail);
					countryEntity.addCity(cityEntity);
					//no need to save city explicitly, as its save is cascaded from Country; 
				}
				empdtil.close();//.
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
		Long userCount = userRepository.count(); // for random Users;

		public EventLoader(BufferedReader empdtil) {
			this.empdtil = empdtil;
		}
		
		void load() {
			try {
				eventRepository.deleteAll();
				eventRepository.flush();//TODO: do we need flush here?
				String detail;
				UserEntity randomOwner = userRepository.findAll().get(0);
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
