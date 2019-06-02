package application.controllers;

import application.controllers.interfaces.ILogsDataController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.types.Predicate;

import application.entities.LogsDataEntity;
import application.models.logsdata.ILogsDataModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/logs")
public class LogsDataController implements ILogsDataController {

    @Autowired
    ILogsDataModel logsModel;

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public Iterable<LogsDataEntity> findAllByWebQuerydsl(@QuerydslPredicate(root = LogsDataEntity.class) Predicate predicate
                                                        ,@RequestHeader HttpHeaders httpHeaders,
                                                         HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
            log.info("LogsDataController -> get -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("LogsDataController -> get -> Remote IP -> " + request.getRemoteAddr());
        return logsModel.getAll(predicate);
    }


    @Override
    @DeleteMapping(value = "/")
    //TODO: test;
    public void delete(@QuerydslPredicate(root = LogsDataEntity.class) Predicate predicate
                      ,@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request) {
        httpHeaders.forEach((key, value) -> {
            log.info("LogsDataController -> get -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("LogsDataController -> get -> Remote IP -> " + request.getRemoteAddr());
        logsModel.delete(predicate);
    }
}
	


