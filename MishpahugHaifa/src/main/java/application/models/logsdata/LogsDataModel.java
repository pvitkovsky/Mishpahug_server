package application.models.logsdata;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import application.entities.LogsDataEntity;

@Service
@Transactional
public class LogsDataModel implements ILogsDataModel {
    @Override
    public LogsDataEntity add(LogsDataEntity data) {
        return null;
    }

    @Override
    public void clear() {

    }
}
