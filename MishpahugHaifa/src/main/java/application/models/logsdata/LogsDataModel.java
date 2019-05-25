package application.models.logsdata;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import application.entities.LogsDataEntity;
import application.repositories.LogsDataRepository;

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
    public Iterable<LogsDataEntity> getAll(Predicate predicate) {
        return logsDataRepository.findAll(predicate);
    }


    @Override
    public void delete(Predicate predicate) {
        logsDataRepository.deleteAll(getAll(predicate));
    }

}
