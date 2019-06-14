package application.configurations.dbloader;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import application.configurations.dbloader.loaders.ILoader;

/**
 * Load the production DB with integration test data; TODO: make it a Spring
 * profile
 */
@Component
@Transactional
public class DB_test_loader_fixed implements CommandLineRunner {

	@Autowired
	@Qualifier("cityLoader")
	private ILoader cityLoader;

	@Autowired
	@Qualifier("addressLoader")
	private ILoader addressLoader;

	@Autowired
	@Qualifier("eventLoader")
	private ILoader eventLoader;

	@Autowired
	@Qualifier("genderLoader")
	private ILoader genderLoader;

	@Autowired
	@Qualifier("guestsLoader")
	private ILoader guestsLoader;

	@Autowired
	@Qualifier("holidaysLoader")
	private ILoader holidaysLoader;

	@Autowired
	@Qualifier("kitchenTypeLoader")
	private ILoader kitchenTypeLoader;

	@Autowired
	@Qualifier("logsLoader")
	private ILoader logsLoader;

	@Autowired
	@Qualifier("maritalStatusLoader")
	private ILoader maritalStatusLoader;

	@Autowired
	@Qualifier("religionLoader")
	private ILoader religionLoader;

	@Autowired
	@Qualifier("templateLoader")
	private ILoader templateLoader;

	@Autowired
	@Qualifier("userLoader")
	private ILoader userLoader;

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

	}

	public void loadTest(MPHEntity entity) {

		switch (entity) {
		case USER: {
			userLoader.load();
			break;
		}
		
		case EVENT: {
			eventLoader.load();
			break;
		}
		
		case CITY: {
			cityLoader.load();
			break;
		}
		
		case KICHENTYPES: {
			kitchenTypeLoader.load();
			break;
		}
		
		case ADDRESS: {
			addressLoader.load();
			break;
		}
		
		case HOLIDAYS: {
			holidaysLoader.load();
			break;
		}
		
		case GENDER: {
			genderLoader.load();
			break;
		}
		
		case MARRIAGE: {
			maritalStatusLoader.load();
			break;
		}
		
		case RELIGION: {
			religionLoader.load();
			break;
		}
		
		case GUESTS: {
			guestsLoader.load();
			break;
		}
		
		case LOGS: {
			logsLoader.load();
			break;
		}
		}

	}

	/*
	 * List<TemplateEntity> templateEntities = templateRepository.findAll(); for
	 * (TemplateEntity x:templateEntities ) { JPGDocumentFormat jpgDocumentFormat =
	 * new JPGDocumentFormat();
	 * jpgDocumentFormat.createInvitationFromTemplate("d://211.jpeg", "d://" +
	 * x.getName() + ".jpg", "fontname", 8, x, 1 , 1);
	 * EMailSender.sender("mishpahug2019@gmail.com", "marina1986ANGEL", "test",
	 * "dfgsdfgsdfg", "mrcolombo1985remote@gmail.com", "d://" + x.getName() +
	 * ".jpg"); }
	 */


}
