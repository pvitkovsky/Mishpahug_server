package application.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

import application.models.user.values.FeedBackValue;

/*
 * Convention for the AOP to work:
 * HttpHeaders is the first argument, HttpServletRequest is the second
 */	
public interface ISubscriptionController {

	public void subscribe(HttpHeaders httpHeaders, HttpServletRequest request, Integer eventId, Integer userId);

	public void unsubscribe(HttpHeaders httpHeaders, HttpServletRequest request, Integer eventId,
			Integer userId);

	public Map<Integer, FeedBackValue> getFeedback(HttpHeaders httpHeaders, HttpServletRequest request, Integer userId,
			Integer eventId);

}