package application.models.logsdata;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import application.entities.LogsDataEntity;
import application.repositories.LogsDataRepository;

@Service
@Transactional
public class LogsDataModel implements ILogsDataModel {
	
	
	@Autowired 
	LogsDataRepository logsRepo;
	
	@Override
	public Iterable<LogsDataEntity> getAll(Predicate predicate){
		return logsRepo.findAll(predicate);
	}

	@Override
	public void delete(Predicate predicate) {
		logsRepo.deleteAll(getAll(predicate));
	}


}
