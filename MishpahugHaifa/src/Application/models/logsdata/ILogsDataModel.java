package Application.models.logsdata;

import Application.entities.LogsDataItem;

public interface ILogsDataModel {
    public LogsDataItem add(LogsDataItem data);
    public void clear();
}
