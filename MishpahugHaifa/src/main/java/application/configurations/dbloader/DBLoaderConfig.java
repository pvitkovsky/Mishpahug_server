package application.configurations.dbloader;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import application.configurations.dbloader.loaders.ChoicesLoader;
import application.configurations.dbloader.loaders.EventLoaderFixed;
import application.configurations.dbloader.loaders.GuestsLoader;
import application.configurations.dbloader.loaders.ILoader;
import application.configurations.dbloader.loaders.UserLoader;

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
		return new EventLoaderFixed(bufferedReader);	//TODO recover EventLoaderRandom
	}
	
	@Bean(name = "eventLoader")
	public ILoader eventLoaderTest() {
		createBufferedReader(MPHEntity.EVENT);
		return new EventLoaderFixed(bufferedReader);	
	}
	
	@Bean(name = "guestsLoader")
	public ILoader guestsLoader() {
		createBufferedReader(MPHEntity.GUESTS);
		return new GuestsLoader(bufferedReader);	
	}
	
	@Bean(name = "userLoader")
	public ILoader userLoader() {
		createBufferedReader(MPHEntity.USER);
		return new UserLoader(bufferedReader);	
	}
	
	@Bean(name = "choicesLoader")
	public ILoader choicesLoaderTest() {
		createBufferedReader(MPHEntity.CHOICES);
		return new ChoicesLoader(bufferedReader);	
	}

}