package application.models.logsdata;

import javax.transaction.Transactional;

import application.repositories.LogsDataRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.LogsDataEntity;

import java.util.HashMap;
import java.util.List;

//TODO ALL
@Service
@Transactional
public class LogsDataModel implements ILogsDataModel {

    @Autowired
    LogsDataRepository logsDataRepository;

    @Override
    public LogsDataEntity add(LogsDataEntity data) {
        return logsDataRepository.save(data);
    }

    @Override
    public void clear() {
        logsDataRepository.deleteAll();
    }

    @Override
    public List<LogsDataEntity> getAll() {
        return logsDataRepository.findAll();
    }

    @Override
    public List<LogsDataEntity> getByFilter(HashMap<String, String> data) {
        return null;
    }

    @Override
    public Iterable<LogsDataEntity> getAll(Predicate predicate){
        return logsDataRepository.findAll(predicate);
    }


}
