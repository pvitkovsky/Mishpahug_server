package application.controllers;

import application.entities.values.FeedBackValue;
import application.models.feedback.FeedBackModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
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
        httpHeaders.forEach((key, value) -> {
            log.info("SubscriptionController -> delete -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("SubscriptionController -> delete -> Remote IP -> " + request.getRemoteAddr());
        if (userId != null) feedBackModel.removeAllByUser(userId);
        if (eventId != null) feedBackModel.removeAllByEvent(eventId);
    }

    @GetMapping(value = "/")
    public Map<Integer, FeedBackValue> get(@RequestHeader HttpHeaders httpHeaders,
                                           HttpServletRequest request,
                                           @RequestParam(name = "userid") Integer userId,
                                           @RequestParam(name = "eventid") Integer eventId){
        httpHeaders.forEach((key, value) -> {
            log.info("SubscriptionController -> get -> headers -> " + String.format("Header '%s' = %s", key, value));
        });
        log.info("SubscriptionController -> get -> Remote IP -> " + request.getRemoteAddr());
        if (userId != null) return feedBackModel.getAllByUser(userId);
        if (eventId != null) return feedBackModel.getAllByEvent(eventId);
        return null;
    }
}
