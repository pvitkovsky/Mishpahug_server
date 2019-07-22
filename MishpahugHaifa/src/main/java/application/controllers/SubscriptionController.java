package application.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.models.relation.IRelationModel;
import application.models.user.values.FeedBackValue;

@RestController
@RequestMapping(value = "/subscription")
public class SubscriptionController implements ISubscriptionController {

	@Autowired
	IRelationModel relationModel;

	@Override
	@PutMapping(value = "/") //TODO: this should be POST, and what URL is this?
	public void subscribe(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@RequestParam(name = "userid") Integer userId, @RequestParam(name = "eventid") Integer eventId) {
		relationModel.subscribe(eventId, userId);
	}

	@Override
	@DeleteMapping(value = "/")
	public void unsubscribe(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@RequestParam(name = "userid") Integer userId, @RequestParam(name = "eventid") Integer eventId) {
		relationModel.unsubscribe(eventId, userId);
	}

	@GetMapping(value = "/")
	public Map<Integer, FeedBackValue> getFeedback(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			@RequestParam(name = "userid", required = false) Integer userId,
			@RequestParam(name = "eventid", required = false) Integer eventId) {
		if (userId != null)
			return relationModel.getAllByUser(userId);
		if (eventId != null)
			return relationModel.getAllByEvent(eventId);
		return null;
	}

}
