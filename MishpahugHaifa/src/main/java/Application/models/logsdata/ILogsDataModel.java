package Application.models.logsdata;

import Application.entities.LogsDataValue;

public interface ILogsDataModel {
    public LogsDataValue add(LogsDataValue data);
    public void clear();
}
