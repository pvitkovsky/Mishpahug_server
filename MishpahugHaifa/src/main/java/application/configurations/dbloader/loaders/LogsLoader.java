package application.configurations.dbloader.loaders;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.EventEntity;
import application.entities.UserEntity;
import application.entities.log.LogsOnEvent;
import application.entities.log.LogsOnEvent.ActionsOnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Slf4j
public class LogsLoader implements ILoader {

	@Autowired
	LoaderDependencies data;
	
	private BufferedReader br;

	public LogsLoader(BufferedReader br) {
		this.br = br;
	}
	
	@Override
	public void load() {
		this.data.logsDataRepository.deleteAll();
		Random gen = new Random();
		List<UserEntity> userEntityList = this.data.userRepository.findAll();
		Integer randomUserRange = userEntityList.size() - 1;
		List<EventEntity> eventEntityList = this.data.eventRepository.findAll();
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
			log.debug("DBLoadTest -> LOGS -> " + logUE);
			this. data.logsDataRepository.save(logUE);
		}
	}

}
