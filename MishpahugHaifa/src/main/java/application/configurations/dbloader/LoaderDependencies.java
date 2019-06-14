package application.configurations.dbloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import application.repositories.AddressRepository;
import application.repositories.CityRepository;
import application.repositories.CountryRepository;
import application.repositories.EventRepository;
import application.repositories.GenderRepository;
import application.repositories.HolyDayRepository;
import application.repositories.KichenTypeRepository;
import application.repositories.LogsDataRepository;
import application.repositories.MaritalStatusRepository;
import application.repositories.ReligionRepository;
import application.repositories.SubscriptionRepository;
import application.repositories.UserRepository;
import application.repositories.template.TemplateRepository;

@Service
public class LoaderDependencies { //TODO: stability risk with public access modifier on all repositories;
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