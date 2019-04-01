package application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import application.entities.LogsDataEntity;
import application.repositories.LogsDataRepository;

@RestController
@RequestMapping(value = "/logs")
public class LogsDataController {

	@Autowired 
	LogsDataRepository logsRepo;
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	@ResponseBody
	public Iterable<LogsDataEntity> findAllByWebQuerydsl(
	  @QuerydslPredicate(root = LogsDataEntity.class) Predicate predicate) {
	    return logsRepo.findAll(predicate);
	}
	
}


