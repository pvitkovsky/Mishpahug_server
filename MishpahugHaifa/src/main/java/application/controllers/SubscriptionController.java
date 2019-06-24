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

import application.models.event.EventEntity;
import application.models.event.IEventModel;
import application.models.relation.IRelationModel;
import application.models.user.IUserModel;
import application.models.user.UserEntity;
import application.models.user.values.FeedBackValue;

@RestController
@RequestMapping(value = "/subscription")
/**
 * Convention on handling subscriptions around the app: 
 *   All signatures where there is (Event, Guest) should have Event first. 
 */
public class SubscriptionController implements ISubscriptionController {

	@Autowired
	IRelationModel relationModel;
	
	@Autowired
	IUserModel userModel;
	
	@Autowired
	IEventModel eventModel;

	@Override
	@PutMapping(value = "/") //TODO: this is post bc no URL;
	public void subscribe(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			 @RequestParam(name = "eventid") Integer eventId, @RequestParam(name = "userid") Integer guestId) {
		EventEntity event = eventModel.getById(eventId);
		UserEntity guest = userModel.getById(guestId);
		relationModel.subscribe(guest, event);
	}

	@Override
	@DeleteMapping(value = "/")
	public void deactivateSubscription(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request,
			 @RequestParam(name = "eventid") Integer eventId, @RequestParam(name = "userid") Integer guestId) {
		relationModel.deactivateSubscription(eventId, guestId);
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
