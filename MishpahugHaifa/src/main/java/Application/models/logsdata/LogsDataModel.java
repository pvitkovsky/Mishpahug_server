package Application.models.logsdata;

import org.springframework.stereotype.Service;

import Application.entities.LogsDataEntity;

@Service
public class LogsDataModel implements ILogsDataModel {
    @Override
    public LogsDataEntity add(LogsDataEntity data) {
        return null;
    }

    @Override
    public void clear() {

    }
}
