package application.controllers.interfaces;

import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.querydsl.core.types.Predicate;

import application.entities.LogsDataEntity;

public interface ILogsDataController {
    Iterable<LogsDataEntity> findAllByWebQuerydsl(
            @QuerydslPredicate(root = LogsDataEntity.class) Predicate predicate);

    void delete( //TODO: test;
                 @QuerydslPredicate(root = LogsDataEntity.class) Predicate predicate);
}
