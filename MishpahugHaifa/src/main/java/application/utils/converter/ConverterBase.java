package application.utils.converter;

import application.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Autowired
	HolyDayRepository holyDayRepository;
	
}
