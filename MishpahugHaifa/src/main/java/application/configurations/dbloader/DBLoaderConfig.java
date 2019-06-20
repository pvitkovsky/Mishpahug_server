package application.configurations.dbloader;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import application.configurations.dbloader.loaders.*;

@Configuration
public class DBLoaderConfig {
	
	@Value("${database-test-folder}") //TODO: test context from Eclipse doesn't load this;
	String testFolder = "short-datafiles";
	ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	InputStream inputStream;
	InputStreamReader inputStreamReader;
	BufferedReader bufferedReader; 
	
	private void createBufferedReader(MPHEntity entity) {
		inputStream = classloader.getResourceAsStream(testFolder + "/" + entity.dataFile());
		if (inputStream != null) {
			inputStreamReader= new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
		}
		if (bufferedReader == null) throw new IllegalArgumentException();
	}
	
	@Bean(name = "addressLoader")
	public ILoader cityLoader() {
		createBufferedReader(MPHEntity.ADDRESS);
		return new AddressLoader(bufferedReader);	
	}
	
	@Bean(name = "cityLoader")
	public ILoader addressLoader() {
		createBufferedReader(MPHEntity.CITY);
		return new CityLoader(bufferedReader);	
	}
	
	@Profile("dev-frontend")
	@Bean(name = "eventLoader")
	public ILoader eventLoaderFixed() {
		createBufferedReader(MPHEntity.EVENT);
		return new EventLoaderFixed(bufferedReader);	
	}

	@Profile("dev-backend")
	@Bean(name = "eventLoader")
	public ILoader eventLoaderRandom() {
		createBufferedReader(MPHEntity.EVENT);
		return new EventLoaderRandom (bufferedReader);	
	}
	
	@Bean(name = "eventLoader")
	public ILoader eventLoaderTest() {
		createBufferedReader(MPHEntity.EVENT);
		return new EventLoaderFixed(bufferedReader);	
	}
	
	@Bean(name = "genderLoader")
	public ILoader genderLoader() {
		createBufferedReader(MPHEntity.GENDER);
		return new GenderLoader(bufferedReader);	
	}

	@Bean(name = "guestsLoader")
	public ILoader guestsLoader() {
		createBufferedReader(MPHEntity.GUESTS);
		return new GuestsLoader(bufferedReader);	
	}
	
	@Bean(name = "holidaysLoader")
	public ILoader holidaysLoader() {
		createBufferedReader(MPHEntity.HOLIDAYS);
		return new HoliDaysLoader(bufferedReader);	
	}
	
	@Bean(name = "kitchenTypeLoader")
	public ILoader kitchenTypeLoader() {
		createBufferedReader(MPHEntity.KICHENTYPES);
		return new KichenTypeLoader(bufferedReader);	
	}
	
	@Bean(name = "logsLoader")
	public ILoader logsLoader() {
		createBufferedReader(MPHEntity.LOGS);
		return new LogsLoader(bufferedReader);	
	}
	
	@Bean(name = "maritalStatusLoader")
	public ILoader maritalStatusLoader() {
		createBufferedReader(MPHEntity.MARRIAGE);
		return new MaritalStatusLoader(bufferedReader);	
	}
	
	@Bean(name = "religionLoader")
	public ILoader religionLoader() {
		createBufferedReader(MPHEntity.RELIGION);
		return new ReligionLoader(bufferedReader);	
	}
	
	@Bean(name = "templateLoader")
	public ILoader templateLoader() {
		createBufferedReader(MPHEntity.TEMPLATE);
		return new TemplateLoader(bufferedReader);	
	}
	
	@Bean(name = "userLoader")
	public ILoader userLoader() {
		createBufferedReader(MPHEntity.USER);
		return new UserLoader(bufferedReader);	
	}

}