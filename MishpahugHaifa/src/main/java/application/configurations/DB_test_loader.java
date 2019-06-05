package application.configurations;

import application.documentstypes.JPGDocumentFormat;
import application.entities.*;
import application.entities.LogsOnEvent.ActionsOnEvent;
import application.entities.template.TemplateEntity;
import application.entities.template.XYTextValue;
import application.repositories.*;
import application.repositories.template.TemplateRepository;
import application.utils.EMailSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Load the production DB with integration test data; TODO: make it a Spring
 * profile
 */
@Slf4j
@Component
@Transactional

public class DB_test_loader implements CommandLineRunner {

	@Autowired
	Environment env;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	EventRepository eventRepository;

	@Autowired
	SubscriptionRepository eventGuestRepository;

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

	@Autowired
	HolyDayRepository holyDayRepository;

	@Autowired
	TemplateRepository templateRepository;

	

	@Override
	public void run(String... args) throws Exception {
		loadTest(MPHEntity.CITY);
		loadTest(MPHEntity.RELIGION);
		loadTest(MPHEntity.KICHENTYPES);
		loadTest(MPHEntity.MARRIAGE);
		loadTest(MPHEntity.ADDRESS);
		loadTest(MPHEntity.GENDER);
		loadTest(MPHEntity.HOLIDAYS);
		loadTest(MPHEntity.USER);
		loadTest(MPHEntity.EVENT);
		loadTest(MPHEntity.GUESTS);
		loadTest(MPHEntity.LOGS);
		TemplateLoader loader = new TemplateLoader();
		loader.load();
		/*List<TemplateEntity> templateEntities = templateRepository.findAll();
		for (TemplateEntity x:templateEntities
			 ) {
			JPGDocumentFormat jpgDocumentFormat = new JPGDocumentFormat();
			jpgDocumentFormat.createInvitationFromTemplate("d://211.jpeg", "d://" + x.getName() + ".jpg", "fontname", 8, x, 1 , 1);
			EMailSender.sender("mishpahug2019@gmail.com",
					"marina1986ANGEL",
					"test",
					"dfgsdfgsdfg",
					"mrcolombo1985remote@gmail.com",
					"d://" + x.getName() + ".jpg");
		}*/
	}


	private void loadTest(MPHEntity entity) {

		String testFolder = "short-datafiles";
		//String testFolder = env.getProperty("database-test-folder"); 
		//TODO: why Spring has stopped reading application.properties from UserWebRequestTest?
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(testFolder + "/" + entity.dataFile());
		InputStreamReader inputStreamReader = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(inputStreamReader);

		switch (entity) {
		case USER: {
			UserLoader loader = new UserLoader(br);
			loader.load();
			break;
		}
		case EVENT: {
			EventLoader loader = new EventLoader(br);
			loader.load();
			break;
		}
		case CITY: {
			CityLoader loader = new CityLoader(br);
			loader.load();
			break;
		}
		case KICHENTYPES: {
			KichenTypeLoader loader = new KichenTypeLoader(br);
			loader.load();
			break;
		}
		case ADDRESS: {
			AddressLoader loader = new AddressLoader(br);
			loader.load();
			break;
		}
		case HOLIDAYS: {
			HoliDaysLoader loader = new HoliDaysLoader(br);
			loader.load();
			break;
		}
		case GENDER: {
			GenderLoader loader = new GenderLoader(br);
			loader.load();
			break;
		}
		case MARRIAGE: {
			MaritalStatusLoader loader = new MaritalStatusLoader(br);
			loader.load();
			break;
		}
		case RELIGION: {
			ReligionLoader loader = new ReligionLoader(br);
			loader.load();
			break;
		}
		case GUESTS: {
			eventGuestRepository.deleteAll();
			Integer randomUserRange = userRepository.findAll().size() - 1;
			Random gen = new Random();
			UserEntity randomGuest = userRepository.findAll().get(gen.nextInt(randomUserRange));
			for (EventEntity event : eventRepository.findAll()) {
				if (!event.getUserEntityOwner().equals(randomGuest)) {
					SubscriptionEntity subscription = new SubscriptionEntity(randomGuest, event);
				}
			}
			break;
		}
		//TODO
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
				log.info("LOGS => " + logUE);
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
		HOLIDAYS {
			public String dataFile() {
				return "holidays.csv";
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
		BufferedReader br;

		public UserLoader(BufferedReader br) {
			this.br = br;
		}

		void load() {
			try {
				List<MaritalStatusEntity> maritalStatusEntityList = maritalStatusRepository.findAll();
				List<GenderEntity> genderEntityList = genderRepository.findAll();
				List<AddressEntity> addressEntityList = addressRepository.findAll();
				List<ReligionEntity> religionEntityList = religionRepository.findAll();
				List<KitchenTypeEntity> kitchenTypeEntityList = kichenTypeRepository.findAll();
				logsDataRepository.deleteAll();
				userRepository.findAll().forEach(UserEntity::putIntoDeletionQueue);
				userRepository.deleteAll();
				userRepository.flush();
				//do we need flush here?
				// need
				// https://stackoverflow.com/questions/49595852/deleteall-in-repository-randomly-causes-constraintviolationexception
				String detail;
				while ((detail = br.readLine()) != null) {
					String[] data = detail.split("!");
					UserEntity user = new UserEntity(data[0].split("@")[0], data[0]);
					user.setFirstName(data[1]);
					user.setLastName(data[2]);
					user.setDateOfBirth(LocalDate.parse(data[3]));
					Random ran = new Random();
					user.setPhoneNumber(data[4]+ ran.nextInt(9)+ ran.nextInt(9)+ ran.nextInt(9)+ ran.nextInt(9)+ ran.nextInt(9)+ ran.nextInt(9)+ ran.nextInt(9));
					user.setEncrytedPassword(DigestUtils.md5Hex(data[0].split("@")[0]));
					user.activate();
					user.setGender(genderEntityList.get(ran.nextInt(genderEntityList.size() - 1)));
					user.setReligion(religionEntityList.get(ran.nextInt(religionEntityList.size() - 1)));
					user.setAddressEntity(addressEntityList.get(ran.nextInt(addressEntityList.size() - 1)));
					user.setKitchenType(kitchenTypeEntityList.get(ran.nextInt(kitchenTypeEntityList.size() - 1)));
					user.setMaritalStatus(maritalStatusEntityList.get(ran.nextInt(maritalStatusEntityList.size() - 1)));
					log.info("UserLoader => " + user);
					userRepository.save(user);
				}
				log.info("UserLoader => In repository " + userRepository.findAll().size() + " records");
				br.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Loads ds
	 */
	private class HoliDaysLoader {
		BufferedReader br;

		public HoliDaysLoader(BufferedReader br) {
			this.br = br;
		}

		void load() {
			try {
				String detail;
				while ((detail = br.readLine()) != null) {
					String[] data = detail.split("/");
					String[] types = data[3].split("!");
					String[] dateSplit = data[2].split("-");
					HoliDayEntity holiDayEntity = new HoliDayEntity();
					holiDayEntity.setName(data[0]);
					LocalDate localDate = LocalDate.of(Integer.parseInt(dateSplit[0]),Integer.parseInt(dateSplit[1]),Integer.parseInt(dateSplit[2]));
					holiDayEntity.setDate(localDate);
					holiDayEntity.setDescription(data[1]);
					log.info("HolidayLoader => " + holiDayEntity);
					holyDayRepository.save(holiDayEntity);
				}
				log.info("HolidaysLoader => In repository " + holyDayRepository.findAll().size() + " records");
				br.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Loads address
	 */
	private class AddressLoader {
		BufferedReader br;

		public AddressLoader(BufferedReader br) {
			this.br = br;
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
				while ((detail = br.readLine()) != null) {
					AddressEntity addressEntity = new AddressEntity();
					addressEntity.setStreet(detail);
					addressEntity.setBuilding(rr.nextInt(100));
					addressEntity.setApartment(rr.nextInt(50));
					cityEntityList.get(rr.nextInt(cityEntityListSize)).addAddress(addressEntity);

				}
				log.info("AddressLoader => In repository " + addressRepository.findAll().size() + " records");
				br.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Loads Template
	 */
	private class TemplateLoader {
		BufferedReader br;

		public TemplateLoader() {
		}

		void load() {
			for (int i = 0; i < 10; i++) {
				TemplateEntity templateEntity = new TemplateEntity();
				templateEntity.setName("item" + i);
				templateEntity.setPicture("n/a");
				Random r = new Random();
				Set<XYTextValue> data = new HashSet<>();
				for (int j = 0; j < 128; j++) {
					XYTextValue xyTextValue = new XYTextValue(50 * (r.nextInt(31) + 1),50 * (r.nextInt(18) + 1),16, "user!firstname");
					data.add(xyTextValue);
				}
				templateEntity.setItems(data);
				templateRepository.save(templateEntity);
			}
		}
	}

	/**
	 * Loads city
	 */
	private class CityLoader {
		BufferedReader br;

		public CityLoader(BufferedReader br) {
			this.br = br;
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
				while ((detail = br.readLine()) != null) {
					CityEntity cityEntity = new CityEntity();
					cityEntity.setName(detail);
					countryEntity.addCity(cityEntity);
					// no need to save city explicitly, as its save is cascaded from Country;
				}
				log.info("CityLoader => In repository " + cityRepository.findAll().size() + " records");
				br.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	/**
	 * Loads kichentype
	 */
	private class KichenTypeLoader {
		BufferedReader br;

		public KichenTypeLoader(BufferedReader br) {
			this.br = br;
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
				while ((detail = br.readLine()) != null) {
					KitchenTypeEntity kichenTypeEntity = new KitchenTypeEntity();
					kichenTypeEntity.setName(detail);
					log.info("KichenTypeLoader => " + kichenTypeEntity);
					kichenTypeRepository.save(kichenTypeEntity);
				}
				log.info("KitchenTypeLoader => In repository " + kichenTypeRepository.findAll().size() + " records");
				br.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	/**
	 * Loads Religion
	 */
	private class ReligionLoader {
		BufferedReader br;

		public ReligionLoader(BufferedReader br) {
			this.br = br;
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
				while ((detail = br.readLine()) != null) {
					ReligionEntity religionEntity = new ReligionEntity();
					religionEntity.setName(detail);
					log.info("ReligionLoader => " + religionEntity);
					religionRepository.save(religionEntity);
				}
				log.info("ReligionLoader => In repository " + religionRepository.findAll().size() + " records");
				br.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	/**
	 * Loads gender
	 */
	private class GenderLoader {
		BufferedReader br;

		public GenderLoader(BufferedReader br) {
			this.br = br;
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
				while ((detail = br.readLine()) != null) {
					GenderEntity genderEntity = new GenderEntity();
					genderEntity.setName(detail);
					log.info("GenderLoader => " + genderEntity);
					genderRepository.save(genderEntity);
				}
				log.info("GenderLoader => In repository " + genderRepository.findAll().size() + " records");
				br.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	/**
	 * Loads maritalstatus
	 */
	private class MaritalStatusLoader {
		BufferedReader br;

		public MaritalStatusLoader(BufferedReader br) {
			this.br = br;
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
				while ((detail = br.readLine()) != null) {
					MaritalStatusEntity maritalStatusEntity = new MaritalStatusEntity();
					maritalStatusEntity.setName(detail);
					maritalStatusRepository.save(maritalStatusEntity);
				}
				log.info("MaritalStatusLoader => In repository " + maritalStatusRepository.findAll().size() + " records");
				br.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}


	/*private class HolidayLoader {
		BufferedReader br;

		public HolidayLoader(BufferedReader br) {
			this.br = br;
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
		BufferedReader br;
		public EventLoader(BufferedReader br) {
			this.br = br;
		}
		
		void load() {
			try {
				logsDataRepository.deleteAll();
				eventRepository.findAll().forEach(EventEntity::putIntoDeletionQueue);
				eventRepository.deleteAll();
				eventRepository.flush();
				//do we need flush here?
				//need
				List<AddressEntity> addressEntityList = addressRepository.findAll();
				Integer addressEntityListCount = addressEntityList.size() - 1;
				List<HoliDayEntity> holiDayEntityList = holyDayRepository.findAll();
				Integer holiDayEntityListCount = holiDayEntityList.size() - 1;
				String detail;
				List<UserEntity> userEntityList = userRepository.findAll();
				Integer userEntityListCount = userEntityList.size() - 1;
				Random r = new Random();
				while ((detail = br.readLine()) != null) {
					UserEntity randomOwner = userEntityList.get(r.nextInt(userEntityListCount));
					String[] eventAttributes = detail.split(",");
					EventEntity event = new EventEntity(randomOwner, LocalDate.parse(eventAttributes[0].replaceAll("/", "-"), DateTimeFormatter.ISO_DATE), LocalTime.parse(eventAttributes[1], DateTimeFormatter.ISO_LOCAL_TIME));
					event.setNameOfEvent(eventAttributes[2]);
					event.setHoliDay(holiDayEntityList.get(r.nextInt(holiDayEntityListCount)));
					event.setAddressEntity(addressEntityList.get(r.nextInt(addressEntityListCount)));
					log.info("EventLoader => " + event);
				}
				br.close();
				log.info("EventLoader => In repository " + eventRepository.findAll().size() + " records");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}


}
