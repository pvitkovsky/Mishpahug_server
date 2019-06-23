package application.controllers;

import application.entities.security.UserSession;
import application.entities.values.FeedBackValue;
import application.models.event.IEventModel;
import application.models.feedback.FeedBackModel;
import application.models.user.IUserModel;
import application.repositories.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/subscription")
public class SubscriptionController {
    @Autowired
    FeedBackModel feedBackModel;

    @Autowired
    IEventModel eventModel;

    @Autowired
    IUserModel userModel;

    @Autowired
    UserSessionRepository userSessionRepository;

    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request,
                       @RequestParam(name = "userid") Integer userId,
                       @RequestParam(name = "eventid") Integer eventId){
        if (userId != null) feedBackModel.removeAllByUser(userId);
        if (eventId != null) feedBackModel.removeAllByEvent(eventId);
    }

    @DeleteMapping(value = "/{eventid}")
    public void unsubscription(@RequestHeader HttpHeaders httpHeaders,
                               HttpServletRequest request,
                               @PathVariable(name = "eventid") Integer eventId){
        String token = request.getHeader("Authorization");
        UserSession session = userSessionRepository.findByTokenAndIsValidTrue(token);
        eventModel.unsubscribe(eventId, userModel.getByUserName(session.getUserName()).getId());
    }

    @PutMapping(value = "/{eventid}")
    public void subscription(@RequestHeader HttpHeaders httpHeaders,
                             HttpServletRequest request,
                             @PathVariable(name = "eventid") Integer eventId){
        String token = request.getHeader("Authorization");
        UserSession session = userSessionRepository.findByTokenAndIsValidTrue(token);
        eventModel.subscribe(eventId, userModel.getByUserName(session.getUserName()).getId());
    }

    @GetMapping(value = "/")
    public Map<Integer, FeedBackValue> get(@RequestHeader HttpHeaders httpHeaders,
                                           HttpServletRequest request,
                                           @RequestParam(name = "userid", required = false) Integer userId,
                                           @RequestParam(name = "eventid", required = false) Integer eventId){
        if (userId != null) return feedBackModel.getAllByUser(userId);
        if (eventId != null) return feedBackModel.getAllByEvent(eventId);
        return null;
    }
}
