package application.configurations;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import javax.transaction.Transactional;
import application.entities.*;
import application.entities.LogsOnEvent.ActionsOnEvent;
import application.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Load the production DB with integration test data; TODO: make it a Spring
 * profile
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
	MaritalStatusRepository maritalStatusRepository;
	@Autowired
	GenderRepository genderRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	ReligionRepository religionRepository;
	@Autowired
	KichenTypeRepository kichenTypeRepository;
	@Autowired
	LogsDataRepository logsDataRepository;
	
	

	@Override
	public void run(String... args) throws Exception {
		loadTest(MPHEntity.CITY);
		loadTest(MPHEntity.RELIGION);
		loadTest(MPHEntity.KICHENTYPES);
		loadTest(MPHEntity.MARRIAGE);
		loadTest(MPHEntity.ADDRESS);
		loadTest(MPHEntity.GENDER);
		loadTest(MPHEntity.USER);
		loadTest(MPHEntity.EVENT);
		loadTest(MPHEntity.GUESTS);
		loadTest(MPHEntity.LOGS);
	}

	private void loadTest(MPHEntity entity) {

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(entity.dataFile());
		BufferedReader empdtil = new BufferedReader(new InputStreamReader(is));

		switch (entity) {
		case USER: {
			UserLoader loader = new UserLoader(empdtil);
			loader.load();
			break;
		}
		case EVENT: {
			EventLoader loader = new EventLoader(empdtil);
			loader.load();
			break;
		}
		case CITY: {
			CityLoader loader = new CityLoader(empdtil);
			loader.load();
			break;
		}
		case KICHENTYPES: {
			KichenTypeLoader loader = new KichenTypeLoader(empdtil);
			loader.load();
			break;
		}
		case ADDRESS: {
			AddressLoader loader = new AddressLoader(empdtil);
			loader.load();
			break;
		}
		case RELIGION: {
			ReligionLoader loader = new ReligionLoader(empdtil);
			loader.load();
			break;
		}
		case GENDER: {
			GenderLoader loader = new GenderLoader(empdtil);
			loader.load();
			break;
		}
		case MARRIAGE: {
			MaritalStatusLoader loader = new MaritalStatusLoader(empdtil);
			loader.load();
			break;
		}
		case GUESTS: {
			eventGuestRepository.deleteAll();
			Integer randomUserRange = userRepository.findAll().size() - 1;
			Random gen = new Random();
			UserEntity randomGuest = userRepository.findAll().get(gen.nextInt(randomUserRange));
			for (EventEntity event : eventRepository.findAll()) {
				EventGuestRelation subscription = new EventGuestRelation();
				if (!event.getUserEntityOwner().equals(randomGuest)) {
					subscription.subscribe(randomGuest, event);
				}
			}
			break;
		}
		case LOGS:{
			logsDataRepository.deleteAll();
			Random gen = new Random();
			List<UserEntity> userEntityList = userRepository.findAll();
			Integer randomUserRange = userEntityList.size() - 1;
			List<EventEntity> eventEntityList = eventRepository.findAll();
			Integer randomEventRange = eventEntityList.size() - 1;

			LocalTime TTIME = LocalTime.of(23, 59);
			for ( int i = 0; i<100; i++) {
				UserEntity randomUserActor = userEntityList.get(gen.nextInt(randomUserRange));
				//System.out.println("Random User = " + randomUserActor);

				EventEntity randomEventTarget= eventEntityList.get(gen.nextInt(randomEventRange));

				LogsOnEvent logUE = new LogsOnEvent();
				logUE.setDate(LocalDate.of(2019, 03, 1 + gen.nextInt(30)));
				logUE.setUserActor(randomUserActor);
				logUE.setEventTarget(randomEventTarget);
				logUE.setAction(ActionsOnEvent.EVENT_VIEW);
				logUE.setTime(TTIME);
				logsDataRepository.save(logUE);
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
		ADDRESS {
			public String dataFile() {
				return "streets.csv";
			}
		},
		KICHENTYPES {
			public String dataFile() {
				return "foods.csv";
			}
		},
		RELIGION {
			public String dataFile() {
				return "religions.csv";
			}
		},
		GUESTS {
			public String dataFile() {
				return "data_blank.csv";
			}
		},
		LOGS {
			public String dataFile() {
				return "data_blank.csv";
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
				List<MaritalStatusEntity> maritalStatusEntityList = maritalStatusRepository.findAll();
				List<GenderEntity> genderEntityList = genderRepository.findAll();
				List<AddressEntity> addressEntityList = addressRepository.findAll();
				List<ReligionEntity> religionEntityList = religionRepository.findAll();
				List<KitchenTypeEntity> kitchenTypeEntityList = kichenTypeRepository.findAll();
				logsDataRepository.deleteAll();
				userRepository.deleteAll();
				userRepository.flush();
				//do we need flush here?
				// need
				// https://stackoverflow.com/questions/49595852/deleteall-in-repository-randomly-causes-constraintviolationexception
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
					user.setGender(genderEntityList.get(rr.nextInt(genderEntityList.size() - 1)));
					user.setReligion(religionEntityList.get(rr.nextInt(religionEntityList.size() - 1)));
					user.setAddressEntity(addressEntityList.get(rr.nextInt(addressEntityList.size() - 1)));
					user.setKitchenType(kitchenTypeEntityList.get(rr.nextInt(kitchenTypeEntityList.size() - 1)));
					user.setMaritalStatus(maritalStatusEntityList.get(rr.nextInt(maritalStatusEntityList.size() - 1)));
					userRepository.save(user);
				}
				empdtil.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private class AddressLoader {
		BufferedReader empdtil;

		public AddressLoader(BufferedReader empdtil) {
			this.empdtil = empdtil;
		}

		void load() {
			try {
				List<CityEntity> cityEntityList = cityRepository.findAll();
				for (CityEntity city : cityEntityList) {
					city.clearAddresses();
				}
				Random rr = new Random();
				Integer cityEntityListSize = cityEntityList.size() - 1;
				String detail;
				while ((detail = empdtil.readLine()) != null) {
					AddressEntity addressEntity = new AddressEntity();
					addressEntity.setStreet(detail);
					addressEntity.setBuilding(rr.nextInt(100));
					addressEntity.setApartment(rr.nextInt(50));
					cityEntityList.get(rr.nextInt(cityEntityListSize)).addAddress(addressEntity);

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
				Collection<UserEntity> users = userRepository.findAll();
				for (UserEntity user : users) {
					user.setAddressEntity(null);
				}
				Collection<EventEntity> events = eventRepository.findAll();
				for (EventEntity event : events) {
					event.setAddressEntity(null);
				}
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
					// no need to save city explicitly, as its save is cascaded from Country;
				}
				empdtil.close();// .
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private class KichenTypeLoader {
		BufferedReader empdtil;

		public KichenTypeLoader(BufferedReader empdtil) {
			this.empdtil = empdtil;
		}

		void load() {
			try {
				Collection<UserEntity> users = userRepository.findAll();
				for (UserEntity user : users) {
					user.setKitchenType(null);
				}
				Collection<EventEntity> events = eventRepository.findAll();
				for (EventEntity event : events) {
					event.setKitchenType(null);
				}
				kichenTypeRepository.deleteAll();
				kichenTypeRepository.flush();
				// do we need flush here?
				// need
				String detail;
				while ((detail = empdtil.readLine()) != null) {
					KitchenTypeEntity kichenTypeEntity = new KitchenTypeEntity();
					kichenTypeEntity.setName(detail);
					kichenTypeRepository.save(kichenTypeEntity);
				}
				empdtil.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private class ReligionLoader {
		BufferedReader empdtil;

		public ReligionLoader(BufferedReader empdtil) {
			this.empdtil = empdtil;
		}

		void load() {
			try {
				Collection<UserEntity> users = userRepository.findAll();
				for (UserEntity user : users) {
					user.setReligion(null);
				}
				religionRepository.deleteAll();
				religionRepository.flush();
				// do we need flush here?
				// need
				String detail;
				while ((detail = empdtil.readLine()) != null) {
					ReligionEntity religionEntity = new ReligionEntity();
					religionEntity.setName(detail);
					religionRepository.save(religionEntity);
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
				Collection<UserEntity> users = userRepository.findAll();
				for (UserEntity user : users) {
					user.setGender(null);
				}
				genderRepository.deleteAll();
				genderRepository.flush();
				//do we need flush here?
				// need
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
	
	private class MaritalStatusLoader {
		BufferedReader empdtil;

		public MaritalStatusLoader(BufferedReader empdtil) {
			this.empdtil = empdtil;
		}

		void load() {
			Collection<UserEntity> users = userRepository.findAll();
			for (UserEntity user : users) {
				user.setMaritalStatus(null);
			}
			try {
				maritalStatusRepository.deleteAll();
				maritalStatusRepository.flush();
				//do we need flush here?
				// need
				String detail;
				while ((detail = empdtil.readLine()) != null) {
					MaritalStatusEntity maritalStatusEntity = new MaritalStatusEntity();
					maritalStatusEntity.setName(detail);
					maritalStatusRepository.save(maritalStatusEntity);
				}
				empdtil.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}


	/*private class HolidayLoader {
		BufferedReader empdtil;

		public HolidayLoader(BufferedReader empdtil) {
			this.empdtil = empdtil;
		}

		void load() {
			ObjectMapper mapper = new ObjectMapper();

			*//**
			 * Read object from file
			 *//*
			ArrHolidayDTO value = null;
			try {
				value = mapper.readValue(new File("holidays.json"), ArrHolidayDTO.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}*/


	/**
	 * Loads events and sets owners randomly
	 */
	private class EventLoader {
		BufferedReader empdtil;
		public EventLoader(BufferedReader empdtil) {
			this.empdtil = empdtil;
		}
		
		void load() {
			try {
				logsDataRepository.deleteAll();
				eventRepository.deleteAll();
				eventRepository.flush();
				//do we need flush here?
				//need
				String detail;
				List<UserEntity> userEntityList = userRepository.findAll();
				Integer userEntityListCount = userEntityList.size() - 1;
				Random r = new Random();
				while ((detail = empdtil.readLine()) != null) {
					UserEntity randomOwner = userEntityList.get(r.nextInt(userEntityListCount));
					String[] eventAttributes = detail.split(",");
					EventEntity event = new EventEntity();
					event.setDate(LocalDate.parse(eventAttributes[0].replaceAll("/", "-"), DateTimeFormatter.ISO_DATE));
					// TODO:  ???
					event.setTime(LocalTime.parse(eventAttributes[1], DateTimeFormatter.ISO_LOCAL_TIME));
					event.setNameOfEvent(eventAttributes[2]);
					randomOwner.makeOwner(event);
				}
				empdtil.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
