package application.models.logsdata;

import application.entities.LogsDataEntity;

import java.util.HashMap;
import java.util.List;

public interface ILogsDataModel {
    public LogsDataEntity add(LogsDataEntity data);
    public void clear();
    public List<LogsDataEntity> getAll();
    public List<LogsDataEntity> getByFilter(HashMap<String, String> data);
}
