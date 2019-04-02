package application.models.logsdata;

import com.querydsl.core.types.Predicate;

import application.entities.LogsDataEntity;

public interface ILogsDataModel {

	public Iterable<LogsDataEntity> getAll(Predicate predicate);
    
	public void delete(Predicate predicate);
	
}
