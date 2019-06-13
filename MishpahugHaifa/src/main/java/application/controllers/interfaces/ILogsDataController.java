package application.controllers.interfaces;

import application.entities.log.LogsDataEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

public interface ILogsDataController {
	Iterable<LogsDataEntity> findAllByWebQuerydsl(HttpHeaders httpHeaders,	HttpServletRequest request, Predicate predicate);
	// TODO: test;
	void delete(HttpHeaders httpHeaders, HttpServletRequest request, Predicate predicate);
}
