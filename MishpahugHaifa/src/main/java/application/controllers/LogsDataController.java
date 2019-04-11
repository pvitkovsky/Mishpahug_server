package application.controllers;

import application.controllers.intarfaces.ILogsDataController;
import application.entities.LogsDataEntity;
import application.models.logsdata.ILogsDataModel;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/logs")
public class LogsDataController implements ILogsDataController {

    @Autowired
    ILogsDataModel logsModel;

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public Iterable<LogsDataEntity> findAllByWebQuerydsl(
            @QuerydslPredicate(root = LogsDataEntity.class) Predicate predicate) {
        return logsModel.getAll(predicate);
    }


    @Override
    @DeleteMapping(value = "/")
    public void delete( //TODO: test;
                        @QuerydslPredicate(root = LogsDataEntity.class) Predicate predicate) {
        logsModel.delete(predicate);
    }
}
	


