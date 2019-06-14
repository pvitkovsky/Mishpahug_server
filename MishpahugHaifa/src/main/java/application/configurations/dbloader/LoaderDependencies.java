package application.configurations.dbloader;

import application.repositories.*;
import application.repositories.template.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class LoaderDependencies {
	@Autowired
	public Environment env;
	
	@Autowired
	public UserRepository userRepository;
	@Autowired
	public EventRepository eventRepository;
	@Autowired
	public SubscriptionRepository eventGuestRepository;
	@Autowired
	public CityRepository cityRepository;
	@Autowired
	public  CountryRepository countryRepository;
	@Autowired
	public  MaritalStatusRepository maritalStatusRepository;
	@Autowired
	public  GenderRepository genderRepository;
	@Autowired
	public  AddressRepository addressRepository;
	@Autowired
	public  ReligionRepository religionRepository;
	@Autowired
	public  KichenTypeRepository kichenTypeRepository;
	@Autowired
	public  LogsDataRepository logsDataRepository;
	@Autowired
	public  HolyDayRepository holyDayRepository;
	@Autowired
	public  TemplateRepository templateRepository;

	public LoaderDependencies() {
	}
}