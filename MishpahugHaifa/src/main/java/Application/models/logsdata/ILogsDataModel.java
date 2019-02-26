package Application.models.logsdata;

import Application.entities.LogsDataEntity;

public interface ILogsDataModel {
    public LogsDataEntity add(LogsDataEntity data);
    public void clear();
}
