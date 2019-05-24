package application.controllers.interfaces;

import application.entities.LogsDataEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public interface ILogsDataController {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    Iterable<LogsDataEntity> findAllByWebQuerydsl(
            @QuerydslPredicate(root = LogsDataEntity.class) Predicate predicate);

    @DeleteMapping(value = "/")
    void delete( //TODO: test;
                 @QuerydslPredicate(root = LogsDataEntity.class) Predicate predicate);
}
