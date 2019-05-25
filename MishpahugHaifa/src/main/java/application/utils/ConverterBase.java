package application.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.repositories.GenderRepository;
import application.repositories.KichenTypeRepository;
import application.repositories.MaritalStatusRepository;
import application.repositories.ReligionRepository;

@Service
public class ConverterBase {
	
	@Autowired
	protected GenderRepository genderRepository;

	@Autowired
	protected KichenTypeRepository kichenTypeRepository;

	@Autowired
	protected ReligionRepository religionRepository;

	@Autowired
	protected MaritalStatusRepository maritalStatusRepository;
	
}
