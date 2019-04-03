package application.controllers;

import application.entities.LogsDataEntity;
import application.models.logsdata.ILogsDataModel;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/logs")
public class LogsDataController {

	@Autowired 
	ILogsDataModel logsModel;
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	@ResponseBody
	public Iterable<LogsDataEntity> findAllByWebQuerydsl(
	  @QuerydslPredicate(root = LogsDataEntity.class) Predicate predicate) {
	    return logsModel.getAll(predicate);
	}
	

    @DeleteMapping(value = "/")
    public void delete( //TODO: test;
    	@QuerydslPredicate(root = LogsDataEntity.class) Predicate predicate) {
    	logsModel.delete(predicate);
    }
}
	


