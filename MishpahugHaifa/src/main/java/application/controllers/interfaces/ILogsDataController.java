package application.controllers.interfaces;

import application.entities.LogsDataEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

public interface ILogsDataController {

	Iterable<LogsDataEntity> findAllByWebQuerydsl(Predicate predicate, HttpHeaders httpHeaders,
			HttpServletRequest request);

	// TODO: test;
	void delete(Predicate predicate, HttpHeaders httpHeaders, HttpServletRequest request);
}
