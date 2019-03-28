package application.models.logsdata;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import application.entities.LogsDataEntity;

import java.util.HashMap;
import java.util.List;

//TODO ALL
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

    @Override
    public List<LogsDataEntity> getAll() {
        return null;
    }

    @Override
    public List<LogsDataEntity> getByFilter(HashMap<String, String> data) {
        return null;
    }
}
