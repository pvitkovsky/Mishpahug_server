package application.controllers;

import application.entities.FeedBackValue;
import application.models.feedback.FeedBackModel;
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

    @DeleteMapping(value = "/")
    public void delete(@RequestHeader HttpHeaders httpHeaders,
                       HttpServletRequest request,
                       @RequestParam(name = "userid") Integer userId,
                       @RequestParam(name = "eventid") Integer eventId){
        if (userId != null) feedBackModel.removeAllByUser(userId);
        if (eventId != null) feedBackModel.removeAllByEvent(eventId);
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
