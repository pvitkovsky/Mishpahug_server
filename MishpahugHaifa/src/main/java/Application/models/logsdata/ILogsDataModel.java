package application.models.logsdata;

import application.entities.LogsDataEntity;

public interface ILogsDataModel {
    public LogsDataEntity add(LogsDataEntity data);
    public void clear();
}
