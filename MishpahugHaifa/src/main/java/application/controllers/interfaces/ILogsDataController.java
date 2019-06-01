package application.controllers.interfaces;

import application.entities.LogsDataEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

public interface ILogsDataController {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    Iterable<LogsDataEntity> findAllByWebQuerydsl(@QuerydslPredicate(root = LogsDataEntity.class) Predicate predicate
            , @RequestHeader HttpHeaders httpHeaders,
                                                  HttpServletRequest request);

    @DeleteMapping(value = "/")
                                                         //TODO: test;
    void delete(@QuerydslPredicate(root = LogsDataEntity.class) Predicate predicate
            , @RequestHeader HttpHeaders httpHeaders,
                HttpServletRequest request);
}
