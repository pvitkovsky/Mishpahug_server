package application.models.logsdata;

import java.util.List;

import com.querydsl.core.types.Predicate;

import application.entities.LogsDataEntity;

public interface ILogsDataModel {
    public LogsDataEntity add(LogsDataEntity data);

    public void clear();

    public List<LogsDataEntity> getAll();

    public Iterable<LogsDataEntity> getAll(Predicate predicate);

    public void delete(Predicate predicate);

}
